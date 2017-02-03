package com.tinygao.thread.safe;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by gsd on 2017/1/27.
 */
@Slf4j
public class Holder {
    private int n;

    public Holder(int n) {this.n = n;}

    public void assertSanity() {
        if(n != n) {
            log.info("assert santity {}", n);
            throw new AssertionError("This statment is false");
        }
    }
}
