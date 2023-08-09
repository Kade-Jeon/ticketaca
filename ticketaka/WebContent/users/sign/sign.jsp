<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ include file="../../header.jsp"%>
<!-- head에 taglib 들어있습니다. 추가 작성 금지. -->
<script type="text/javascript">

	$(document).ready(function() {

		$('#inputPw').keyup(function() {
			$('#checkPw').text(' ');
		});

		$('#inputPwCfm').keyup(function() {

			if ($('#inputPw').val() != $('#inputPwCfm').val()) {
				$('#checkPw').text(' ');
				$('#checkPw').addClass("red");
				$('#checkPw').html('<b>암호가 틀립니다.</b>');
				$('#submit').prop("disabled", true);
				
				
			} else {
				$('#checkPw').text(' ');
				$('#checkPw').addClass("blue");
				$('#checkPw').html('<b>암호가 일치합니다.</b>');
				$('#submit').prop("disabled", false);
				
			}
		});
	});

	function fn_checkId() {
		var inputId = $("#inputId").val();
		if (inputId == '') {
			alert("ID를 입력하세요");
			return; //fn_checkId()함수 종료
		}

		$.ajax({
			type : "POST",
			async : false,
			url : "http://localhost:9000${contextPath}/user/checkId.do",
			//dataType : "text", //리턴타입 문자열
			data : {
				userId : inputId
			}, //전송 파라미터 (파라미터명 id : 값 )
			success : function(result, textStatus) {
// 				alert(result)
// 				alert(textStatus) 확인용 메시지 추후 삭제할 것

				if (result == 'usable') {
					$('#checkResult').text("사용할 수 있는 ID입니다.");
// 					$('#btn-checkId').prop("disabled", true);
// 					$('#inputId').prop("readonly", true);  -> 처리 방법 찾으면 변경예정
					$('#inputId').addClass("blue");
				} else {
					$('#checkResult').text("사용할 수 없는 ID입니다.");
					$('#inputId').addClass("red");
				}
			},
			error : function(data, textStatus) {
				alert("에러가 발생했습니다.");
				ㅣ
			},
			complete : function(data, textStatus) {
				//alert("작업을 완료 했습니다");
			}
		}); //end ajax	 

	}
</script>

<!-- 내용 시작. container에 크기를 지정하거나 추가 div 컨테이너 생성 후 제작하세요. -->
<div class="container" style="margin-top: 60px">
	<!-- 내용을 작성하는 부분 입니다. -->
	<div class="row">		
		<div class="col-sm-3">
		</div>
			<div class="col-sm-6">
				<div class="container justify-content-center">
				<h3>회원 가입</h3>
				</div>
				<!-- 회원 가입 폼 시작  -->
				<form action="${pageContext.request.contextPath }/user/signUpUser.do" method="post">
				<div class="card shadow">
					<div class="card-body">					
						<div class="form-group mb-3">
							<label for="username">아이디</label>
							<div class="input-group">
								<input type="text" id="inputId" name="userId" class="form-control" required />
<!-- 								<div class="input-group-append"> -->
									<button type="button" class="btn btn-warning" id="btn-checkId" onclick="fn_checkId()">중복확인</button>
<!-- 								</div> -->
							</div>
						</div>
						<div class="form-group ">
							<p id="checkResult" style="color: blue;"></p>
						</div>
						<div class="form-group mb-3">
							<label for="password">비밀번호</label>
							<input type="password" id="inputPw" name="userPw" class="form-control" required />
						</div>
						<div class="form-group mb-3">
							<label for="password2">비밀번호 확인</label>
							<input type="password" id="inputPwCfm" class="form-control" required/>
						</div>
						<div class="form-group ">
							<p id="checkPw" style="color: blue;"></p>
						</div>
						<div class="form-group mb-3">
							<label for="full_name">이름</label>
							<input type="text" id="full_name" name="userName" class="form-control" required />
						</div>
						<div class="form-group mb-3">
						    <label for="birthdate">생년월일</label>
						    <input type="date" id="birthdate" name="userBirth" class="form-control" required />
						</div>
						<div class="form-group mb-3">
							<label for="tel">전화번호</label>
							<input type="text" id="tel" name="userTel" placeholder="010-0000-0000" class="form-control" required />
						</div>
						<div class="form-group mb-3">
							<label for="email">이메일</label>
							<input type="email" id="email" name="userEmail" class="form-control" required />
						</div>
						<div class="form-group d-flex justify-content-end align-items-center">
                            <div class="mb-3">
                                <div class="text-right">
                                	<button type="reset" class="btn btn-outline-secondary" id="reset">다시입력</button>
                                    <button type="submit" class="btn btn-warning" id="signupBtn">회원가입</button>                                    
                                </div>
                            </div>
                        </div>
					</form>
				</div>
			</div>
		</div>
		<div class="col-sm-3"></div>
	</div>
	</div>
	</div>
		
	
	</div> <!-- 내용 끝나는 부분 -->
	

<%@ include file="../../footer.jsp" %>