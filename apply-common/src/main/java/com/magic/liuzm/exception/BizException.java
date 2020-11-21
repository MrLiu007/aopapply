
package com.magic.liuzm.exception;

/**
 * 实现描述：业务异常
 */
public class BizException extends RuntimeException {

    private static final long serialVersionUID = -1170501462599127698L;

    final private int errCode;

    final private String errMsg;

    public BizException(ErrCode errCode) {
        super(errCode.getMessage());
        this.errCode = errCode.getCode();
        this.errMsg = errCode.getMessage();
    }

    public BizException(int code, String errMsg) {
        super(errMsg);
        this.errCode = code;
        this.errMsg = errMsg;
    }

    public int getErrCode() {
        return errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }
}
