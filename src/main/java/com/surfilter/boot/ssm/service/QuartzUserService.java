package com.surfilter.boot.ssm.service;

import java.util.Date;

import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.Trigger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.surfilter.boot.component.scheduler.QuartzTest;
import com.surfilter.boot.ssm.core.AppContext;

@Service
@Transactional
public class QuartzUserService {

	public void addJob(String jobName, String jobGroupName,String triggerName, String triggerGroupName, Class<? extends Job> cls, String cron){
		try {
			//在当前时间往后延迟2秒启动
			Date triggerStartTime = new Date(new Date().getTime()+1000*60*1);
			int intervalSeconds = 2;
			int triggerRepeatCount = 10;
			Scheduler scheduler = (Scheduler)AppContext.getBean("quartzScheduler");
			JobDetail jobDetail = QuartzTest.getJobDetail(cls,jobName,jobGroupName);
			Trigger trigger = QuartzTest.getTriggerForAppointTimeForever(triggerStartTime,intervalSeconds,triggerName,triggerGroupName);
			scheduler.scheduleJob(jobDetail,trigger);
			scheduler.start();
			System.out.println("eating启动成功");
			//Thread.sleep(40000);
			//scheduler.shutdown();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
