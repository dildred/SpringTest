package com.project.emp.dao;

import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Repository;

import com.project.emp.dto.MaterialOrderDto;

@MapperScan
@Repository
public interface MaterialOrderDao {

    /**
     * 발주 내력을 입력한다.<br>
     * @param materialOrderDto 등록 DTO
     * */
    public Integer registMatOrder(MaterialOrderDto materialOrderDto) throws Exception;

    /**
     * 가장 큰 발주 내력 번호를 취득한다.<br>
     * @param ordCd 발주 주문번호
     * */
    public Integer getBiggestMaterialOrdNoToOrdCd(@Param("ordCd")String ordCd) throws Exception;

    /**
     * 해당 재료의 발주가 존재하는지 확인한다. 1-존재 0또는null-존재하지 않음<br>
     * @param ordCd 발주 주문번호
     * @param matName 발주 상품명
     * */
    public Integer existingDataToOrdCdAndName(@Param("ordCd")String ordCd, @Param("matName")String matName) throws Exception;

}
