package com.tinygao.thread.unsafe;

import com.google.common.base.Preconditions;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.math.BigInteger;
import java.sql.Time;
import java.util.Arrays;
import java.util.concurrent.*;

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

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        AddAdd add = new AddAdd();
        ExecutorService es = Executors.newFixedThreadPool(2);
        for(int i = 0; i < 100000; i++) {
            Future<int[]> result1 = es.submit(()->{
                int tmp = add.getCount();
                int count = add.service();
                return new int[]{tmp,count};
            });
            Future<int[]> result2 = es.submit(()->{
                int tmp = add.getCount();
                int count = add.service();
                return new int[]{tmp,count};
            });
            result1.get();
            result2.get();
            add.count = 0;
            if(result1.get()[1] == result2.get()[1]) {
                log.info("result1:{}, result2:{}", result1.get(), result2.get());
            }
        }
        es.shutdown();
    }
}
