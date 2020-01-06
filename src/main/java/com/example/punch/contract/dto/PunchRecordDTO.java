package com.example.punch.contract.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @author zzs
 * @date 2020/1/6 16:50
 */
@Setter
@Getter
@ToString
public class PunchRecordDTO implements Serializable {

    private Integer id;

    private Integer userId;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date punchDay;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date punchOnTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date punchOffTime;

    private Byte punchStatus;

    private String punchOnAddr;

    private String punchOffAddr;

    private String onRemark;

    private String offRemark;

    private Integer updatedId;

    private Long updatedTime;
}
