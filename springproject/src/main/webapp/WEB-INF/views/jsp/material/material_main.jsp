<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- JSTL사용 필요한것 알아서 짤라서 사용 -->
<%-- <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> --%>
<%-- <%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %> --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
<script type="text/javascript" src="<c:url value = "/js/material/material_main.js"/>"></script>
<!-- css개별 처리 -->
<link rel="stylesheet" href="<c:url value = "/css/material/material_main.css"/>">

</head>
<body>
<jsp:include page="/WEB-INF/views/jsp/process/header.jsp"></jsp:include>
<div id = "contents">
	<div id="list_header" class = "text-light text-center font-weight-bold row align-items-start table-primary border-bottom border-secondary mt-2 py-3 mx-auto">
		<div class = "col"></div>
		<div class = "col">재료명</div>
		<div class = "col">재료 중량 단위</div>
		<div class = "col">재료 분류</div>
		<div class = "col">재료 등록일</div>
		<div class = "col">재료 등록자</div>
		<div class = "col">재료 수정일</div>
		<div class = "col">재료 수정자</div>
    </div>
	<div id="material_list">
		
	</div>
	<div id = "contents-footer" class = "mt-2">
		<div class="float-left">
		<button id = "registBtn" class = "btn btn-success">신규 재료 등록</button>
		<button id = "modifyBtn" class = "btn btn-warning">재료 수정 및 변경</button>
		<button id = "deleteBtn" class = "btn btn-danger">재료 삭제</button>
		</div>
		<div id = "pageContainer" class="float-right form-row">
		<button id="prev" class = "btn btn-info mr-2">&lt;</button>
		<input type = "number" id = "pageInput" value="1" class = "form-control mr-1 col-2 text-center"><input type = "text" id = "slash" value = "/" class = "col-1 border-0 text-center" disabled><input type = "text" id = "allPageCount" disabled class = "col-2 border-0 text-center">
		<button id="next" class = "btn btn-info">&gt;</button>
		</div>
	</div>
</div>
<jsp:include page="/WEB-INF/views/jsp/process/footer.jsp"></jsp:include>
</body>
</html>
