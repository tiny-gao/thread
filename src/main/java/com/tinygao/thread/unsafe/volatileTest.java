package com.tinygao.thread.unsafe;

import com.google.common.base.Preconditions;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;

/**
 * Created by gsd on 2017/2/5.
 */
@Slf4j
public class volatileTest {
    public static boolean asleep = false;
    public static int num = 0;

    public static void service() {
        while(!asleep) {
            log.info("num:{}",num);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        asleep = false;
        num = 1;
        /*for(int i = 0 ; i < 100000; i++) {
            asleep = false;
            num = 0;
            ExecutorService es = Executors.newFixedThreadPool(2);
            es.submit(volatileTest::service);
            num = 42;
            asleep = true;
            log.info("sleep ~~~~~~");
            es.shutdown();
        }*/
    }
}
