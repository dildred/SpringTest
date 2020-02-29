package com.project.emp.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.emp.dao.TestDao;
import com.project.emp.dto.TestDto;

/**
 * Annotation Service<br>
 * 아래와 같이 적으면 이 자바 파일이 서비스라는 것을 명시(컨트롤러와 동일!)<br>
 * bean컨테이너에 등록이 되어 있어야 Annotation Autowired(의존 객체 주입)를 사용할 수 있음.<br>
 * */
@Service
public class TestService {

	private Logger log = LoggerFactory.getLogger(TestService.class);
	
	@Autowired
	private TestDao testDao;
	
	public void proc(TestDto testDto) {
		// TODO Auto-generated method stub
		log.info("여기에서 데이터 처리등등 함. Dao를 호출해서 데이터베이스에 등록시킬 수도 있음.");
		Integer i = testDao.insertTestDtoTable(testDto);
		if(i != 1) {
			log.error("DB등록 실패!");
			return;
		}
		log.debug("DB등록 성공!");
	}

}
