<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div id = "detailContents">
		<div id="companyList">
			<table>
				<thead>
					<tr>
						<th colspan="2">발주 회사 정보</th>
						<td colspan="1">발주 회사 찾기</td>
						<td colspan="2"><input type = "text"/></td>
						<td colspan="1"><button>찾기</button></td>
					</tr>
					<tr>
						<th>발주 회사 코드</th>
						<th>발주 회사명</th>
						<th>발주 회사 전화번호</th>
						<th>발주 회사 주소</th>
						<th>발주 회사 비고</th>
						<th>변경/삭제</th>
					</tr>
				</thead>
				<tbody>
				
				
				</tbody>
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
