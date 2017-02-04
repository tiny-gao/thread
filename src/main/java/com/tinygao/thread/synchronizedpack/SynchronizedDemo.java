package com.tinygao.thread.synchronizedpack;

/**
 * Created by gsd on 2017/2/3.
 */
public class SynchronizedDemo {
    public void method() {
        synchronized (this) {
             System.out.println("Method 1 start");
          }
      }
}
