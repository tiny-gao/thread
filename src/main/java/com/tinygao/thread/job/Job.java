package com.tinygao.thread.job;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;

/**
 * Created by gsd on 2017/1/14.
 */
@Data
@Slf4j
public class Job {
    private Date startTime;
    private Date finishedTime;
    private long elapsedTime;
    private JobStatus status;
    protected final JobType type;

    public  void logStatus() {
        log.info("{} job status : {}， elapse time : {}s ",
                type,
                status,
                elapsedTime);
    }
    public enum  JobStatus {
        STARTING(false), RUNNING(false), FAILED(true), FINISHED(true);
        private boolean jobEnd;
        private JobStatus(boolean endElse) {
            jobEnd = endElse;
        }
    }

}
