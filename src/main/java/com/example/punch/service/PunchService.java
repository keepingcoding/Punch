package com.example.punch.service;

import com.example.punch.entity.PunchNotesDTO;

import java.util.List;

/**
 * @author zzs
 * @date 2019/10/24 23:24
 */
public interface PunchService {

    /** 打卡 **/
    void doPunch(PunchNotesDTO punchNotesDTO) throws Exception;

    /** 查询list **/
    List<PunchNotesDTO> queryAll(String time) throws Exception;
}
