package com.project.emp.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

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
    
    private Logger log = LoggerFactory.getLogger(MaterialOrderService.class);

    public Integer registMatOrder(List<MaterialOrderDto> materialOrderDtoList) {
        // TODO Auto-generated method stub
        for(MaterialOrderDto materialOrderDto : materialOrderDtoList) {
            //발주 번호 생성 -> 회사 코드+오늘날짜
            String ordCd = materialOrderDto.getCompanyCd()+materialOrderDto.dateToString(materialOrderDto.getOrderDate());
            MaterialDto materialDto;
            try {
                materialDto = materialDao.getMaterialDataWithPrimaryKey(null, materialOrderDto.getMatName());
                materialOrderDto.setMatNo(materialDto.getMatNo());
                materialOrderDto.setOrderCd(ordCd);
            } catch (Exception e) {
                //matName이 존재하지 않는다면 지금까지 넣은 데이터 롤백
                log.error("DB에러 발생 위치 : registMatOrder");
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                return 2;
            }
            try {
                materialOrderDao.registMatOrder(materialOrderDto);
            } catch (Exception e) {
               //모종의 에러(DB에러)로 데이터가 입력이 되지 않았을 때
                return 3;
            }
        }
        return 1;
    }

    //회사 코드로 모든 회사 정보 불러오기
    public OrderCompanyDto callAllOrderCompanyData(String companyCd) throws Exception {
        // TODO Auto-generated method stub
        if(CodeMap.isEmpty(companyCd)) {
            return null;
        }
        return orderCompanyDao.getCompanyToCompanyCd(companyCd);
    }

}
