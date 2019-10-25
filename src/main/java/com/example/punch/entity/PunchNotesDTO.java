package com.example.punch.entity;

import com.google.common.base.Converter;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.NotNull;

/**
 * @author zzs
 * @date 2019/10/24 21:30
 */
@Setter
@Getter
@ToString
public class PunchNotesDTO {

    /** 打卡时间 **/
    @NotNull
    private String time;

    /** 打卡地点 **/
    private String location;

    /** 备注 **/
    private String remark;

}
