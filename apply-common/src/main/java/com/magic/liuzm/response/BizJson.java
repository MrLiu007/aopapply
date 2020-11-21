package com.magic.liuzm.response;

/**
 * 实现描述：标准json数据格式
 *
 * @author zuochi
 * @version v1.0.0
 * @date 2016-08-03
 */
public final class BizJson{

    /**
     * 常量-成功
     */
    public static final String DEFAULT_SUCCESS_MSG = "操作成功";

    /**
     * 常量-失败
     */
    public static final String DEFAULT_FAILURE_MSG = "服务器开小差了!";

    /**
     * 常量-成功码
     */
    public static final int DEFAULT_SUCCESS_CODE = 0;
    /**
     * 常量-失败码
     */
    public static final int DEFAULT_FAILURE_CODE = 1;


    /**
     * 状态码
     */
    private final int status;

    /**
     * 数据对象
     */
    private final Object data;

    /**
     * 提示语
     */
    private final String message;

    private BizJson(int status, Object data, String message) {
        this.status = status;
        this.data = data;
        this.message = message;
    }

}
