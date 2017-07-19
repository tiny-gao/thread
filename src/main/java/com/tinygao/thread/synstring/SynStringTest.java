package com.tinygao.thread.synstring;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.function.BiConsumer;

import com.google.common.base.Stopwatch;
import com.google.common.util.concurrent.ThreadFactoryBuilder;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SynStringTest {

	private final static SynString synStr = new SynString();
	private final static Stopwatch sw = Stopwatch.createStarted();
	private static BiConsumer<SynString, String> function = (x, y)->{
		synchronized (x.getStringLock(y)) {
    		log.info("Get lock: {}", y);
    		try {
				TimeUnit.SECONDS.sleep(3);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	};
	public static void main(String[] args) throws InterruptedException {
        final ExecutorService executorService = Executors.newFixedThreadPool(
                4,
                new ThreadFactoryBuilder().setNameFormat("SynString-%d").build()
        );
        
        executorService.submit(()->{
        	doTask("test");
        });
        executorService.submit(()->{
        	doTask("test");
        });
        executorService.submit(()->{
        	doTask("test1");
        });
        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.DAYS);
        sw.stop();
    }
	
	private static void doTask(String lockStr) {
		function.accept(synStr, lockStr);
		log.info("Do get lockStr successed waste time elapsed : {} ms", sw.elapsed(TimeUnit.MILLISECONDS));
	}
}
