package com.clx.springboot30.application.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SchedulerTask {

    private int count = 0;

    @Scheduled(cron = "0 */1 * * * ?")
    private void printCount() {
        System.out.println("scheduler task is running:  " + (count++));
    }
}
