package com.project.emp.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.project.emp.dto.StaffDto;
import com.project.emp.service.StaffService;

/**
 * 직원 관련 컨트롤러<br>
 * URL : http://localhost:8080/emp<b>/staff~</b>
 * */

@Controller
@RequestMapping("/staff")
public class StaffController {

	//로그작성
	private Logger log = LoggerFactory.getLogger(StaffController.class);
	
	
	private final String defaultForder = "jsp/staff/";
	
	@Autowired
	private StaffService staffService;
	
	//호출페이지
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView staffWindowOpen(ModelAndView model) {
		model.setViewName(defaultForder+"staff_signup");
		return model;
	}
	
	//요청
	@RequestMapping(value= "/addstaff", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Integer> getAddStaff(@RequestBody StaffDto staffDtoList) {
		log.info("===========가입창===========");
		 Integer successCode = staffService.registStaff(staffDtoList);
		
		return new ResponseEntity<Integer>(successCode,HttpStatus.OK);
	}
	
	
}
