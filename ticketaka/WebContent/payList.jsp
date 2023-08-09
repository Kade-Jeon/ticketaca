<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%-- <%@ include file="/header.jsp"%> --%>

<c:set  var="orderList"  value="${orderVO.orderList}" />
<%
  request.setCharacterEncoding("UTF-8");
%>

	<div class="container mt-9" style="margin-top: 60px">

		<!--  이 부분에 내용이 들어갑니다. -->

		<div class="row">
			<!-- 메뉴 제목 -->
			<h3>내 결제내역</h3>
			
			<!-- 분류별 보여주는 기능 
			<!--  <div class="container">
				<ul class="nav justify-content-end">
					<li class="nav-item"><a class="nav-link" href="#">등록한 문의</a></li>
					<li class="nav-item"><a class="nav-link" href="#">전체</a></li>
					<li class="nav-item"><a class="nav-link" href="#">상품</a></li>
					<li class="nav-item"><a class="nav-link" href="#">회원</a></li>
			    	<li class="nav-item"><a class="nav-link disabled" href="#">비활성화 메뉴</a> 
					</li>
				</ul>
			</div> -->

			<!-- 결제 목록 시작 -->
			<div class="container-fluid">
				<div class="card shadow">
					<div class="card-body">
						<table class="table table-hover" id='board_list'>
							<thead>
								<tr>
									<!-- 분류 이름 -->
									<th class="text-center d-none d-md-table-cell">결제일</th>
									<th class="text-center d-none d-md-table-cell">상품명</th>
									<th class="text-center d-none d-md-table-cell">총 주문수량</th>
									<th class="text-center d-none d-md-table-cell">결제수단</th>
									<th class="text-center d-none d-md-table-cell">결제 금액</th>
								</tr>
							</thead>
							<tbody>
								<!-- 주문내역 목록 반복 문 -->
								<form action="${contextPath}/user/userPayDetail.do" method="post">
								<c:choose>
									<c:when test="${empty orderList}">
									<td class="text-center d-none d-md-table-cell">
										<p align="center"><b><span style="font-size:11pt;">결제 내역이 없습니다.</span></b></p>	
									</td>
									</c:when>
									
									<c:when test="${!empty orderList}">
									
									<c:forEach var="order" items="${orderList }" >
										<td class="text-center d-none d-md-table-cell">${order.ord_no}</td>
										<td class="text-center d-none d-md-table-cell"><a href="#">${order.exh_no}</a></td>
										<!-- ${contextPath}/user/userPayDetail.do?ord_no=${order.ord_no} -->
										<td class="text-center d-none d-md-table-cell">${order.ord_tqnt}</td>
										<td class="text-center d-none d-md-table-cell">${order.ord_pay }</td>
										<td class="text-center d-none d-md-table-cell">${order.ord_price}</td>
									</c:forEach>
									</c:when>
								</c:choose>
								</form>
							</tbody>
						</table>
						<!-- 페이징 샘플 -->
						<ul class="pagination justify-content-center">
							<li class="page-item"><a class="page-link" href="#">이전</a></li>
							<li class="page-item"><a class="page-link" href="#">1</a></li>
							<li class="page-item"><a class="page-link" href="#">2</a></li>
							<li class="page-item"><a class="page-link" href="#">3</a></li>
							<li class="page-item"><a class="page-link" href="#">4</a></li>
							<li class="page-item"><a class="page-link" href="#">5</a></li>
							<li class="page-item"><a class="page-link" href="#">6</a></li>
							<li class="page-item"><a class="page-link" href="#">7</a></li>
							<li class="page-item"><a class="page-link" href="#">8</a></li>
							<li class="page-item"><a class="page-link" href="#">9</a></li>
							<li class="page-item"><a class="page-link" href="#">10</a></li>
							<li class="page-item"><a class="page-link" href="#">다음</a></li>
						</ul>
					</div>
				</div>
			</div>
		</div>

	</div>
	<!-- 내용 부분 끝 -->



<%-- 	<%@ include file="/footer.jsp"%> --%>