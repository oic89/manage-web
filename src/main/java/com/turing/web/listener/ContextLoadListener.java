package com.turing.web.listener;

import com.turing.service.SalaryService;
import com.turing.service.impl.SalaryServiceImpl;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ContextLoadListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        //加载资源
        try {
            //启动调度器Scheduler
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
            scheduler.start();
            //定义作业job
            JobDetail job = JobBuilder.newJob(salaryStat.class)
                    .withIdentity("job", "group")
                    .build();
            //定义触发器trigger
            Trigger trigger = TriggerBuilder.newTrigger()
                    .withIdentity("trigger", "group")
                    //定义cron
                    .withSchedule(CronScheduleBuilder.cronSchedule("0 0 0 1 * ?"))
                    .build();

            //触发job
            scheduler.scheduleJob(job, trigger);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        // 关闭调度器
        try {
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
                if (scheduler != null && !scheduler.isShutdown()) {
                    scheduler.shutdown();
                }
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    //工资统计
    public static class salaryStat implements Job {
        public void execute(JobExecutionContext context) throws JobExecutionException {
            System.out.println("salaryStat1");
            SalaryService salaryService=new SalaryServiceImpl();
            salaryService.salaryStat();
        }
    }
}
