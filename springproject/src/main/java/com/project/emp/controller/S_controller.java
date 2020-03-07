package com.project.emp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.project.emp.dto.TestDto;
import com.project.emp.service.S_service;

@Controller
@RequestMapping("/ttest")
public class S_controller {
	private Logger log = LoggerFactory.getLogger(S_controller.class);

	@Autowired
	private S_service s_service;
	
	@RequestMapping(value = "/S_signup", method=RequestMethod.GET) 
	public String getSighUpUrl() {
		return "jsp/test/S_signup";
		
	}
	
	@RequestMapping(value = "/S_signup", method=RequestMethod.POST)
	public String postSignUpUrl(@ModelAttribute TestDto test) {
		log.debug(test.toString());
		s_service.proc(test);
		return "redirect:./S_signup";
	}
	
}
