package com.tinygao.thread.unsafe.publish;

import com.google.common.base.Preconditions;
import com.tinygao.thread.unsafe.SingletonLazyInit;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

import static java.lang.Thread.sleep;

/**
 * Created by gsd on 2017/2/4.
 * 很难复现
 */
@Slf4j
public class HolderTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService es = Executors.newFixedThreadPool(2000);
        for(int k = 0; k<10000; k++) {
            HolderFactory hf = new HolderFactory();
            hf.holder = new Holder(0);
            es.submit(() -> {
                try {
                    TimeUnit.MILLISECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                hf.initialize();
            });
            for (int i = 0; i < 2000; i++) {
                es.submit(() -> {
                    try {
                        hf.holder.assertSanity();
                    } catch (AssertionError e) {
                        log.error(e.getMessage());
                    }
                });
            }
        }
        es.shutdown();
    }
}
