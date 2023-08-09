
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>

<%@ page import="com.ticketaca.service.UserService" %>
<%@ page import="com.ticketaca.vo.ExhVO" %>

<% 
	UserService userService = new UserService();
	List<ExhVO> exhsList = new ArrayList<ExhVO>();
	exhsList = userService.listExhs();
%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>TICKETAKA</title>

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9"
	crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js"
	integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r"
	crossorigin="anonymous"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.min.js"
	integrity="sha384-Rx+T1VzGupg4BHQYs2gCW9It+akI2MM/mndMCy36UVfodzcJcF0GGLxZIzObiEfa"
	crossorigin="anonymous"></script>
<script type="text/javascript">
	function fn_user_join(){
		document.location='${contextPath}/user/signUp.do'
	}
	function fn_user_login(){
		document.location='${contextPath}/user/login.do'
	}
	function fn_user_logout(){
		document.location='${contextPath}/user/logout.do'
	}
	function checkSession() {
        <c:if test="${empty sessionScope.user}">
            alert("로그인이 필요한 서비스입니다.");
        </c:if>
    }

</script>

<c:choose>
	<c:when test='${msg == "signUpUser" }'>
		<script>
			window.onload = function() {
				alert("회원가입이 완료 되었습니다. 로그인 해주세요 :)");
			}
		</script>
	</c:when>
	<c:when test='${msg == "loginFail" }'>
		<script>
			window.onload = function() {
				alert("아이디/비밀번호를 확인해주세요.");
			}
		</script>
	</c:when>
	<c:when test='${msg == "userOut" }'>
		<script>
			window.onload = function() {
				alert("탈퇴한 회원입니다.");
			}
		</script>
	</c:when>
	<c:when test='${msg == "loginSuccess" || sessionScope.user != null }'>
		<script>
			window.onload = function() {
				document.getElementById("welcome").style.display = "inline-block";
				document.getElementById("signIn").style.display = "none";
				document.getElementById("login").style.display = "none";
				document.getElementById("logout").style.display = "inline-block";
			}
		</script>
	</c:when>
	<c:when test='${msg == "logout" }'>
		<script>
			window.onload = function() {
				document.getElementById("welcome").style.display = "none";
				document.getElementById("signIn").style.display = "inline-block";
				document.getElementById("login").style.display = "inline-block";
				alert("로그아웃 되었습니다.");
			}
		</script>
	</c:when>
	<c:when test='${msg == "userOutSuccess" }'>
		<script>
			window.onload = function() {
				document.getElementById("welcome").style.display = "none";
				document.getElementById("signIn").style.display = "inline-block";
				document.getElementById("login").style.display = "inline-block";
				alert("정상적으로 탈퇴되었습니다.\n그동안 이용해주셔서 감사합니다.");
			}
		</script>
	</c:when>
</c:choose>

</head>
<body>
	
	<div class="container-fluid">
	<div class="p-4 bg-warning text-white text-center">
	<div class="row">
	<div class="col-3" >
	<a href="${contextPath}/userMain.jsp">
    <img src="${contextPath}/image/head_ticketaka.png" width="200px" alt="mainIcon">
	</a>
	</div>
	<div class="col-6">
		<form action="${contextPath}/user/mainSearch.do" class="d-flex">
			<input class="form-control me-2	" size="100" name="keyword" type="text" placeholder="전시검색" list="exhList">
			<button class="btn btn-danger" type="submit">Search</button>
				<datalist id="exhList">
					<c:forEach var="exh" items="<%=exhsList %>" end="4">
						<option value="${exh.name}"/>
					</c:forEach>
				</datalist>
		</form>
	</div>
	
	<c:if test="${empty sessionScope.user}">
	<div class="col-3">
	<input type="button" class="btn btn-outline-dark" value="회원가입" 
	style="margin-left:20px;" onclick="fn_user_join()">
	<input type="button" class="btn btn-outline-dark" value="로그인" 
	onclick="fn_user_login()">
	</div>
	</c:if>
	<c:if test="${not empty sessionScope.user}">
	<div class="col-2">
	<input type="button" class="btn btn-outline-dark" value="로그아웃" 
	onclick="fn_user_logout()">
	</div>
	</c:if>
	</div>
	</div>
	</div>
	
	
	<div class="container-fluid">
        <nav class="navbar navbar-expand-sm bg-danger justify-content-center ">
        	
            <ul class="nav">
				<li class="nav-item"><a class="nav-link text-light" href="${contextPath}/user/listExhs.do">전시회 목록</a></li>
                <li class="nav-item"><a class="nav-link text-light" href="${contextPath}/user/mypage.do" onclick="checkSession()">마이페이지</a></li>
                <li class="nav-item"><a class="nav-link text-light" href="${contextPath}/user/userPayList.do" onclick="checkSession()">결제내역</a></li>
                <li class="nav-item"><a class="nav-link text-light" href="${contextPath}/user/zzimList.do" onclick="checkSession()">찜</a></li>
                <li class="nav-item"><a class="nav-link text-light" href="${contextPath}/user/UQnas/listQna.do" onclick="checkSession()">Q&A</a></li>
                <li class="nav-item"><button class="btn btn-warning" type="button" data-bs-toggle="offcanvas" data-bs-target="#demo">찜.t</button></li>
            </ul>
           
        </nav>
    </div>
    
	
	<div class="offcanvas offcanvas-start" id="demo">
		<div class="offcanvas-header">
			<h1 class="offcanvas-title">내 찜 목록</h1>
			<button type="button" class="btn-close" data-bs-dismiss="offcanvas"></button>
		</div>
		<div class="offcanvas-body">
			<p>다시보다: 한국근현대미술전</p>
			<p>문도 멘도: 판타스틱 시티 라이프</p>
			<p>하리보 골드베렌 100주년 생일 기념전</p>
			<button class="btn btn-secondary" type="button">A Button</button>
		</div>
	</div>