package com.project.emp.service;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.emp.dao.MasterDao;

@Service
public class MasterProcessService extends AbstractService {

    private Logger log = LoggerFactory.getLogger(MasterProcessService.class);
    
    @Autowired
    private MasterDao masterDao;
    
    //마스터 디비에서 메시지를 가져와서 호출하기
    public HashMap<String, String> getSuccessCodeMessageResultMap(Integer successCode, String code, String windowId, String process) {
        HashMap<String, String> resultMap = new HashMap<String, String>();
        try {
            String message = masterDao.getValue(code, windowId, process, String.valueOf(successCode));
            if(successCode==1) {
                resultMap.put("success", message);
            } else {
                resultMap.put("error", message);
            }
        } catch (Exception e) {
            log.error("DB에러 발생 위치 : getSuccessCodeMessageResultMap"+e.getMessage());
            //모종의 에러(DB에러)로 데이터가 입력이 되지 않았을 때
            resultMap.put("error", "DB에러로 인하여 데이터 작업이 정상적으로 작동하지 않았습니다. 다시 시도하여 주십시오.");
        }
        return resultMap;
    }
}
