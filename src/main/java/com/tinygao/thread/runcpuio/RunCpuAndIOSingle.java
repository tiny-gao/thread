package com.tinygao.thread.runcpuio;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import lombok.extern.slf4j.Slf4j;

import com.google.common.base.Stopwatch;
import com.tinygao.thread.runcpu.SingleRunCpu;
import com.tinygao.thread.runio.SingleRunIO;

@Slf4j
public class RunCpuAndIOSingle {

	// 最大计算到多少未知，每一次IO写入的字节数， 
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		makehood();
		
		log.info("start run cpu");
        SingleRunCpu.runCpu(Long.parseLong(args[0]),false, null);
        
        log.info("start run io");
        SingleRunIO.runIO(Integer.parseInt(args[1]), 1_000_000_000L);
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
