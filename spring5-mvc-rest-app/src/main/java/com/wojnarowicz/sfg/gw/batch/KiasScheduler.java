package com.wojnarowicz.sfg.gw.batch;


import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
public class KiasScheduler {

    @Autowired
    JobLauncher jobLauncher;
    
    @Autowired
    Job kiasJob;
    
    @Autowired
    Job sapJob;
    
    @Scheduled(cron = "0 1/20 * * * ?")
    public void myScheduler() {
        JobParameters jobParameters = new JobParametersBuilder()
                .addLong("time", System.currentTimeMillis())
                .toJobParameters();
        
        try {
            JobExecution jobExecution = jobLauncher.run(kiasJob, jobParameters);
            System.out.println("Job's status: " + jobExecution.getStatus());
        } catch (JobExecutionAlreadyRunningException | JobRestartException | JobInstanceAlreadyCompleteException | JobParametersInvalidException e) {
            e.printStackTrace();
        }
        
        System.out.println("KIAS Scheduler executed");
    }
    
    @Scheduled(cron = "0 2/20 * * * ?")
    public void ESBScheduler() {
        JobParameters jobParameters = new JobParametersBuilder()
                .addLong("time", System.currentTimeMillis())
                .toJobParameters();
        
        try {
            JobExecution jobExecution = jobLauncher.run(sapJob, jobParameters);
            System.out.println("Job's status: " + jobExecution.getStatus());
        } catch (JobExecutionAlreadyRunningException | JobRestartException | JobInstanceAlreadyCompleteException | JobParametersInvalidException e) {
            e.printStackTrace();
        }
        
        System.out.println("SAP Scheduler executed");
    }
}
