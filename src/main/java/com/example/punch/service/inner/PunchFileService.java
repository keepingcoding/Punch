package com.example.punch.service.inner;

import com.example.punch.model.PunchRecord;
import com.example.punch.contract.bo.PunchRecordBO;

import java.io.IOException;
import java.util.List;

/**
 * 文件操作
 *
 * @author zzs
 * @date 2019/11/15 22:21
 */
public interface PunchFileService {

    /** 执行打卡 **/
    void writeToFile(PunchRecordBO punchRecordBO) throws IOException;

    /** 读取打卡记录 **/
    List<PunchRecord> readFromFile(String fileName) throws Exception;
}
