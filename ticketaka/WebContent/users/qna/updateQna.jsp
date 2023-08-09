<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/header.jsp"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />
    

<script type="text/javascript">

</script>
</head>
<body>
<form action="${contextPath }/user/UQnas/updateQna.do" >
		<table border="1">
		
			<c:forEach var="qna" items="${qnaList }">
				<tr>
					<td>제목 ${qna.QNo }</td>
					<td><input type="text" value="${qna.QTitle }" name="O_title" /></td>
				</tr>
				<tr>
					<td>작성일</td>
					<td><input type="text" value="${qna.QDate }" name="O_date" disabled /></td>
				</tr>
				<tr>
					<td>문의 유형</td>
<%-- 					<td><input type="text" value="${qna.QCate }" name="O_category" /></td> --%>
					<td>
						<select name="O_category">
						<option value="상품문의">상품문의</option>
						<option value="회원문의">회원문의</option>	
					</select>
					</td>
				</tr>
				<tr>
					<td>글 내용</td>
					<td><textarea rows="30" cols="50" name="O_content" >${qna.QContent }</textarea></td>
				</tr>		
				<input type="hidden" value="${qna.QNo }" name="O_no" />
 			</c:forEach>
 		</table>
		<input type="submit" value="수정하기" />
	</form>


<%@ include file="/footer.jsp"%>

	