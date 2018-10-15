package com.lingtoo.wechat.components;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * Created: 2016/1/8.
 * Author: Qiannan Lu
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/spring/spring-other.xml"})
public class AlidayuClientTest {

    @Autowired
    private AlidayuClient alidayuClient;

    @Ignore
    @Test
    public void testSendMessage() {
        alidayuClient.sendMessage("18000000000", "1234", "test");
    }
}