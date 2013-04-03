package com.twistlet.falcon.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.twistlet.falcon.model.entity.FalconPatron;
import com.twistlet.falcon.model.entity.FalconUser;

public interface FalconPatronRepository extends JpaRepository<FalconPatron, Integer> {
	
	List<FalconPatron> findByFalconUserByAdmin(FalconUser admin);

}