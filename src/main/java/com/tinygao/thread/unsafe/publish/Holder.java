package com.tinygao.thread.unsafe.publish;

/**
 * Created by gsd on 2017/2/4.
 */
public class Holder {
    private int n;

    public Holder(int n) {
        this.n = n;
    }

    public void assertSanity() {
        if(n != n) {
            throw new AssertionError("This statment is false");
        }
    }
}
