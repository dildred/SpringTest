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
					console.log(data);
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
		
		//발주 내역 등록 버튼의 발주 회사 코드 입력시
		$(document).on("focusout","#companyCd",function(){
			var companyCd = $(this).val();
			if(companyCd == null || companyCd == ""){
				$("#companyName").prop("disabled",true);
				$("#companyName").val("");
				$("#companyTel").prop("disabled",true);
				$("#companyTel").val("");
				$("#companyAddress").prop("disabled",true);
				$("#companyAddress").val("");
				return false;
			}
			$.ajax({
				type : 'GET', 
				url : './order/dataCall',
				data : 'action=companyCd&val='+companyCd,
				contentType : "application/json; charset=utf-8",
				success : function(data){
					if(data==null || data == ""){
						$("#companyName").prop("disabled",false);
						$("#companyName").val("");
						$("#companyTel").prop("disabled",false);
						$("#companyTel").val("");
						$("#companyAddress").prop("disabled",false);
						$("#companyAddress").val("");
						return false;
					}
					console.log(data);
					$("#companyName").prop("disabled",true);
					$("#companyName").val(data.companyName);
					$("#companyTel").prop("disabled",true);
					$("#companyTel").val(data.companyTel);
					$("#companyAddress").prop("disabled",true);
					$("#companyAddress").val(data.companyAddress);
					$("#companyName").focus();
				},
				error : function(data){
					console.log("데이터를 불러오는 중 에러가 발생하였습니다. 새로고침 해주세요.")
				}
			});
		});
		
		$(document).on("click","#submitBtn",function(){
			var jsonData = Array();
			$companyCd = $("#companyCd").val();
			$companyName = $("#companyName").val();
			$orderDate = $("#orderDate").val();
			for(var i = 0; i < $(".matName").length; i++){
				$matName = $(".matName").eq(i).val();
				$orderQty = $(".orderQty").eq(i).val();
				$orderBill = $(".orderBill").eq(i).val();
				$orderComment = $(".orderComment").eq(i).val();
				var datas = {
						"companyCd" : $companyCd,
						"companyName" : $companyName,
						"orderDate" : $orderDate,
						"matName" : $matName,
						"orderQty" : $orderQty,
						"orderBill" : $orderBill,
						"orderComment" : $orderComment
					}
				jsonData[i] = datas;
			}
			$.ajax({
				type : 'POST', 
				url : './order/regist',
				data : JSON.stringify(jsonData),
				contentType : "application/json; charset=utf-8",
				success : function(data){
					
				},
				error : function(data){
					
				}
			});
			
		});
		
	})
	
	
	
	
})