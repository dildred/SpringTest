<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- JSTL사용 필요한것 알아서 짤라서 사용 -->
<%-- <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> --%>
<%-- <%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %> --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport"
	content="width=device-width, user-scalable=no, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0">
<title>재료 검색</title>
<!-- 미 변경 목록(JQuery설정, BootStrap설정) -->
<!-- JQuery -->
<script src="http://code.jquery.com/jquery-3.3.1.js"
	integrity="sha256-2Kok7MbOyxpgUVvAk/HJ2jigOSYS2auK4Pfzbm7uH60="
	crossorigin="anonymous"></script>
<!-- JQuery UI -->
<script src="http://code.jquery.com/ui/1.12.1/jquery-ui.js"
	integrity="sha256-T0Vest3yCU7pafRw9r+settMBX6JkKN06dqBnpQ8d30="
	crossorigin="anonymous"></script>
<!-- BootStrap -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<!-- BootStrap -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
	integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
	crossorigin="anonymous"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
	integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
	crossorigin="anonymous"></script>
<!-- 공통 처리 -->
<script type="text/javascript"
	src="<c:url value = "/js/process/process_check.js"/>"></script>
<script type="text/javascript"
	src="<c:url value = "/js/material/material_search.js"/>"></script>
<!-- css개별 처리 -->
<link rel="stylesheet" href="<c:url value = "/css/material/material_search.css"/>">

</head>
<body>
	<div id="contents" class="col-12 card fixed-top">
		<!-- 자기자신을 호출하는 form만들었음 대신 POST방식으로 데이터를 전송시킬것임. -->
		<h3 class="title card-header text-center">재료 검색</h3>
		<div class="card-body mx-auto">
			<div class="form-row align-items-center">
				<div class="col-auto">
					<input type="text" class="form-control col-md-4 sm-3" id="matName"
						name="matName" placeholder="재료명을 입력해주세요">
				</div>
				<div class="col-auto">
					<button id="searchBtn" class="btn btn-outline-primary col-md-2">검색</button>
				</div>
			</div>
		</div>
	</div>
	<div class="modal fade" id="popUpErrMsgDiaLog" tabindex="-1"
				role="dialog">
				<div class="modal-dialog modal-dialog-centered" role="document">
					<div class="modal-content">
						<div class="modal-header">
							<h5 class="modal-title text-danger font-weight-bold">Error</h5>
							<button type="button" class="popUpErrMsgClose close"
								data-dismiss="modal" aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
						</div>
						<div class="modal-body" id="errMsg">
							<p>에러 내용</p>
						</div>
						<div class="modal-footer">
							<button type="button" class="popUpErrMsgClose btn btn-secondary"
								data-dismiss="modal">닫기</button>
						</div>
					</div>
				</div>
			</div>
	<div id = "searchResultList">
				
		</div>
		<input type ="hidden" id = "selectListNum" value ="${list }">
</body>
</html>
