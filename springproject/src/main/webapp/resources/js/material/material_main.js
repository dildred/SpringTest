/**
 * 재료 메인 관련 js
 */
$(function(){
	
	/**
 	* 재료 등록 버튼 클릭시 팝업창 호출
 	*/
	$("#registBtn").on("click",function(){
		window.open('./material/regist'
			    ,null
			    ,'width=400
			    ,height=500
			    ,toolbar=no
			    ,scrollbars=no
			    ,menubar=no
			    ,resizable=no');
	})
})
