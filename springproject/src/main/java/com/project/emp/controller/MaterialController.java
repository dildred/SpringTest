package com.project.emp.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.project.emp.dto.MaterialDto;
import com.project.emp.other.CodeMap;
import com.project.emp.service.MaterialService;

/**
 * 재료 관련 컨트롤러<br>
 * URL : http://localhost:8080/emp<b>/material~</b>
 * */
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
	 * 재료 메인 웹 페이지 호출
	 * */
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView mainWndOpen(ModelAndView model) {
		log.info("※========Material Main Window 호출함=========※");
//		List<MaterialDto> materialList = materialService.getMaterialList();
		//jsp를 뷰로 설정함
		model.setViewName(defaultFolder+"material_main");
//		model.addObject("materialList", materialList);
		return model;
	}
	
	/**
	 * 리스트 페이지 호출
	 * */
	@RequestMapping(value = "/list-proc", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView listProc(ModelAndView model, @RequestParam String page) {
		log.info("==========Material List 프로세스 실행==========");
		if(page==null) {
			page = "1";
		}
		List<MaterialDto> materialList = materialService.getMaterialList(page);
		model.setViewName(defaultFolder+"material_list");
		model.addObject("materialList", materialList);
		log.info("==========Material List 프로세스 실행 완료==========");
		return model;
	}
	
	/**
	 * 전체 페이지 갯수
	 * */
	@RequestMapping(value = "/list-count", method = RequestMethod.GET)
	@ResponseBody
	public String listCount() {
		log.info("==========Material Count 호출=========");
		int count = materialService.getPageAllCount();
		log.info("==========Material Count 호출 완료==========");
		return String.valueOf(count);
	}
	
	
	/**
	 * 재료 등록 웹 페이지 호출
	 * */
	@RequestMapping(value = "/regist", method = RequestMethod.GET)
	public ModelAndView registWndOpen(ModelAndView model, @RequestParam(value = "request", required = false) String matName) {
		log.info("※========Material Regist 호출함=========※");
		if(matName!=null) {
		    model.addObject("matName", matName);
		}
		model.addObject("isRegMod", "regist");
		model.setViewName(defaultFolder+"material_regist");
		return model;
	}

	/**
	 * 재료 변경 웹 페이지 호출
	 * */
	@RequestMapping(value = "/modify", method = RequestMethod.GET)
	public ModelAndView modifyWndOpen(ModelAndView model, @ModelAttribute("matNo") String materialNo, @ModelAttribute("matName") String materialName) {
		log.info("※========Material Modify 호출함=========※");
		if(materialNo!=null){
			MaterialDto modifyData = materialService.getMaterialData(materialNo, materialName);
			model.addObject("modifyDto",modifyData);
		}
		model.addObject("isRegMod", "modify");
		model.setViewName(defaultFolder+"material_regist");
		return model;
	}
	
	/**
     * 재료 검색창 웹 페이지 호출
     * */
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public ModelAndView searchWndOpen(ModelAndView model, @RequestParam(value = "list") String listNo) {
        log.info("※========Material Search 호출함=========※");
        if(listNo.startsWith("list")) {
            //에러 페이지 호출(추후 제작생각)
        }
        model.addObject("list", listNo);
        model.setViewName(defaultFolder+"material_search(popup)");
        return model;
    }
    
    /**
     * 재료 검색버튼 입력시 처리
     * */
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    @ResponseBody
    public ModelAndView materialSearchProc(ModelAndView model, @RequestParam(value = "matName", defaultValue = "") String materialName) {
        log.info("※========Material Search Process 실행=========※");
        List<MaterialDto> material = materialService.getSearchMaterialData(materialName);
        model.addObject("materialList", material);
        model.setViewName(defaultFolder+"search_list");
        log.info("※========Material Search Process 실행 완료=========※");
        return model;
    }
	
	/**
	 * 재료 변경 이름 입력시에 데이터 호출
	 * */
	@RequestMapping(value = "/modify", params="action=modi-data",method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<MaterialDto> modifyDataCall(ModelAndView model, @ModelAttribute("matName") String materialName) {
		log.info("※========Material Modify Data 호출함=========※");
		MaterialDto modifyData = null;
		//이름 값이 비어있는지 체크
		if(CodeMap.isEmpty(materialName)) {
			return new ResponseEntity<MaterialDto>(modifyData,HttpStatus.BAD_REQUEST);
		}
		modifyData = materialService.getMaterialData(materialName);
		
		return new ResponseEntity<MaterialDto>(modifyData,HttpStatus.OK);
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
		Integer successCode = materialService.registMaterialData(material);
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
	
	/**
	 * 재료 삭제하기
	 * */
	@RequestMapping(value = "/delete-proc", method = RequestMethod.POST, produces="application/json")
	@ResponseBody
	public ResponseEntity<Integer> deleteProc(@RequestBody List<MaterialDto> materialList) {
		log.info("==========Material Status DB호출==========");
		Integer endNum = materialService.deleteMaterialData(materialList);
		log.info("==========Material Status 호출 완료==========");
		return new ResponseEntity<Integer>(endNum,HttpStatus.OK);
	}
	
	/**
	 * 재료 변경 프로세스
	 * */
	@RequestMapping(value = "/modify-proc", method = RequestMethod.POST, consumes = "application/json", produces="application/json")
	@ResponseBody
	public ResponseEntity<Integer> modifyProc(@RequestBody MaterialDto material) {
		log.info("==========Material Modify 프로세스 실행==========");
		Integer successCode = materialService.modifyMaterialProc(material);
		log.info("==========Material Modify 프로세스 실행 완료==========");
		return new ResponseEntity<Integer>(successCode,HttpStatus.OK);
	}
	
}
