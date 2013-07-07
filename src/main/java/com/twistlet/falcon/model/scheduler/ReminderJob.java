package com.twistlet.falcon.model.scheduler;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.twistlet.falcon.model.entity.FalconAppointment;
import com.twistlet.falcon.model.service.ReminderService;

public class ReminderJob extends QuartzJobBean {

	protected final Logger logger = LoggerFactory.getLogger(getClass());

	private final ReminderService reminderService;

	public ReminderJob(final ReminderService reminderService) {
		this.reminderService = reminderService;
	}

	@Override
	protected void executeInternal(final JobExecutionContext context) throws JobExecutionException {
		logger.info("Starting up...");
		final List<FalconAppointment> list = reminderService.listAppointmentsNeedingReminders(TimeUnit.DAYS.toSeconds(2));
		logger.info("{} items found", list.size());
		for (final FalconAppointment falconAppointment : list) {
			logger.info("Processing #{}", falconAppointment.getId());
			reminderService.sendNotification(falconAppointment);
			logger.info("Done #{}", falconAppointment.getId());
		}
		logger.info("Done...");
	}

}