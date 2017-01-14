package com.tinygao.thread.job;

import com.google.gson.GsonBuilder;
import lombok.Value;

/**
 * Created by gsd on 2017/1/14.
 */
@Value
public class DBJob  extends  Job{
    private final String query;
    public DBJob(final String query) {
        super(JobType.DB_JOB);
        this.query = query;
    }
    public String toString() {
        return new GsonBuilder().create().toJson(this);
    }
}
