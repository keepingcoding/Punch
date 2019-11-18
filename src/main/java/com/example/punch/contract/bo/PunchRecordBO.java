package com.example.punch.contract.bo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * @author zzs
 * @date 2019/11/17 23:40
 */
@Getter
@Setter
@ToString
public class PunchRecordBO {

    /** 日期 **/
    private Date punchDay;

    /** 打卡时间 **/
    private Date time;

    /** 打卡地点 **/
    private String location;

    /** 备注 **/
    private String remark;

    /** 打卡类型: 0 上班 1 下班 **/
    private Byte punchType;
}
