package com.common;

/**
 * Created with IntelliJ IDEA.
 * User: songzhimao
 * Date: 15-8-28
 * Time: 下午3:05
 * To change this template use File | Settings | File Templates.
 */
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: songzhimao
 * Date: 14-12-17
 * Time: 下午5:09
 * To change this template use File | Settings | File Templates.
 */
public class CookieUtil {
    public static ThreadLocal<String>  cookieThreadLocal = new ThreadLocal<String>(){
        protected synchronized String initialValue() {
            return "";
        }
    };
    public static String getCookieValueByName(HttpServletRequest request,
                                              String name) {

        Cookie cookie = getCookieByName(request, name);
        if (cookie != null && !(cookie.getValue().length() < 1)) {
            return cookie.getValue();
        }

        return null;
    }

    public static Cookie getCookieByName(HttpServletRequest request, String name) {
        Map<String, Cookie> cookieMap = readCookieMap(request);
        if (cookieMap.containsKey(name)) {
            Cookie cookie = (Cookie) cookieMap.get(name);
            return cookie;
        }

        return null;
    }

    private static Map<String, Cookie> readCookieMap(HttpServletRequest request) {
        Map<String, Cookie> cookieMap = new HashMap<String, Cookie>();
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                cookieMap.put(cookie.getName(), cookie);
            }
        }

        return cookieMap;
    }

    public static String getCookietoString(HttpServletRequest request){
        StringBuilder str = new StringBuilder();
        Cookie[] cookies = request.getCookies();
        for(Cookie cookie : cookies){
            str.append(cookie.getName()).append("=").append(cookie.getValue()).append(";");
        }
        return str.toString();
    }
    public static void setCookie(String key, String value, String domain,String path,
                                 HttpServletResponse response) {
        Cookie cookie = new Cookie(key, value);
        cookie.setDomain(domain);
        cookie.setPath(path);
        addCookie(response,cookie);
    }

    public static void clearCookie(String key, String value, String domain,String path,
                                   HttpServletResponse response) {
        Cookie cookie = new Cookie(key, value);
        cookie.setDomain(domain);
        cookie.setPath(path);
        addCookie(response,cookie);
    }

    // TODO 重构所有用到此方法的地方，提高代码重用性
    public static void addCookie(HttpServletResponse response, Cookie cookie) {
        cookie.setValue(filterCookieValue(cookie.getValue()));
        response.addCookie(cookie);
    }

    public static String filterCookieValue(String value) {
        if (value != null) {
            value = value.replace("\r", "");
            value = value.replace("\n", "");
            value = value.replace("%0D", "");
            value = value.replace("%0A", "");
            value = value.replace("%0d", "");
            value = value.replace("%0a", "");
        }
        return value;
    }

    /**
     * 对于cookie中含有特殊字符('\t', ' ', '\"', '(', ')', ',', ':', ';', '<', '=', '>', '?', '@', '[', '\\', ']', '{', '}')从header中直接取cookie的方式
     * @param name
     * @param request
     * @return
     */
    public static String getCookieValue(String name, HttpServletRequest request){
        try {
            if(name == null)
                return "";

            String allCookieStr = request.getHeader("Cookie");
            if (allCookieStr != null) {
                name = name+"=";
                int begin = allCookieStr.indexOf(name);
                if (begin >= 0) {
                    int end = allCookieStr.indexOf(";", begin);
                    if (end >= 0) {
                        return allCookieStr.substring(begin + name.length(), end);
                    }else{
                        return  "";
                        //return allCookieStr.substring(0 + name.length());
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }


}
