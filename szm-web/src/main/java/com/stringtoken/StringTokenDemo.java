package com.stringtoken;

import java.util.StringTokenizer;

/**
 * string token的分组demo
 *
 * @author songzhimao
 * @date 2016/11/17
 */
public class StringTokenDemo {
    public static void main(String[] args) {
        String original = "The Java platform is the ideal platform for network==computing";
        String original1 = "The=Java=platform=is=the=ideal=platform=for=network==computing";
        StringTokenizer s = new StringTokenizer(original1, " =", true);
        while (s.hasMoreTokens()) {
            System.out.println(s.nextToken());
        }
    }
}
