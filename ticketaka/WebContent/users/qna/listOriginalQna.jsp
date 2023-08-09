<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/header.jsp"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />


<script type="text/javascript">
	function backlist() {
		location.href="../../users/qna/listQna.jsp";
	}
	
</script>
	
</head>
<body>
	
	<form action="${contextPath }/user/UQnas/updateListQna.do" >
		<table border="1">
		
			<c:forEach var="qna" items="${qnaList }">
				<tr>
					<td>제목 </td>
					<td><input type="text" value="${qna.QTitle }" name="O_title"  readonly /></td>
				</tr>
				<tr>
					<td>작성일</td>
					<td><input type="text" value="${qna.QDate }" name="O_date" readonly /></td>
				</tr>
				<tr>
					<td>문의 유형</td>
					<td><input type="text" value="${qna.QCate }" name="O_category" readonly /></td>
				</tr>
				<tr>
					<td>글 내용</td>
					<td><textarea rows="30" cols="50" name="O_content" readonly >${qna.QContent }</textarea></td>
				</tr>		
				<input type="hidden" value="${qna.QNo }" name="O_no" />
 			</c:forEach>
 		</table>
 		
		<input type="submit" value="수정하기" />
		
	</form>
	<button onclick="backlist()">목록</button>


<%@ include file="/footer.jsp"%>

	