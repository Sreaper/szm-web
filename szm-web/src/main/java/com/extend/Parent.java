package com.extend;

/**
 * @author songzhimao
 * @date 2018/5/15
 */
public abstract class Parent {
    protected void onStart(){}

    public void start() {
       onStart();
    }
}
