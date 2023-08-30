package com.example.noProject.constants;

public class BussinessConstants {
    /**
     * Token
     */
    public static final String ACCESS_TOKEN = "X-Access-Token";
    /**
     * redis相关
     * */
    //session的生命周期,秒
    public static final Long MAX_SESSION_IN_SECONDS=60*60*12L;
    /**
     * redis中的key值
     */
    public static final String TOKEN_USER_KEY = "user_obj"; //用户信息的key
    public static final String TOKEN_AUTHORITIES_KEY = "authoritiesList_obj"; //菜单信息的key
}
