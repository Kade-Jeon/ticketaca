<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/admins/adminHeader.jsp"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />
    

	<form action="${contextPath}/admin/Qnas/admininsertQna.do">
		<table border="1">
			<c:forEach var="adminqna" items="${adminlistQna}" >
				<tr>
					<td>사용자 아이디</td>
					<td><input type="text" value=" ${adminqna.UId}" readonly /></td>
				</tr>
				<tr>
					<td>제목 </td>
					<td><input type="text" value="${adminqna.QTitle}" name="A_title" readonly /></td>
				</tr>
				<tr>
					<td>작성일 ${adminqna.QNo}</td>
					<td><input type="text" value="${adminqna.QDate}" readonly /></td>
				</tr>
				<tr>
					<td>문의 유형 </td>
					<td><input type="text" value="${adminqna.QCate}" name="A_category" readonly /></td>
				</tr>
				<tr>
					<td colspan="2" align="center">글 내용</td>
				</tr>
				<tr>
					<td colspan="2">
						<textarea rows="30" cols="100"  readonly>${adminqna.QContent}</textarea>
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
					<td colspan="2"><textarea rows="30" cols="100" name="C_content">${P_Content}</textarea></td>
				</tr>
				<input type="hidden" value="${adminqna.QNo}" name="A_Qno" />
				<input type="hidden" value="${adminqna.UId}" name="A_Uid" />
			</c:forEach>		
		</table>
		<input type="submit" value="답글  작성" />
	</form>


<%@ include file="/admins/adminFooter.jsp"%>

	