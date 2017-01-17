package com.tinygao.thread.runio;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import lombok.extern.slf4j.Slf4j;

import com.google.common.base.Stopwatch;
import com.google.common.io.Files;
import com.google.common.util.concurrent.RateLimiter;


/**
 * Created by gsd on 2017/1/15.
 */
@Slf4j
public class SingleRunIO {

	static boolean stop = false;
	static double rate = 100.0;
	
    public static void main(String[] args) throws IOException {
    	
    	makeHook();
    	
    	ScheduledExecutorService s = Executors.newScheduledThreadPool(1);
    	s.schedule(()->{stop=true;}, 
    			Integer.parseInt(args[1]), 
    			TimeUnit.SECONDS);
    	
    	rate = Double.parseDouble(args[2]);
    	runIO(Integer.parseInt(args[0]));
    	s.shutdown();
    }

	private static void makeHook() {
		Stopwatch st = Stopwatch.createStarted();
    	Runtime.getRuntime().addShutdownHook(new Thread() {
    		public void run() {
    			log.info("waster time : {} s" ,
    					st.elapsed(TimeUnit.SECONDS)
    					);
    			
    			st.stop();
    		}
    	});
	}
    
    private static String getTestStr(int byteLength) {
    	StringBuffer sb = new StringBuffer(byteLength);
    	for(int i = 0; i < byteLength; i++) {
    		sb.append("f");
    	}
    	return sb.toString();
    }
    
    
    public static void runIO(int size) throws IOException {
    	log.info("start ...");
    	File file = initFile();
    	
    	String testStr = getTestStr(size);
    	log.info("gen test str size {} bytes", testStr.length());
    	
    	RateLimiter rateLimiter  = RateLimiter.create(rate);
    	int acquireNum = testStr.length()/1000000;
    	log.info("" + acquireNum);
    	
    	while(!stop) {
    		rateLimiter.acquire(acquireNum);
    		Files.append(testStr, 
    				file, 
    				Charset.forName("utf-8"));
    	}
    	log.info("end file size {} KB",
    			file.length()/1024);
    }
    
    public static void runIO(int size, long maxSize) throws IOException {
    	log.info("start ...");
    	File file = initFile();
    	
    	String testStr = getTestStr(size);
    	log.info("gen test str size {} bytes", testStr.length());
    	
    	RateLimiter rateLimiter  = RateLimiter.create(70.0);
    	int acquireNum = testStr.length()/1000000;
    	log.info("" + acquireNum);
    	
    	Stopwatch sw = Stopwatch.createStarted();
    	
    	
    	while(file.length() < maxSize) {
    		rateLimiter.acquire(acquireNum);
    		Files.append(testStr, 
    				file, 
    				Charset.forName("utf-8"));
    	}
    	log.info("end write file size {} KB, water time : {} s",
    			file.length()/1024, sw.elapsed(TimeUnit.SECONDS));
        sw.stop();
    }

	private static File initFile() throws IOException {
		File file = new File("fileTest.log");
    	file.createNewFile();
    	Files.write("".getBytes(), file);
		return file;
	}
}
