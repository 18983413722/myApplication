package com.example.atry.utils;




//这一段写入时间是在网上cv的


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
