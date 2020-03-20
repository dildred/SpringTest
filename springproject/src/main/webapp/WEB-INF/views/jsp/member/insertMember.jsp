<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix ="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix ="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath }"/>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>사원 입력</title>
	<style>
		.text_center{
			text-align:center;
		}
	</style>
</head>
<body>

	<form method="post" action="${contextPath}/member/insertMember.do">
		<h1 class="text_center">사원 입력창</h1>
		<table align="center">
			<tr>
				<td width="200"><p align="right">사원명</td>
				<td width="400"><input type="text" name="name"></td>
			</tr>
			<tr>
				<td width="200"><p align="right">사원명</td>
				<td width="400"><input type="text" name="name"></td>
			</tr>
			<tr>
				<td width="200"><p align="right">사원명</td>
				<td width="400"><input type="text" name="name"></td>
			</tr>
			<tr>
				<td width="200"><p align="right">사원명</td>
				<td width="400"><input type="text" name="name"></td>
			</tr>
		</table>
	</form>
</body>
</html>