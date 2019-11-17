package com.example.punch.contract;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@Accessors(chain = true)
public class BaseResponse<T> implements Serializable {

    /** 调用成功与否 **/
    private boolean success = true;

    /** 业务处理代码 **/
    private String resultCode = ServiceStatus.API_OK.getCode();

    /** 状态描述 **/
    private String resultMsg = "ok";

    /** 业务模型对象 **/
    private T result;

    /** 参数校验错误列表 **/
    private Map<String, List<String>> validationErrors;

    /** 耗费时间 **/
    private long costTime;

    public BaseResponse() {
    }

    public BaseResponse(T result) {
        this.success = true;
        this.resultCode = ServiceStatus.API_OK.getCode();
        this.resultMsg = "ok";
        this.validationErrors = null;
        this.result = result;
    }

    public BaseResponse(Map<String, List<String>> validationErrors) {
        this.success = false;
        this.resultCode = ServiceStatus.VALIDATE_ERROR.getCode();
        this.resultMsg = ServiceStatus.VALIDATE_ERROR.getDescription();
        this.validationErrors = validationErrors;
    }

    public long calcCostTime(long beginTime) {
        this.costTime = System.currentTimeMillis() - beginTime;
        return this.costTime;
    }
}
