package com.project.emp.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.project.emp.dto.TestDto;
import com.project.emp.other.AutoFileSaving;
import com.project.emp.service.TestService;

/**
 * Annotation Controller<br>
 * 아래와 같이 적으면 이 자바 파일이 컨트롤러라는 것을 명시(bean에 자동으로 등록시켜주기 때문에 무조건 컨트롤러에 작성해야함)<br>
 * <br>
 * Annotation RequestMapping<br>
 * 해당 주소를 컨트롤러에 속하는 모든 메서드에게 이 주소 다음에 올 것임을 명시해줌(등록하지 않아도 상관없음)<br>
 * http://localhost:8080/springproject<b>/test</b><br>
 * 작성은 하지 않았지만 아래에 적혀있는 것과 같은 value= (혹은path=)를 생략할 수 있음<br>
 * */
@Controller
@RequestMapping("/test")
public class TestController {
	
	/**
	 * 로그를 작성하기 위해 가져옴
	 * */
	private Logger log = LoggerFactory.getLogger(TestController.class);
	
	/**
	 * Annotation Autowired<br>
	 * 스프링 Bean 컨테이너에 담겨있는 Components(Component, Controller, Service, Repository)들을 자동으로 불러와서 넣어줌.<br>
	 * <br>
	 */
	@Autowired
	private TestService testService;
	
	/**
	 * 이 주소로 GET방식으로 들어오게 되면 이 메소드를 실행시켜줌<br>
	 * http://localhost:8080/springproject/test<b>/signup</b>
	 * 
	 * */
	@RequestMapping(value = "/signup", method=RequestMethod.GET)
	public String getSignUpUrl() {
		/**
		 * jsp를 불러옴<br>
		 * 기본 경로가 WEB-INF/views로 servlet-context.xml에 작성되어 있으므로<br>
		 * 그 다음의 경로를 입력해주기만 하면 됨.<br>
		 * 참고로 원래라면 .jsp까지 작성해야하나 작성을 생략하는 것도 servlet-context.xml에 작성되어 있으므로 생략함<br>
		 * 
		 * */
		return "jsp/test/signup";
	}
	
	/**
	 * 이 주소로 POST방식으로 들어오게 되면 이 메소드를 실행시켜줌<br>
	 * http://localhost:8080/springproject/test<b>/signup</b>
	 * POST방식이므로 FORM으로 SUBMIT하거나 AJAX로밖에 접근 불가.<br>
	 * <br>
	 * Annotation ModelAttribute<br>
	 * Url로부터 제공 받는 id와 똑같은 변수를 가진 dto를 만들어서 다음과 같이 사용하게 되면<br>
	 * 자동으로 값을 넣어줌<br>
	 * 테스트 해보면 됨.<br>
	 * */
	@RequestMapping(value = "/signup", method=RequestMethod.POST)
	public String postSignUpUrl(@ModelAttribute TestDto test, @RequestParam(value = "efile", required = false) MultipartFile file, HttpServletRequest request) {
		/**
		 * 주소로 redirect시켜줌
		 * 이때 주의점은 jsp가 아니고 주소로 연결시켜주기 때문에 RequestMapping에 작성된 주소로 연결된다는 것임.
		 * 
		 * */
		log.debug(test.toString());
//		AutoFileSaving.OnefileClassficationing(file, request.getSession().getServletContext().getRealPath("/") + "/resources/files/savefiles/");
//		testService.proc(test);
		return "redirect:./signup";
	}
	
	
}
