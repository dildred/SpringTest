<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div id = "fixedBox"></div>
<c:if test="${empty materialList }">
	<div id="nothing"
		class="text-center font-weight-bold align-items-center mt-1 py-4">재료가
		존재하지 않습니다.<br>해당 재료를 등록하시겠습니까?</div>
		<div id ="selectBtn" class = "btn-group container">
			<button class = "btn btn-outline-primary" id = "newMaterialInput">등록</button>
			<button class = "btn btn-outline-danger" id ="cancelBtn">취소</button>
		</div>
</c:if>
<c:if test="${!empty materialList }">
			<table class="table table-hover text-center">
				<thead  id ="head-fixed" class="thead-dark">
					<tr>
						<th scope="col">재료명</th>
						<th scope="col">중량 단위</th>
						<th scope="col"></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${materialList }" var="material">
					<tr class = "matList" >
						<td class = "matName" scope="row">${material.matName }</th>
						<td class = "weightUnit">${material.weightUnit }</th>
						<td><button class = "selectOne btn btn-outline-info">선택</button></th>
					</tr>
					</c:forEach>
				</tbody>
			</table>	
</c:if>

