package com.concurrent.volitile;

/**
 * @author songzhimao
 * @date 2018/4/27
 */
public class Tee {
    public static void main(String[] args) {
        try {
            RunThread thread = new RunThread();
            thread.start();
            Thread.sleep(1000);
//            thread.setRunning(false);
            Sub sub = thread.getSub();
            thread.setRunning(false);
            sub.setFlag(false);
            thread.setSub(sub);
            System.out.println("已经赋值为false");
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}



class RunThread extends Thread {

    private volatile boolean isRunning = true;
    private Sub sub =new Sub();


    public Sub getSub() {
        return sub;
    }

    public void setSub(Sub sub) {
        this.sub = sub;
    }

    public boolean isRunning() {
        return isRunning;
    }

    public void setRunning(boolean isRunning) {
        this.isRunning = isRunning;
    }

    @Override
    public void run() {
        System.out.println("进入run了");

        while (isRunning) {
        }
        while (sub.isFlag()) {
        }



        System.out.println("线程被停止了！");
    }

}

class Sub {
    private boolean flag = true;

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
}