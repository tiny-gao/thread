package com.tinygao.thread.unsafe.publish;

/**
 * Created by gsd on 2017/2/4.
 */
public class HolderFactory {
    public Holder holder;

    public void initialize() {
        holder = new Holder(42);
    }
}
