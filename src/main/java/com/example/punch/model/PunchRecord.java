package com.example.punch.model;

import java.io.Serializable;
import java.util.Date;

public class PunchRecord implements Serializable {
    /**
     * t_punch_record.id
     */
    private Integer id;

    /**
     * 用户id
     * t_punch_record.user_id
     */
    private Integer userId;

    /**
     * 打卡日期
     * t_punch_record.punch_day
     */
    private Date punchDay;

    /**
     * 上班打卡时间
     * t_punch_record.punch_on_time
     */
    private Date punchOnTime;

    /**
     * 下班打卡时间
     * t_punch_record.punch_off_time
     */
    private Date punchOffTime;

    /**
     * 打卡状态: 0 迟到 1 早退
     * t_punch_record.punch_status
     */
    private Byte punchStatus;

    /**
     * 打卡地点
     * t_punch_record.punch_on_addr
     */
    private String punchOnAddr;

    /**
     * 打卡地点
     * t_punch_record.punch_off_addr
     */
    private String punchOffAddr;

    /**
     * 备注
     * t_punch_record.on_remark
     */
    private String onRemark;

    /**
     * 备注
     * t_punch_record.off_remark
     */
    private String offRemark;

    /**
     * t_punch_record.created_id
     */
    private Integer createdId;

    /**
     * t_punch_record.created_time
     */
    private Long createdTime;

    /**
     * t_punch_record.updated_id
     */
    private Integer updatedId;

    /**
     * t_punch_record.updated_time
     */
    private Long updatedTime;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getPunchDay() {
        return punchDay;
    }

    public void setPunchDay(Date punchDay) {
        this.punchDay = punchDay;
    }

    public Date getPunchOnTime() {
        return punchOnTime;
    }

    public void setPunchOnTime(Date punchOnTime) {
        this.punchOnTime = punchOnTime;
    }

    public Date getPunchOffTime() {
        return punchOffTime;
    }

    public void setPunchOffTime(Date punchOffTime) {
        this.punchOffTime = punchOffTime;
    }

    public Byte getPunchStatus() {
        return punchStatus;
    }

    public void setPunchStatus(Byte punchStatus) {
        this.punchStatus = punchStatus;
    }

    public String getPunchOnAddr() {
        return punchOnAddr;
    }

    public void setPunchOnAddr(String punchOnAddr) {
        this.punchOnAddr = punchOnAddr == null ? null : punchOnAddr.trim();
    }

    public String getPunchOffAddr() {
        return punchOffAddr;
    }

    public void setPunchOffAddr(String punchOffAddr) {
        this.punchOffAddr = punchOffAddr == null ? null : punchOffAddr.trim();
    }

    public String getOnRemark() {
        return onRemark;
    }

    public void setOnRemark(String onRemark) {
        this.onRemark = onRemark == null ? null : onRemark.trim();
    }

    public String getOffRemark() {
        return offRemark;
    }

    public void setOffRemark(String offRemark) {
        this.offRemark = offRemark == null ? null : offRemark.trim();
    }

    public Integer getCreatedId() {
        return createdId;
    }

    public void setCreatedId(Integer createdId) {
        this.createdId = createdId;
    }

    public Long getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Long createdTime) {
        this.createdTime = createdTime;
    }

    public Integer getUpdatedId() {
        return updatedId;
    }

    public void setUpdatedId(Integer updatedId) {
        this.updatedId = updatedId;
    }

    public Long getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Long updatedTime) {
        this.updatedTime = updatedTime;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        PunchRecord other = (PunchRecord) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getPunchDay() == null ? other.getPunchDay() == null : this.getPunchDay().equals(other.getPunchDay()))
            && (this.getPunchOnTime() == null ? other.getPunchOnTime() == null : this.getPunchOnTime().equals(other.getPunchOnTime()))
            && (this.getPunchOffTime() == null ? other.getPunchOffTime() == null : this.getPunchOffTime().equals(other.getPunchOffTime()))
            && (this.getPunchStatus() == null ? other.getPunchStatus() == null : this.getPunchStatus().equals(other.getPunchStatus()))
            && (this.getPunchOnAddr() == null ? other.getPunchOnAddr() == null : this.getPunchOnAddr().equals(other.getPunchOnAddr()))
            && (this.getPunchOffAddr() == null ? other.getPunchOffAddr() == null : this.getPunchOffAddr().equals(other.getPunchOffAddr()))
            && (this.getOnRemark() == null ? other.getOnRemark() == null : this.getOnRemark().equals(other.getOnRemark()))
            && (this.getOffRemark() == null ? other.getOffRemark() == null : this.getOffRemark().equals(other.getOffRemark()))
            && (this.getCreatedId() == null ? other.getCreatedId() == null : this.getCreatedId().equals(other.getCreatedId()))
            && (this.getCreatedTime() == null ? other.getCreatedTime() == null : this.getCreatedTime().equals(other.getCreatedTime()))
            && (this.getUpdatedId() == null ? other.getUpdatedId() == null : this.getUpdatedId().equals(other.getUpdatedId()))
            && (this.getUpdatedTime() == null ? other.getUpdatedTime() == null : this.getUpdatedTime().equals(other.getUpdatedTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getPunchDay() == null) ? 0 : getPunchDay().hashCode());
        result = prime * result + ((getPunchOnTime() == null) ? 0 : getPunchOnTime().hashCode());
        result = prime * result + ((getPunchOffTime() == null) ? 0 : getPunchOffTime().hashCode());
        result = prime * result + ((getPunchStatus() == null) ? 0 : getPunchStatus().hashCode());
        result = prime * result + ((getPunchOnAddr() == null) ? 0 : getPunchOnAddr().hashCode());
        result = prime * result + ((getPunchOffAddr() == null) ? 0 : getPunchOffAddr().hashCode());
        result = prime * result + ((getOnRemark() == null) ? 0 : getOnRemark().hashCode());
        result = prime * result + ((getOffRemark() == null) ? 0 : getOffRemark().hashCode());
        result = prime * result + ((getCreatedId() == null) ? 0 : getCreatedId().hashCode());
        result = prime * result + ((getCreatedTime() == null) ? 0 : getCreatedTime().hashCode());
        result = prime * result + ((getUpdatedId() == null) ? 0 : getUpdatedId().hashCode());
        result = prime * result + ((getUpdatedTime() == null) ? 0 : getUpdatedTime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", userId=").append(userId);
        sb.append(", punchDay=").append(punchDay);
        sb.append(", punchOnTime=").append(punchOnTime);
        sb.append(", punchOffTime=").append(punchOffTime);
        sb.append(", punchStatus=").append(punchStatus);
        sb.append(", punchOnAddr=").append(punchOnAddr);
        sb.append(", punchOffAddr=").append(punchOffAddr);
        sb.append(", onRemark=").append(onRemark);
        sb.append(", offRemark=").append(offRemark);
        sb.append(", createdId=").append(createdId);
        sb.append(", createdTime=").append(createdTime);
        sb.append(", updatedId=").append(updatedId);
        sb.append(", updatedTime=").append(updatedTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}