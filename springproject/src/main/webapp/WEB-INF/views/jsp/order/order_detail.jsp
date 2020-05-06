<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div id = "detailContents">
		<div>발주 회사 코드 <input type = "text" id ="companyCd" name ="companyCd" maxlength="8">
		발주 회사 명<input type = "text" id = "companyName" name ="companyName" maxlength="50" disabled></div>
		<div id = "cdComfirm"></div>
		<div>발주 회사 전화 번호<input type = "text" id = "companyTel"  name ="companyTel"  maxlength="50"  placeholder="전화번호는 - 제외하고 입력해 주세요." disabled></div>
		<div>발주 회사 주소<input type = "text"  id = "companyAddress" name ="companyAddress"  maxlength="200"  disabled></div>
		<div>발주 날짜(Date Picker)<input type = "date"  id = "orderDate" name ="orderDate" ></div>
		<div id = "orderList">
		<label>발주 리스트 작성</label>
		<table id = "listTable">
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
		<tr class = "matList"  id="list1">
			<td><input type = "text"  class = "matName" name ="matName"></td>
			<td><input type = "number" min="1"  class = "orderQty" name ="orderQty" disabled><span class="weiUnit"></span></td>
			<td><input type = "number" min="0" step="100" class = "orderBill" name ="orderBill"  disabled></td>
			<td><input type = "text" class = "orderComment" name ="orderComment" maxlength="100"  disabled></td>
			<td><input type = "button" value = "-" class="listMinusBtn"></td>
		</tr>	
		</table>
		</div>
		발주 리스트 추가
		<input type = "button" value = "+" id="listPlusBtn">
		<div><button id ="submitBtn">발주 등록</button></div>
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
