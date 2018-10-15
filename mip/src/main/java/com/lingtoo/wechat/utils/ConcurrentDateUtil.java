package com.lingtoo.wechat.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by shenzh on 2017/1/16.
 */
public class ConcurrentDateUtil {

    private static ThreadLocal<DateFormat> threadLocal = new ThreadLocal<DateFormat>() {
        @Override
        protected DateFormat initialValue() {
            return new SimpleDateFormat("yyyyMMddHHmmssSSS");
        }
    };

    public static Date parse(String dateStr) throws ParseException {
        return threadLocal.get().parse(dateStr);
    }

    public static String format(Date date) {
        return threadLocal.get().format(date);
    }
}