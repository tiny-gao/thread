package com.tinygao.thread.executor;

import com.google.common.base.Stopwatch;
import com.tinygao.thread.job.Job;

import java.util.concurrent.TimeUnit;

/**
 * Created by gsd on 2017/1/14.
 */
public class FileJobExecution implements  JobExecution{
    @Override
    public void execute(Job job) {
        final Stopwatch stopwatch = Stopwatch.createStarted();
        int time = 5;
        while(time > 0) {
            try {
                //模拟文件上传
                TimeUnit.SECONDS.sleep(1);
                job.setElapsedTime(stopwatch.elapsed(TimeUnit.SECONDS));
                time--;
                job.logStatus();
            } catch (InterruptedException e) {
                stopwatch.stop();
                job.setStatus(Job.JobStatus.FAILED);
                job.logStatus();
            }
        }
        stopwatch.stop();
    }
}
