package com.project.emp.dao;

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

}
