<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script type="text/javascript" src="<c:url value = "/js/order/order_window2.js"/>"></script>
<div id = "detailContents">
		<div id="companyList">
			<table  class="table  table-bordered  table-hover">
			<caption>더블클릭으로 원하는 값을 바꾼 후 수정 버튼으로 변경 가능</caption>
				<thead>
					<tr>
						<th colspan="2" class ="border-0 h2">발주 회사 정보</th>
						<td colspan="3" class ="border-0">
							<input type = "text" id ="searchQuery" class = "form-control col-md-5 float-right"/>
							<select id ="where" class = "custom-select col-md-3 float-right">
								<option value = "searchingCd">코드로 찾기</option>
								<option value = "searchingName">이름으로 찾기</option>
								<option value = "searchingTel">전화번호로 찾기</option>
								<option value = "searchingAddress">주소로 찾기</option>
							</select>
						</td>
						<td colspan="1"  class ="border-0"><button id = "serachCompany" class = "btn btn-outline-success">찾기</button></td>
					</tr>
					<tr>
						<th scope="col" class = "table-info">발주 회사 코드</th>
						<th scope="col" class = "table-info">발주 회사명</th>
						<th scope="col" class = "table-info">발주 회사 전화번호</th>
						<th scope="col" class = "table-info">발주 회사 주소</th>
						<th scope="col" class = "table-info">발주 회사 비고</th>
						<th scope="col" class = "table-info">변경/삭제</th>
					</tr>
				</thead>
				<tbody>
				<c:if test="${empty CompanyData}">
						<tr>
							<td colspan ="6">해당 데이터가 존재하지 않습니다.</td>
						</tr>
					</c:if>
					<c:if test="${!empty CompanyData}">
						<c:forEach items="${CompanyData }" var="data">
						<tr>
							<td scope="col"  class = "changeName companyCd table-light">${data.companyCd }</td>
							<td scope="col"  class = "changeName companyName table-light">${data.companyName }</td>
							<td scope="col"  class = "changeName companyTel table-light">${data.companyTel }</td>
							<td scope="col"  class = "changeName companyAddress table-light">${data.companyAddress }</td>
							<td scope="col"  class = "changeName companyComment table-light">${data.companyComment }</td>
							<td scope="col"  class = "table-light"><button class = "changeCompany btn btn-outline-warning">수정</button><button class = "deleteCompany btn btn-outline-danger">삭제</button></td>
						</tr>
						</c:forEach>
					</c:if>
				</tbody>
				<c:if test="${!empty CompanyData}">
				<tfoot>
					<tr>
						<td colspan ="6">
							<div id = "paging">
								<c:if test="${paging.prev }"><button type="button" class="btn btn-link">◁</button></c:if>
								<c:forEach begin="${paging.startPage }"  end="${paging.endPage }" step="1" var="num">
									<button type="button" class="btn btn-link">${num }</button>
								<c:if test="${paging.next }"><button type="button" class="btn btn-link">◁</button></c:if>
								</c:forEach>
							</div>
						</td>
					</tr>
				</tfoot>
				</c:if>
			</table>
		</div>
		<div class="modal fade" id = "errMsgDiaLog" tabindex="-1" role="dialog">
	  <div class="modal-dialog modal-dialog-centered" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title text-danger font-weight-bold" id = "diaLogInformation"></h5>
	        <button type="button" class="errMsgClose close" data-dismiss="modal" aria-label="Close">
	          <span aria-hidden="true">&times;</span>
	        </button>
	      </div>
	      <div class="modal-body" id = "errMsg">
	        <p>에러 내용</p>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="errMsgClose btn btn-secondary" data-dismiss="modal">닫기</button>
	      </div>
	    </div>
	 </div>
</div>
</div>
