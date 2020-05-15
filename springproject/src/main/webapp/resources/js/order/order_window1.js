/**
 * 발주 처리 JS파일
 */
$(function() {
	//발주 완료를 확인하는 변수
	var isOrderEnd = false;
	
	var companyCd = "--------------";
	//발주 내역 등록 버튼의 발주 회사 코드 입력시
	$(document).on("focusout", "#companyCd", function() {
		if(companyCd == $(this).val()){
			return false;
		}
		companyCd = $(this).val();
		//코드 비어있을 때 처리
		if (companyCd == null || companyCd == "") {
			companyInfoOpCl(false, null);
			return false;
		}
		//코드 발리데이션 체크 처리
		if (!companyCdValidation(companyCd)) {
			companyInfoOpCl(false, null);
			return false;
		}
		$.ajax({
			type : 'GET',
			url : './order/dataCall',
			data : 'action=companyCd&val=' + companyCd,
			contentType : "application/json; charset=utf-8",
			success : function(data) {
				if (data == null || data == "") {
					companyInfoOpCl(true, null);
					$.errMsgProc("존재하지 않는 회사 코드입니다. 회사 정보를 입력하여 주십시오.", "info")
					$("#companyName").focus();
					return false;
				}
				//				console.log(data);
				companyInfoOpCl(false, data);
			},
			error : function(data) {
				$.errMsgProc("데이터를 불러오는 중 에러가 발생하였습니다. 새로고침 해주세요.", "err")
			},
			beforeSend : function() {
				$.loadingImgCall();
			},
			complete : function() {
				$.loadingImgCallClose();
			}
		});
	})
	//회사 정보 자동 입력 함수(isOpen -> true 회사 정보가 없음, isOpen -> false 회사 정보가 있음);
	function companyInfoOpCl(isOpen, data) {
		$("#companyName").prop("disabled", !isOpen);
		$("#companyTel").prop("disabled", !isOpen);
		$("#companyAddress").prop("disabled", !isOpen);
		if (!isOpen && data != null) {
			$("#companyName").val(data.companyName);
			$("#companyTel").val(data.companyTel);
			$("#companyAddress").val(data.companyAddress);
		} else {
			$("#companyName").val("");
			$("#companyTel").val("");
			$("#companyAddress").val("");
		}
	}
	//발주 이력 등록 버튼을 눌렀을 시에
	$(document).on("click", "#submitBtn", function() {
		if(isOrderEnd){
			$.errMsgProc("이미 완료된 발주입니다.", "err");
			return false;
		}
		var jsonData = Array();
		$companyCd = $("#companyCd").val();
		//회사코드가 입력이 안되어 있다면
		if ($companyCd.length < 1) {
			$.errMsgProc("회사 코드를 입력해주세요");
			$("#companyCd").focus();
			return false;
		}
		$companyName = $("#companyName").val();
		//회사명이 입력이 안되어 있다면
		if ($companyName.length < 1) {
			$.errMsgProc("회사명을 입력해주세요");
			$("#companyName").focus();
			return false;
		}
		$companyTel = $("#companyTel").val();
		//회사번호가 입력이 안되어 있다면
		if ($companyTel.length < 1) {
			$.errMsgProc("회사전화번호를 입력해주세요");
			$("#companyTel").focus();
			return false;
		}
		$companyAddress = $("#companyAddress").val();
		//회사주소가 입력이 안되어 있다면
		if ($companyAddress.length < 1) {
			$.errMsgProc("회사주소를 입력해주세요");
			$("#companyAddress").focus();
			return false;
		}
		$orderDate = $("#orderDate").val();
		//발주 날짜가 입력이 안되어 있다면
		if ($orderDate.length < 1) {
			$.errMsgProc("발주 날짜를 선택해주세요");
			$("#orderDate").focus();
			return false;
		}
		for (var i = 0; i < $(".matName").length; i++) {
			$matName = $(".matName").eq(i).val();
			//발주 상품이 하나라도 입력이 안되어 있다면
			if ($matName.length < 1) {
				$.errMsgProc("발주 상품을 입력해주세요");
				$(".matName").eq(i).focus();
				return false;
			}
			$orderQty = $(".orderQty").eq(i).val();
			//발주 수량이 하나라도 입력이 안되어 있다면
			if ($orderQty.length < 1) {
				$.errMsgProc("발주 수량을 입력해주세요");
				$(".orderQty").eq(i).focus();
				return false;
			}
			$orderBill = $(".orderBill").eq(i).val();
			//발주 금액이 하나라도 입력이 안되어 있다면
			if ($orderBill.length < 1) {
				$.errMsgProc("발주 금액을 입력해주세요");
				$(".orderBill").eq(i).focus();
				return false;
			}
			$orderComment = $(".orderComment").eq(i).val();
			var datas = {
				"companyCd" : $companyCd,
				"companyName" : $companyName,
				"companyTel" : $companyTel,
				"companyAddress" : $companyAddress,
				"orderDate" : $orderDate,
				"matName" : $matName,
				"orderQty" : $orderQty,
				"orderBill" : $orderBill,
				"orderComment" : $orderComment
			}
			jsonData[i] = datas;
		}
//		console.log(jsonData);
		$.ajax({
			type : 'POST',
			url : './order/regist',
			data : JSON.stringify(jsonData),
			contentType : "application/json; charset=utf-8",
			success : function(data) {
				if(data.success!=null){
					isOrderEnd = true;
					$("#companyCd").prop("disabled",true);
					$("#companyName").prop("disabled",true);
					$("#companyTel").prop("disabled",true);
					$("#companyAddress").prop("disabled",true);
					$("#orderDate").prop("disabled",true);
					$(".matName").prop("disabled",true);
					$(".orderQty").prop("disabled",true);
					$(".orderBill").prop("disabled",true);
					$(".orderComment").prop("disabled",true);
					$.errMsgProc(data.success, "suc");
				}
				if(data.error!=null){
					$.errMsgProc(data.error, "err");
				}
			},
			error : function(data) {
				$.errMsgProc("데이터를 등록하던 중 에러가 발생하였습니다. 새로고침 해주세요.", "err");
			},
			beforeSend : function() {
				$.loadingImgCall();
			},
			complete : function() {
				$.loadingImgCallClose();
			}
		});

	});
	
	//발주 리스트 추가 버튼을 클릭시
	$(document).on("click", "#listPlusBtn", function(){
		if(isOrderEnd){
			return false;
		}
		if($(".matList").length >= 10){
			$.errMsgProc("발주 목록은 최대 10개까지 한 번에 입력 가능합니다.", "info");
			return false;
		}
		var ListData = "<tr class = 'matList'><td><input type = 'text'  class = 'matName form-control' name ='matName'></td>"+
			"<td><div class = 'input-group'><input type = 'number' min='1'  class = 'orderQty form-control' name ='orderQty' disabled>"+
			"<div class='input-group-append'><span class='input-group-text weiUnit'></span></div></td>"+
			"<td><div class = 'input-group'><input type = 'number' min='0' step='100' class = 'orderBill form-control' name ='orderBill' disabled>"+
			"<div class='input-group-append'><span class='input-group-text' >₩</span></div></td>"+
			"<td><input type = 'text' class = 'orderComment form-control' name ='orderComment' maxlength='100' disabled></td>"+
			"<td><input type = 'button' value = '-' class='listMinusBtn form-control'></td></tr>"
			$("#listTable").append(ListData);
		listNumAddition();
	});
	
	//재료 구분 리스트에 아이디 추가
	function listNumAddition(){
		for(var i = 1; i < $(".matList").length+1; i++){
			$(".matList").eq(i-1).attr("id","list"+i);
		}
	}
	
	//재료 이름을 클릭했을 때
	$(document).on("click",".matName",function(){
		var regPopup = window
		var listNo = $(this).parents(".matList").attr("id");
		window.open('./material/search?list='+listNo, null,
				'width=400,height=500,toolbar=no,scrollbars=no,menubar=no,resizable=no');
		
	})
	
	//각 발주 항목의 발주 리스트 삭제 버튼을 클릭시
	$(document).on("click", ".listMinusBtn", function(){
		if(isOrderEnd){
			return false;
		}
		if($(".matList").length < 2){
			$.errMsgProc("발주 항목은 더 이상 지울 수 없습니다.", "info");
			return false;
		}
		$(this).parents(".matList").remove();
		listNumAddition();
	});

	//발주 상품 입력 창에 키입력 하지 않게 처리
	$(document).on("keydown focusout",".matName",function(){
		$(this).val("");
	})

	function companyCdValidation(companyCd) {
		var regExp = /^[a-z0-9]{8}$/g;
		if (!regExp.test(companyCd)) {
			$.errMsgProc("코드는 소문자와 숫자 8자리로만 구성되어 있습니다.", "err");
			return false;
		}
		return true;
	}

})