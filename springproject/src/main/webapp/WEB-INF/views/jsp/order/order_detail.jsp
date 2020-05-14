<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script type="text/javascript" src="<c:url value = "/js/order/order_window1.js"/>"></script>
<div id = "detailContents">
		<div class = "form-group row" id ="firstRow">
			<div class = "col">
				<label for ="companyCd" class="col col-form-label">발주 회사 코드</label><input type = "text" id ="companyCd"  class = "form-control" name ="companyCd" maxlength="8" tabindex="1">
			</div>
			<div  class = "col">		
				<label for ="companyName"  class="col col-form-label">발주 회사 명</label><input type = "text" id = "companyName"  class = "form-control" name ="companyName" maxlength="50"  disabled>
			</div>
		</div>
		<div class = "form-group row" id ="secondRow">
			<div class = "col-8">
				<label for ="companyTel" class="col col-form-label">발주 회사 전화 번호</label><input type = "text"  class = "form-control" id = "companyTel"  name ="companyTel"  maxlength="50"  placeholder="전화번호는 - 제외하고 입력해 주세요." disabled>
			</div>	
			<div class = "col-4">
				<label for ="orderDate" class="col col-form-label">발주 날짜</label><input type = "text"  class = "form-control"  id = "orderDate" name ="orderDate"  tabindex="3">
			</div>
			<script>
			let now = new Date();
			now.setDate(now.getDate()-90);
			$('#orderDate').daterangepicker({
				"singleDatePicker": true,
				"minYear" : (new Date().getFullYear())-1,
				"maxYear" : new Date().getFullYear(),
				"minDate" : now.toISOString().substring(0,10),
				"locale": {
					"format": "YYYY-MM-DD",
					"separator": " / ",
					"weekLabel": "주",
					"daysOfWeek": [
						"일",
						"월",
						"화",
						"수",
						"목",
						"금",
						"토"
						],
						"monthNames": [
							"1월",
							"2월",
							"3월",
							"4월",
							"5월",
							"6월",
							"7월",
							"8월",
							"9월",
							"10월",
							"11월",
							"12월"
							],
							"firstDay": 1
				},
			});
			</script>
		</div>
		<div class = "form-group row" id ="thirdRow">
			<div class = "col">
				<label for ="companyAddress" class="col col-form-label">발주 회사 주소</label><input type = "text"  class = "form-control"  id = "companyAddress" name ="companyAddress"  maxlength="200"  disabled></div>
			</div>
		<div id = "orderList"  class = "form-group mt-4" >
		<label class="col h4 font-weight-bold text-info">발주 리스트</label>
		<table class = "table">
		<thead>
			<tr id = "orderListTitle">
				<th>
				발주 재료명
				</th>
				<th>
				발주 수량
				</th>
				<th>
				발주 금액(총단가)
				</th>
				<th>
				비고 사항
				</th>
				<th>
				삭제
				</th>
			</tr>
			</thead>	
			<tbody id = "listTable" >
				<tr class = "matList"  id="list1">
					<td><input type = "text"  class = "matName form-control" name ="matName" tabindex="2"></td>
					<td>
					<div class = "input-group">
						<input type = "number" min="1"  class = "orderQty form-control" name ="orderQty" disabled>
						<div class="input-group-append">
    						<span class="input-group-text weiUnit" ></span>
  						</div>
  					</div>
  					</td>
					<td>
					<div class = "input-group">
						<input type = "number" min="0" step="100" class = "orderBill form-control"  name ="orderBill"  disabled>
						<div class="input-group-append">
    						<span class="input-group-text" >₩</span>
  						</div>
  					</div>
						</td>
					<td><input type = "text" class = "orderComment form-control" name ="orderComment" maxlength="100"  disabled></td>
					<td><input type = "button"  class = "listMinusBtn form-control"  value = "-" ></td>
				</tr>	
			</tbody>
			<tfoot>
				<tr>
					<td colspan="4" class = "text-right" id = "plusListMsg">발주 리스트 추가</td>
					<td><input type = "button" class = "form-control" value = "+" id="listPlusBtn"></td>
				</tr>
			</tfoot>
		</table>
		</div>
		<div><button class = "btn btn-success" id ="submitBtn">발주 등록</button></div>
		<div class="modal fade" id = "errMsgDiaLog" tabindex="-1" role="dialog">
	  <div class="modal-dialog modal-dialog-centered" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title text-danger font-weight-bold" id = "diaLogInformation"></h5>
	        <button type="button" class="errMsgClose close" data-dismiss="modal" aria-label="Close">
	          <span aria-hidden="true">&times;</span>
	        </button>
	      </div>
	      <div class="modal-body" id = "errMsg">
	        <p>에러 내용</p>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="errMsgClose btn btn-secondary" data-dismiss="modal">닫기</button>
	      </div>
	    </div>
	 </div>
</div>
</div>
