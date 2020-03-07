package com.project.emp.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.emp.dao.TestDao;
import com.project.emp.dto.TestDto;

@Service
public class S_service {
	private Logger log = LoggerFactory.getLogger(S_service.class);
	
	@Autowired
	private TestDao testDao;
	
	public void proc(TestDto testDto) {
		log.info("가나다라마바사");
		Integer i = testDao.insertTestDtoTable(testDto);
		if(i != 1) {
			log.error("DB등록 안댐");
			return;
		}
		log.debug("DB등록 됌");
	}

}
