package com.tinygao.thread.safe;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Slf4j
public class FinalClass {
    private final Integer lastNumber;
    private final Integer currentNumber;

    public FinalClass(Integer lastNumber, Integer currentNumber) {
        this.lastNumber = lastNumber;
        this.currentNumber = currentNumber;
    }

    public Integer getCurrentNumber(Integer i) {
        if(lastNumber == null || !lastNumber.equals(i)) {
            return null;
        }
        else {
            return 1;
        }
    }

    public static void main(String[] args) {
        FinalClass finalclass = new FinalClass(1, 1);

        ExecutorService es = Executors.newFixedThreadPool(2);
        es.submit(()->{
            log.info("get first {}", finalclass.getCurrentNumber(1));
        });

        es.submit(()->{
            log.info("get seconde {}", finalclass.getCurrentNumber(1));
        });
        es.shutdown();
    }
}
