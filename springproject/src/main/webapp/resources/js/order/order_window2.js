/**
 * 발주 회사 수정 JS파일
 */
$(function() {
	var thisPage = 1;
	var whereValue = null;
	var query = null;
	
	//검색 버튼 클릭시
	$("#serachCompany").on("click",function(){
			thisPage = 1;
			whereValue = $("#where").val();
			query = $("#searchQuery").val();
			dataCall();
	});
	
	//데이터 호출
	function dataCall(){
		var requestData = 'action=modify_company&page='+thisPage+'&search='+whereValue+'&query='+query;
		$.ajax({
			type : 'GET', 
			url : './order/deatil',
			data : requestData,
			dataType : 'html',
			contentType : "html; charset=utf-8",
			success : function(data){
				$("#main_window").html(data);
				$("#searchQuery").val(query);
				$("#where option[value="+whereValue+"]").prop('selected', 'selected').change();
			},
			error : function(data){
				console.log("비 정상적인 에러가 발생하였습니다.  새로고침 해주세요")
			},
			beforeSend : function() {
				$.loadingImgCall();
			},
			complete : function() {
				$.loadingImgCallClose();
			}
		});
	}
	//현재 더블클릭으로 인풋박스를 생성하지 않은 상태
	var inputboxOn = false;
	$(".changeName").on("dblclick",function(){
		if(inputboxOn){
			return false;
		}
		var isId = $(this).hasClass("companyCd");
		if(isId){
			return false;
		}
		var isValue = $(this).text();
		if($(this).hasClass("companyAddress") || $(this).hasClass("companyComment")){
			var box = '<textarea id = "changeVal" class = "form-control">'+isValue+'</textarea>';
		} else{
			var box = '<input type = "text" id = "changeVal" class = "form-control" value="'+isValue+'">';
		}
		$(this).html(box);
		$("#changeVal").focus();
		inputboxOn = true;
	});
	
	$(document).on("focusout","#changeVal",function(){
		if(!inputboxOn){
			return false;
		}
		var isValue = $(this).val();
		$(this).parent().text(isValue);
		inputboxOn = false;
	});
	
	//값 변경 후 엔터키 입력시
	$(document).on("keypress",'#changeVal',function(event){
	     if ( event.which == 13 ) {
	         $('#changeVal').focusout();
	         return false;
	     }
	});
	
	//검색창에 엔터키 입력시
	$(document).on("keypress",'#searchQuery',function(event){
	     if ( event.which == 13 ) {
	         $('#serachCompany').click();
	         return false;
	     }
	});

})