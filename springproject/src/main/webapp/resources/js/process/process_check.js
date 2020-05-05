/**
 * 
 */
$(function(){
	var URLCheck = location.href;
	if(URLCheck.lastIndexOf("/")==URLCheck.length-1){
		URLCheck = URLCheck.substring(0,URLCheck.lastIndexOf("/"));
		history.pushState(null,null,URLCheck);
	}
	
	//에러메시지 창 클로즈 버튼 눌렀을 때(부트스트랩)
	$(document).on("click",".errMsgClose",function(){
		$("#errMsgDiaLog").modal("hide");
	})
	
	//에러메시지 띄우기(부트스트랩) information -> 에러 정보 ex)err : Error, info : Info, Collect : col
	$.errMsgProc = function(errMsg, information){
		if($("#errMsgDiaLog").length==0){
			return false;
		}
		var errorMessage = "<p>" + errMsg + "</p>"
		$("#errMsg").html(errorMessage);
		switch(information){
			case "col" :
			case "collect" :
				$("#diaLogInformation").text("Collect");
				$("#diaLogInformation").removeClass("text-danger text-warning");
				$("#diaLogInformation").addClass("text-success");
				break;
			case "info" :
			case "information" :
				$("#diaLogInformation").text("Info");
				$("#diaLogInformation").removeClass("text-danger text-success");
				$("#diaLogInformation").addClass("text-warning");
				break;
			case "err" :
			case "error" :
			default :
				$("#diaLogInformation").text("Error");
				$("#diaLogInformation").removeClass("text-success text-warning");
				$("#diaLogInformation").addClass("text-danger");
				break;
		};
		$("#errMsgDiaLog").modal("show");
	}
	
	
});