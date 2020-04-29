package com.project.emp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

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
	
	@RequestMapping(value= "/addstaff", method = RequestMethod.GET)
	public String getAddStaffUrl() {
		return "jsp/staff/staff_signup";
	}
	
	
}
