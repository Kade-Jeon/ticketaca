<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/header.jsp"%>
<%
  request.setCharacterEncoding("UTF-8");
%>

	<div class="container mt-9" style="margin-top: 60px">

		<!--  이 부분에 내용이 들어갑니다. -->

		<div class="row">
			<!-- 메뉴 제목 -->
			<h3>내 결제내역</h3>

			<!-- 결제 목록 시작 -->
			<div class="container-fluid">
				<div class="card shadow">
					<div class="card-body">
						<table class="table table-hover" id='board_list'>
							<thead>
								<tr>
									<!-- 분류 이름 -->
									<th class="text-center d-none d-md-table-cell">주문번호</th>
									<th class="text-center d-none d-md-table-cell">결제일</th>
									<th class="text-center d-none d-md-table-cell">전시명</th>
									<th class="text-center d-none d-md-table-cell">시작일</th>
									<th class="text-center d-none d-md-table-cell">종료일</th>
									<th class="text-center d-none d-md-table-cell">성인</th>
									<th class="text-center d-none d-md-table-cell">청소년</th>
									<th class="text-center d-none d-md-table-cell">어린이</th>
									<th class="text-center d-none d-md-table-cell">총 주문수량</th>
									<th class="text-center d-none d-md-table-cell">결제금액</th>
									<th class="text-center d-none d-md-table-cell">결제수단</th>
								</tr>
							</thead>
							
								
								<!-- 주문내역 목록 반복 문 -->
								<form action="${contextPath}/user/userPayDetail.do" method="post">
								<tbody>
								<c:choose>
									<c:when test="${empty userOrders}">
									<tr>
									<td colspan="12" style="text-align: center;">결제내역이 없습니다.</td>
<!-- 									<td class="text-center d-none d-md-table-cell"> -->
<!-- 										<p align="center"><b><span style="font-size:11pt;">결제 내역이 없습니다.</span></b></p>	 -->
<!-- 									</td> -->
									</tr>
									</c:when>
									
									<c:when test="${!empty userOrders}" >
									
									<!-- ${contextPath}/user/userPayDetail.do?ord_no=${order.ord_no} -->
									<c:forEach var="orders" items="${userOrders}" >
										<tr>
										<td class="text-center d-none d-md-table-cell">${orders.ordNo}</td>
										<td class="text-center d-none d-md-table-cell">${orders.ordDate}</td>
										<td class="text-center d-none d-md-table-cell">${orders.exhName}</td>
										<td class="text-center d-none d-md-table-cell">${orders.exhStrDate}</td>
										<td class="text-center d-none d-md-table-cell">${orders.exhEndDate }</td>
										<td class="text-center d-none d-md-table-cell">${orders.ordAQnt}</td>
										<td class="text-center d-none d-md-table-cell">${orders.ordYQnt}</td>
										<td class="text-center d-none d-md-table-cell">${orders.ordCQnt}</td>
										<td class="text-center d-none d-md-table-cell">${orders.ordTQnt}</td>
										<td class="text-center d-none d-md-table-cell">${orders.ordPrice}</td>
										<td class="text-center d-none d-md-table-cell">${orders.ordPay}</td>
										</tr>
									</c:forEach>
									</c:when>
								</c:choose>
								</tbody>
								</form>
							
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



	<%@ include file="/footer.jsp"%>