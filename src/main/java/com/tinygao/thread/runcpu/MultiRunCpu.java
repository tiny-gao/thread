package com.tinygao.thread.runcpu;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import lombok.extern.slf4j.Slf4j;

import com.google.common.base.Preconditions;
import com.google.common.base.Stopwatch;

/**
 * Created by gsd on 2017/1/15.
 */
@Slf4j
public class MultiRunCpu {
    public static long maxCount = 1L;
    public static int threadNum = 1;
    public static void main(String[] args) throws InterruptedException {
        Preconditions.checkNotNull(args, "输入不能为空");

        Preconditions.checkState((maxCount = Long.parseLong(args[0])) > 0,
                "输入的运行的，且不能小于0");
        Preconditions.checkState((threadNum = Integer.parseInt(args[1])) > 0,
                "输入的线程数必须是大于0的");

        Stopwatch sw = Stopwatch.createStarted();
        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                log.info("waster time : {} s" ,
                        sw.elapsed(TimeUnit.SECONDS)
                );

                sw.stop();
            }
        });

        ExecutorService runners  = Executors.newFixedThreadPool(threadNum);
        for(int i = 0 ;i < threadNum; i++) {
            runners.submit(MultiRunCpu::run);
        }
        runners.shutdown();
        runners.awaitTermination(1,TimeUnit.DAYS);
    }

    private static void run() {

        long threadCount = (maxCount/threadNum) + 1;
        long count = 0;
        while(count < threadCount) {
            count++;
        }
    }
}
