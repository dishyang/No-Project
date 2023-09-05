package com.example.noProject.constants;

public class RequestResultConstants {
    /**
     * 正常返回/操作成功
     **/
    public static final int SERVICE_SUCCESS_CODE = 200;
    public static final String SERVICE_SUCCESS_MSG = "ok";
    /**
     * 失败返回/操作失败
     */
    public static final int SERVICE_FAIL_CODE = 400;
    public static final String SERVICE_FAIL_MSG = "Fail";
    /**
     * 鉴定失败返回/非法访问
     */
    public static final int AUTHENTICATION_FAIL_CODE = 4001;
    public static final String AUTHENTICATION_FAIL_MSG = "非法访问";
    /**
     * 权限不足返回/无权限
     */
    public static final int PERMISSION_DENIED_CODE = 4002;
    public static final String PERMISSION_DENIED_MSG = "无权限";

    /**
     * 系统内部错误
     */
    public static final int SERVICE_SYSTEM_ERROR_CODE = 5000;
    public static final String SERVICE_SYSTEM_ERROR_MSG = "系统内部异常";

}
