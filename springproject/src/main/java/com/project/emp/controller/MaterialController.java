package com.project.emp.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

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

import com.project.emp.dto.MaterialDto;
import com.project.emp.service.MaterialService;

/**재료 관련 컨트롤러*/
@Controller
@RequestMapping("/material")
public class MaterialController {
	
	/**
	 * 로그작성
	 * */
	private Logger log = LoggerFactory.getLogger(MaterialController.class);

	/**
	 * jsp파일 들어있는 고정 폴더명
	 * */
	private final String defaultFolder = "jsp/material/";
	
	@Autowired
	private MaterialService materialService;
	
	/**
	 * 재료 등록 웹 페이지 호출
	 * */
	@RequestMapping(value = "/regist", method = RequestMethod.GET)
	public String registWndOpen(HttpServletRequest request) {
		log.info("※========Material Regist 호출함=========※");
		return defaultFolder+"material_regist";
	}
	
	/**
	 * 재료 등록 프로세스 호출 미작업중
	 * consumes -> ajax로부터 요청받았을 때의 파일 양식
	 * produces -> ajax한테 요청받은 파일을 전송할 때 보낼 양식
	 * */
	@RequestMapping(value = "/regist", method = RequestMethod.POST, consumes = "application/json", produces="application/json")
	@ResponseBody
	public ResponseEntity<Integer> registProc(@RequestBody MaterialDto material) {
		log.info("==========Material Regist 프로세스 실행==========");
		Integer successCode = materialService.inputMaterialData(material);
		log.info("==========Material Regist 프로세스 실행 완료==========");
		return new ResponseEntity<Integer>(successCode,HttpStatus.OK);
	}
	
	
	/**
	 * 재료 분류 불러오기
	 * */
	@RequestMapping(value = "/regist-init", method = RequestMethod.GET, produces="application/json")
	@ResponseBody
	public ResponseEntity<List<String>> requestMaterialStatus() {
		log.info("==========Material Status DB호출==========");
		List<String> getMaterialStatus = materialService.getMaterialStatus();
		log.info("==========Material Status 호출 완료==========");
		return new ResponseEntity<List<String>>(getMaterialStatus,HttpStatus.OK);
	}
	
	
}
