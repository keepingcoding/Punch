package com.example.punch.model.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * @author zzs
 * @date 2019/11/16 19:40
 */
public class PunchRecordVO implements Serializable {

    private Integer id;

    private Integer userId;

    private Date punchDay;

    private Date punchOnTime;

    private Date punchOffTime;

    private Byte punchStatus;

    private String punchOnAddr;

    private String punchOffAddr;

    private String onRemark;

    private String offRemark;
}
