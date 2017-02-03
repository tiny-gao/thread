package com.tinygao.thread.safe;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by gsd on 2017/1/27.
 */
@Slf4j
public class Unfinal {
    private Integer lastNumber;
    private Integer currentNumber;

    public void setLastNumber(Integer i) {
        this.lastNumber = i;
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
        Unfinal unfinal = new Unfinal();

        ExecutorService es = Executors.newFixedThreadPool(2);
        es.submit(()->{
            unfinal.setLastNumber(1);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //预期打印是1，看看实际打印多少?
            log.info("get first {}", unfinal.getCurrentNumber(1));
        });

        es.submit(()->{
            unfinal.setLastNumber(2);
            //预期打印是null，看看实际打印多少?
            log.info("get seconde {}", unfinal.getCurrentNumber(1));
        });
        es.shutdown();
    }
}
