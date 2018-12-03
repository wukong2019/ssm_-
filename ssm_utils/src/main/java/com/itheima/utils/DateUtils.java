package com.itheima.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

    public static String Date2String(Date date, String pattern){
        SimpleDateFormat sd=new SimpleDateFormat(pattern);
        return sd.format(date);
    }

    public static Date String2Date(String str,String pattern){
        SimpleDateFormat sd=new SimpleDateFormat(pattern);
        Date date = null;
        try {
            date = sd.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
}
