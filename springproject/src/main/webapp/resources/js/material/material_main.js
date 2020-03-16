/**
 * 재료 메인 관련 js
 */
$(function(){
	
	/**
 	* 재료 등록 버튼 클릭시 팝업창 호출
 	*/
	var isRegistEnd = false;
	$("#registBtn").on("click",registPopUp());

	function registPopUp(){
		isRegistEnd = false;
		var regPopup = window.open('./material/regist'
			    ,null
			    ,'width=400,height=500,toolbar=no,scrollbars=no,menubar=no,resizable=no');
		
		var interval = window.setInterval(function() {
			try {
			    if (regPopup == null || regPopup.closed) {
				window.clearInterval(interval);
				isRegistCheck();
			    }
			}
			catch (e) {
			}
		    }, 1000);
	}
	/**
 	* 재료 팝업창 종료시 이벤트(등록을 해서 종료가 된 것인지 혹은 그냥 종료한 것인지)
 	*/
	function isRegistCheck(){
		if(isRegistEnd){
			alert("정상적으로 등록이 완료되었습니다");
			if(confirm("이어서 등록하시겠습니까?")){
				registPopUp();
			} else {
				isRegistEnd = false;
			}
		}
	}
	/**
 	* 재료 팝업창 종료의 정상 종료 여부 
 	*/
	function setRegistEnd(bool){
		isRegistEnd = bool;
	}
})
