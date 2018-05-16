package com.extend;

/**
 * @author songzhimao
 * @date 2018/5/15
 */
public class Sub extends Parent implements Interface1 {
    @Override
    public void onStart() {
        System.out.println("sub");
    }

    public static void main(String[] args) {
        Interface1 sub = new Sub();

        ((Parent) sub).start();
    }
}
