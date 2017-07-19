package com.tinygao.thread.synstring;
import java.util.concurrent.ConcurrentMap;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SynString {

	private static ConcurrentMap<String,Object> parMap =  Maps.newConcurrentMap();
	
	public  Object getStringLock(String string) {
		Object lock = this;
		if(parMap != null) {
			Object newLock = new Object();
			lock = parMap.putIfAbsent(string, newLock);
			if(lock == null) {
				lock = newLock;
			}
		}
		return lock;
	}
	
	public static void main(String[] args) {
		Object result = parMap.putIfAbsent("h", "g");
		log.info("Get result: {}", result);
	}
}
