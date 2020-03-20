/**
 * 재료 메인 관련 js
 */
$(function() {

	/**
	 * 재료 등록 버튼 클릭시 팝업창 호출
	 */
	var isRegistEnd = false;

	$("#registBtn").on("click", registPopUp);
	listCount();
	listCall();
	$page = $("#pageInput");
	
	/**
	 * 페이지 숫자 수동 조절시 리스트 호출(포커스가 끝났을 때 실행)
	 */
	$("#pageInput").on("focusout", listCall);
	
	/**
	 * 삭제 버튼 클릭시 삭제 프로세스 호출
	 */
	$("#deleteBtn").on("click", function(){
		$checkBox = $(".matCheck");
		var checkCount = 0;
		var checkArray = Array();
		for(i=0;i < $checkBox.length;i++) {
			var checkItem = Array();
		    if ($checkBox[i].checked == true){
		    	//matNo 재료 번호 취득
		    	checkItem[0] = $checkBox.eq(i).val();
		    	//matName 재료 이름 취득
		    	checkItem[1] = $checkBox.eq(i).parent().next().text();
		    	checkArray[checkCount] = checkItem;
		        checkCount++;
		    }
		}
		if(checkCount == 0){
			alert("삭제할 항목을 선택하여 주십시오.");
			return false;
		}
		if(checkCount == 1){
			if(confirm("해당 항목 "+checkArray[0][1]+"을(를) 정말로 삭제하시겠습니까?")){
				deleteProc(checkArray);
				listCount();
				listCall();
			}
			return false;
		}
		if(confirm("해당 항목 "+checkCount+"개를 정말로 삭제하시겠습니까?")){
			deleteProc(checkArray);
			listCount();
			listCall();
		}
		return false;
	});
	
	/**
	 * 페이지 이전 버튼 클릭
	 */
	$("#prev").on("click",function(){
		if(pageNumCheck($page.val(),"1")){
			pageNum = ($page.val()*1-1);
			$page.val(pageNum);
			listCount();
			listCall();
		}
		
	});
	/**
	 * 페이지 이후 버튼 클릭
	 */
	$("#next").on("click",function(){
		if(pageNumCheck($page.val(),"2")){
			pageNum = ($page.val()*1+1);
			$page.val(pageNum);
			listCount();
			listCall();
		}
	});
	
})

function pageNumCheck(pageval,isButton){
	if((pageval == "1" && isButton == "1") || (pageval <= "0" && isButton=="3")){
		alert("첫 페이지 입니다");
		$page.val(1);
		return false;
	}
	if((pageval == pageCount && isButton == "2") || (pageval > pageCount && isButton=="3")){
		alert("마지막 페이지 입니다");
		$page.val($("#allPageCount").val());
		return false;
	}
	return true;
}

/**
 * 재료 팝업창 종료시 이벤트(등록을 해서 종료가 된 것인지 혹은 그냥 종료한 것인지)
 */
function isRegistCheck() {
	if (isRegistEnd) {
		alert("정상적으로 등록이 완료되었습니다");
		if (confirm("이어서 등록하시겠습니까?")) {
			registPopUp();
		} else {
			isRegistEnd = false;
		}
		listCount();
		listCall();
	}
}
/**
 * 팝업창 호출
 */
function registPopUp() {
	isRegistEnd = false;
	var regPopup = window
			.open('./material/regist', null,
					'width=400,height=500,toolbar=no,scrollbars=no,menubar=no,resizable=no');

	var interval = window.setInterval(function() {
		try {
			if (regPopup == null || regPopup.closed) {
				window.clearInterval(interval);
				listCall();
				isRegistCheck();
			}
		} catch (e) {
		}
	}, 1000);
}
/**
 * 재료 팝업창 종료의 정상 종료 여부 
 */
function setRegistEnd(bool) {
	isRegistEnd = bool;
}
//start확인용 변수
var isInit = false;
/**
 * 리스트 가져오기(html형식으로 가져오기)
 */
var pageNum = 1;
function listCall(){
	if(isInit){
		pageNum = $("#pageInput").val();
		if(!pageNumCheck(pageNum*1,"3")){
			return false;
		};
	}
	$.ajax({
		type : 'GET', 
		url : './material/list-proc',
		data : 'page='+pageNum,
		dataType : 'html',
		contentType : "html; charset=utf-8",
		success : function(data){
			//console.log(data);
			$("#material_list").html(data);
			isInit = true;
		},
		error : function(data){
			console.log("비 정상적인 에러가 발생하였습니다. 재료 리스트 로딩에 실패하였습니다.")
		}
	});
}
/**
 * 카운트 가져오기(text형식으로 가져오기)
 */
var pageCount = 1;
function listCount(){
	$.ajax({
		type : 'GET', 
		url : './material/list-count',
		dataType : 'text',
		contentType : "text/plain; charset=utf-8",
		success : function(data){
			//console.log(data);
			pageCount = data*1;
			$("#allPageCount").val(pageCount);
		},
		error : function(data){
			console.log("비 정상적인 에러가 발생하였습니다. 재료 리스트 로딩에 실패하였습니다.")
		}
	});
}

function deleteProc(arrayData){
	var jsonData = Array();
	for(var i = 0; i <arrayData.length ; i++){
		var datas = {
				"matNo" : arrayData[i][0],
				"matName" : arrayData[i][1]
			}
		jsonData[i] = datas;
		$.ajax({
			type : 'POST', 
			url : './material/delete-proc',
			data : JSON.stringify(jsonData),
			dataType : 'json',
			contentType : "application/json; charset=utf-8",
			success : function(data){
				//console.log(data);
				alert(data + " 개의 데이터를 정상적으로 삭제하였습니다!");
			},
			error : function(data){
				console.log("비 정상적인 에러가 발생하였습니다. 재료 삭제에 실패하였습니다.")
			}
		});
	}
	
}

