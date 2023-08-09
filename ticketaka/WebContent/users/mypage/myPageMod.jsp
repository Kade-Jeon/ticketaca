<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ include file="/header.jsp"%>
<!-- head에 taglib 들어있습니다. 추가 작성 금지. -->
<script type="text/javascript">
	function fn_userOut() {
		if (confirm("회원 탈퇴를 진행하시겠습니까?")) {
			if (confirm("재확인: 회원탈퇴를 진행하시겠습니까?")) {
				document.location = '${contextPath}/user/userOut.do';
			} else {
				document.location = '${contextPath}/user/mypage.do';
			}
		} else {
			document.location = '${contextPath}/user/mypage.do';
		}
		;

	}
</script>

<!-- 내용 시작. container에 크기를 지정하거나 추가 div 컨테이너 생성 후 제작하세요. -->
<div class="container" style="margin-top: 60px">
	<!-- 내용을 작성하는 부분 입니다. -->
	<div class="row">
		<div class="col-sm-3"></div>
		<div class="col-sm-6">
			<div class="container justify-content-center">
				<h3>마이 페이지</h3>
			</div>
			<!-- 회원 수정 폼 시작  -->
			<form method="POST" action="${contextPath}/user/mypageUpdate.do">
				<div class="card shadow">
					<div class="card-body">
						<div class="form-group mb-3">
							<label for="username">아이디</label>
							<div class="input-group">
								<input type="text" id="inputId" value="${userInfo.userId}"
									class="form-control" disabled /> <input type="hidden"
									name="userId" value="${userInfo.userId}" />
							</div>
						</div>

						<div class="form-group mb-3">
							<label for="password">비밀번호</label> <input type="password"
								id="userPw" name="userPw" value="${userInfo.userPw}"
								class="form-control" required />
						</div>

						<div class="form-group mb-3">
							<label for="full_name">이름</label> <input type="text"
								id="full_name" name="userName" value="${userInfo.userName}"
								class="form-control" required />
						</div>
						<div class="form-group mb-3">
							<label for="birthdate">생년월일</label> <input type="date"
								id="birthdate" name="userBirth" value="${userInfo.userBirth}"
								class="form-control" disabled />
						</div>
						<div class="form-group mb-3">
							<label for="tel">전화번호</label> <input type="text" id="tel"
								name="userTel" placeholder="010-0000-0000"
								value="${userInfo.userTel}" class="form-control" required />
						</div>
						<div class="form-group mb-3">
							<label for="email">이메일</label> <input type="email" id="email"
								name="userEmail" value="${userInfo.userEmail}"
								class="form-control" required />
						</div>
						<div class="form-group mb-3">
							<label for="userDate">회원가입일</label> <input type="Date" id="email"
								name="userDate" value="${userInfo.userDate}"
								class="form-control" disabled />
						</div>
						<div class="d-flex justify-content-between align-items-center">
							<div class="form-group mb-3">
								<div class="text-right">
									<button type="submit" class="btn btn-warning" id="signupBtn">수정하기</button>
								</div>
							</div>
							<div class="form-group mb-3">
								<div class="text-right">
									<button type="reset" class="btn btn-warning" id="reset">다시입력</button>
								</div>
							</div>
							<div class="form-group mb-3">
							<div class="text-right">
								<button type="button" class="btn btn-danger" id="reset">회원탈퇴</button>
								<input type="password" name="userPw" class="form-control"
									style="display: none;" />
							</div>
						</div>
						</div>

						

					</div>
				</div>
			</form>
		</div>
		<div class="col-sm-3"></div>
	</div>


</div>
<!-- 내용 끝나는 부분 -->


<%@ include file="/footer.jsp"%>