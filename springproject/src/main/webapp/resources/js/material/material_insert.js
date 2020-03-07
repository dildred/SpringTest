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
			//데이터가 존재하지 않는다면 기타만 넣기
			if(data!="" && data!=null){
				data.forEach(function(item,index){
					if(index==0){
						$etcStatus.val(item);
					}
					$matStatus.append("<option value = '"+item+"'>"+item+"</option>")
					$etcStatus.prop("disabled",true);
				});
			} 
			$matStatus.append("<option value = '기타'>기타</option>")
			
		},
		error : function(data){
			console.log("비 정상적인 에러가 발생하였습니다. 재료 분류 로딩에 실패하였습니다.")
		}
	});
	$matStatus.on("change",function(){
		$matVal = $matStatus.val();
		if($matVal==etc){
			$etcStatus.val("");
			$etcStatus.prop("disabled",false);
		} else{
			$etcStatus.prop("disabled",true);
			$etcStatus.val($matVal);
		}
	});
	
	$("#submitBtn").on("click",function(){
		$matName = $("#matName").val();
		$weightUnit = $("#weightUnit").val();
		$matStatus = $("#matStatus").val();
		$etcStatus = $("#etcStatus").val();
		if($matStatus=="기타"){
			$matStatus = $etcStatus;
		}
		var datas = {
			"matName" : $matName,
			"weightUnit" : $weightUnit,
			"matStatus" : $matStatus
		}
		$.ajax({
			type : 'POST', 
			url : './regist',
			data : JSON.stringify(datas),
			contentType : "application/json; charset=utf-8",
			success : function(data){
				switch(data){
				/**중복 재료 에러*/
				case 0 : 
					alert("이미 존재하는 재료입니다.");
					break;
				/**데이터 베이스 입력 에러*/
				case 1 : 
					console.log("비 정상적인 에러가 발생하였습니다. 재료 등록에 실패하였습니다.");
					break;
				/**성공*/
				case 2 : 
					alert("등록 완료");
					break;
				}
			},
			error : function(data){
				console.log("비 정상적인 에러가 발생하였습니다. 재료 분류 로딩에 실패하였습니다.")
			}
		});
	});
	
})