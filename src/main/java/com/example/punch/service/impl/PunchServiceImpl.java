package com.example.punch.service.impl;

import com.alibaba.fastjson.TypeReference;
import com.example.punch.contract.Tuple;
import com.example.punch.model.PunchNotes;
import com.example.punch.model.PunchNotesDTO;
import com.example.punch.service.PunchService;
import com.example.punch.utils.BeanConverter;
import com.example.punch.utils.FileUtil;
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

import java.io.*;
import java.nio.charset.Charset;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Properties;

import static com.example.punch.contract.CommonConstant.StorageType;

/**
 * @author zzs
 * @date 2019/10/24 23:24
 */
@Slf4j
@Service
public class PunchServiceImpl implements PunchService {

    @Autowired
    private Environment env;

    @Override
    public void doPunch(PunchNotes punchNotes) throws Exception {
        log.info(">>>>>>>>>>>>>>>>>> Processing begins >>>>>>>>>>>>>>>>>>");
        if (punchNotes == null) {
            throw new Exception("非法参数");
        }
        log.info(">>> Receive data [{}]", punchNotes);

        //默认存储到文件
        String punchType = env.getProperty("punch.type", StorageType.getCodeByNameEn("file"));
        log.info(">>> The current storage type is [{}].", StorageType.getNameByCode(punchType));
        switch (punchType) {
            case "0":
                writeToFile(punchNotes);
                break;
            case "1":
                writeToDb(punchNotes);
                break;
        }
        log.info("<<<<<<<<<<<<<<<<<< Processing ends <<<<<<<<<<<<<<<<<<");
    }

    /** 写到文件 **/
    private void writeToFile(PunchNotes punchNotes) throws IOException {
        String fileName = formatDate(new Date(), "yyyy-MM");
        File file = getFile(fileName);
        log.info(">>> Get file [{}].", file.getCanonicalPath());
        Files.createParentDirs(file);

        String fileSeparator = env.getProperty("punch.file.separator");
        StringBuilder sb = new StringBuilder();
        sb.append(punchNotes.getTime());
        sb.append(fileSeparator);
        sb.append(punchNotes.getLocation() == null ? "" : punchNotes.getLocation());
        sb.append(fileSeparator);
        sb.append(punchNotes.getRemark() == null ? "" : punchNotes.getRemark());
        String res = sb.toString();
        String writeData = res + System.lineSeparator();
        log.info(">>> The data currently being written is [{}].", res);

        //读取文件最后一行，判断是否需要更新
        Tuple<String> tuple = FileUtil.readLastLine(file);
        if (tuple != null) {
            String lastLine = tuple.getResult();
            long lastIndex = tuple.getIndex();
            List<String> list = Splitter.on(fileSeparator).trimResults().splitToList(lastLine);
            //todo 目前逻辑是一天只能打一次卡
            String oldStr = punchNotes.getTime().substring(0, 10);
            if (list.get(0).contains(oldStr)) {
                //更新
                log.info(">>> The write mode is [update].");
                FileUtil.writeFromIndex(file, lastIndex, writeData);
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

    /** 写到数据库 **/
    private void writeToDb(PunchNotes punchNotes) {

    }

    @Override
    public List<PunchNotesDTO> queryAll(String time) throws Exception {
        List<PunchNotes> res = Lists.newArrayList();

        String punchType = env.getProperty("punch.type", StorageType.getCodeByNameEn("file"));
        log.info(">>> The current storage type is [{}].", StorageType.getNameByCode(punchType));

        String fileName;
        if (StringUtils.hasText(time)) {
            fileName = time;
        } else {
            fileName = formatDate(new Date(), "yyyy-MM");
        }

        switch (punchType) {
            case "0":
                res = readFromFile(fileName);
                break;
            case "1":
                res = readFromDb(fileName);
                break;
        }
        log.info(">>> Read [{}].", res.size());
        return BeanConverter.convert(res, new TypeReference<List<PunchNotesDTO>>(){});
    }

    /** 从文件读取 **/
    private List<PunchNotes> readFromFile(String fileName) throws Exception {
        log.info(">>> Start to read from file.");
        File file = getFile(fileName);
        if (!file.exists()) {
            log.error("文件[{}]不存在！", fileName);
            return Lists.newArrayList();
        }
        String fileSeparator = env.getProperty("punch.file.separator");

        return Files.asCharSource(file, Charset.forName("UTF-8"))
                .readLines(new LineProcessor<List<PunchNotes>>() {
                    final List<PunchNotes> result = Lists.newArrayList();

                    @Override
                    public boolean processLine(String line) {
                        List<String> list = Splitter.on(fileSeparator).trimResults().splitToList(line);
                        PunchNotes bean = new PunchNotes();
                        bean.setTime(list.get(0));
                        bean.setLocation(list.get(1));
                        bean.setRemark(list.get(2));
                        result.add(bean);
                        return true;
                    }

                    @Override
                    public List<PunchNotes> getResult() {
                        return result;
                    }
                });
    }

    /** 从数据库读取 **/
    private List<PunchNotes> readFromDb(String time){
        return null;
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

    private String getRootPath() {
        return ClassUtils.getDefaultClassLoader().getResource("").getPath();
    }

    /** 格式化日期 **/
    private String formatDate(Date date, String pattern) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(pattern);
        return dateTimeFormatter.format(LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault()));
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
}
