package com.tinygao.thread;

import java.net.URI;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

import lombok.extern.slf4j.Slf4j;

import com.google.common.base.Stopwatch;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.tinygao.thread.executor.JobExecutionFactory;
import com.tinygao.thread.job.DBJob;
import com.tinygao.thread.job.FileJob;
import com.tinygao.thread.job.Job;
import com.tinygao.thread.job.JobType;

/**
 * Created by gsd on 2017/1/14.
 */
@Slf4j
public class ExecutionMultiClient {

    public static ThreadFactory threadsNamed(String nameFormat){
        return new ThreadFactoryBuilder().setNameFormat(nameFormat).setDaemon(false).build();
    }

    public static void main(String[] args) throws Exception {
        final Stopwatch stopwatch = Stopwatch.createStarted();

        ExecutorService threads = Executors.newFixedThreadPool(
                    				2,
                    				threadsNamed("job-threads-%d"));
        
        threads.submit(ExecutionMultiClient::runDBJob);
        threads.submit(ExecutionMultiClient::runFileJob);

        threads.shutdown();
        threads.awaitTermination(1, TimeUnit.DAYS);

        log.info("total elapse time {}s", stopwatch.elapsed(TimeUnit.SECONDS));
        stopwatch.stop();
    }

    private static void runDBJob() {
        //创建一个任务
        Job dbJob = new DBJob("select * from tbl_user");
        //创建一个数据库执行者
        Execution dbExecution = new Execution(
                				dbJob,
                				JobExecutionFactory.createInstance(JobType.DB_JOB));
        try {
            dbExecution.call();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void runFileJob() {
    		//创建一个任务
        try {
        	Job fileJob = new FileJob(new URI("file://d:"));
        	//创建一个文件执行者
        	Execution fileExecution = new Execution(
					                fileJob,
					                JobExecutionFactory.createInstance(JobType.FILE_JOB));

            fileExecution.call();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
