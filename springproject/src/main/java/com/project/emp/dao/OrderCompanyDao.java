package com.project.emp.dao;

import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Repository;

import com.project.emp.dto.OrderCompanyDto;

@MapperScan
@Repository
public interface OrderCompanyDao {

    /**
     * 회사 코드로 회사의 정보를 불러옴<br>
     * @param companyCd 회사 코드
     * */
    public OrderCompanyDto getCompanyToCompanyCd(@Param("companyCd") String companyCd) throws Exception;

}
