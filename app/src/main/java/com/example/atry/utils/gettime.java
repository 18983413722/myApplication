package com.example.atry.utils;

//封装获取时间的方法

import java.text.SimpleDateFormat;
import java.util.SimpleTimeZone;
import java.util.TimeZone;
import java.util.Date;

public class gettime {
    public static String getTime(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
        Date date = new Date(System.currentTimeMillis());
        return simpleDateFormat.format(date);
    }
}
