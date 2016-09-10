package com;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import java.io.StringWriter;

/**
 * Created with IntelliJ IDEA.
 * User: songzhimao
 * Date: 15-7-8
 * Time: 下午5:36
 * To change this template use File | Settings | File Templates.
 */
public class velocityTest {
    public static  void main(String args[]){
        try {
            VelocityEngine ve = new VelocityEngine();
            ve.init();
            Template t = ve.getTemplate("hellosite.vm");
            VelocityContext context = new VelocityContext();
            context.put("name", "aaaaa");
            context.put("site", "htt://www.baidu1.com");
            StringWriter writer = new StringWriter();
            t.merge(context, writer);

            System.out.println(writer.toString());

            System.out.println("成功");
            String bs =  Integer.toBinaryString(33);
            bs.lastIndexOf("1");
        } catch (Exception e) {
            System.out.println(String.format("失败，%s", e.getMessage()));
            System.out.println(e.fillInStackTrace());
        }
    }
}
