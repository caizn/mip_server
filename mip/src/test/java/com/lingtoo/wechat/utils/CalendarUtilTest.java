package com.lingtoo.wechat.utils;

import org.junit.Assert;
import org.junit.Test;

import java.awt.*;
import java.text.ParseException;
import java.util.Date;

/**
 * Created: 2015/10/21.
 * Author: Qiannan Lu
 */
public class CalendarUtilTest {
    private static final long EXPECTED_TIME_MILLIS = 1445410073768L;
    private static final String EXPECTED_TIME = "14:47";

    @Test
    public void testGetTomorrowDate() throws ParseException {
        Assert.assertEquals("今天", CalendarUtil.parseFutureDay(new Date()));
        Assert.assertEquals("明天", CalendarUtil.parseFutureDay(new Date(System.currentTimeMillis() + CalendarUtil.TIME_PERIOD_DAY)));
        Assert.assertEquals(EXPECTED_TIME, CalendarUtil.parseTime(new Date(EXPECTED_TIME_MILLIS)));
    }
}
