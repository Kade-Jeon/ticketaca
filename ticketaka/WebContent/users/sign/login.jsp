<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ include file="/header.jsp"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!-- head에 taglib 들어있습니다. 추가 작성 금지. -->
<script type="text/javascript">
	function fn_sign_up() {
		document.location = '${contextPath}/user/signUp.do';
	}
</script>
<c:if test='${msg == "loginFail" }'>
	<script>
		window.onload = function() {
			alert("아이디와 비밀번호를 확인해주세요.");
		}
	</script>
</c:if>

<!-- 내용 시작. container에 크기를 지정하거나 추가 div 컨테이너 생성 후 제작하세요. -->
	<div class="container" style="margin-top: 60px">
	<!-- 내용을 작성하는 부분 입니다. -->
	<div class="container">
			<div class="row">
				<div class="col-sm-9 col-md-7 col-lg-5 mx-auto">
					<div class="card border-0 shadow rounded-3 my-5">
						<div class="card-body p-4 p-sm-5">
							<!-- 로그인 칸 제목 -->
							<h5 class="card-title text-center mb-5 fw-light fs-5">환영합니다.</h5>
							
							<!-- 폼 입력 시작 -->
							<form method="POST" action="${contextPath}/user/loginUser.do" >
								<!-- 아이디 입력 칸 -->
								<div class="form-floating mb-3">
									<input type="text" name="userId" class="form-control" id="floatingInput" placeholder="아이디"> 
										<label for="floatingInput">ID</label>
								</div>
								
								<!-- 비밀번호 입력 칸 -->
								<div class="form-floating mb-3">
									<input type="password" name="userPw" class="form-control" id="floatingPassword" placeholder="비밀번호"> 
										<label for="floatingPassword">Password</label>
								</div>
								
								
								<div class="d-grid">
									<button class="btn btn-warning btn-login text-uppercase fw-bold"
										type="submit">로그인</button>
								</div>
								<br>
								<div class="d-grid">
									<button
										class="btn btn-warning btn-login text-uppercase fw-bold"
										type="button" onclick="fn_sign_up()">회원가입</button>
								</div>
								
								<br>
								<div class="d-grid">
									<button
										class="btn btn-link btn-login text-uppercase fw-bold"
										type="submit">아이디/비밀번호 찾기</button>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
		
	
	</div> <!-- 내용 끝나는 부분 -->
	

<%@ include file="/footer.jsp"%>