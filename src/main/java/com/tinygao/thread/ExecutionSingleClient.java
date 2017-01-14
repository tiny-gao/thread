package com.tinygao.thread;

import com.google.common.base.Stopwatch;
import com.tinygao.thread.executor.JobExecutionFactory;
import com.tinygao.thread.job.DBJob;
import com.tinygao.thread.job.FileJob;
import com.tinygao.thread.job.Job;
import com.tinygao.thread.job.JobType;
import lombok.extern.slf4j.Slf4j;

import java.net.URI;
import java.util.concurrent.TimeUnit;

/**
 * Created by gsd on 2017/1/14.
 */
@Slf4j
public class ExecutionSingleClient {

    public static void main(String[] args) throws Exception {
        final Stopwatch stopwatch = Stopwatch.createStarted();
        runDBJob();
        runFileJob();
        log.info("total elapse time {}s", stopwatch.elapsed(TimeUnit.SECONDS));
        stopwatch.stop();
    }

    private static void runDBJob() throws Exception {
        //创建一个任务
        Job dbJob = new DBJob("select * from tbl_user");
        //创建一个数据库执行者
        Execution dbExecution = new Execution(
                dbJob,
                JobExecutionFactory.createInstance(JobType.DB_JOB));
        dbExecution.call();
    }

    private static void runFileJob() throws Exception {
        //创建一个任务
        Job fileJob = new FileJob(new URI("file://d:"));
        //创建一个文件执行者
        Execution fileExecution = new Execution(
                fileJob,
                JobExecutionFactory.createInstance(JobType.FILE_JOB));
        fileExecution.call();
    }
}
