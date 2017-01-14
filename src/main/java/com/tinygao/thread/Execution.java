package com.tinygao.thread;

import com.tinygao.thread.executor.JobExecution;
import com.tinygao.thread.job.Job;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;

/**
 * Created by gsd on 2017/1/14.
 */
@AllArgsConstructor
@Slf4j
public class Execution implements Callable<Job> {

    private final Job job;
    private final JobExecution concreateExecution;

    @Override
    public Job call() throws Exception {
        return doExecute();
    }

    private Job doExecute(){

        job.setStatus(Job.JobStatus.STARTING);
        job.logStatus();
        //这里可以做一些准备动作

        job.setStatus(Job.JobStatus.RUNNING);
        job.logStatus();

        concreateExecution.execute(job);
        job.setStatus(Job.JobStatus.FINISHED);
        job.logStatus();

        return job;
    }
}