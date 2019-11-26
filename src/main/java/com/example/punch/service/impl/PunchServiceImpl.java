package com.example.punch.service.impl;

import com.example.punch.model.PunchRecord;
import com.example.punch.contract.bo.PunchRecordBO;
import com.example.punch.model.PunchRecordExp;
import com.example.punch.service.PunchService;
import com.example.punch.service.inner.PunchDBService;
import com.example.punch.service.inner.PunchFileService;
import com.example.punch.util.DateUtils;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;
import java.util.Map;

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

    @Autowired
    private PunchFileService punchFileService;

    @Autowired
    private PunchDBService punchDBService;


    @Override
    public void doPunch(PunchRecordBO punchRecordBO) throws Exception {
        log.info(">>>>>>>>>>>>>>>>>> Processing begins >>>>>>>>>>>>>>>>>>");
        if (punchRecordBO == null) {
            throw new Exception("非法参数");
        }
        log.info(">>> Receive data [{}]", punchRecordBO);

        String storageType = getStorageType();
        switch (storageType) {
            case "0":
                this.punchFileService.writeToFile(punchRecordBO);
                break;
            case "1":
                doPunchToDb(punchRecordBO);
                break;
        }
        log.info("<<<<<<<<<<<<<<<<<< Processing ends <<<<<<<<<<<<<<<<<<");
    }

    /** 写入数据库 **/
    private void doPunchToDb(PunchRecordBO punchRecordBO) throws Exception {
        PunchRecord punchRecord = new PunchRecord();

        Date punchTime = punchRecordBO.getTime();
        Byte punchType = punchRecordBO.getPunchType();
        switch (punchType) {
            case 0:
                punchRecord.setPunchDay(punchTime);
                punchRecord.setPunchOnTime(punchTime);
                punchRecord.setPunchOnAddr(punchRecordBO.getLocation());
                punchRecord.setOnRemark(punchRecordBO.getRemark());
                break;
            case 1:
                punchRecord.setPunchOffTime(punchTime);
                punchRecord.setPunchOffAddr(punchRecordBO.getLocation());
                punchRecord.setOffRemark(punchRecordBO.getRemark());
                break;
        }

        this.punchDBService.writeToDb(punchRecord, punchType);
    }

    @Override
    public Map<String,Object> getPunchType(String date) {
        Map<String,Object> res = null;
        String storageType = getStorageType();
        switch (storageType) {
            case "0":
                log.info(">>> StorageType is file, not support this method");
                break;
            case "1":
                res = this.punchDBService.getPunchType(date);
                break;
        }
        return res;
    }

    @Override
    public List<PunchRecordExp> queryAll(String time) throws Exception {
        List<PunchRecordExp> res = Lists.newArrayList();

        String queryDate;
        if (StringUtils.hasText(time)) {
            queryDate = time;
        } else {
            queryDate = DateUtils.formatDate(new Date(), "yyyy-MM");
        }

        String storageType = getStorageType();
        switch (storageType) {
            case "0":
                res = this.punchFileService.readFromFile(queryDate);
                break;
            case "1":
                res = this.punchDBService.readFromDb(queryDate);
                break;
        }
        log.info(">>> Read [{}].", res.size());
        return res;
    }

    private String getStorageType() {
        String punchType = env.getProperty("punch.type", StorageType.getCodeByNameEn("db"));
        log.info(">>> The current storage type is [{}].", StorageType.getNameByCode(punchType));
        return punchType;
    }
}
