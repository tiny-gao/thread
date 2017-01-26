package com.tinygao.thread.synchronizedpack;

import com.google.common.util.concurrent.MoreExecutors;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;



/**
 * Created by gsd on 2017/1/25.
 */
@Slf4j
public class SynchronizedExtendClass {
    public static void main(String[] args) {
        final ExecutorService executorService = Executors.newFixedThreadPool(
                2,
                new ThreadFactoryBuilder().setNameFormat("run-%d").build()
        );
        Animal dog = new Dog();
        executorService.submit(dog::doSomething);
        executorService.submit(dog::anotherSomething);

        executorService.shutdown();
    }

    static class Animal{
        public  synchronized  void doSomething()  {
            try {
                log.info("run Animal {} start", Thread.currentThread().getName() );
                TimeUnit.SECONDS.sleep(2);
                log.info("run Animal {} end", Thread.currentThread().getName() );
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        public  synchronized  void anotherSomething()  {
            try {
                log.info("run Animal anotherSomething {} start", Thread.currentThread().getName() );
                TimeUnit.SECONDS.sleep(2);
                log.info("run Animal anotherSomething {} end", Thread.currentThread().getName() );
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class Dog extends  Animal {
        public  synchronized  void doSomething()  {
            try {
                log.info("run Dog {} start", Thread.currentThread().getName() );
                TimeUnit.SECONDS.sleep(2);
                log.info("run Dog {} end", Thread.currentThread().getName() );
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}

