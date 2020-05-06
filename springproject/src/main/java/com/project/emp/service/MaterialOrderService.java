package com.project.emp.service;

import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.emp.dao.MasterDao;
import com.project.emp.dao.MaterialDao;
import com.project.emp.dao.MaterialOrderDao;
import com.project.emp.dao.OrderCompanyDao;
import com.project.emp.dto.MaterialDto;
import com.project.emp.dto.MaterialOrderDto;
import com.project.emp.dto.OrderCompanyDto;
import com.project.emp.other.CodeMap;

@Service
public class MaterialOrderService {
    
    @Autowired
    private MaterialDao materialDao;
    
    @Autowired
    private MaterialOrderDao materialOrderDao;
    
    @Autowired
    private OrderCompanyDao orderCompanyDao;
    
    @Autowired
    private MasterDao masterDao;
    
    private Logger log = LoggerFactory.getLogger(MaterialOrderService.class);

    public Integer registMatOrder(List<MaterialOrderDto> materialOrderDtoList) {
        int isErr = 1;
        if((isErr = companyValidationCheck(materialOrderDtoList))>1) {
            return isErr;
        }
        OrderCompanyDto companyInfo = null;
        //회사가 데이터베이스에 존재하는 회사인지 확인
        String ordCd = null;
        Integer ordNo = null;
        try {
            companyInfo = orderCompanyDao.getCompanyToCompanyCd(materialOrderDtoList.get(0).getCompanyCd());
            if(companyInfo==null) {
                log.info("발주 회사 정보가 존재하지 않으므로 새로운 발주 회사의 정보를 입력합니다.");
                companyInfo = new OrderCompanyDto();
                companyInfo.convertToMaterialOrderDto(materialOrderDtoList.get(0));
                Integer success = orderCompanyDao.registOrderCompany(companyInfo);
                if(success==0) {
                    //DB에러는 아닌데 데이터가 들어가지 않았을 때
                    return 3;
                }
            }
           //발주 번호 생성 -> 회사 코드+오늘날짜
            ordCd = materialOrderDtoList.get(0).getCompanyCd()+materialOrderDtoList.get(0).dateToString(materialOrderDtoList.get(0).getOrderDate());
            //발주 내역 번호를 취득한다
            ordNo = materialOrderDao.getBiggestMaterialOrdNoToOrdCd(ordCd);
            
        } catch (Exception e) {
            log.error("DB에러 발생 위치 : registMatOrder returning 3"+e.getMessage());
            //모종의 에러(DB에러)로 데이터 검색이 안됐을 때 혹은 에러로 회사 정보가 입력이 되지 않았을 때
             return 3;
        }
        log.info("발주 회사 정보 등록 대기중 -> 발주 재료 등록 데이터 확인");
        for(MaterialOrderDto materialOrderDto : materialOrderDtoList) {
            if((isErr = materialDtoValidationCheck(materialOrderDto))>1) {
                //하나라도 문제가 되는 것이 있으면 모든 데이터를 롤백후 에러 표시
                CodeMap.rollback();
                return isErr;
            }
            MaterialDto materialDto;
            try {
                Integer materialOrderDataIsExisting = materialOrderDao.existingDataToOrdCdAndName(ordCd, materialOrderDto.getMatName());
                if(!CodeMap.isEmpty(materialOrderDataIsExisting) && materialOrderDataIsExisting==1) {
                    //이미 해당 발주 데이터가 존재함
                    log.error("이미 존재하는 발주 데이터 입니다. ordCd : "+ordCd+" | matName : " + materialOrderDto.getMatName());
                    CodeMap.rollback();
                    return 17;
                }
                if(ordNo==null) {
                    ordNo=1;
                } else {
                    ordNo++;
                }
                materialDto = materialDao.getMaterialDataWithPrimaryKey(null, materialOrderDto.getMatName());
                materialOrderDto.setMatNo(materialDto.getMatNo());
                materialOrderDto.setOrderCd(ordCd);
                materialOrderDto.setOrderNo(ordNo);
            } catch (Exception e) {
                //matName이 존재하지 않는다면 지금까지 넣은 데이터 롤백
                log.error("DB에러 발생 위치 : registMatOrder returning 2" +e.getMessage());
                CodeMap.rollback();
                return 2;
            }
            try {
                materialOrderDao.registMatOrder(materialOrderDto);
            } catch (Exception e) {
                log.error("DB에러 발생 위치 : registMatOrder returning 3"+e.getMessage());
               //모종의 에러(DB에러)로 데이터가 입력이 되지 않았을 때
                return 3;
            }
        }
        //정상 코드 1
        return 1;
    }

    //발주 상품 데이터 발리데이션 체크
    private int materialDtoValidationCheck(MaterialOrderDto materialOrderDto) {
        if(CodeMap.isEmpty(materialOrderDto.getMatName())) {
            log.error("에러 발생 위치 : materialDtoValidationCheck - Message : MatName이 존재하지 않습니다.");
            //재료명이 존재하지 않음
            return 14;
        }
        if(CodeMap.isEmpty(materialOrderDto.getOrderQty()) || materialOrderDto.getOrderQty() == 0) {
            log.error("에러 발생 위치 : materialDtoValidationCheck - Message : OrderQty가 존재하지 않습니다.");
            //발주 재료 수량이 존재하지 않음
            return 15;
        }
        if(CodeMap.isEmpty(materialOrderDto.getOrderBill())) {
            log.error("에러 발생 위치 : materialDtoValidationCheck - Message : OrderQty가 존재하지 않습니다.");
            //발주 금액 데이터가 존재하지 않음
            return 16;
        }
        return 1;
    }

    //발주 회사 데이터 발리데이션 체크
    private int companyValidationCheck(List<MaterialOrderDto> materialOrderDtoList) {
        if(CodeMap.isEmpty(materialOrderDtoList)) {
            log.error("에러 발생 위치 : companyValidationCheck - Message : List가 존재하지 않습니다.");
            //모종의 에러 발생(데이터가 존재하지 않음)
            return 3;
        }
        if(CodeMap.isEmpty(materialOrderDtoList.get(0).getCompanyCd())) {
            log.error("에러 발생 위치 : companyValidationCheck - Message : CompanyCd가 존재하지 않습니다.");
            //회사 코드가 존재하지 않음
            return 4;
        }
        if(CodeMap.isEmpty(materialOrderDtoList.get(0).getCompanyName())) {
            log.error("에러 발생 위치 : companyValidationCheck - Message : CompanyName이 존재하지 않습니다.");
            //회사 명이 존재하지 않음
            return 5;
        }
        if(CodeMap.isEmpty(materialOrderDtoList.get(0).getCompanyTel())) {
            log.error("에러 발생 위치 : companyValidationCheck - Message : CompanyTel이 존재하지 않습니다.");
            //회사 전화번호가 존재하지 않음
            return 6;
        }
        if(CodeMap.isEmpty(materialOrderDtoList.get(0).getCompanyAddress())) {
            log.error("에러 발생 위치 : companyValidationCheck - Message : CompanyAddress가 존재하지 않습니다.");
            //회사 주소가 존재하지 않음
            return 7;
        }
        if(CodeMap.isNumbericAndAlphabet(materialOrderDtoList.get(0).getCompanyCd())) {
            log.error("에러 발생 위치 : companyValidationCheck - Message : CompanyCd에 잘못된 문자가 들어 있습니다 | 확인 : "+materialOrderDtoList.get(0).getCompanyCd());
            //회사 코드에 영어 소문자 및 숫자만 포함되어 있지 않음
            return 8;
        }
        if(!CodeMap.isNumberic(materialOrderDtoList.get(0).getCompanyTel())) {
            log.error("에러 발생 위치 : companyValidationCheck - Message : CompanyTel에는 숫자만이 가능합니다 | 확인 : "+materialOrderDtoList.get(0).getCompanyTel());
            //회사 전화번호에 숫자 외의 문자가 포함되어 있음
            return 9;
        }
        if(!CodeMap.isLengthShortest(materialOrderDtoList.get(0).getCompanyName(), 50)) {
            //회사명 제한(최대 30글자) 너무 길 때
            log.error("에러 발생 위치 : companyValidationCheck - Message : CompanyName이 너무 깁니다(30자 가능) | 확인 : Name"+materialOrderDtoList.get(0).getCompanyName().length());
            return 10;
        }
        if(!CodeMap.isLengthShortest(materialOrderDtoList.get(0).getCompanyTel(), 20)) {
            //회사전화번호 제한(최대 20글자) 너무 길 때
            log.error("에러 발생 위치 : companyValidationCheck - Message : CompanyTel이 너무 깁니다(20자 가능) | 확인 : Name"+materialOrderDtoList.get(0).getCompanyTel().length());
            return 11;
        }
        if(!CodeMap.isLengthShortest(materialOrderDtoList.get(0).getCompanyAddress(), 50)) {
            //회사주소 제한(최대 50글자) 너무 길 때
            log.error("에러 발생 위치 : companyValidationCheck - Message : CompanyAddress가 너무 깁니다(50자 가능) | 확인 : Name"+materialOrderDtoList.get(0).getCompanyAddress().length());
            return 12;
        }
        if(CodeMap.isEmpty(materialOrderDtoList.get(0).getOrderDate())) {
            //발주 날짜 데이터가 존재하지 않을 때
            log.error("에러 발생 위치 : companyValidationCheck - Message : OrderDate가 존재하지 않습니다");
            return 13;
        }
        
        return 1;
    }

    //회사 코드로 모든 회사 정보 불러오기
    public OrderCompanyDto callAllOrderCompanyData(String companyCd) throws Exception {
        if(CodeMap.isEmpty(companyCd)) {
            return null;
        }
        return orderCompanyDao.getCompanyToCompanyCd(companyCd);
    }

    //마스터 디비에서 메시지를 가져와서 호출하기
    public HashMap<String, String> registOrderServiceResultMap(Integer successCode) {
        HashMap<String, String> resultMap = new HashMap<String, String>();
        try {
            String message = masterDao.getValue("M001", "MATORDER", "REGIST", String.valueOf(successCode));
            if(successCode==1) {
                resultMap.put("success", message);
            } else {
                resultMap.put("error", message);
            }
        } catch (Exception e) {
            log.error("DB에러 발생 위치 : registOrderServiceResultMap returning 3"+e.getMessage());
            //모종의 에러(DB에러)로 데이터가 입력이 되지 않았을 때
            resultMap.put("error", "DB에러로 인하여 데이터 작업이 정상적으로 작동하지 않았습니다. 다시 시도하여 주십시오.");
        }
        return resultMap;
    }

}
