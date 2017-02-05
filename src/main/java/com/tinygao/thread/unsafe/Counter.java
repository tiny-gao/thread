package com.tinygao.thread.unsafe;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * Created by gsd on 2017/2/5.
 */
@Slf4j
public class Counter {
    public static volatile int count = 0;

    public static void inc() {
        count++;
    }

    public static void main(String[] args) {

        ExecutorService es = Executors.newFixedThreadPool(1000);
        for(int i = 0; i < 1000; i++) {
            es.submit(Counter::inc);
        }
        es.shutdown();
        log.info("count:{}", count);
    }
}
