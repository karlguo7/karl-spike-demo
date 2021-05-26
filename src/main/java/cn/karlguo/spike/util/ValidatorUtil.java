package cn.karlguo.spike.util;

import org.springframework.util.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * @Classname ValidatorUtil
 * @Date 2021/5/26
 * @author karlguo
 * @Description 验证工具类
 */
public class ValidatorUtil {
    private static final Pattern mobile_pattern = Pattern.compile("[1]([3-9])[0-9]{9}$");

    /*
     * @return boolean
     * @Description 验证手机号码格式，正确返回true，不正确返回false
     */
    public static boolean isMobile(String moblie) {
        if (StringUtils.isEmpty(moblie)) {
            return false;
        }
        Matcher matcher = mobile_pattern.matcher(moblie);
        return matcher.matches();
    }
}
