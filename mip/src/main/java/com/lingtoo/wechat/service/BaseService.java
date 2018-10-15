package com.lingtoo.wechat.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created: 2015/12/29.
 * Author: Qiannan Lu
 */
abstract public class BaseService {
    protected final Logger logger = LoggerFactory.getLogger(getClass());

    protected static final String JSON = "json";
    protected static final String XML = "xml";
}
