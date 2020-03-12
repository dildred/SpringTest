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
		<c:if test="${empty materialList }">
		<div id = "nothing" class = "text-center font-weight-bold align-items-center border-top border-bottom border-secondary mt-1 py-4">재료가 존재하지 않습니다.</div>
		</c:if>
		<c:if test="${!empty materialList }">
			<c:forEach items="${materialList }" var="material">
				<div><input type = "checkbox"></div>
				<div>${material.matName}</div>
				<div>${material.weightUnit}</div>
				<div>${material.matStatus}</div>
				<div><fmt:formatDate pattern="YYYY/MM/dd" value="${material.registDate}"></fmt:formatDate></div>
				<div>${material.registUser}</div>
				<div><fmt:formatDate pattern="YYYY/MM/dd" value="${material.updateDate}"></fmt:formatDate></div>
				<div>${material.updateUser}</div>
			</c:forEach>
		</c:if>
	</div>
	<div>
	<button id = "registBtn">신규 재료 등록</button>
	<button id = "modifyBtn">재료 수정 및 변경</button>
	<button id = "deleteBtn">재료 삭제</button>
	</div>
</div>
<jsp:include page="/WEB-INF/views/jsp/process/footer.jsp"></jsp:include>
</body>
</html>
