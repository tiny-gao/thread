package com.tinygao.thread.stream;

import com.google.common.base.Stopwatch;
import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

import static com.google.common.collect.ImmutableList.of;

/**
 * Created by gsd on 2017/1/25.
 */
@Slf4j
public class ParallelStreamTest {
    public static void main(String[] args) {
        List<String> tinygao1 = new ArrayList<>();
        List<String> tinygao2 = new ArrayList<>();
        List<String> tinygao3 = new ArrayList<>();
        initList(tinygao1, tinygao2, tinygao3);

        Stopwatch s = Stopwatch.createStarted();
        int lengthNum = Stream.of(tinygao1,tinygao2,tinygao3)
                                .flatMap(x-> x.stream())
                                .mapToInt(x->x.length())
                                .sum();
        log.info("lengthNum :{} , waster : {} ms",
                    lengthNum,
                    s.elapsed(TimeUnit.MILLISECONDS));


        /*集合类都有parallelStream方法，实现并行化*/
        s.reset().start();
        int parallelLengthNum = Stream.of(tinygao1,tinygao2,tinygao3)
                                        .flatMap(x-> x.parallelStream())
                                        .mapToInt(x->x.length())
                                        .sum();
        log.info("parallelLengthNum :{} , waster : {} ms",
                parallelLengthNum,
                    s.elapsed(TimeUnit.MILLISECONDS));
        s.stop();
    }

    private static void initList(List<String> tinygao1,
                                  List<String> tinygao2,
                                  List<String> tinygao3) {
        for(int i = 0; i < 5000000; i++) {
            tinygao1.add("tinygao1"+i);
            tinygao2.add("tinygao2"+i);
            tinygao3.add("tinygao3"+i);
        }
    }
}
