package com.tinygao.thread.job;

import com.google.gson.GsonBuilder;
import lombok.Data;

import java.net.URI;

import static com.tinygao.thread.job.JobType.FILE_JOB;

/**
 * Created by gsd on 2017/1/14.
 */
@Data
public class FileJob extends  Job {
    private final URI location;
    public FileJob(final URI location) {
        super(FILE_JOB);
        this.location = location;
    }
    public String toString() {
        return new GsonBuilder().create().toJson(this);
    }
}
