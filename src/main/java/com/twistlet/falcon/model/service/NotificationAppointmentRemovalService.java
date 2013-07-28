package com.twistlet.falcon.model.service;

import java.util.List;
import java.util.Map;

import javax.mail.internet.MimeMessage;

public interface NotificationAppointmentRemovalService {

	List<MimeMessage> createMessages(Integer appointmentId);

	Map<String, String> createSmsMessages(Integer appointmentId);

	void send(List<MimeMessage> list);

	void sendSmsMessages(Integer appointmentId, Map<String, String> messages);
}