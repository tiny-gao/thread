package com.tinygao.thread.safe;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by gsd on 2017/1/27.
 */
public class HolderDemo {
    public Holder holder;

    public void initialize()  {
        holder = new Holder(42);
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService es = Executors.newFixedThreadPool(3);
        HolderDemo hd = new HolderDemo();

        for(int i = 0; i < 10000000; i++) {
            es.submit(()->{
                hd.holder = null;
            });
            es.submit(()->{
                hd.initialize();
            });
            es.submit(()->{
                hd.holder.assertSanity();
            });

        }
        es.shutdown();
        es.awaitTermination(1, TimeUnit.DAYS);
    }
}
