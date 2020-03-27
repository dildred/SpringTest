<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- JSTL사용 필요한것 알아서 짤라서 사용 -->
<%-- <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> --%>
<%-- <%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %> --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0" >
<title>재료 등록</title>
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
<!-- 공통 처리 -->
<script type="text/javascript" src="<c:url value = "/js/process/process_check.js"/>"></script>
<!-- jsp개별 처리 -->
<c:if test="${isRegMod eq 'regist'}">
	<script type="text/javascript" src="<c:url value = "/js/material/material_insert.js"/>"></script>
</c:if>
<c:if test="${isRegMod eq 'modify'}">
	<script type="text/javascript" src="<c:url value = "/js/material/material_modify.js"/>"></script>
</c:if>


</head>
<body>
<jsp:include page="/WEB-INF/views/jsp/process/header.jsp"></jsp:include>
<div id = "contents" class = "col-12 card">
<!-- 자기자신을 호출하는 form만들었음 대신 POST방식으로 데이터를 전송시킬것임. -->
    <h3 class = "title card-header text-center">재료 <c:if test="${isRegMod eq 'regist'}">등록</c:if><c:if test="${isRegMod eq 'modify'}">변경</c:if></h3>
    <div class="card-body">
    <div class="form-group">
    <label for="matName" class = "col-form-label col-form-label-sm pl-1">재료명</label>
    <input type="text" class="form-control form-control-sm" id="matName"  name = "matName" <c:if test="${!empty modifyDto}">value = "${modifyDto.matName}" disabled</c:if>>
    </div>
    <div class="form-group">
    <label for="weightUnit" class = "col-form-label col-form-label-sm pl-1">재료 중량 단위</label>
    <input type="text" class="form-control form-control-sm" id = "weightUnit" name = "weightUnit" <c:if test="${!empty modifyDto}">value = "${modifyDto.weightUnit}" </c:if>>
    </div>
    <div class="form-group">
    <label for="matStatus" class = "col-form-label col-form-label-sm pl-1">재료 분류</label>
    <select id = "matStatus" class="form-control form-control-sm"></select>
    </div>
    <div class="form-group">
    <label for="etcStatus" class = "col-form-label col-form-label-sm pl-1">기타 시 직접 입력</label>
    <input type="text" class="form-control form-control-sm" id = "etcStatus" name = "matStatus" <c:if test="${!empty modifyDto}">value = "${modifyDto.matStatus}" disabled</c:if>>
    </div>
    </div>
    <div class = "card-footer btn-group">
    <button id = "submitBtn" class = "btn btn-outline-primary"><c:if test="${isRegMod eq 'regist'}">등록</c:if><c:if test="${isRegMod eq 'modify'}">변경</c:if></button>
    <button id = "cancelBtn" class = "btn btn-outline-danger">취소</button></div>
</div>
<jsp:include page="/WEB-INF/views/jsp/process/footer.jsp"></jsp:include>
<c:if test="${!empty modifyDto}">
    <input type="hidden" class="" id = "matNo" name = "matNo" value = "${modifyDto.matNo}">
</c:if>
</body>
</html>
