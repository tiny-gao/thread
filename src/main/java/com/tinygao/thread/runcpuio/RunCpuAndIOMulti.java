package com.tinygao.thread.runcpuio;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

import lombok.extern.slf4j.Slf4j;

import com.google.common.base.Stopwatch;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.tinygao.thread.runcpu.SingleRunCpu;
import com.tinygao.thread.runio.SingleRunIO;

@Slf4j
public class RunCpuAndIOMulti {

	 public static ThreadFactory threadsNamed(String nameFormat){
	        return new ThreadFactoryBuilder().setNameFormat(nameFormat).setDaemon(false).build();
	    }
	 // 最大计算到多少未知，每一次IO写入的字节数， 
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		makehood();
		
		ExecutorService es = Executors.newFixedThreadPool(2, 
					threadsNamed("runIO-%d"));
		
		es.submit(()->{
			 log.info("start run io");
		        try {
					SingleRunIO.runIO(Integer.parseInt(args[1]), 1_000_000_000L);
				} catch (Exception e) {
					e.printStackTrace();
				}
		});
		
		log.info("start run cpu");
        SingleRunCpu.runCpu(Long.parseLong(args[0]),false, null);
        
        es.shutdown();
	}

		private static void makehood() {
			Stopwatch sw = Stopwatch.createStarted();
	        Runtime.getRuntime().addShutdownHook(new Thread() {
	            public void run() {
	                log.info("waster time : {} s" ,
	                        sw.elapsed(TimeUnit.SECONDS)
	                );

	                sw.stop();
	            }
	        });
		}
}
