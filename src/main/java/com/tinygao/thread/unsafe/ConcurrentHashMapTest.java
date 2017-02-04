package com.tinygao.thread.unsafe;

import com.google.common.base.Preconditions;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.concurrent.*;

/**
 * Created by gsd on 2017/2/4.
 */
@Slf4j
public class ConcurrentHashMapTest {
    private static Map<String, String> map = new ConcurrentHashMap<>();
    private static int a = 0;

    public static void setValue(String key, String value) {
        if(!map.containsKey(key)) {
            a++;
            map.put(key, value);
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService es = Executors.newFixedThreadPool(2);

        for(int i = 0; i < 1000; i++) {
            Future<Void> thread1 = es.submit(()->{
                ConcurrentHashMapTest.setValue("key","value1");
                return null;
            });
            Future<Void>  thread2 = es.submit(()->{
                ConcurrentHashMapTest.setValue("key","value2");
                return null;
            });
            try {
                thread2.get();
                thread1.get();
                Preconditions.checkState(a <= 1, "map ： %s",map);
            } catch (IllegalStateException ex) {
                log.error(ex.getMessage());
            }
            a = 0;
            map = new ConcurrentHashMap<>();
        }
        es.shutdown();
    }
}
