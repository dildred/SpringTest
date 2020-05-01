package com.project.emp.dao;


import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Repository;

import com.project.emp.dto.StaffDto;

@MapperScan
@Repository
public interface StaffDao {
	
	/*
	 * public Integer isStaffNumber(String staffnumber);
	 */
	
	//등록
	public Integer addStaffData(StaffDto staffDto) throws Exception;
	
	//기본키 이용 데이터 호출
	//public StaffDto getStaffDataWithPrimaryKey(@Param("staff_number")String StaffNo, @Param("Staff_name")String staffName) throws Exception;
}
