package com.magic.liuzm.exception;

/**
 * http接口统一错误码
 */
public enum ErrCode {

    //
    SYSTEM_ERROR(0, "服务器开小差，请稍后再试"),

    //
    BIZ_PARAM_INVALID(1, "参数不合法"),

    BIZ_LOCKED(2, "业务已锁，请重试"),

    BIZ_ULTRA_VIRES_INVALID(3, "业务权限不足");

    private int code;

    private String message;

    ErrCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
