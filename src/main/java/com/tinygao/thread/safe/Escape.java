package com.tinygao.thread.safe;

import com.sun.org.apache.bcel.internal.classfile.InnerClass;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * Created by gsd on 2017/1/26.
 */
@Slf4j
public class Escape{
    private AtomicInteger value = new AtomicInteger(1);
    public Escape(){
        new InnerClass();
    }
    private class InnerClass {
        public InnerClass()  {
            ExecutorService es = Executors.newFixedThreadPool(2);
            es.submit(()->{
                Escape.this.value.addAndGet(ThreadLocalRandom.current().nextInt(1,4));
                log.info("value:{}", Escape.this.value.get());});
            es.shutdown();
            try {
                es.awaitTermination(10, SECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public static void main(String[] args) {
        log.info("escape value {}",  new Escape().value);
        log.info("escape value {}",  new Escape().value);
    }
}