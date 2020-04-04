package com.project.emp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.project.emp.dto.TestDto;
import com.project.emp.service.TestService;

/**
 * 발주 관련 컨트롤러<br>
 * URL : http://localhost:8080/emp<b>/order~</b>
 * */
@Controller
@RequestMapping("/order")
public class OrderController {
	
	/**
	 * 로그를 작성하기 위해 가져옴
	 * */
	private Logger log = LoggerFactory.getLogger(OrderController.class);
	
	/**
	 * jsp파일 들어있는 고정 폴더명
	 * */
	private final String defaultFolder = "jsp/order/";
	
	/**
	 * 재료 메인 웹 페이지 호출
	 * */
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView mainWndOpen(ModelAndView model) {
		log.info("※========Material Order Main Window 호출함=========※");
		//jsp를 뷰로 설정함
		model.setViewName(defaultFolder+"order_main");
		return model;
	}
	
	
	
}
