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
			case "suc" :
			case "success" :
				$("#diaLogInformation").text("Success");
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
	$.loadingImgCall = function(selector){
		var width = 0;
		var height = 0;
		var black_width = $('body').width();
		var black_height = $(window).height()-1;
		var left = 0;
		var top = 0;
		width = 50;
		height = 50;
		top = ($(window).height() - height) / 2
				+ $(window).scrollTop();
		left = ($(window).width() - width) / 2
				+ $(window).scrollLeft();
		if ($("#loading_image").length != 0) {
			$("#loading_image").css({
				"top" : top + "px",
				"left" : left + "px"
			});
			$("#loading_image").show();
		} else {
			$('body').append("<div id = 'loading_black_window' style='position:absolute; top: 0px; width:" +
					black_width +
					"px; height:" +
					black_height +
					"px; background-color:black; opacity:0.2; z-index:9998;'>")
			$('body')
					.append(
							'<div id="loading_image" style="position:absolute; top:'
							+ top
							+ 'px; left:'
							+ left
							+ 'px; width:'
							+ width
							+ 'px; height:'
							+ height
							+ 'px; z-index:9999; margin:auto; padding:0; "><img src="/emp/img/loading.gif" style="width:50px; height:50px;"></div>');
		}
	}
	$.loadingImgCallClose = function(){
		$("#loading_image").remove();
		$("#loading_black_window").remove();
	}
	
	
});