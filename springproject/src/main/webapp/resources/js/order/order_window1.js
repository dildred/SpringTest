/**
 * 발주 처리 JS파일
 */
$(function() {

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
		return false;
		console.log(jsonData);
		$.ajax({
			type : 'POST',
			url : './order/regist',
			data : JSON.stringify(jsonData),
			contentType : "application/json; charset=utf-8",
			success : function(data) {

			},
			error : function(data) {
				$.errMsgProc("데이터를 등록하던 중 에러가 발생하였습니다. 새로고침 해주세요.", "err");
			}
		});

	});
	
	//발주 리스트 추가 버튼을 클릭시
	$(document).on("click", "#listPlusBtn", function(){
		if($(".matList").length >= 10){
			$.errMsgProc("발주 목록은 최대 10개까지 한 번에 입력 가능합니다.", "info");
			return false;
		}
		var ListData = "<tr class = 'matList'><td><input type = 'text'  class = 'matName' name ='matName'></td>"+
			"<td><input type = 'number' min='1'  class = 'orderQty' name ='orderQty'></td>"+
			"<td><input type = 'number' min='0' step='100' class = 'orderBill' name ='orderBill'></td>"+
			"<td><input type = 'text' class = 'orderComment' name ='orderComment' maxlength='100'></td>"+
			"<td><input type = 'button' value = '-' class='listMinusBtn'></td></tr>"
			$("#listTable").append(ListData);
			
	});
	$(document).on("click",".matName",function(){
		var regPopup = window
		.open('./material/search', null,
				'width=400,height=500,toolbar=no,scrollbars=no,menubar=no,resizable=no');
		
	})
	
	//각 발주 항목의 발주 리스트 삭제 버튼을 클릭시
	$(document).on("click", ".listMinusBtn", function(){
		if($(".matList").length < 2){
			$.errMsgProc("발주 항목은 더 이상 지울 수 없습니다.", "info");
			return false;
		}
		$(this).parents(".matList").remove();
			
	});

	function companyCdValidation(companyCd) {
		var regExp = /^[a-z0-9]{8}$/g;
		if (!regExp.test(companyCd)) {
			$.errMsgProc("코드는 소문자와 숫자 8자리로만 구성되어 있습니다.", "err");
			return false;
		}
		return true;
	}

})