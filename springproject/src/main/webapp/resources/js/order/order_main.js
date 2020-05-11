/**
 * 창 확인을 위한 처리(현재 어떤 창이 켜져있는지 확인)
 */
var RegistWindow = false;
var companyModifyWindow = false;
var orderConfirmWindow = false;
$(function(){
	
	//발주 내역 등록 버튼 클릭시 order_detail창을 열어줌
	$("#orderRegist").on("click",function(){
		if(!RegistWindow){
			$.ajax({
				type : 'GET', 
				url : './order/deatil',
				data : 'action=regist',
				dataType : 'html',
				contentType : "html; charset=utf-8",
				success : function(data){
//					console.log(data);
					$("#main_window").html(data);
					RegistWindow = true;
					companyModifyWindow = false;
					orderConfirmWindow = false;
				},
				error : function(data){
					console.log("비 정상적인 에러가 발생하였습니다.  새로고침 해주세요")
				}
			});
		}
	});
	
	//발주 회사 수정 버튼 클릭시 order_company창을 열어줌
	$("#companyModify").on("click",function(){
		if(!companyModifyWindow){
			$.ajax({
				type : 'GET', 
				url : './order/deatil',
				data : 'action=modify_company',
				dataType : 'html',
				contentType : "html; charset=utf-8",
				success : function(data){
//					console.log(data);
					$("#main_window").html(data);
					RegistWindow = false;
					companyModifyWindow = true;
					orderConfirmWindow = false;
				},
				error : function(data){
					console.log("비 정상적인 에러가 발생하였습니다.  새로고침 해주세요")
				}
			});
		}
	});
	
	
})