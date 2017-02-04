package com.tinygao.thread.unsafe;

import com.google.common.base.Preconditions;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by gsd on 2017/2/4.
 */
@Slf4j
public class SetValue {
    @Getter
    private String value = "";

    public void setValue(String value) {
        this.value = value;
    }

    public static void main(String[] args) {
        SetValue sv = new SetValue();
        ExecutorService es = Executors.newFixedThreadPool(1000);
        for(int i = 0; i < 1000000; i++) {
            es.submit(()->{
                String expect = Thread.currentThread().getName();
                sv.setValue(Thread.currentThread().getName());
                String result = sv.getValue();
                try {
                    Preconditions.checkState(result.equals(expect),
                                            "expect:%s, result:%s",
                                            expect,
                                            result);
                } catch (IllegalStateException e) {
                    log.error(e.getMessage());
                }
            });
        }
        es.shutdown();
    }
}


