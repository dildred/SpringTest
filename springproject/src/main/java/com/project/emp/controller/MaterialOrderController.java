package com.project.emp.controller;

import java.util.HashMap;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.project.emp.dto.MaterialOrderDto;
import com.project.emp.dto.OrderCompanyDto;
import com.project.emp.other.annotation.Window;
import com.project.emp.other.annotation.Process;
import com.project.emp.service.MaterialOrderService;

/**
 * 발주 관련 컨트롤러<br>
 * URL : http://localhost:8080/emp<b>/order~</b>
 * */
@Controller
@RequestMapping("/order")
public class MaterialOrderController {
	
	/**
	 * 로그를 작성하기 위해 가져옴
	 * */
	private Logger log = LoggerFactory.getLogger(MaterialOrderController.class);
	
	/**
	 * jsp파일 들어있는 고정 폴더명
	 * */
	private final String defaultFolder = "jsp/order/";
	
	@Autowired
    private MaterialOrderService materialOrderService;
	
	
	/**
	 * 발주 메인 웹 페이지 호출
	 * */
	@RequestMapping(method = RequestMethod.GET)
	@Window("Material Order Main")
	public ModelAndView mainWndOpen(ModelAndView model) {
		//jsp를 뷰로 설정함
		model.setViewName(defaultFolder+"order_main");
		return model;
	}
	
	/**
     * 발주 내역 등록 버튼 클릭했을 때
     * */
    @RequestMapping(value = "/deatil", params = "action=regist",method = RequestMethod.GET)
    @Window("Material Order Regist")
    public ModelAndView detailWindowVerRegist(ModelAndView model) {
        //jsp를 뷰로 설정함
        model.setViewName(defaultFolder+"order_detail");
        return model;
    }
    
    /**
     * 발주 회사 수정 버튼 클릭했을 때
     * */
    @RequestMapping(value = "/deatil", params = "action=modify_company",method = RequestMethod.GET)
    @Window("Order Company Modify")
    public ModelAndView detailWindowVerModify(ModelAndView model) {
        //jsp를 뷰로 설정함
        model.setViewName(defaultFolder+"order_company");
        return model;
    }
    
    /**
     * 발주 내역 등록할 때
     * */
    @RequestMapping(value = "/regist", method = RequestMethod.POST)
    @ResponseBody
    @Process("Material Order Regist")
    public ResponseEntity<HashMap<String, String>> registMaterialOrder(@RequestBody List<MaterialOrderDto> materialOrderDtoList) {
        Integer successCode = materialOrderService.registMatOrder(materialOrderDtoList);
        HashMap<String, String> resultMap = materialOrderService.registOrderServiceResultMap(successCode);
        return new ResponseEntity<HashMap<String, String>>(resultMap,HttpStatus.OK);
    }
    
    /**
     * 데이터 호출(회사 코드)
     * */
    @RequestMapping(value = "/dataCall", params = "action=companyCd", method = RequestMethod.GET)
    @ResponseBody
    @Process("Company Data Call")
    public ResponseEntity<OrderCompanyDto> dataCall(@RequestParam("val") String companyCd) {
        OrderCompanyDto orderDto;
        try {
            orderDto = materialOrderService.callAllOrderCompanyData(companyCd);
            return new ResponseEntity<OrderCompanyDto>(orderDto,HttpStatus.OK);
        } catch (Exception e) {
            //모종의 에러로 데이터 취득 실패시
            return new ResponseEntity<OrderCompanyDto>(orderDto=null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
	
	
}
