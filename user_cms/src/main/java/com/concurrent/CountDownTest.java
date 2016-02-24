package com.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by songzhimao on 16-2-2.
 */
public class CountDownTest {
    public static void main(String[] args) {
        final CountDownLatch latch = new CountDownLatch(10);
        final ExecutorService executorService = Executors.newFixedThreadPool(10);
        List<User> list = new ArrayList<User>();
        int i = 0;
        while (i <= 100) {
            User user = new User();
            user.setId(i);
            user.setName("szm" + i);
            list.add(user);
            i++;
        }
        System.out.println("开始");
        for ( User user : list) {
            final User user1 = user;
            Runnable runnable = new Runnable() {
                public void run() {
                    try {
                        System.out.println(user1.toString());
                    } finally {
                        latch.countDown();
                    }
                }
            };
            executorService.submit(runnable);
        }
        try {
            latch.await();
            executorService.shutdown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}

class User {
    private long id;
    private String name;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("User{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append('}');
        return sb.toString();
    }
}

