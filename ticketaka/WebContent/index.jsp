<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>TIECKETACA</title>
<script type="text/javascript">
	function fn_sign_up() {
		document.location = '${contextPath}/user/signUp.do';
	}
	function fn_login_form(){
		document.location = '${contextPath}/user/login.do';
		}
	function fn_mypage(){
		document.location = '${contextPath}/user/mypage.do';
		}
	function fn_logout(session){
		if(session != null){
			document.location = '${contextPath}/user/logout.do';
			}
		}
	function fn_user_orders(){
		document.location = '${contextPath}/user/userPayList.do';
	}
	function fn_zzim_list(){
		document.location = '${contextPath}/user/zzimList.do';
		}
	function fn_admin_page(){
		document.location = '${contextPath}/user/adminPage.do';
		}

</script>

<c:choose>
	<c:when test='${msg == "signUpUser" }'>
		<script>
			window.onload = function() {
				alert("회원가입이 완료 되었습니다.");
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
				document.getElementById("welcome").style.display="inline-block";
				document.getElementById("signIn").style.display="none";
				document.getElementById("login").style.display="none";
				document.getElementById("logout").style.display="inline-block";
			}
			
		</script>
	</c:when>
	<c:when test='${msg == "logout" }'>
		<script>
			window.onload = function() {
				document.getElementById("welcome").style.display="none";
				document.getElementById("signIn").style.display="inline-block";
				document.getElementById("login").style.display="inline-block";
				alert("로그아웃 되었습니다.");
			}
		</script>
	</c:when>
	<c:when test='${msg == "userOutSuccess" }'>
		<script>
			window.onload = function() {
				document.getElementById("welcome").style.display="none";
				document.getElementById("signIn").style.display="inline-block";
				document.getElementById("login").style.display="inline-block";
				alert("정상적으로 탈퇴되었습니다.\n그동안 이용해주셔서 감사합니다.");
			}
		</script>
	</c:when>
</c:choose>

</head>
<body>
	
	<h6 id='welcome' style="display: none;">안녕하세요 ${sessionScope.user.userName}님 </h6>
	<input id='signIn' type="button" value="회원가입" onclick= "fn_sign_up()" />
	<input id='login' type="button" value="로그인" onclick= "fn_login_form()" />
	<input id='logout' style="display: none;" type="button" value="로그아웃" onclick= "fn_logout('${sessionScope.user}')" />
	<input type="button" value="마이페이지" onclick= "fn_mypage()" />
	<input type="button" value="주문목록" onclick= "fn_user_orders()" />
	<input type="button" value="찜목록" onclick= "fn_zzim_list()" />
	
<h1>테스트 확인용 - 유저페이지</h1>	
	세션: ${sessionScope.user}
	세션: ${sessionScope.user.userId}
	경로: ${contextPath}

<input type="button" value="관리자 페이지" onclick= "fn_admin_page()" />
</body>
</html>