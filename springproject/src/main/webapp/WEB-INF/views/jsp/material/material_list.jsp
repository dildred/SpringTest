<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:if test="${empty materialList }">
	<div id="nothing"
		class="text-center font-weight-bold align-items-center border-top border-bottom border-secondary mt-1 py-4">재료가
		존재하지 않습니다.</div>
</c:if>
<c:if test="${!empty materialList }">
	<c:forEach items="${materialList }" var="material">
	<div class = "text-center table row border-bottom border-secondary my-0 py-3 mx-auto">
		<div class = "col">
			<input type="checkbox" class = "matCheck" value = "${material.matNo}">
		</div>
		<div class = "col" class = "matName">${material.matName}</div>
		<div class = "col">${material.weightUnit}</div>
		<div class = "col">${material.matStatus}</div>
		<div class = "col">
			<fmt:formatDate pattern="YYYY/MM/dd" value="${material.registDate}"></fmt:formatDate>
		</div>
		<div class = "col">${material.registUser}</div>
		<div class = "col">
			<fmt:formatDate pattern="YYYY/MM/dd" value="${material.updateDate}"></fmt:formatDate>
		</div>
		<div class = "col">${material.updateUser}</div>
	</div>
	</c:forEach>
</c:if>

