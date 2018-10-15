package com.lingtoo.wechat.service;

import com.lingtoo.wechat.domain.Address;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created: 2015/12/29.
 * Author: Qiannan Lu
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/spring/spring-other.xml"})
public class BaiduAPIServiceTest {
    @Autowired
    private BaiduAPIService apiService;

    @Test
    public void parseGPSToLocation() {
        Address address = apiService.parseGPSToLocation(23.287016, 113.83734);
        //Assert.assertEquals("广东省", address.getAddressComponent().getProvince());
    }
}