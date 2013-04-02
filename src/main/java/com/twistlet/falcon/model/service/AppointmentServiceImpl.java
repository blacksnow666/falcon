package com.twistlet.falcon.model.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.twistlet.falcon.controller.bean.Schedule;
import com.twistlet.falcon.model.entity.FalconAppointment;
import com.twistlet.falcon.model.entity.FalconPatron;
import com.twistlet.falcon.model.repository.FalconAppointmentRepository;
import com.twistlet.falcon.model.repository.FalconPatronRepository;

@Service
public class AppointmentServiceImpl implements AppointmentService {

	private final FalconAppointmentRepository falconAppointmentRepository;
	
	private final FalconPatronRepository falconPatronRepository;
	
	@Autowired
	public AppointmentServiceImpl(
			FalconAppointmentRepository falconAppointmentRepository,
			FalconPatronRepository falconPatronRepository) {
		this.falconAppointmentRepository = falconAppointmentRepository;
		this.falconPatronRepository = falconPatronRepository;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void createAppointment(FalconAppointment falconAppointment) {
		falconAppointmentRepository.save(falconAppointment);
		for(FalconPatron falconPatron : falconAppointment.getFalconPatrons()){
			falconPatron.setFalconAppointment(falconAppointment);
			falconPatronRepository.save(falconPatron);
		}
	}

	@Override
	public List<Schedule> getMonthlySchedule(Date date) {
		final SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		final Date start = DateUtils.truncate(date, Calendar.MONTH);
		final Date end = DateUtils.addSeconds(DateUtils.ceiling(date, Calendar.MONTH), -1);
		List<FalconAppointment> appointments = falconAppointmentRepository.findByAppointmentDateBetween(start, end);
		HashMap<String, Integer> organiser = new LinkedHashMap<>();
		String key = StringUtils.EMPTY;
		Integer total = 0;
		for(FalconAppointment appointment : appointments){
			key = sdf.format(appointment.getAppointmentDate());
			total = organiser.get(key);
			if(total == null){
				total = 0;
			}
			organiser.put(key, total + 1);
		}
		List<Schedule> schedules = new ArrayList<>();
		Schedule schedule;
		for(String id : organiser.keySet()){
			schedule = new Schedule();
			schedule.setDay(Integer.parseInt(id.substring(6)));
			schedule.setTotalAppointment(organiser.get(id));
			schedules.add(schedule);
		}
		return schedules;	
	}

}
