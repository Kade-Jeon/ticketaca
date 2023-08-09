<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/admins/adminHeader.jsp"%>
<%
	request.setCharacterEncoding("UTF-8");
%>

<div class="container mt-9" style="margin-top: 60px">

<c:choose>
	<c:when test="${sessionScope.adm == null}">
		<jsp:forward page="/admin/" />
	</c:when>
	<c:when test="${sessionScope.adm != null}">
		<div class="row">
		<!-- 메뉴 제목 -->
		<h3>주문 목록</h3>

		<!-- 주문 목록 시작 -->
		<div class="container-fluid">
		<div class="card shadow">
		<div class="card-body">
		<table class="table table-hover table-responsive" id='board_list'>
			<thead>
				<tr>
				<!-- 분류 이름 -->
				<th class="text-center d-none d-md-table-cell">INDEX</th>
				<th class="text-center d-none d-md-table-cell">주문번호</th>
				<th class="text-center d-none d-md-table-cell">구매일자</th>
				<th class="text-center d-none d-md-table-cell">유저ID</th>
				<th class="text-center d-none d-md-table-cell">성함</th>
				<th class="text-center d-none d-md-table-cell">전화번호</th>
				<th class="text-center d-none d-md-table-cell">전시명</th>
				<th class="text-center d-none d-md-table-cell">시작일자</th>
				<th class="text-center d-none d-md-table-cell">종료일자</th>
				<th class="text-center d-none d-md-table-cell">성인</th>
				<th class="text-center d-none d-md-table-cell">청소년</th>
				<th class="text-center d-none d-md-table-cell">어린이</th>
				<th class="text-center d-none d-md-table-cell">총수량</th>
				<th class="text-center d-none d-md-table-cell">총액</th>
				<th class="text-center d-none d-md-table-cell">결제수단</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach var="order" items="${orderAllList}" varStatus="status">
				
				<tr>
				<td class="text-center d-none d-md-table-cell">${status.count}</td>
				<td class="text-center d-none d-md-table-cell">${order.ordNo}</td>
				<td class="text-center d-none d-md-table-cell">${order.ordDate}</td>
				<td class="text-center d-none d-md-table-cell">${order.userId}</td>
				<td class="text-center d-none d-md-table-cell">${order.userName}</td>
				<td class="text-center d-none d-md-table-cell">${order.userTel}</td>
				<td class="text-center d-none d-md-table-cell">${order.exhName}</td>
				<td class="text-center d-none d-md-table-cell">${order.exhStrDate}</td>
				<td class="text-center d-none d-md-table-cell">${order.exhEndDate}</td>
				<td class="text-center d-none d-md-table-cell">${order.ordAQnt}</td>
				<td class="text-center d-none d-md-table-cell">${order.ordYQnt}</td>
				<td class="text-center d-none d-md-table-cell">${order.ordCQnt}</td>
				<td class="text-center d-none d-md-table-cell">${order.ordTQnt}</td>
				<td class="text-center d-none d-md-table-cell">${order.ordPrice}</td>
				<td class="text-center d-none d-md-table-cell">${order.ordPay}</td>					
				</tr>
				
			</c:forEach>	
			</tbody>
		</table>
	</c:when>
</c:choose>
<form method="POST" action="${contextPath}/admin/searchOrder.do">
	<div class="context mb-5 mt-5">
		<select class="form-control" id="condition" name="condition" style="width:100px; float:left; margin-left:20px;">
			<option value="ORD_NO">주문번호</option>
			<option value="USER_ID">아이디</option>
			<option value="USER_NAME">이름</option>
		</select>
		<div class="aa">
		<input type="text" name="keyword" class="form-control" style="width:200px;float:left; margin-left:20px;"/>			
		<input type="submit" value="검색" class="btn btn-primary" style="width:80px; float:left; margin-left:20px;"/>
		</div>
	</div>
</form>

</div>
</div>
</div>
</div>
</div>


<%@ include file="/admins/adminFooter.jsp"%>

