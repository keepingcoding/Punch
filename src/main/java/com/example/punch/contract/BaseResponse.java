package com.example.punch.contract;

import com.example.punch.contract.exception.ServiceException;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @author zzs
 * @date 2019/9/26 14:43
 */
@Getter
@Setter
@Accessors(chain = true)
public class BaseResponse<T> implements Serializable {

    /** 状态吗 **/
    private String statusCode = "200";

    /** 业务状态码 **/
    private String resultCode = "200";

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
        this.result = result;
    }


    public BaseResponse(String statusCode, String resultMsg) {
        this.statusCode = statusCode;
        this.resultMsg = resultMsg;
    }

    public BaseResponse(ServiceException e) {
        this(e.getStatusCode(), e.getResultCode(), e.getMessage());
    }

    public BaseResponse(Map<String, List<String>> validationErrors) {
        this(ServiceException.PARAM_VERIFY_EXCEPTION, validationErrors);
    }

    public BaseResponse(ServiceException e, Map<String, List<String>> validationErrors) {
        this(e);
        this.validationErrors = validationErrors;
    }

    public BaseResponse(String statusCode, String resultCode, String resultMsg) {
        this.statusCode = statusCode;
        this.resultCode = resultCode;
        this.resultMsg = resultMsg;
    }

    public long calcCostTime(long beginTime) {
        costTime = System.currentTimeMillis() - beginTime;
        return costTime;
    }
}
