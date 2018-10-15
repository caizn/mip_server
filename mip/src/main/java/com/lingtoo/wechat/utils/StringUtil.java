package com.lingtoo.wechat.utils;

/*import net.sourceforge.pinyin4j.PinyinHelper;
import org.apache.commons.lang.StringUtils;*/

import java.util.Random;
import java.util.regex.Pattern;

public class StringUtil {

    public static boolean isEmpty(String string) {
        return string == null || string.trim().length() == 0 || string.trim().equals("null") || string.trim().equals("(null)");
    }

    public static boolean isEmpty(Integer in) {
        return in == null || in == 0;
    }

    public static boolean isEmpty(Double in) {
        return in == null || in == 0;
    }

    public static boolean isEmpty(Long in) {
        return in == null || in == 0;
    }

    /**
     * 根据传入的数字，得到对应的字母
     *
     * @param bits
     * @return
     */
    public static String getRandCode(int bits) {
        String code = "";
        String strings = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        Random random = new Random();
        for (int i = 1; i <= bits; i++) {
            int randValue = random.nextInt(strings.length());
            code += strings.substring(randValue, randValue + 1);
        }
        return code;
    }

    public static String getRandPhoneCode() {
        String code = "";
        String strings = "1234567890";
        Random random = new Random();
        for (int i = 1; i <= 6; i++) {
            int randValue = random.nextInt(strings.length());
            code += strings.substring(randValue, randValue + 1);
        }
        return code;
    }

    public static String getSubString(String value, int length) {
        if (value == null) {
            return "";
        }
        if (value.length() <= length) {
            return value;
        }
        return value.substring(0, length) + "...";
    }

    public static String getSubString(String value) {
        if (value == null) {
            return "";
        }
        if (value.length() > 2) {
            value = value.substring(1, value.length() - 1);
        }
        return value;
    }

    /*public static String getPinyinAbbr(String name) {
        String pinyin = "";
        for (int i = 0; i < name.length(); i++) {
            String[] result = PinyinHelper.toHanyuPinyinStringArray(name.charAt(i));
            if (result == null) {
                pinyin += name.charAt(i);
            } else {
                pinyin += result[0].charAt(0);
            }
        }
        return pinyin;
    }*/

    /**
     * 将null字符串转换为空串，方便HTML的显示
     *
     * @param sourceStr 待处理的字符串
     * @return String 处理的的字符串
     */
    public static String toVisualString(Object sourceStr) {
        if (sourceStr == null || "".equals(sourceStr.toString())) {
            return "";
        } else {
            return sourceStr.toString();
        }
    }

    /**
     * 判断文字是否为数字
     *
     * @param str
     * @return
     */
    public static boolean isNumeric(String str) {
        if (isEmpty(str)) {
            return false;
        }
        Pattern pattern = Pattern.compile("^-?\\d+(\\.\\d+)?$");
        return pattern.matcher(str).matches();
    }

    /**
     * 判断字符串转化为数字是否为0
     *
     * @param str
     * @return
     */
    public static boolean isZero(String str) {
        if (isNumeric(str)) {
            if (Double.valueOf(str) == 0L) {
                return true;
            }
        }
        return false;
    }

    /**
     * 格式化距离
     *
     * @param str
     * @return
     */
    public static String fmtDistance(String str) {
        Double doubleNum = Double.parseDouble(str);
        doubleNum = Math.floor(doubleNum);
        Double returnNum = doubleNum;
        String returnStr = "";
        if (doubleNum > 1000) {
            returnNum = doubleNum / 1000;
            returnStr = returnNum.toString().replace(".0", "") + "km";
        } else {
            returnStr = doubleNum.toString().replace(".0", "") + "m";
        }
        return returnStr;
    }

    public static String getProduct(String... strs) {
        Double result = 1d;
        for (String str : strs) {
            if (!isNumeric(str)) {
                return "0";
            }
            if (isZero(str)) {
                return "0";
            }
        }
        for (String str : strs) {
            result = result * Double.valueOf(str);
        }

        return String.format("%1$.2f", result);

    }

    /**
     * 规范获取不到的数据展现格式
     *
     * @param str
     * @return
     */
    public static String formatNullStr(String str) {
        if (!StringUtil.isNumeric(str)) {
            str = "0";
        }
        return str;
    }

   /* public static String formatFPNS(String fpns) {
        if (StringUtils.isEmpty(fpns)) {
            return "0.0";
        }
        return RegexUtil.replace(fpns, "(^\\d+(?:\\.\\d+)?)", new Replacement() {
            @Override
            public String format(Matcher m) {
                return String.format("%1$.1f", Double.parseDouble(m.group(1)));
            }
        });
    }

    public static String formatFPNS2(String fpns) {
        if (StringUtils.isEmpty(fpns)) {
            return "0.00";
        }
        return RegexUtil.replace(fpns, "(^\\d+(?:\\.\\d+)?)", new Replacement() {
            @Override
            public String format(Matcher m) {
                return String.format("%1$.2f", Double.parseDouble(m.group(1)));
            }
        });
    }
*/
    /*public static String district(String target, double max, double min) {

        double max_;
        double min_;
        if (max < min) {
            max_ = min;
            min_ = max;
        } else {
            max_ = max;
            min_ = min;
        }
        double result = Double.parseDouble(formatNullStr(target));
        if (result > max_) {
            result = max_;
        }
        if (result < min_) {
            result = min_;
        }
        return formatFPNS2(Double.toString(result));
    }*/

    public static String district(String target, int max, int min) {

        int max_;
        int min_;
        if (max < min) {
            max_ = min;
            min_ = max;
        } else {
            max_ = max;
            min_ = min;
        }
        int result = (int) Double.parseDouble(formatNullStr(target));
        if (result > max_) {
            result = max_;
        }
        if (result < min_) {
            result = min_;
        }
        return Integer.toString(result);
    }

    /*public static void main(String[] args) {
        //System.out.println(formatFPNS("1.452kg"));
        System.out.println(formatFPNS2("1.2161L"));
    }*/
    
    /**
     * 正则表达式：验证手机号
     */
    public static final String REGEX_MOBILE = "^((13[0-9])|(15[^4,\\D])|(14[0-9])|(18[0-9])|(17[0-9]))\\d{8}$";
    
    /**
     * 校验手机号
     * 
     * @param mobile
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isMobile(String mobile) {
        return Pattern.matches(REGEX_MOBILE, mobile);
    }
    
	public static String emptyIfNull(String string) {
		return (string == null || "null".equals(string)) ? "" : string;
	}

	// 产生length位数字字母的随机数
	public static String randomString(int length) {
		String[] CHARS = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
				"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l",
				"m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x",
				"y", "z" };
		char c[] = new char[length];
		for (int i = 0; i < length; i++) {
			int r = (int) (Math.random() * CHARS.length);
			c[i] = r % 2 == 0 ? CHARS[r].toUpperCase().charAt(0) : CHARS[r]
					.charAt(0);
		}
		return new String(c);
	}
}
