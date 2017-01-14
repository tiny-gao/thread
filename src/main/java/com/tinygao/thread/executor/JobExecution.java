package com.tinygao.thread.executor;

import com.tinygao.thread.job.Job;

/**
 * Created by gsd on 2017/1/14.
 */
public interface JobExecution {
    void execute(Job job);
}
