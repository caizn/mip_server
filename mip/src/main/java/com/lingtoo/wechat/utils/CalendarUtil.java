package com.lingtoo.wechat.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

/**
 * Created: 2015/10/21.
 * Author: Qiannan Lu
 */
public class CalendarUtil {
    public static final SimpleDateFormat DATE_TIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
    public static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("yyyy/MM/dd");
    public static final SimpleDateFormat SIMPLE_TIME_FORMAT = new SimpleDateFormat("HH:mm");

    public static final String[] WEEK_DAYS = {"周日", "周一", "周二", "周三", "周四", "周五", "周六"};

    public static final long TIME_PERIOD_SECOND = 1000;
    public static final long TIME_PERIOD_MINUTE = 60 * TIME_PERIOD_SECOND;
    public static final long TIME_PERIOD_HOUR = 60 * TIME_PERIOD_MINUTE;
    public static final long TIME_PERIOD_DAY = 24 * TIME_PERIOD_HOUR;
    public static final long TIME_PERIOD_WEEK = 7 * TIME_PERIOD_DAY;

    public static String parseFutureDay(Date date) throws ParseException {
        long currentTime = System.currentTimeMillis();
        long interval = Math.abs(date.getTime() - currentTime);
        if (interval > TIME_PERIOD_WEEK)
            return SIMPLE_DATE_FORMAT.format(date);
        if (getDateInterval(date) > 1)
            return WEEK_DAYS[getCalendar(date).get(Calendar.DAY_OF_WEEK) - 1];
        if (getDateInterval(date) == 1)
            return  "明天";
        return "今天";
    }

    public static Date defaultGameTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DAY_OF_YEAR, +1);
        calendar.set(Calendar.HOUR_OF_DAY, 18);
        calendar.set(Calendar.MINUTE, 30);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

    public static String formatDatetime(Date date) {
        return DATE_TIME_FORMAT.format(date);
    }

    public static String formatDate(Date date) {
        return SIMPLE_DATE_FORMAT.format(date);
    }

    public static String parseTime(Date date) throws ParseException {
        return SIMPLE_TIME_FORMAT.format(date);
    }

    public static Calendar getCalendar(Date date) {
        Calendar current = Calendar.getInstance();
        current.setTime(date);
        return current;
    }

    public static String getTodayDateString() {
        return SIMPLE_DATE_FORMAT.format(new Date());
    }

    public static Calendar getTodayWithoutTime() throws ParseException {
        Calendar today = Calendar.getInstance();
        today.setTime(SIMPLE_DATE_FORMAT.parse(getTodayDateString()));
        return today;
    }

    public static int getDateInterval(Date date) throws ParseException {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        long interval = Math.abs(calendar.getTimeInMillis() - getTodayWithoutTime().getTimeInMillis());
        return (int) (interval / TIME_PERIOD_DAY);
    }
}
