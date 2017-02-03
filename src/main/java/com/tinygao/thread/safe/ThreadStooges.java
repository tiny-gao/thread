package com.tinygao.thread.safe;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by gsd on 2017/1/27.
 */
public class ThreadStooges {
    private  final Set<String> stooges = new HashSet<>();

    public ThreadStooges() {
        stooges.add("more");
        stooges.add("Larry");
        stooges.add("Curly");
    }

    public static void main(String[] args) {
        ThreadStooges s = new ThreadStooges();
        s.stooges.add("tt");
        s.stooges.forEach(System.out::println);
    }
}
