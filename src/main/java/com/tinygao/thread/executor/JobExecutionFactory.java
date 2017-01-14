package com.tinygao.thread.executor;

import com.tinygao.thread.job.JobType;

/**
 * Created by gsd on 2017/1/14.
 */
public class JobExecutionFactory {
   public static JobExecution createInstance(JobType type) {
      switch (type) {
         case DB_JOB :
            return new DBJobExecution();
         case FILE_JOB:
            return new FileJobExecution();
         default:
            //TODO: by tinygao. Throw concreate Exception.
            throw new RuntimeException();
      }
   }
}
