package com.tinygao.thread.runcpu;

import com.google.common.base.*;
import com.google.common.primitives.Booleans;
import com.google.common.util.concurrent.RateLimiter;
import lombok.extern.slf4j.Slf4j;

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

        while(time < 1000000000000L) {
            time++;
            if(Boolean.parseBoolean(args[1])) {
                rateLimiter.acquire(1);
            }
        }
    }

}
