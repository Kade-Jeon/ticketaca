<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/admins/adminHeader.jsp"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />

	<table>
		<tr>
			<td>글번호</td>
    	 	<td>작성 아이디</td>              
     		<td>제목</td>
     		<td>작성일</td>
     		<td>답글 여부</td>
		</tr>
		<c:choose>
				<c:when test="${empty adminlistQna}">
					<tr>
						<td colspan="5">
							<p algin="center">
								<b>등록된 문의가 없습니다.</b>
							</p>
						</td>
					</tr>
				</c:when>
				
				<c:when test="${!empty adminlistQna}">
					<c:forEach var="adminqna" items="${adminlistQna }" varStatus="adminStatus">
						<tr>
							<td>${adminStatus.count }</td>
							<td>${adminqna.UId }</td>
							<td><a href="${contextPath}/admin/Qnas/adminlistOriginalQna.do?Qno=${adminqna.QNo}&Pno=${adminqna.PNo}"> ${adminqna.QTitle }</a></td>
							<td>${adminqna.QDate }</td>
							<td>
								<c:choose>
									<c:when test="${adminqna.AAnswer eq 'F' }">
										<td>답변 대기</td>
									</c:when>
									<c:otherwise>
										<td>답변 완료</td>
									</c:otherwise>
								</c:choose>
							</td>
						</tr>
					</c:forEach>
				</c:when>
			</c:choose>
	</table>


<%@ include file="/admins/adminFooter.jsp"%>

	