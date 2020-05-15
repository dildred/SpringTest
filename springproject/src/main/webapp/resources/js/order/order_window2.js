/**
 * 발주 회사 수정 JS파일
 */
$(function() {
	var thisPage = 1;
	var whereValue = null;
	var query = null;
	
	//검색 버튼 클릭시
	$("#serachCompany").on("click",function(){
			thisPage = 1;
			whereValue = $("#where").val();
			query = $("#searchQuery").val();
			dataCall();
	});
	
	$(".page").on("click",function(){
		thisPage = $(this).text();
		whereValue = $("#ingW").val();
		query = $("#ingS").val();
		thisPage = thisPage.trim();
		dataCall();
});
	
	//데이터 호출
	function dataCall(){
		var requestData = 'action=modify_company&page='+thisPage+'&search='+whereValue+'&query='+query;
		$.ajax({
			type : 'GET', 
			url : './order/deatil',
			data : requestData,
			dataType : 'html',
			contentType : "html; charset=utf-8",
			success : function(data){
				$("#main_window").html(data);
				$("#searchQuery").val(query);
				$("#ingS").val(query);
				$("#ingW").val(whereValue);
				if(whereValue != ""){
					$("#where option[value="+whereValue+"]").prop('selected', 'selected').change();
				}
			},
			error : function(data){
				console.log("비 정상적인 에러가 발생하였습니다.  새로고침 해주세요")
			},
			beforeSend : function() {
				$.loadingImgCall();
			},
			complete : function() {
				$.loadingImgCallClose();
			}
		});
	}
	//현재 더블클릭으로 인풋박스를 생성하지 않은 상태
	var inputboxOn = false;
	$(".changeName").on("dblclick",function(){
		if(inputboxOn){
			return false;
		}
		var isId = $(this).hasClass("companyCd");
		if(isId){
			return false;
		}
		var isValue = $(this).text();
		var maxLength = 0;
		if($(this).hasClass("companyName")){
			maxLength = 30;
		}
		if($(this).hasClass("companyTel")){
			maxLength = 11;
		}
		if($(this).hasClass("companyAddress")){
			maxLength = 50;
		}
		if($(this).hasClass("companyComment")){
			maxLength = 50;
		}
		if($(this).hasClass("companyAddress") || $(this).hasClass("companyComment")){
			var box = '<textarea id = "changeVal" class = "form-control" maxlength="'+maxLength+'">'+isValue+'</textarea>';
		} else{
			var box = '<input type = "text" id = "changeVal" class = "form-control" value="'+isValue+'" maxlength="'+maxLength+'">';
		}
		$(this).html(box);
		$("#changeVal").focus();
		inputboxOn = true;
	});
	
	$(document).off("focusout").on("focusout","#changeVal",function(){
		if(!inputboxOn){
			return false;
		}
		var isValue = $(this).val();
		$(this).parent().text(isValue);
		inputboxOn = false;
	});
	
	//값 변경 후 엔터키 입력시
	$(document).off("keypress").on("keypress",'#changeVal',function(event){
	     if ( event.which == 13 ) {
	         $('#changeVal').focusout();
	         return false;
	     }
	     var val = $(this).val();
	     var length = val.length;
	     var maxlength = $(this).prop("maxlength");
	     if(length > maxlength){
	    	 $(this).val(val.substring(0,maxlength));
	     }
	});
	
	//검색창에 엔터키 입력시
	$(document).off("keypress").on("keypress",'#searchQuery',function(event){
	     if ( event.which == 13 ) {
	         $('#serachCompany').click();
	         return false;
	     }
	});
	
	//수정 버튼 클릭시
	$(".changeCompany").on("click",function(){
		$modifyData = $(this).parents(".comData");
		var companyCd = $modifyData.find(".companyCd").text();
		//회사코드가 입력이 안되어 있다면
		if (companyCd.length < 1) {
			$.errMsgProc("회사 코드를 입력해주세요");
			return false;
		}
		var companyName = $modifyData.find(".companyName").text();
		//회사명이 입력이 안되어 있다면
		if (companyName.length < 1) {
			$.errMsgProc("회사명을 입력해주세요");
			return false;
		}
		var companyTel = $modifyData.find(".companyTel").text();
		//회사번호가 입력이 안되어 있다면
		if (companyTel.length < 1) {
			$.errMsgProc("회사전화번호를 입력해주세요");
			return false;
		}
		var companyAddress = $modifyData.find(".companyAddress").text();
		//회사주소가 입력이 안되어 있다면
		if (companyAddress.length < 1) {
			$.errMsgProc("회사주소를 입력해주세요");
			return false;
		}
		var companyComment = $modifyData.find(".companyComment").text();
		var datas = {
				"companyCd" : companyCd.trim(),
				"companyName" : companyName.trim(),
				"companyTel" : companyTel.trim(),
				"companyAddress" : companyAddress.trim(),
				"companyComment" : companyComment.trim(),
			}
		$.ajax({
			type : 'POST',
			url : './order/company?action=modify',
			data : JSON.stringify(datas),
			contentType : "application/json; charset=utf-8",
			success : function(data) {
				if(data.success!=null){
					$.errMsgProc(data.success, "suc");
				}
				if(data.error!=null){
					$.errMsgProc(data.error, "err");
				}
			},
			error : function(data) {
				$.errMsgProc("데이터를 수정하던 중 에러가 발생하였습니다. 새로고침 해주세요.", "err");
			},
			beforeSend : function() {
				$.loadingImgCall();
			},
			complete : function() {
				$.loadingImgCallClose();
			}
		});
	});
	
	//삭제 버튼 클릭시
	$(".deleteCompany").on("click",function(){
		$modifyData = $(this).parents(".comData");
		var companyCd = $modifyData.find(".companyCd").text();
		//회사코드가 입력이 안되어 있다면
		if (companyCd.length < 1) {
			$.errMsgProc("회사 코드를 입력해주세요");
			return false;
		}
		var datas = {
				"companyCd" : companyCd.trim()
			}
		$.ajax({
			type : 'POST',
			url : './order/company?action=delete',
			data : JSON.stringify(datas),
			contentType : "application/json; charset=utf-8",
			success : function(data) {
				if(data.success!=null){
					$.errMsgProc(data.success, "suc");
				}
				if(data.error!=null){
					$.errMsgProc(data.error, "err");
				}
				dataCall();
			},
			error : function(data) {
				$.errMsgProc("데이터를 수정하던 중 에러가 발생하였습니다. 새로고침 해주세요.", "err");
			},
			beforeSend : function() {
				$.loadingImgCall();
			},
			complete : function() {
				$.loadingImgCallClose();
			}
		});
	});

})