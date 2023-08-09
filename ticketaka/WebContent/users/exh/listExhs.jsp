<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/header.jsp"%>
<% request.setCharacterEncoding("UTF-8"); %> 
<%-- Map의 각각의 값을 추출하여 변수 처리 --%>

<%-- 제목, 내용 등 Content를 뿌려주는 작업에 쓰는 변수 --%>
<c:set  var="exhsList"  value="${exhsMap.exhsList}" />

<%-- 페이징 작업에서 사용하는 변수들 --%>
<c:set  var="totalExhs"  value="${exhsMap.totalExhs}" />
<c:set  var="section"  value="${exhsMap.section}" />
<c:set  var="pageNum"  value="${exhsMap.pageNum}" />

 <style>
   .no-uline {text-decoration:none;}
   .sel-page{text-decoration:none;color:red;}
   .cls1 {text-decoration:none;}
   .cls2{text-align:center; font-size:30px;}
   .text-right {
    text-align: right;
	}
</style>

	<div class="container mt-9" style="margin-top: 60px">

		<!--  이 부분에 내용이 들어갑니다. -->
		
		<div class="row">
			<div class="col-sm-2">
				<h3 class="mt-4">전시 분류</h3>
				<hr>
				<ul class="nav nav-pills flex-column">
					<li class="nav-item"><a class="nav-link text-body" href="${contextPath}/user/listExhs.do">전체 전시</a></li>
					<li class="nav-item"><a class="nav-link text-body" href="${contextPath}/user/listExhsOngoing.do">진행 중 전시</a></li>
					<li class="nav-item"><a class="nav-link text-body" href="${contextPath}/user/listExhsNearingDeadline.do">마감 임박</a></li>
					<li class="nav-item"><a class="nav-link text-body" href="${contextPath}/user/listExhsUpcoming.do">예정 전시</a></li>
					<li class="nav-item"><a class="nav-link text-body" href="${contextPath}/user/listExhsClosing.do">종료 전시</a></li>
				<!-- <li class="nav-item"><a class="nav-link disabled" href="#">Disabled</a></li> -->
				</ul>
<!-- 				<h3 class="mt-4">지역별 분류</h3> -->
<!-- 				<hr> -->
<!-- 				<ul class="nav nav-pills flex-column"> -->
<!-- 					<li class="nav-item"><a class="nav-link text-body" href="#">서울</a></li> -->
<!-- 					<li class="nav-item"><a class="nav-link text-body" href="#">경기</a></li> -->
<!-- 					<li class="nav-item"><a class="nav-link text-body" href="#">인천</a></li> -->
<!-- 					<li class="nav-item"><a class="nav-link text-body" href="#">부산</a></li> -->
<!-- 					<li class="nav-item"><a class="nav-link text-body" href="#">대구</a></li> -->
<!-- 					<li class="nav-item"><a class="nav-link text-body" href="#">광주</a></li> -->
<!-- 					<li class="nav-item"><a class="nav-link text-body" href="#">충청</a></li> -->
<!-- 					<li class="nav-item"><a class="nav-link text-body" href="#">제주</a></li> -->
<!-- 				<li class="nav-item"><a class="nav-link disabled" href="#">Disabled</a></li> -->
<!-- 				</ul> -->
			</div>
			<div class="col-sm-10">
				<div class="card shadow">
					<div class="card-body">
						<h4 class="card-title">전시 목록</h4>
						<table class="table table-hover" id='board_list'>
							<thead>
								<tr>
									<th class="text-center d-none d-md-table-cell">구분</th>
									<th class="text-center d-none d-md-table-cell">전시명</th>
									<th class="text-center d-none d-md-table-cell">장소</th>
									<th class="text-center d-none d-md-table-cell">시작일</th>
									<th class="text-center d-none d-md-table-cell">종료일</th>
								</tr>
							</thead>
					<c:choose>
						<c:when test="${empty exhsList}">											
							<tbody>
								<tr  height="10">
      								<td colspan="5">
         								<p align="center">
											<b><span style="font-size:9pt;">등록된 전시가 없습니다.</span></b>
        								</p>
      								</td>  
    							</tr>
							</tbody>
						</c:when>
					</c:choose>
					<c:choose>
						<c:when test="${!empty exhsList}">
							<c:forEach var="exh" items="${exhsList}">										
							<tbody>
								<tr>
									<td class="text-center d-none d-md-table-cell">${exh.exhStatus}</td>
									<td class="text-center d-none d-md-table-cell"><a href="${contextPath}/user/exhDetail.do?no=${exh.no}">${exh.name}</a></td>
									<td class="text-center d-none d-md-table-cell">${exh.place}</td>
									<td class="text-center d-none d-md-table-cell">${exh.strDate}</td>
									<td class="text-center d-none d-md-table-cell">${exh.endDate}</td>
								</tr>
							</tbody>
							</c:forEach>
						</c:when>
					</c:choose>
						</table>
						<div class="text-right"> <%-- ############ --%>
<%-- 							<a href="${contextPath}/admin/exhAddForm.do"> --%>
<!-- 						        		<input type="button" class="btn btn-outline-warning" value="신규 전시 등록"> -->
<!-- 						    	</a> -->
						    	<a href="${contextPath}/user/listExhs.do">
						        		<input type="button" class="btn btn-outline-warning" value="전체 목록 보기">
						    	</a>
						</div>
						<%-- 여기부터 페이징 기능 --%>
						<%-- JSP view 확인용 코드 --%>
						<c:if test="${totalExhs == null}">
							<ul class="pagination justify-content-center">
								<li class="page-item"><a class="page-link" href="#">이전</a></li>
								<li class="page-item"><a class="page-link" href="#">1</a></li>
								<li class="page-item"><a class="page-link" href="#">다음</a></li>
							</ul>
						</c:if>
						
						<%-- 여기부터 페이징 기능 --%>
						<c:if test="${totalExhs != null}">
							<c:choose>
								<c:when test="${totalExhs > 100}">	<%-- 전시 개수가 100 초과인 경우 --%>
									<c:forEach var="page" begin="1" end="10" step="1" >
									<ul class="pagination justify-content-center">
										<c:if test="${section > 1 && page == 1}" >											
											<li class="page-item"><a class="page-link" href="${contextPath}/user/listExhs.do?section=${section-1}&pageNum=${(section-1) * 10 + 1 }">이전</a></li>
										</c:if>
											<li class="page-item"><a class="page-link" href="${contextPath}/user/listExhs.do?section=${section}&pageNum=${page}">${(section-1) * 10 + page}></a></li>
										<c:if test="${page == 10}" >
											<li class="page-item"><a class="page-link" href="${contextPath}/user/listExhs.do?section=${section-1}&pageNum=${(section+1) * 10 + 1 }">다음</a></li>											
										</c:if>
										</ul>
									</c:forEach>
								</c:when>
								<c:when test="${totalExhs == 100}" >	<%-- 전시 개수가 100개인 경우 --%>
									<c:forEach var="page" begin="1" end="10" step="1" >
										<a class="no-uline" href="#####">${page}</a>
									</c:forEach>
								</c:when>
								
								<c:when test="${totalExhs < 100}" >	<%-- 전시 개수가 100 미만인 경우 --%>
									<c:forEach var="page" begin="1" end="${totalExhs/10 + 1}" step="1" >
										<c:choose>									
											<c:when test="${page == pageNum}">
												<a class="sel-page" href="${contextPath}/user/listExhs.do?section=${section}&pageNum=${page}">${page}</a>
											</c:when>
										<c:otherwise>
											<a class="no-uline"  href="${contextPath }/user/listExhs.do?section=${section}&pageNum=${page}">${page } </a>
										</c:otherwise>
									</c:choose>
								</c:forEach>
							</c:when>
						</c:choose>
					</c:if>
					</div>
				</div>
			</div>
		</div>
	</div>
	<%@ include file="/footer.jsp"%>