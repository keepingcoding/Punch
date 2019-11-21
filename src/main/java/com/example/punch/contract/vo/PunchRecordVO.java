package com.example.punch.contract.vo;

import com.example.punch.contract.CommonConstant;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * @author zzs
 * @date 2019/11/16 19:40
 */
@Getter
@Setter
@ToString
public class PunchRecordVO implements Serializable {

    private Integer id;

    private Integer userId;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date punchDay;

    @JsonFormat(pattern = "HH:mm:ss", timezone = "GMT+8")
    private Date punchOnTime;

    @JsonFormat(pattern = "HH:mm:ss", timezone = "GMT+8")
    private Date punchOffTime;

    private Byte punchStatus;

    private String punchOnAddr;

    private String punchOffAddr;

    private String onRemark;

    private String offRemark;

}
