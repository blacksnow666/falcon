package com.twistlet.falcon.model.service;

import java.util.List;

import com.twistlet.falcon.model.entity.FalconUser;

public interface StaffService {
	List<FalconUser> listPatients(String partialName);

	boolean sendEmail(String name, String address, String message);
}
