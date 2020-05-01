
$(function(){
//	$("#staff_number").on("focusout",function(){
//		$staff_number =$("#staff_number").val();
//		if($staff_number.length < 1){
//			$("#numErr").text("사원번호를 입력해 주세요.");
//			$("#numErr").show();
//		}
//		if($staff_number.length > 1 && $staff_number.length <= 8){
//			$("#numErr").text("올바른 사원 번호입니다.");
//			$("#numErr").removeClass("text-danger");
//			$("#numErr").addClass("text-success");
//			$("#numErr").fadeOut();
//			//show는 보이게, hide는 안보이게
//		} 
//		if($staff_number.length > 8){
//			$("#numErr").text("사원 번호는 8자 이하로만 작성해 주세요.");
//			$("#numErr").show();
//		} 
//		
//	})
	//등록 버튼 입력시 처리
	$("#submitAdd").on("click",function(){
		$staff_number =$("#staff_number").val();
		$staff_name =$("#staff_name").val();
		$staff_age =$("#staff_age").val();
		$staff_tel =$("#staff_tel").val();
		$staff_id_num =$("#staff_id_num").val();
		$staff_email =$("#staff_email").val();
		$staff_end_degree =$("#staff_end_degree").val();
		$staff_dep =$("#staff_dep").val();
		$staff_level =$("#staff_level").val();
		$staff_pay =$("#staff_pay").val();
		$staff_address =$("#staff_address").val();
		
		var datas ={
				"staffNumber" : $staff_number,
				"staffName" : $staff_name,
				"staffAge" : $staff_age,
				"staffTel" : $staff_tel,
				"staffId_num" : $staff_id_num,
				"staffEmail" : $staff_email,
				"staffEnd_degree" : $staff_end_degree,
				"staffDep" : $staff_dep,
				"staffLevel" : $staff_level,
				"staffPay" : $staff_pay,
				"staffAddress" : $staff_address
		}
		
		//console.log(JSON.stringify(datas));
		
		$.ajax({
			type : 'POST',
			url : './staff/addstaff',
			data : JSON.stringify(datas),
			contentType : "application/json; charset=utf-8",
			success : function(data){
				
			},
			error : function(data){
				
			}
			
		});
		
		
	});
	
})