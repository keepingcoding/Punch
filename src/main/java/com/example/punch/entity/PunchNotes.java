package com.example.punch.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * @author zzs
 * @date 2019/10/24 21:34
 */
@Setter
@Getter
public class PunchNotes {

    /** 打卡时间 **/
    private String time;

    /** 打卡地点 **/
    private String location;

    /** 备注 **/
    private String remark;
}
