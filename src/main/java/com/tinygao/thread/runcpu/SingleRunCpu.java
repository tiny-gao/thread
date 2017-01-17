package com.tinygao.thread.runcpu;

import java.util.concurrent.TimeUnit;

import lombok.extern.slf4j.Slf4j;

import com.google.common.base.Preconditions;
import com.google.common.base.Stopwatch;
import com.google.common.util.concurrent.RateLimiter;

/**
 * Created by gsd on 2017/1/15.
 */
@Slf4j
public class SingleRunCpu {
    public static  long time = 0;
    public static void main(String[] args) {
        Preconditions.checkNotNull(args, "输入不能为空");
        Preconditions.checkState(Double.parseDouble(args[0]) > 0,
                "输入要是double类型的，且不能小于0");
        Preconditions.checkState(args.length==2, "有两个参数");
        final RateLimiter rateLimiter = RateLimiter.create(Double.parseDouble(args[0]));

        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                log.info("time : "  + time);
            }
        });

        runCpu(1_000_000_000_000L,Boolean.parseBoolean(args[1]), rateLimiter);
    }
    
	public static void runCpu(final long maxCount, final boolean isRateLimit, final RateLimiter rateLimiter) {
		Stopwatch sw = Stopwatch.createStarted();
        
		while(time < maxCount) {
            time++;
            if(isRateLimit) {
                rateLimiter.acquire(1);
            }
        }
		log.info("runCpu waster time : {} s" ,
                sw.elapsed(TimeUnit.SECONDS)
        );

        sw.stop();
	}

}
