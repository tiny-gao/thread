package com.tinygao.thread.executor;

import com.google.common.base.Stopwatch;
import com.tinygao.thread.job.Job;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * Created by gsd on 2017/1/14.
 */
@AllArgsConstructor
@Slf4j
public class DBJobExecution implements  JobExecution{

    public void execute(Job job){
        final Stopwatch stopwatch = Stopwatch.createStarted();
        int time = 10;
        while(time > 0) {
            try {
                //模拟文件查询语句
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
