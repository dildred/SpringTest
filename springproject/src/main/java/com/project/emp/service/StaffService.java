package com.project.emp.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.emp.dao.StaffDao;
import com.project.emp.dto.StaffDto;
import com.project.emp.other.JsonPasing;

@Service
public class StaffService {

	@Autowired
	private StaffDao staffDao;
	
	@Autowired
	private JsonPasing<StaffDto> jsonPasing;
	
	private Logger log = LoggerFactory.getLogger(StaffService.class);
	
	
	// 직원 등록
	
	public Integer inputStaffData(StaffDto staff) {
		log.info("data확인");
		log.info(jsonPasing.ModelOnJson(staff));
		
		//Validation 쳌
		Integer validationCheck = 0;
		
		
		if(validationCheck.compareTo(2)==1) {
			return validationCheck;
		}
		
		//중복 확인
		Integer i = staffDao.isStaffNumber(staff.getStaffnumber());
		
		
		//직원 등록
		i = staffDao.registStaffDtoData(staff);
		if(i==0) {
			return 1;
		}
		return 2;
		
	}
}
