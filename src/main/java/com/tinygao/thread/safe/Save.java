package com.tinygao.thread.safe;

import com.google.common.collect.Maps;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by gsd on 2017/1/26.
 */
public class Save {
    private ThreadLocal<Integer> num = new ThreadLocal<>();
    private AtomicInteger num2 = new AtomicInteger(0);
    private Map<String, String> map = Maps.newConcurrentMap();

    public int getNumAdd() {
        num.set(num.get()+1);
        return num.get();
    }

    public int getNumAdd2() {
        return num2.incrementAndGet();
    }

    public void safeMap() {
        map.putIfAbsent("tinygao", Thread.currentThread().getName());
    }
}
