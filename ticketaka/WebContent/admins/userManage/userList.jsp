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
				<h3>유저 관리</h3>

				<!-- 유저 목록 시작 -->
				<div class="container-fluid">
					<div class="card shadow">
						<div class="card-body">
							<table class="table table-hover" id='board_list'>
								<thead>
									<tr>
										<!-- 분류 이름 -->
										<th class="text-center d-none d-md-table-cell">INDEX</th>
										<th class="text-center d-none d-md-table-cell">아이디</th>
										<th class="text-center d-none d-md-table-cell">비밀번호</th>
										<th class="text-center d-none d-md-table-cell">이름</th>
										<th class="text-center d-none d-md-table-cell">생년월일</th>
										<th class="text-center d-none d-md-table-cell">전화번호</th>
										<th class="text-center d-none d-md-table-cell">이메일</th>
										<th class="text-center d-none d-md-table-cell">회원가입일</th>
										<th class="text-center d-none d-md-table-cell">탈퇴여부</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="user" items="${usersInfo}" varStatus="status">

										<tr>
											<td class="text-center d-none d-md-table-cell">${status.count}</td>
											<td class="text-center d-none d-md-table-cell">${user.userId}</td>
											<td class="text-center d-none d-md-table-cell">${user.userPw}</td>
											<td class="text-center d-none d-md-table-cell">${user.userName}</td>
											<td class="text-center d-none d-md-table-cell">${user.userBirth}</td>
											<td class="text-center d-none d-md-table-cell">${user.userTel}</td>
											<td class="text-center d-none d-md-table-cell">${user.userEmail}</td>
											<td class="text-center d-none d-md-table-cell">${user.userDate}</td>
											<td class="text-center d-none d-md-table-cell">${user.userOut}</td>
										</tr>

									</c:forEach>
								</tbody>
							</table>
		</c:when>
	</c:choose>

	<form method="POST" action="${contextPath}/admin/searchUser.do">
		<div class="context mb-5 mt-5">
			<select class="form-control" id="condition" name="condition" style="width:100px; float:left; margin-left:20px;">
				<option value="USER_ID">아이디</option>
				<option value="USER_NAME">이름</option>
				<option value="USER_TEL">전화번호</option>
				<option value="USER_EMAIL">이메일</option>
			</select>
		<div class="aa">
				<input type="text" name="keyword" class="form-control" style="width:200px;float:left; margin-left:20px;"/>			
					<input type="submit" value="검색" class="btn btn-primary" style="width:80px; float:left; margin-left:20px;"/>
				</div>
			</div>
			
			
		</div>
	</div>
</div>
	</form>
</div>
</div>
</div>
</div>
</div>

<%@ include file="/admins/adminFooter.jsp"%>
