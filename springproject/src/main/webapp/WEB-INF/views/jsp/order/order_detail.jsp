<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div id = "detailContents">
		<div>발주 회사 코드 <input type = "text" id ="companyCd" name ="companyCd">
		발주 회사 명<input type = "text" id = "companyName" name ="companyName" disabled></div>
		<div id = "cdComfirm"></div>
		<div>발주 회사 전화 번호<input type = "text" id = "companyTel"  name ="companyTel" disabled></div>
		<div>발주 회사 주소<input type = "text"  id = "companyAddress" name ="companyAddress"  disabled></div>
		<div>발주 날짜(Date Picker)<input type = "date"  id = "orderDate" name ="orderDate" ></div>
		<div id = "orderList">
		<label>발주 리스트 작성</label>
		<table>
		<tr>
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
		<tr class = "matList">
			<td><input type = "text"  class = "matName" name ="matName"></td>
			<td><input type = "text"  class = "matName" name ="matName"></td>
			<td><input type = "number" min="1"  class = "orderQty" name ="orderQty"></td>
			<td><input type = "number" min="0" step="100" class = "orderBill" name ="orderBill"></td>
			<td><input type = "text" class = "orderComment" name ="orderComment" maxlength="100"></td>
			<td><input type = "button" value = "-" id="listMinusBtn"></td>
		</tr>	
		</table>
		</div>
		발주 리스트 추가
		<input type = "button" value = "+" id="listPlusBtn">
		<div><button id ="submitBtn">발주 등록</button></div>
</div>
