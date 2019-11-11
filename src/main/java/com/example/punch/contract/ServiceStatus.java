package com.example.punch.contract;

/**
 * 状态码
 */
public enum ServiceStatus {

    API_OK("200", "成功"),
    API_PARTIAL_OK("206", "部分成功"),

    BAD_REQUEST("400", "格式错误"),
    NOT_EXIST("404", "对象不存在"),
    PARAM_VERIFY_ERROR("409", " 参数校验失败"),

    GENERAL_ERROR("500", "服务器内部错误"),
    VALIDATE_ERROR("501", "参数验证错误"),
    NOT_IMPLEMENTED("502", "接口已关闭"),
    FREQUENCY_LIMIT_ERROR("503", "频次超限"),
    FORBID_ERROR("504", "访问被禁"),
    PARAMS_ERROR("505", "请求参数非法"),
    REPEATED_REQ_ERROR("506", "重复请求"),
    TRAFFIC_LIMIT_ERROR("507", "访问被限流"),

    APPCODE_ERROR("600", "APP CODE 不存在"),

    AUTHEN_ERROR("701", "用户验证错误"),
    AUTHOR_ERROR("702", "用户授权错误"),

    NETWORK_ERROR("801", "网络错误"),
    OUTER_API_INVOKE_FAIL("802", "外部API调用失败"),
    INNER_API_INVOKE_FAIL("803", "内部API调用失败"),

    SIGN_VERIFY_ERROR("901", "签名验证失败"),
    LOGIN_INVALID("904", "登录已失效"),
    NOT_LOGGED_IN("907", "未登录"),
    ;

    private final String code;
    private final String description;

    ServiceStatus(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
