package cn.karlguo.spike.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/*
 * @Classname CookieUtil
 * @Date 2021/5/26
 * @author karlguo
 * @Description Cookie工具类
 */
public final class CookieUtil {

    /**
     * @return java.lang.String
     * @Description 得到Cookie得值，不编码
     */
    public static String getCookieValue(HttpServletRequest request, String cookieName) {
        return getCookieValue(request, cookieName, false);
    }

    public static String getCookieValue(HttpServletRequest request, String cookieName, boolean isDecoder) {
        Cookie[] cookieList = request.getCookies();
        if (cookieList == null || cookieName == null) {
            return null;
        }
        String retValue = null;
        try {
            for (int i = 0; i < cookieList.length; i++) {
                if (isDecoder) {
                    retValue = URLDecoder.decode(cookieList[i].getValue(), "UTF-8");
                } else {
                    retValue = cookieList[i].getValue();
                }
                break;
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return retValue;
    }

    public static String getCookieValue(HttpServletRequest request, String cookieName, String encodeString) {
        Cookie[] cookieList = request.getCookies();
        if (cookieList == null || cookieName == null) {
            return null;
        }
        String retValue = null;
        try {
            for (int i = 0; i < cookieList.length; i++) {
                if (cookieList[i].getName().equals(cookieName)) {
                    retValue = URLDecoder.decode(cookieList[i].getValue(), encodeString);
                    break;
                }
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return retValue;
    }

    /**
     * [request, response, cookieName, cookieValue]
     *
     * @return void
     * @Description 设置Cookie的值 不设置生效时间默认浏览器关闭即失效，也不编码
     */
    public static void setCookie(HttpServletRequest request, HttpServletResponse response, String cookieName, String cookieValue) {
        setCookie(request, response, cookieName, cookieValue, -1);
    }

    /**
     * [request, response, cookieName, cookieValue]
     *
     * @return void
     * @Description 设置Cookie的值 在指定时间内生效，但不编码
     */
    public static void setCookie(HttpServletRequest request, HttpServletResponse response, String cookieName, String cookieValue, int cookieMaxage) {
        setCookie(request, response, cookieName, cookieValue, cookieMaxage, false);
    }

    /**
     * [request, response, cookieName, cookieValue]
     *
     * @return void
     * @Description 设置Cookie的值 不设置生效时间默认浏览器关闭即失效，但编码
     */
    public static void setCookie(HttpServletRequest request, HttpServletResponse response, String cookieName, String cookieValue, boolean isEncode) {
        setCookie(request, response, cookieName, cookieValue, -1, isEncode);
    }

    /**
     * [request, response, cookieName, cookieValue]
     *
     * @return void
     * @Description 设置Cookie的值 不设置生效时间默认浏览器关闭即失效，但编码
     */
    public static void setCookie(HttpServletRequest request, HttpServletResponse response, String cookieName, String cookieValue, int cookieMaxage, boolean isEncode) {
        doSetCookie(request, response, cookieName, cookieValue, cookieMaxage, isEncode);
    }

    /**
     * [request, response, cookieName, cookieValue]
     *
     * @return void
     * @Description 设置Cookie的值 在指定时间内生效，指定编码
     */
    public static void setCookie(HttpServletRequest request, HttpServletResponse response, String cookieName, String cookieValue, int cookieMaxage, String encodeString) {
        doSetCookie(request, response, cookieName, cookieValue, cookieMaxage, encodeString);
    }

    /**
     * [request, response, cookieName, cookieValue]
     *
     * @return void
     * @Description 删除cookie带cookie域名
     */
    public static void deleteCookie(HttpServletRequest request, HttpServletResponse response, String cookieName) {
        doSetCookie(request, response, cookieName, "", -1, false);
    }

    /**
     * @return void
     * @Description 设置Cookie的值 在指定时间内生效
     */
    public static final void doSetCookie(HttpServletRequest request, HttpServletResponse response, String cookieName, String cookieValue, int cookieMaxage, boolean isEncode) {
        try {
            if (cookieValue == null) {
                cookieValue = "";
            } else if (isEncode) {
                cookieValue = URLEncoder.encode(cookieValue, "UTF-8");
            }
            Cookie cookie = new Cookie(cookieName, cookieValue);
            if (cookieMaxage > 0) {
                cookie.setMaxAge(cookieMaxage);
            }
            if (null != request) {//设置域名的cookie
                String domainName = getDomainName(request);
                System.out.println(domainName);
                if (!"localhost".equals(domainName)) {
                    cookie.setDomain(domainName);
                }
            }
            cookie.setPath("/");
            response.addCookie(cookie);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @return void
     * @Description 设置Cookie的值 在指定时间内生效
     */
    public static final void doSetCookie(HttpServletRequest request, HttpServletResponse response, String cookieName, String cookieValue, int cookieMaxage, String encodeString) {
        try {
            if (cookieValue == null) {
                cookieValue = "";
            } else {
                cookieValue = URLEncoder.encode(cookieValue, encodeString);
            }
            Cookie cookie = new Cookie(cookieName, cookieValue);
            if (cookieMaxage > 0) {
                cookie.setMaxAge(cookieMaxage);
            }
            if (null != request) {//设置域名的cookie
                String domainName = getDomainName(request);
                System.out.println(domainName);
                if (!"localhost".equals(domainName)) {
                    cookie.setDomain(domainName);
                }
            }
            cookie.setPath("/");
            response.addCookie(cookie);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * [request]
     *
     * @return java.lang.String
     * @Description 得到cookie域名
     */
    private static final String getDomainName(HttpServletRequest request) {
        String domainName = null;
        //通过request对象获取访问但url地址
        String serverName = request.getRequestURL().toString();
        if(serverName == null || "".equals(serverName)){
            domainName = "";
        }else {
            //将url地下转换为小写
            serverName = serverName.toLowerCase();
            //如果url地址是以http://开头 将http://截取
            if(serverName.startsWith("http://")){
                serverName = serverName.substring(7);
            }
            int end = serverName.length();
            //判断url地址是否包含"/"
            if(serverName.contains("/")){
                //得到第一个"/"出现但位置
                end = serverName.indexOf("/");
            }

            //截取
            serverName = serverName.substring(0,end);
            //根据"."进行分割
            final String[] domains = serverName.split("\\.");
            int len = domains.length;
            if(len > 3){
                domainName = domains[len - 3] + "." + domains[len - 2] + "." + domains[len - 1];
            }else if(len <=3 && len >1){
                domainName = domains[len - 2] + "." + domains[len - 1];
            }else {
                domainName = serverName;
            }
        }

        if(domainName!=null&&domainName.indexOf(":")>0){
            String[] ary = domainName.split("\\:");
            domainName = ary[0];
        }
        return domainName;
    }
}
