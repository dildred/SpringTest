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
import com.project.emp.other.CodeMap;
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
		try {
            return materialDao.getMaterialStatus("0");
        } catch (Exception e) {
            //DB에러 발생시 데이터를 불러오지 못함;
            log.error("DB에러 발생 위치 : getMaterialStatus");
            return null;
        }
	}
	
	/**
	 * 재료 등록 프로세스 서비스<br>
	 * 재료를 등록함 => 미완성
	 * */
	public Integer registMaterialData(MaterialDto material) {
		log.info("data확인");
		log.info(jsonPasing.ModelOnJson(material));
		//Validation체크
		Integer validationCheck = 0;
		try {
			validationCheck = materialRegistModifyValidation(material);
		}catch(Exception e) {
			return 1;
		}
		if(validationCheck.compareTo(2)==1) {
			return validationCheck;
		}
		try {
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
		}catch(Exception e) {
		    log.error("DB에러 발생 위치 : registMaterialData");
		    return 1;
		}
		return 2;
	}

	/**
	 * 재료 리스트 프로세스 서비스<br>
	 * 재료 리스트를 호출함
	 * */
	public List<MaterialDto> getMaterialList(String page) {
		try {
		    AutoPaging paging = getThisPaging(page);
		    List<MaterialDto> materialList = materialDao.getMaterialList(paging);
		    log.info(jsonPasing.ModelOnJson(materialList));
	        return materialList;
		}catch(Exception e) {
		    //DB에러 발생시 호출하지 않음
		    log.error("DB에러 발생 위치 : getMaterialList");
		    return null;
		}
	}
	
	/**
	 * 현 페이지 총 개수 가져오기
	 * */
	public int getPageAllCount() {
		AutoPaging paging = getThisPaging("1");
		return paging.getMaxPage();
	}
	
	/**
	 * 현 화면의 자동 페이징 불러오기
	 * */
	protected AutoPaging getThisPaging(String page) {
		AutoPaging paging = null;
		if(CodeMap.isNumberic(page)) {
			paging = new AutoPaging(Integer.parseInt(page),10,10);
		} else {
			paging = new AutoPaging(1,10,10);
		}
		Integer listCount;
        try {
            listCount = materialDao.getListCount();
        } catch (Exception e) {
            log.error("DB에러 발생 위치 : getThisPaging");
            listCount = 0;
        }
		paging.setListCount(listCount);
		return paging;
	}

	/**
	 * 재료 삭제 서비스<br>
	 * 재료 데이터의 삭제플래그를 1로 만듬.
	 * */
	public Integer deleteMaterialData(List<MaterialDto> materialList) {
		int i = 0;
		log.info("delete data size : "+materialList.size());
		for(MaterialDto material : materialList) {
		    try {
		        i = i + materialDao.deleteMaterial(material);
		    } catch(Exception e) {
		        //에러 발생시 현재 트랜잭션을 멈추고 아무것도 지우지 않고 0으로 리턴
		        log.error("DB에러 발생 위치 : deleteMaterialData");
		        return 0;
		    }
		}
		return i;
	}

	/**
	 * 데이터 호출 서비스. 재료 번호와 재료 이름으로 데이터를 호출한다.
	 * */
	public MaterialDto getMaterialData(String materialNo, String mateialName) {
		try {
            return materialDao.getMaterialDataWithPrimaryKey(materialNo,mateialName);
        } catch (Exception e) {
            log.error("DB에러 발생 위치 : getMaterialData");
            return null;
        }
	}
	/**
	 * 데이터 호출 서비스. 재료 이름으로만 데이터를 호출한다.
	 * */
	public MaterialDto getMaterialData(String mateialName) {
		try {
            return materialDao.getMaterialDataWithPrimaryKey(null,mateialName);
        } catch (Exception e) {
            log.error("DB에러 발생 위치 : getMaterialData");
            return null;
        }
	}

	/**
	 * 재료 변경 서비스<br>
	 * 저장되어 있는 재료명과 재료 번호를 토대로 변경함
	 * */
	public Integer modifyMaterialProc(MaterialDto material) {
		//Validation체크
		Integer validationCheck = 0;
		try {
			validationCheck = materialRegistModifyValidation(material);
		}catch(Exception e) {
			return 1;
		}
		if(validationCheck.compareTo(2)==1) {
			return validationCheck;
		}
		//데이터가 존재하는지 확인
		try {
    		if(materialDao.getMaterialDataWithPrimaryKey(material.getMatNo(),material.getMatName())==null) {
    			return 1;
    		}
    		if(materialDao.updateMaterial(material)==1) {
    			return 2;
    		} 
		}catch(Exception e) {
		    //변경 서비스를 취소하고 에러코드 1 리턴
		    log.error("DB에러 발생 위치 : modifyMaterialProc");
		    return 1;
		}
		return 1;
	}
	
	//데이터 입력 변경에 있어 문제가 있는지 체크(이름, 유닛, 스테이터스와 기타 등등)
	public Integer materialRegistModifyValidation(MaterialDto material) throws Exception{
		if(CodeMap.isEmpty(material.getMatName())) {
			return 3;
		}
		if(CodeMap.isEmpty(material.getWeightUnit())) {
			return 4;
		}
		if(CodeMap.isEmpty(material.getMatStatus())) {
			return 5;
		}
		if(CodeMap.isSpecialChaReg(material.getMatName()) 
				|| CodeMap.isSpecialChaReg(material.getWeightUnit())
				|| CodeMap.isSpecialChaReg(material.getMatStatus())) {
			return 6;
		}
		
		return 0;
	}

}
