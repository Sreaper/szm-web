package com.concurrent.lock;

/**
 * @author songzhimao
 * @date 2018/2/19
 */
public class Main {

    public static void main(String[] args) {
        PricesInfo pricesInfo=new PricesInfo();
        Reader readers[] = new Reader[5];
        Thread threadsReader[] = new Thread[5];
        for (int i = 0; i < 5; i++) {
            readers[i] = new Reader(pricesInfo);
            threadsReader[i] = new Thread(readers[i]);
        }

        Writer writer=new Writer(pricesInfo);
        Thread threadWriter=new Thread(writer);

        for (int i=0; i<5; i++){
            threadsReader[i].start();
        }
        threadWriter.start();

    }
}
