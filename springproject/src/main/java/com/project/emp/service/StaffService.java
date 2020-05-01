package com.project.emp.service;

import java.util.List;

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

	
	 @Autowired private JsonPasing<StaffDto> jsonPasing;
	 

	private Logger log = LoggerFactory.getLogger(StaffService.class);

	// 직원 등록
		
	public Integer registStaff(StaffDto staffDto) {
		try {
			System.out.println(staffDto);
			staffDao.addStaffData(staffDto);
		} catch (Exception e) {
			log.error("DB에러 발생 위치 : addStaff");
			return 2;
		}
		return 1;

	}
}
