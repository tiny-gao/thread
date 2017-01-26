package com.tinygao.thread.safe;

import com.google.common.collect.Maps;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by gsd on 2017/1/26.
 */
@Slf4j
public class UnSafe {
    private int num;
    private Map<String, String> map = Maps.newConcurrentMap();

    public int getNumAdd() {
        return num++;
    }

    public String getMapValue() {
        if(!map.containsKey("tinygao")) {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            map.put("tinygao", Thread.currentThread().getName());
        }
        return map.get("tinygao");
    }

    public static void main(String[] args) {
        UnSafe unSafe = new UnSafe();
        ExecutorService es = Executors.newFixedThreadPool(
                2,
               new ThreadFactoryBuilder().setNameFormat("map-%d").build());

        es.submit(()->{
            log.info("map {}",unSafe.getMapValue());
        });
        es.submit(()->{
            log.info("map {}",unSafe.getMapValue());
        });
    }
}
