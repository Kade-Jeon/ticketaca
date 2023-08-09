<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/header.jsp"%>
<%
  request.setCharacterEncoding("UTF-8");
%>

<script type="text/javascript">
	function fn_remove_zzim(zzNo) {
		var isConfirm = confirm("상품을 찜 목록에서 삭제하시겠습니까?");
		if (isConfirm) {
			document.location = '${contextPath}/user/removezzim.do?no=' + zzNo;
		} else {
			document.location = '${contextPath}/user/zzimList.do';
		}
	}
	
// 	확인필요 작업안함.
// 	function readURL(input) {
// 	     if (input.files && input.files[0]) {
// 	         var reader = new FileReader();
// 	         reader.onload = function (e) {
// 	             $('#preview').attr('src', e.target.result);
// 	         }
// 	         reader.readAsDataURL(input.files[0]);
// 	     }
</script>
	<div class="container mt-9" style="margin-top: 60px">
		<!--  이 부분에 내용이 들어갑니다. -->
		<div class="row">
		<!-- 메뉴 제목 -->
			<h3>나의 찜 목록</h3>
			
	<c:choose>
		<c:when test="${sessionScope.user == null}">
			<jsp:forward page="/userMain.jsp" />
		</c:when>
		<c:when test="${sessionScope.user != null}">
			<div class="container-fluid">
				<div class="card shadow">
					<div class="card-body">
						<table class="table table-hover" id='board_list'>
							<thead>
							<tr>
							<!-- 분류 이름 -->
							<th class="text-center d-none d-md-table-cell">INDEX</th>
							<th class="text-center d-none d-md-table-cell">찜한날짜</th>
							<th class="text-center d-none d-md-table-cell">상태</th>
							<th class="text-center d-none d-md-table-cell">이미지</th>
							<th class="text-center d-none d-md-table-cell">전시명</th>
							<th class="text-center d-none d-md-table-cell">전시장</th>
							<th class="text-center d-none d-md-table-cell">주소</th>
							<th class="text-center d-none d-md-table-cell">시작일</th>
							<th class="text-center d-none d-md-table-cell">종료일</th>
							<th class="text-center d-none d-md-table-cell">삭제</th>
							</tr>
							</thead>			
				<tbody>
				<c:if test="${empty zzimList}">
					<td colspan="10" style="text-align: center;">찜한 전시가 없습니다.</td>
				</c:if>
				<c:if test="${not empty zzimList }">
					<c:forEach var="zzim" items="${zzimList}" varStatus="status">
						<tr>
						<td class="text-center align-middle d-none d-md-table-cell" style="width:5%;">${status.count}</td>
						<td class="text-center align-middle d-none d-md-table-cell" style="width:8%;">${zzim.zzDate}</td>
						<td class="text-center align-middle d-none d-md-table-cell" style="width:5%;">${zzim.exhAbleBuy}</td>
						<c:if test="${not empty zzim.exhThumbImg && zzim.exhThumbImg !='null'}">
							<td class="text-center align-middle d-none d-md-table-cell" style="width:10%;">
							<input type="hidden" name="image" value="${zzim.exhThumbImg}" />
							<img src="${contextPath}/download.do?exhNo=${zzim.exhNo}&image=${zzim.exhThumbImg}" id="preview" width="90"/></a>
							</td>
						</c:if>
						<td class="text-center align-middle d-none d-md-table-cell"><a href="상세보기주소?exhNo=${zzim.exhNo}">${zzim.exhName}</a></td>
						<td class="text-center align-middle d-none d-md-table-cell" style="width:8%;">${zzim.exhPlace}</td>
						<td class="text-center align-middle d-none d-md-table-cell" style="width:10%;">${zzim.exhAddress}</td>
						<td class="text-center align-middle d-none d-md-table-cell" style="width:8%;">${zzim.exhStrDate}</td>
						<td class="text-center align-middle d-none d-md-table-cell" style="width:8%;">${zzim.exhEndDate}</td>
						<td class="text-center align-middle d-none d-md-table-cell" style="width:6%;">
						<button class="btn btn-danger" onclick="fn_remove_zzim('${zzim.zzNo}')">삭제</button>
						</tr>
						</c:forEach>
					</c:if>	
					</tbody>
				</table>
			</c:when>
		</c:choose>
	</div>
	</div>
	</div>
	</div>
	</div>

<%@ include file="/footer.jsp"%>