/**
 * 
 */
$(function(){
	$matStatus = $("#matStatus");
	$etcStatus = $("#etcStatus");
	var etc = "기타";
	//기본 처리 DB에서 재료 분류 관련 데이터 가져오기
	$.ajax({
		type : 'GET', 
		url : './regist-init',
		contentType : "application/json; charset=utf-8",
		success : function(data){
			if(data!="" && data!=null){
				data.forEach(function(item,index){
					if($etcStatus.val()==""){
						if(index==0){
							$etcStatus.val(item);
							$matStatus.append("<option value = '"+item+"'>"+item+"</option>")
							$etcStatus.prop("disabled",true);
						}
					} else{
						if($etcStatus.val() == item){
							//현재 변경중인 아이템 선택
							$matStatus.append("<option value = '"+item+"' selected>"+item+"</option>")
						} else{
							$matStatus.append("<option value = '"+item+"'>"+item+"</option>")
						}
						$etcStatus.prop("disabled",true);
					}
					
				});
			} 
			$matStatus.append("<option value = '기타'>기타</option>")
			
		},
		error : function(data){
			popErrMsgProc("비 정상적인 에러가 발생하였습니다. 재료 분류 로딩에 실패하였습니다.")
		},
		beforeSend : function() {
			$.loadingImgCall();
		},
		complete : function() {
			$.loadingImgCallClose();
		}
	});
	
	//재료 명 입력이 끝나면 재료 정보 가져오기
	$("#matName").on("focusout",function(){
		$matVal = $("#matName").val();
		$("body").focus();
		$.ajax({
			type : 'GET', 
			url : './modify?action=modi-data',
			data : 'matName='+$matVal,
			contentType : "application/json; charset=utf-8",
			success : function(data){
				if(data=="" || data==null){
					popErrMsgProc("존재하지 않는 재료입니다. 다시 입력하여 주십시오.");
					$("#matName").val("");
					return false;
				}
				$matNo = $("body").append("<input type='hidden' class='' id = 'matNo' name = 'matNo' value = "+data.matNo+">");
				$matName = $("#matName").val(data.matName);
				$weightUnit = $("#weightUnit").val(data.weightUnit);
				$("#weightUnit").prop("disabled",false);
				$("#matStatus option[value="+data.matStatus+"]").prop('selected', 'selected').change();
				$etcStatus = $("#etcStatus").val(data.matStatus);
				$("#matName").prop("disabled",true);
				return true;
			},
			error : function(data){
				popErrMsgProc("비 정상적인 에러가 발생하였습니다. 재료 불러오기를 실패하였습니다.");
				return false;
			},
			beforeSend : function() {
				$.loadingImgCall();
			},
			complete : function() {
				$.loadingImgCallClose();
			}
		});
	});
	
	//재료 분류 변경에 따른 처리 -> 기타를 입력하면 직접 입력 가능하게
	$matStatus.on("change",function(){
		$matVal = $("#matStatus").val();
		$etcSta = $("#etcStatus");
		if($matVal==etc){
			$etcSta.val("");
			$etcSta.prop("disabled",false);
		} else{
			$etcSta.prop("disabled",true);
			$etcSta.val($matVal);
		}
		return;
	});
	
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
	
	//등록 버튼 입력시의 처리. 작성된 데이터를 전부 넣은 후 ajax를 통해 데이터 전송함
	$("#submitBtn").on("click",function(){
		$matNo = $("#matNo").val();
		$matName = $("#matName").val();
		$weightUnit = $("#weightUnit").val();
		$matStatus = $("#matStatus").val();
		$etcStatus = $("#etcStatus").val();
		//상품명이 입력이 안되어 있다면
		if($matName.length<1){
			popErrMsgProc("재료명을 입력해주세요");
			return false;
		}
		//중량 단위가 입력이 안되어 있다면
		if($weightUnit.length<1){
			popErrMsgProc("중량단위를 입력해주세요");
			return false;
		}
		//기타인데 기타란에 입력을 하지 않았다면
		if($matStatus=="기타" && $etcStatus.length<1){
			popErrMsgProc("기타시에는 직접 입력해주세요");
			return false;
		}
		var regExp = /[\{\}\[\]\/?.,;:|\)*~`!^\-_+<>@\#$%&\\\=\(\'\"]/gi;
	    if(regExp.test($matName) || regExp.test($weightUnit) || regExp.test($matStatus)){
	    	popErrMsgProc("항목에 특수문자는 입력하실 수 없습니다.");
	    	return false;
	    }
		if($matStatus=="기타"){
			$matStatus = $etcStatus;
		}
		var datas = {
			"matNo" : $matNo,
			"matName" : $matName,
			"weightUnit" : $weightUnit,
			"matStatus" : $matStatus
		}
		$.ajax({
			type : 'POST', 
			url : './modify-proc',
			data : JSON.stringify(datas),
			contentType : "application/json; charset=utf-8",
			success : function(data){
				switch(data){
				/**중복 재료 에러*/
				case 0 : 
					popErrMsgProc("이미 존재하는 재료입니다.");
					break;
				/**데이터 베이스 입력 에러*/
				case 1 : 
					popErrMsgProc("비 정상적인 에러가 발생하였습니다. 재료 등록에 실패하였습니다.");
					break;
				/**성공*/
				case 2 : 
					console.log("변경 완료");
					opener.location.href="javascript:setRegistEnd(true)"
					self.close();	
					break;
				/**상품명이 입력이 안되어 있다면*/
				case 3 :
					popErrMsgProc("재료명을 입력해주세요");
					break;
				/**중량 단위가 입력이 안되어 있다면*/
				case 4 :
					popErrMsgProc("중량단위를 입력해주세요");
					break;
				/**기타인데 기타란에 입력을 하지 않았다면*/
				case 5 :
					popErrMsgProc("기타시에는 직접 입력해주세요");
					break;
				/**상품명, 중량단위, 기타란에 특수문자가 포함되어 있다면*/	
				case 6 :
					popErrMsgProc("항목에 특수문자는 입력하실 수 없습니다.");
					break;
				}
			},
			error : function(data){
				popErrMsgProc("비 정상적인 에러가 발생하였습니다. 재료 변경에 실패하였습니다.")
				return false;
			},
			beforeSend : function() {
				$.loadingImgCall();
			},
			complete : function() {
				$.loadingImgCallClose();
			}
		});
		return true;
	});
	
	//취소 버튼 입력시의 처리. 팝업창을 닫음
	$("#cancelBtn").on("click",function(){
		opener.location.href="javascript:setRegistEnd(false)"
			self.close();	
	});
	
})
function popErrMsgProc(errMsg){
	var errorMessage = "<p>" + errMsg + "</p>"
	$("#errMsg").html(errorMessage);
	$("#popUpErrMsgDiaLog").modal("show");
}
