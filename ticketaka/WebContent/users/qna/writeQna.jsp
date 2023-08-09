<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/header.jsp"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />


<script type="text/javascript">
	function backlist() {
		location.href="";
	}
</script>
</head>
<body>
	<form action="${contextPath}/user/UQnas/writeQna.do" >
		<table>
			<tr>
				<td>제목 </td> 
				<td><input type="text" name="title" /> </td>
			</tr>
			<tr>
				<td>유형 </td> 
				<td>
					<select name="category">
						<option value="상품문의">상품문의</option>
						<option value="회원문의">회원문의</option>	
					</select>
				</td>
			</tr>
				<td>내용</td> 
				<td><textarea rows="30" cols="50" name="content"></textarea></td>
				
		</table>
			
		<input type="submit" value="작성">
		<input type="reset" value="초기화">
		<input type="button" value="취소" onclick="backlist()">
		
	</form>


<%@ include file="/footer.jsp"%>

	