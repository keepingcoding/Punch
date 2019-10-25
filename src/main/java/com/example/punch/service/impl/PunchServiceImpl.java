package com.example.punch.service.impl;

import com.alibaba.fastjson.TypeReference;
import com.example.punch.entity.PunchNotes;
import com.example.punch.entity.PunchNotesDTO;
import com.example.punch.service.PunchService;
import com.example.punch.utils.BeanConverter;
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
import java.io.IOException;
import java.nio.charset.Charset;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

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
    public void doPunch(PunchNotesDTO punchNotesDTO) throws Exception {
        log.info(">>>>>>>>>>>>>>>>>> Processing begins >>>>>>>>>>>>>>>>>>");
        log.info(">>> Receive data [{}]", punchNotesDTO);
        PunchNotes punchNotes = BeanConverter.convert(punchNotesDTO, PunchNotes.class);

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
        log.info(">>> The data currently being written is [{}].", res);

        Files.asCharSink(file, Charset.forName("UTF-8"), FileWriteMode.APPEND).write(res + System.lineSeparator());
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
        return BeanConverter.convert(res, new TypeReference<List<PunchNotesDTO>>(){});
    }

    /** 从文件读取 **/
    private List<PunchNotes> readFromFile(String fileName) throws Exception {
        log.info(">>> Start to read from file.");
        File file = getFile(fileName);
        if (!file.exists()) {
            log.error("文件[{}]不存在！", fileName);
            throw new Exception("文件不存在！");
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
        String rootUri = ClassUtils.getDefaultClassLoader().getResource("").getPath();
        String parentPath = rootUri + File.separator + "records" + File.separator;
        return new File(parentPath, fileName);
    }

    /** 格式化日期 **/
    private String formatDate(Date date, String pattern) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(pattern);
        return dateTimeFormatter.format(LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault()));
    }
}
