<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<%-- <%@ include file="../../layout/header.jsp"%> --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="container" style="margin-top: 100px">
		<!-- 코드 -->






		<div class="row">
			<div class="col-8">
				<p class="h1">
					<img src="../../image/pay_test_img.jpg" alt="test_img"
						class="d-block" style="width: 100%">
				</p>
			</div>


			<div class="col-4">
				<div class="container mt-5">
					<p class="h1">선택한 전시 명</p>
					<p class="h6">2023.08.27(일)</p>
					<p class="h6">고양아람누리</p>
					<table class="table table-bordered">
						<thead>
							<tr style="border-top-width: 0; border-bottom-width: 2px;">
								<th
									style="text-align: center; vertical-align: middle; border: none;">예매구분</th>
								<th
									style="text-align: center; vertical-align: middle; border: none;">예매수량</th>
							</tr>
						</thead>
						<tbody>
							<tr style="border: none;">
								<td
									style="text-align: center; vertical-align: middle; border: none;">성인</td>
								<td
									style="text-align: center; vertical-align: middle; border: none;">
									<div class="dropdown">
										<button type="button" class="btn btn-primary dropdown-toggle"
											data-bs-toggle="dropdown">수량</button>
										<ul class="dropdown-menu">
											<li><a class="dropdown-item" href="#">1</a></li>
											<li><a class="dropdown-item" href="#">2</a></li>
											<li><a class="dropdown-item" href="#">3</a></li>
											<li><a class="dropdown-item" href="#">4</a></li>
										</ul>
									</div>
								</td>
							</tr>
							<tr style="border: none;">
								<td
									style="text-align: center; vertical-align: middle; border: none;">청소년</td>
								<td
									style="text-align: center; vertical-align: middle; border: none;"><div
										class="dropdown">
										<button type="button" class="btn btn-primary dropdown-toggle"
											data-bs-toggle="dropdown">수량</button>
										<ul class="dropdown-menu">
											<li><a class="dropdown-item" href="#">1</a></li>
											<li><a class="dropdown-item" href="#">2</a></li>
											<li><a class="dropdown-item" href="#">3</a></li>
											<li><a class="dropdown-item" href="#">4</a></li>
										</ul>
									</div></td>
							</tr>
							<tr style="border: none;">
								<td
									style="text-align: center; vertical-align: middle; border: none;">어린이</td>
								<td
									style="text-align: center; vertical-align: middle; border: none;"><div
										class="dropdown">
										<button type="button" class="btn btn-primary dropdown-toggle"
											data-bs-toggle="dropdown">수량</button>
										<ul class="dropdown-menu">
											<li><a class="dropdown-item" href="#">1</a></li>
											<li><a class="dropdown-item" href="#">2</a></li>
											<li><a class="dropdown-item" href="#">3</a></li>
											<li><a class="dropdown-item" href="#">4</a></li>
										</ul>
									</div></td>
							</tr>
							<tr style="border-top-width: 2px; border-bottom-width: 0px;">
								<td style="text-align: center; vertical-align: middle; border: none;">총 수량</td>
								<td	style="text-align: center; vertical-align: middle; border: none;"></td>
							</tr>
						</tbody>
					</table>
				</div>

			</div>

			<!-- 			<div class="col-1"></div> -->





			<!-- 코드 -->
		</div>
<%-- 		<%@ include file="../../layout/footer.jsp"%> --%>