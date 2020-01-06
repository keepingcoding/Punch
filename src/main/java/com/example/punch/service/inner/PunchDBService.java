package com.example.punch.service.inner;

import com.example.punch.contract.dto.PunchRecordDTO;
import com.example.punch.model.PunchRecord;
import com.example.punch.model.PunchRecordExp;
import com.example.punch.model.SysConfig;

import java.util.List;
import java.util.Map;

/**
 * 数据库操作
 *
 * @author zzs
 * @date 2019/11/15 22:20
 */
public interface PunchDBService {

    /** 获取打卡类型，上班还是下班 **/
    Map<String,Object> getPunchType(String date);

    /** 执行打卡 **/
    void writeToDb(PunchRecord punchRecord, Byte punchType) throws Exception;

    /** 读取打卡记录 **/
    List<PunchRecordExp> readFromDb(String time, Integer type);

    /** 修改 **/
    int editPunch(PunchRecordDTO punchRecordDTO);

    /** 获取配置 **/
    SysConfig getSysConfig(String configName);
}
