package com.example.noProject.utils;

import java.util.UUID;

public class UuidUtil {

    /**
     * 获取UUID
     * @return
     */
    public static String getUUID(){
        return UUID.randomUUID().toString().replaceAll("-","");
    }
}
