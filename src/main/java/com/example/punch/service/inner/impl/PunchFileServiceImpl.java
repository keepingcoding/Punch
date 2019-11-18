package com.example.punch.service.inner.impl;

import com.example.punch.contract.Tuple;
import com.example.punch.model.PunchRecord;
import com.example.punch.contract.bo.PunchRecordBO;
import com.example.punch.service.inner.PunchFileService;
import com.example.punch.util.DateUtils;
import com.example.punch.util.FileUtils;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.google.common.io.FileWriteMode;
import com.google.common.io.Files;
import com.google.common.io.LineProcessor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Properties;

/**
 * @author zzs
 * @date 2019/11/15 22:34
 */
@Slf4j
@Service
public class PunchFileServiceImpl implements PunchFileService {

    @Autowired
    private Environment env;

    /** 写到文件 **/
    @Override
    public void writeToFile(PunchRecordBO punchRecordBO) throws IOException {
        String fileName = DateUtils.formatDate(new Date(), "yyyy-MM");
        File file = getFile(fileName);
        log.info(">>> Get file [{}].", file.getCanonicalPath());
        Files.createParentDirs(file);

        String fileSeparator = env.getProperty("punch.file.separator");
        StringBuilder sb = new StringBuilder();
        sb.append(punchRecordBO.getTime());
        sb.append(fileSeparator);
        sb.append(punchRecordBO.getLocation() == null ? "" : punchRecordBO.getLocation());
        sb.append(fileSeparator);
        sb.append(punchRecordBO.getRemark() == null ? "" : punchRecordBO.getRemark());
        String res = sb.toString();
        String writeData = res + System.lineSeparator();
        log.info(">>> The data currently being written is [{}].", res);

        //读取文件最后一行，判断是否需要更新
        Tuple<String> tuple = FileUtils.readLastLine(file);
        if (tuple != null) {
            String lastLine = tuple.getResult();
            long lastIndex = tuple.getIndex();
            List<String> list = Splitter.on(fileSeparator).trimResults().splitToList(lastLine);
            //todo 目前逻辑是一天只能打一次卡
            Date time = punchRecordBO.getTime();
            String oldStr = DateUtils.formatDate(time,"yyyy-MM-dd");
            if (list.get(0).contains(oldStr)) {
                //更新
                log.info(">>> The write mode is [update].");
                FileUtils.writeFromIndex(file, lastIndex, writeData);
            } else {
                //追加
                log.info(">>> The write mode is [add].");
                Files.asCharSink(file, Charset.forName("UTF-8"), FileWriteMode.APPEND).write(writeData);
            }
        } else {
            //追加
            log.info(">>> The write mode is [add].");
            Files.asCharSink(file, Charset.forName("UTF-8"), FileWriteMode.APPEND).write(writeData);
        }

        log.info(">>> Write completion.");
    }

    /** 从文件读取 **/
    @Override
    public List<PunchRecord> readFromFile(String fileName) throws Exception {
        log.info(">>> Start to read from file.");
        File file = getFile(fileName);
        if (!file.exists()) {
            log.error("文件[{}]不存在！", fileName);
            return Lists.newArrayList();
        }
        String fileSeparator = env.getProperty("punch.file.separator");

        return Files.asCharSource(file, Charset.forName("UTF-8"))
                .readLines(new LineProcessor<List<PunchRecord>>() {
                    final List<PunchRecord> result = Lists.newArrayList();

                    @Override
                    public boolean processLine(String line) {
                        List<String> list = Splitter.on(fileSeparator).trimResults().splitToList(line);
                        PunchRecord bean = new PunchRecord();
                        String s = list.get(0);
                        bean.setPunchOnTime(DateUtils.parseDateTime(s,"yyyy-MM-dd HH:mm:ss"));
                        bean.setPunchOnAddr(list.get(1));
                        bean.setOnRemark(list.get(2));
                        result.add(bean);
                        return true;
                    }

                    @Override
                    public List<PunchRecord> getResult() {
                        return result;
                    }
                });
    }

    private File getFile(String fileName) {
        String configParentPath = env.getProperty("punch.file.store-path", "");
        String parentPath;
        if (StringUtils.hasText(configParentPath)) {
            parentPath = configParentPath;
        } else {
            String rootUri = getRootPath();
            parentPath = rootUri + File.separator + "records" + File.separator;
        }
        return new File(parentPath, fileName);
    }

    /** 修改数据存储方式 **/
    private void changeStoreType(String type) throws Exception {
        boolean b = Objects.equals("0", type) || Objects.equals("1", type);
        if (!b) {
            log.error(">>> 参数[{}]非法", type);
            throw new Exception("参数非法");
        }
        FileInputStream fis = null;
        FileOutputStream fos = null;
        try {
            String rootPath = getRootPath();
            String filePath = rootPath + File.separator + "config" + File.separator + "storeType.conf";

            Properties prop = new Properties();
            fis = new FileInputStream(filePath);
            prop.load(fis);

            fos = new FileOutputStream(filePath);
            prop.setProperty("punch.type", type);
            prop.store(fos, "### 打卡存储的方式：0 文件，1 数据库");
        } catch (IOException e) {
            log.error(">>> 修改配置出现异常", e);
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                }
            }
        }
    }

    private String getRootPath() {
        return ClassUtils.getDefaultClassLoader().getResource("").getPath();
    }
}
