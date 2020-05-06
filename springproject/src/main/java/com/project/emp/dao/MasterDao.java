package com.project.emp.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Repository;

/**
 * 
 * Master 정보에 관한 DAO
 * */
@MapperScan
@Repository
public interface MasterDao {
    
    /**
     * Master의 값(MasterValue)을 습득한다.
     * @param masterCd 마스터 코드
     * @param masterKey1 마스터 키1
     * @param masterKey1 마스터 키2
     * @param masterKey1 마스터 키3
     * */
    public String getValue(@Param("mCd") String masterCd, 
            @Param("key1") String masterKey1, 
            @Param("key2") String masterKey2, 
            @Param("key3") String masterKey3) throws Exception;
    
    /**
     * Master의 값(MasterValue)들을 습득한다.
     * @param masterCd 마스터 코드
     * @param masterKey1 마스터 키1
     * @param masterKey1 마스터 키2
     * @param masterKey1 마스터 키3
     * */
    public List<String> getValues(@Param("mCd") String masterCd, 
            @Param("key1") String masterKey1, 
            @Param("key2") String masterKey2, 
            @Param("key3") String masterKey3) throws Exception;
}
