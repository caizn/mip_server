package com.lingtoo.wechat;

import org.junit.Assert;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

/**
 * Created: 2015/11/5.
 * Author: Qiannan Lu
 */
public class EncodeTest {

    @Test
    public void testBase64() throws UnsupportedEncodingException {
        Assert.assertEquals("MTIzNDU2", Base64.getEncoder().encodeToString("123456".getBytes("utf-8")));
        Assert.assertEquals("some string", new String(Base64.getDecoder().decode("c29tZSBzdHJpbmc="), "utf-8"));;
    }
}
