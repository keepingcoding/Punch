package com.example.punch.model.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author zzs
 * @date 2019/10/24 21:30
 */
@Setter
@Getter
@ToString
public class PunchNotesDTO implements Serializable {

    /** 打卡时间 **/
    @NotNull
    private String time;

    /** 打卡地点 **/
    private String location;

    /** 备注 **/
    private String remark;

    /** 打卡类型: 0 上班 1 下班 **/
    private Byte punchType;

}
