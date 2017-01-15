# thread
java多线程课程
#### 1. 为什么要引入多线程？
    -  没有多线程的情况
    -  有多线程的情况

#### 2. 何时应该使用多线程？

#### 3. 多线程实现的机制是如何？我用多个单线程进程方式不行么？

#### 4. 多线程存在那些问题？如何解决这些问题？

#### 5. 多线程在Java中实践实战经验

#### 6. 程序运行
     > java  -Djava.ext.dirs=/data/storage/thread/threadCourse -cp . com.tinygao.thread.runcpu.MultiRunCpu $1 $2
     > java  -Djava.ext.dirs=/data/storage/thread/threadCourse -cp . com.tinygao.thread.runcpu.SingleRunCpu $1 $2
     > /runMultiCpu.sh  500000000000 4
     > /runSingleCpu.sh  500000000000 true
