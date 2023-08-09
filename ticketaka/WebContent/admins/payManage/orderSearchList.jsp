<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>주문관리</title>
</head>
<script type="text/javascript">
	function fn_order_all_list() {
		document.location = '${contextPath}/admin/orderAllList.do';
	}
</script>
<body>
<c:choose>
	<c:when test="${sessionScope.adm == null}">
		<jsp:forward page="/admin/" />
	</c:when>
	<c:when test="${sessionScope.adm != null}">
		<h2>주문 관리</h2>
		<table>
			<thead>
				<td>INDEX</td>
				<td>주문번호</td>
				<td>구매일자</td>
				<td>유저ID</td>
				<td>성함</td>
				<td>전화번호</td>
				<td>전시명</td>
				<td>시작일자</td>
				<td>종료일자</td>
				<td>성인</td>
				<td>청소년</td>
				<td>어린이</td>
				<td>총수량</td>
				<td>총액</td>
				<td>결제수단</td>
			</thead>
			<tbody>
					<c:forEach var="order" items="${searchOrderList}" varStatus="status">
						<div id="result">
							<tr>
								<td>${status.count}</td>
								<td>${order.ordNo}</td>
								<td>${order.ordDate}</td>
								<td>${order.userId}</td>
								<td>${order.userName}</td>
								<td>${order.userTel}</td>
								<td>${order.exhName}</td>
								<td>${order.exhStrDate}</td>
								<td>${order.exhEndDate}</td>
								<td>${order.ordAQnt}</td>
								<td>${order.ordYQnt}</td>
								<td>${order.ordCQnt}</td>
								<td>${order.ordTQnt}</td>
								<td>${order.ordPrice}</td>
								<td>${order.ordPay}</td>
							</tr>
						</div>
					</c:forEach>
				</tbody>
		</table>
	</c:when>
</c:choose>
<form method="POST" action="${contextPath}/admin/searchOrder.do">
	<select id='condition' name="condition">
		<option value="ORD_NO">주문번호</option>
		<option value="USER_ID">아이디</option>
		<option value="USER_NAME">이름</option>
	</select>
	<input type="search" name="keyword" />
	<input type="submit" value="검색" />
	<input type="button" value="전체목록" onclick="fn_order_all_list()" />
</form>
</body>
</html>