/**
 * 
 */
$(function(){
	
	//팝업창 사이즈 조절 불가능하게 처리
	$(window).resize(function popResizer(o){
		var width = window.outerWidth;
		var height = window.outerHeight;

		if(width != 416){
			width = 416;
		}
		if(height != 566){
			height = 566;
		}
        window.resizeTo(width,height);
        return;
	});
	
	//재료 입력 후 엔터키 입력시 검색버튼 처리
	$('#matName').on("keypress",function(event){
	     if ( event.which == 13 ) {
	         $('#searchBtn').click();
	         return false;
	     }
	});
	
	
	//검색 버튼 입력시 처리
	$("#searchBtn").on("click",function(){
		$matName = $("#matName").val();
		//상품명이 입력이 안되어 있다면
		if($matName.length<1){
			popErrMsgProc("재료명을 입력해주세요");
			return false;
		}
		var regExp = /[\{\}\[\]\/?.,;:|\)*~`!^\-_+<>@\#$%&\\\=\(\'\"]/gi;
	    if(regExp.test($matName)){
	    	popErrMsgProc("재료명에 특수문자는 입력하실 수 없습니다.");
	    	return false;
	    }
		$.ajax({
			type : 'POST', 
			url : './search',
			data : 'matName='+$matName,
			dataType : "html",
			success : function(data){
				$("#searchResultList").html(data);
			},
			error : function(data){
				popErrMsgProc("비 정상적인 에러가 발생하였습니다. 재료 변경에 실패하였습니다.");
				return false;
			},
			beforeSend : function() {
				$("#searchResultList").html("");
				$.loadingImgCall();
			},
			complete : function() {
				$.loadingImgCallClose();
			}
		});
		return true;
	});
	
	$(document).on("click",".selectOne", function(){
		var selectMatName = $(this).parent().prevAll(".matName").text();
		var selectWeiUnit = $(this).parent().prevAll(".weightUnit").text();
		selectMatName = selectMatName.trim();
		selectWeiUnit = selectWeiUnit.trim();
		var listNum = "#"+$("#selectListNum").val();
		for(var i = 0; i < $(".matName",opener.document).length; i++){
			if($(".matName",opener.document).eq(i).val()==selectMatName){
				popErrMsgProc("이미 등록중인 발주 재료입니다.");
				return false;
			}
		}
		$(listNum, opener.document).find(".matName").val(selectMatName);
		$(listNum, opener.document).find(".matName").prop("disabled",true);
		$(listNum, opener.document).find(".weiUnit").text(selectWeiUnit);
		$(listNum, opener.document).find(".orderQty").val("1");
		$(listNum, opener.document).find(".orderQty").prop("disabled",false);
		$(listNum, opener.document).find(".orderBill").val("0");
		$(listNum, opener.document).find(".orderBill").prop("disabled",false);
		$(listNum, opener.document).find(".orderComment").prop("disabled",false);
		
		self.close();
		
	});
	
	$(document).on("click","#newMaterialInput",function(){
		var matName = $("#matName").val();
		var regPopup = window
			.open('../material/regist?request='+matName, null,
					'width=400,height=500,toolbar=no,scrollbars=no,menubar=no,resizable=no');
	});
	
	$(document).on("click","#cancelBtn",function(){
		if(confirm("재료가 입력되지 않았습니다. 정말 종료하시겠습니까?")){
			self.close();	
		};
	});
	
	
});



function popErrMsgProc(errMsg){
	var errorMessage = "<p>" + errMsg + "</p>"
	$("#errMsg").html(errorMessage);
	$("#popUpErrMsgDiaLog").modal("show");
}
