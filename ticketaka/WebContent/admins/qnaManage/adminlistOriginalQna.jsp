<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/admins/adminHeader.jsp"%>
<%
	request.setCharacterEncoding("UTF-8");
%>

<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />


	<form action="${contextPath}/admin/Qnas/admininsertList.do">
		<table border="1">
			<c:forEach var="adminqna" items="${adminlistQna}" >
				<tr>
					<td>사용자 아이디</td>
					<td><input type="text" name="A_id" placeholder=" ${adminqna.UId}" readonly /></td>
				</tr>
				<tr>
					<td>제목 </td>
					<td><input type="text" name="A_title" placeholder="${adminqna.QTitle}" readonly /></td>
				</tr>
				<tr>
					<td>작성일</td>
					<td><input type="text" name="A_date" placeholder="${adminqna.QDate}" readonly /></td>
				</tr>
				<tr>
					<td>문의 유형 </td>
					<td><input type="text" name="A_category" placeholder="${adminqna.QCate}" readonly /></td>
				</tr>
				<tr>
					<td colspan="2" align="center">글 내용</td>
				</tr>
				<tr>
					<td colspan="2">
						<textarea rows="30" cols="100" name="P_content" placeholder="${adminqna.QContent}" readonly></textarea>
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center" >답변 내용</td>
				</tr>
				<tr>
					<c:forEach var="selectPno" items="${adminselectPno}" >
								<c:if test="${selectPno.PNo != 0}">
										<c:set var="P_Content" value="${selectPno.QContent}"></c:set>
								</c:if>
					</c:forEach>
					<td colspan="2"><textarea rows="30" cols="100" placeholder="${P_Content}" name="C_content" readonly></textarea></td>
				</tr>
				<input type="hidden" value="${adminqna.QNo}" name="A_Qno" />
			</c:forEach>		
		</table>
		<input type="submit" value="답글 달기" />
	</form>



<%@ include file="/admins/adminFooter.jsp"%>

	
	