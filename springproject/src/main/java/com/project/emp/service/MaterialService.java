package com.project.emp.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.emp.dao.MaterialDao;
import com.project.emp.dto.MaterialDto;
import com.project.emp.other.AutoPaging;
import com.project.emp.other.JsonPasing;

@Service
public class MaterialService {
	
	@Autowired
	private MaterialDao materialDao;
	
	@Autowired
	private JsonPasing<MaterialDto> jsonPasing;
	
	private Logger log = LoggerFactory.getLogger(MaterialService.class);
	
	/**
	 * 재료 분류 데이터 처리<br>
	 * 삭제 되지 않은 데이터만 불러옴
	 * */
	public List<String> getMaterialStatus() {
		log.info("==========등록된 재료의 분류를 가져오겠습니다.==========");
		return materialDao.getMaterialStatus("0");
	}
	
	/**
	 * 재료 등록 프로세스 서비스<br>
	 * 재료를 등록함 => 미완성
	 * */
	public Integer inputMaterialData(MaterialDto material) {
		// TODO Auto-generated method stub
		log.info("data확인");
		log.info(jsonPasing.ModelOnJson(material));
		//중복 확인(재료명)
		Integer i = materialDao.isMatName(material.getMatName());
		if(i!=null && i==0) {
			return 0;
		}
		//matNo를 생성한다
		String matNo = new SimpleDateFormat("YYYYMMddhhmmss").format(new Date());
		material.setMatNo(matNo);
		//재료 등록
		i = materialDao.registMaterialData(material);
		if(i==0) {
			return 1;
		}
		return 2;
	}

	public List<MaterialDto> getMaterialList() {
		// TODO Auto-generated method stub
		AutoPaging paging = new AutoPaging(1,10,10);
		Integer listCount = materialDao.getListCount();
		paging.setListCount(listCount);
		List<MaterialDto> materialList = materialDao.getMaterialList(paging);
		log.info(jsonPasing.ModelOnJson(materialList));
		return materialList;
	}

}
