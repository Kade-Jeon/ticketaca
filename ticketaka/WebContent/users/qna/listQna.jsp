<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/header.jsp"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<%@ page import="com.ticketaca.vo.QnaVO" %>
<%@ page import="java.util.List" %>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />
<%
	request.setCharacterEncoding("UTF-8");
	List<QnaVO> qnaList =(List<QnaVO>)request.getAttribute("qnaList");
%>
<script type="text/javascript">
 	function sendwrite(){
 		location.href="../../users/qna/writeQna.jsp";
 	}
 	
</script> 
</head>
<body>
	<table border="1">
			<tr>
				<td>글번호</td>
	<!--     	<td>작성자</td>               -->
	     		<td>제목</td>
	     		<td>작성일</td>
	     		
			</tr>
				 
				<c:forEach var="qna" items="${qnaList }" varStatus="qnanum">
					<tr>
						<td>${qnanum.count}</td>
	<%-- 					<td>${qna.UId }</td> --%>
<%-- 						<td>${qna.QTitle }</td> --%>
							<td>
								<c:choose>
								 	<c:when test='${qna.level > 1 }'>  
								    		<span style="padding-left:10px"></span>    
								        	<span style="font-size:12px;">[답변]</span>
							                <b>${qna.QTitle }</b>
								   	</c:when>
								  	<c:otherwise>
								            <a href="${contextPath}/user/UQnas/listOriginalQna.do?no=${qna.QNo }">${qna.QTitle }</a>
						         	</c:otherwise>
						        </c:choose>
						     </td>  
						<td>${qna.QDate }</td>
					</tr>
				</c:forEach>
		</table>
	<button onclick="sendwrite()">문의하기</button>


<%@ include file="/footer.jsp"%>

	