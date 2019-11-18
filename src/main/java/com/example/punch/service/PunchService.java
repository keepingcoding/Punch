package com.example.punch.service;

import com.example.punch.model.PunchRecord;
import com.example.punch.contract.bo.PunchRecordBO;

import java.util.List;
import java.util.Map;

/**
 * @author zzs
 * @date 2019/10/24 23:24
 */
public interface PunchService {

    /** 打卡 **/
    void doPunch(PunchRecordBO punchRecordBO) throws Exception;

    /** 获取打卡类型 **/
    Map<String,Object> getPunchType(String date);

    /** 查询list **/
    List<PunchRecord> queryAll(String time) throws Exception;
}
