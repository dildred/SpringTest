package com.project.emp.dao;

import java.util.List;

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

    /**
     * 발주 회사의 정보를 등록함<br>
     * @param companyInfo 발주 회사 데이터
     * */
    public Integer registOrderCompany(OrderCompanyDto companyInfo) throws Exception;

    /**
     * 발주 회사의 정보를 취득함<br>
     * @param search 검색할 것
     * @param query 검색어
     * @param  limitA A부터
     * @param  limitB B개까지
     * */
    public List<OrderCompanyDto> getCompanyData(@Param("search")String search, 
            @Param("query") String query, 
            @Param("limitA") int limitA, 
            @Param("limitB")int j) throws Exception;

    /**
     * 해당 파라미터에 포함되는 발주 회사의 총 개수를 가져옴<br>
     * @param search 검색할 것
     * @param query 검색어
     * */
    public Integer getCompanyCount(@Param("search")String search, 
            @Param("query")String query) throws Exception;

}
