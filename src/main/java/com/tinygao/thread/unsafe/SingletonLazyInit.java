package com.tinygao.thread.unsafe;

import com.google.common.base.Preconditions;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

import static java.lang.Thread.sleep;
import static sun.swing.SwingUtilities2.submit;

/**
 * Created by gsd on 2017/2/4.
 */
@Slf4j
public class SingletonLazyInit {
    private static SingletonLazyInit instance = null;

    public static SingletonLazyInit getInstance() throws InterruptedException {
        if(instance == null) {
            //TimeUnit.NANOSECONDS.sleep(1);
            instance = new SingletonLazyInit();
        }
        return instance;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService es = Executors.newFixedThreadPool(2);
        for(int i = 0; i < 200; i++) {
            Future<SingletonLazyInit> obj1 = es.submit(()->{
                return SingletonLazyInit.getInstance();
            });
            Future<SingletonLazyInit> obj2 = es.submit(()->{
                return SingletonLazyInit.getInstance();
            });
            try {
                Preconditions.checkState(obj1.get() == obj2.get(), "error ----> obj1:%s, ojb2:%s",
                        obj1.get().hashCode(),
                        obj2.get().hashCode());
            } catch (IllegalStateException ex) {
                log.error(ex.getMessage());
            }
            /*log.info("obj1:{}, ojb2:{}",
                    obj1.get().hashCode(),
                    obj2.get().hashCode());*/

            instance = null;
        }
        es.shutdown();
    }
}
