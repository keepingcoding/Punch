package com.example.punch.contract.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

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
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date time;

    /** 打卡地点 **/
    private String location;

    /** 备注 **/
    private String remark;

    /** 打卡类型: 0 上班 1 下班 **/
    private Byte punchType;

}
