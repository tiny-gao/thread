package com.tinygao.thread.unsafe;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Created by gsd on 2017/2/4.
 */
public class ListHelper<E> {
    public List<E> list = Collections.synchronizedList(new ArrayList<E>());

    public synchronized boolean putIfAbsent(E x) {
        boolean absent = !list.contains(x);
        if(absent) {
            list.add(x);
        }
        return absent;
    }
}
