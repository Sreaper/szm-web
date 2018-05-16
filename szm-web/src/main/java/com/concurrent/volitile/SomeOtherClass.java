package com.concurrent.volitile;

/**
 * @author songzhimao
 * @date 2018/4/27
 */
public class SomeOtherClass {
    public volatile Flooble theFlooble;

    public void initInBackground() {
        // do lots of stuff
        theFlooble = new Flooble();  // this is the only write to theFlooble
    }
}
