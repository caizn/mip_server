package com.lingtoo.common.utils;

import java.util.Random;

/**
 * Created: 2015/9/6.
 * Author: Qiannan Lu
 */
public class ValidateCodeUtil {
    public static String getPhoneValidateCode() {
        StringBuilder validateCode = new StringBuilder();
        String strings = "1234567890";
        Random random = new Random();
        for (int i = 1; i <= 6; i++) {
            int randValue = random.nextInt(strings.length());
            validateCode.append(strings.charAt(randValue));
        }
        return validateCode.toString();
    }

    /**
     * 根据传入的数字，得到对应的字母
     *
     * @param bits
     * @return
     */
    public static String getRandCode(int bits) {
        StringBuilder randCode = new StringBuilder();
        String strings = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        Random random = new Random();
        for (int i = 1; i <= bits; i++) {
            int randValue = random.nextInt(strings.length());
            randCode.append(strings.charAt(randValue));
        }
        return randCode.toString();
    }
}

