package com.tinygao.thread.unsafe;

import com.google.common.base.Preconditions;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.math.BigInteger;
import java.sql.Time;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by gsd on 2017/2/4.
 */
@Slf4j
public class AddAdd {
    @Getter
    private int count = 0;

    public int service() {
        /*try {
            TimeUnit.MILLISECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        return ++count;
    }

    public static void main(String[] args) {
        AddAdd add = new AddAdd();
        ExecutorService es = Executors.newFixedThreadPool(1000);
        for(int i = 0; i < 1000000; i++) {
            es.submit(()->{
                int tmp = add.getCount();
                int count = add.service();
                try {
                    Preconditions.checkState(tmp == count - 1, "tmp:%s, count:%s", tmp, count);
                } catch (IllegalStateException e) {
                    log.error(e.getMessage());
                }
            });
        }
        es.shutdown();
    }
}
