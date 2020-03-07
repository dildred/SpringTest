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
<script type="text/javascript" src="<c:url value = "/js/material/material_insert.js"/>"></script>


</head>
<body>
<jsp:include page="/WEB-INF/views/jsp/process/header.jsp"></jsp:include>
<div id = "contents">
<!-- 자기자신을 호출하는 form만들었음 대신 POST방식으로 데이터를 전송시킬것임. -->
	<h3>재료 등록</h3>
	<div>재료명</div>
	<div><input type ="text" id = "matName" name = "matName"></div>
	<div>재료 중량 단위</div>
	<div><input type ="text" id = "weightUnit" name = "weightUnit"></div>
	<div>재료 분류</div>
	<div><select id = "matStatus"></select></div>
	<div>기타 시 직접 입력</div>
	<div><input id = "etcStatus" name = "matStatus"></div>
	<div><button id = "submitBtn">등록</button></div>
</div>
<jsp:include page="/WEB-INF/views/jsp/process/footer.jsp"></jsp:include>
</body>
</html>