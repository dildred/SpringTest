package com.project.emp.dao;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Repository;

import com.project.emp.dto.StaffDto;

@MapperScan
@Repository
public interface StaffDao {

	public Integer registStaffDtoData (StaffDto staffDto);

	public Integer isStaffNumber(String staffnumber);
	
}
