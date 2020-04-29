<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath }" />   
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>사원 등록창</title>
<!-- 미 변경 목록(JQuery설정, BootStrap설정) -->
<!-- JQuery -->
<script
  src="http://code.jquery.com/jquery-3.3.1.js"
  integrity="sha256-2Kok7MbOyxpgUVvAk/HJ2jigOSYS2auK4Pfzbm7uH60="
  crossorigin="anonymous"></script>
<!-- JQuery UI -->
<script
  src="http://code.jquery.com/ui/1.12.1/jquery-ui.js"
  integrity="sha256-T0Vest3yCU7pafRw9r+settMBX6JkKN06dqBnpQ8d30="
  crossorigin="anonymous"></script>
<!-- BootStrap -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<!-- BootStrap -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>

<style>
	.text_center{
		text-align:center;
	}
</style>
</head>
<body>
	<form method="post" action="/addstaff">
	<h1 class="text_center">사원 등록창</h1>
	<table align="center">
		<tr>
			<td width="200"><p align="right">사번</td>
			<td width="400"><input type="text" name="staff_number"></td>
		</tr>
		<tr>
			<td width="200"><p align="right">이름</td>
			<td width="400"><input type="text" name="staff_name"></td>
		</tr>
		<tr>
			<td width="200"><p align="right">나이</td>
			<td width="400"><input type="text" name="staff_age"></td>
		</tr>
		<tr>
			<td width="200"><p align="right">전화번호</td>
			<td width="400"><input type="text" name="staff_tel"></td>
		</tr>
		<tr>
			<td width="200"><p align="right">주민등록번호</td>
			<td width="400"><input type="text" name="staff_id_num"></td>
		</tr>
		<tr>
			<td width="200"><p align="right">이메일</td>
			<td width="400"><input type="text" name="staff_email"></td>
		</tr>
		<tr>
			<td width="200"><p align="right">학력</td>
			<td width="400"><input type="text" name="staff_end_degree"></td>
		</tr>
		<tr>
			<td width="200"><p align="right">부서</td>
			<td width="400"><input type="text" name="staff_dep"></td>
		</tr>
		<tr>
			<td width="200"><p align="right">직급</td>
			<td width="400"><input type="text" name="staff_level"></td>
		</tr>
		<tr>
			<td width="200"><p align="right">급여</td>
			<td width="400"><input type="text" name="staff_pay"></td>
		</tr>
		<tr>
			<td width="200"><p align="right">주소</td>
			<td width="400"><input type="text" name="staff_address"></td>
		</tr>
		<tr>
			<td width="200"><p>&nbsp;</p></td>
			<td width="400"><input type="submit" value="가입하기"><input type="reset" value="다시입력"></td>
		</tr>
		
	</table>
	</form>

</body>
</html>