package com.example.punch.service;

import com.example.punch.contract.bo.PunchRecordBO;
import com.example.punch.contract.dto.PunchRecordDTO;
import com.example.punch.model.PunchRecordExp;
import com.example.punch.model.SysConfig;

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
    List<PunchRecordExp> queryAll(String time, Integer type) throws Exception;

    /** 修改 **/
    int editPunch(PunchRecordDTO punchRecordDTO);

    /** 获取配置 **/
    SysConfig getSysConfig(String configName);
}
