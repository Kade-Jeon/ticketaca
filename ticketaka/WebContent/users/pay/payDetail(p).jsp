<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/header.jsp"%>

	<!-- 내용 시작. container에 크기를 지정하거나 추가 div 컨테이너 생성 후 제작하세요. -->
	<div class="container" style="margin-top: 100px">

		<!--  이 부분에 내용이 들어갑니다. -->

		<div class="container px-5 my-5">
			<div class="row justify-content-center">
				<div class="col-lg-8">
					<div class="card border-0 rounded-3 shadow-lg">
						<div class="card-body p-4">
							<div class="text-center">
								<div class="h1 fw-light">Q & A 내용</div>
<!-- 								<p class="mb-4 text-muted">상품(전시)문의 같은경우 상세한 설명 부탁드립니다.</p> -->
							</div>

							<!-- 결제내역 상세보기 입력 시작 -->
													
								<!-- 회원 정보 받는 곳 -->
								<div class="container mb-3">
									<input class="form-control" id="text" type="text" value="회원 ID가 들어갑니다.." disabled/> 							
								</div>
								
								<!-- 문의글 제목 입력 -->
								<div class="container mb-3">
									<input class="form-control" id="text" type="text" value="제목 param 값이 들어갑니다." disabled /> 
<!-- 									<label for="title">제목</label>								 -->
								</div>

								<!-- Message Input -->
								<div class="form-floating mb-3">
									<textarea class="form-control" id="message" type="text"
										placeholder="Message" style="height: 10rem;" disabled ></textarea>
										<!-- 글 내용 고정 어떻게..? -->	
								</div>

							
								<!-- 버튼 부 폼 -->
								<div class="form-group">
									<form>
									<div class="text right">
									<!-- 만약 명령값 전달 한다면? -->
										<input type="hidden" name="목록 돌아가기" value="">
										<input type="hidden" name="삭제 버튼?" value="">
										<button type="submit" name="action" value="목록" class="btn btn-warning">목록</button>
										<button type="submit" name="action" value="삭제" class="btn btn-danger">삭제</button>
									</div>
									</form>
								</div>
								
							<!-- End of contact form -->

						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- 전체 Q&A 폼 끝 -->

	</div>
	<!-- 내용 끝 -->


	<%@ include file="/footer.jsp"%>