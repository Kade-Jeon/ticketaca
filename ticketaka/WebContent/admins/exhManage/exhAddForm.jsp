<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="utf-8" isELIgnored="false"%>
<% request.setCharacterEncoding("UTF-8"); %>
<%@ include file="/admins/adminHeader.jsp" %>

<!-- 전시 등록 폼 시작 -->

<div class="container my-5">
  <div class="row justify-content-center">
    <div class="col-lg-10">
      <div class="card border-0 rounded-3 shadow-lg">
        <div class="card-body p-4">
          <div class="text-center ">
            <div class="h1 fw-light my-5">신규 전시 등록</div>
<!--             <p class="mb-4 text-muted">A functional, Bootstrap 5 contact form with validation built using SB Forms. Try it out!</p> -->
          </div>

          <form name="newExh" action="${contextPath}/admin/addExh.do" class="form-horizontal" method="post" enctype="multipart/form-data">

            <!-- 전시명 -->
            <div class="form-floating mb-3">
              <input class="form-control" id="name" name="name" type="text" placeholder="전시작품 명" required />
              <label for="전시명">전시명</label>
            </div>

            <!-- 전시 장소 -->
            <div class="form-floating mb-3">
              <input class="form-control" id="place" name="place" type="text" placeholder="전시장소" required />
              <label for="전시 장소">전시 장소</label>
            </div>
            
            <!-- 전시장 주소 -->
            <div class="form-floating mb-3">
              <input class="form-control" id="address" name="address" type="text" placeholder="전시장 주소" required />
              <label for="전시장 주소">전시장 주소</label>
            </div>
            
            <!-- 전시 기간 -->
            <br>
            <div class="input-group mb-3">
  				<span class="input-group-text">전시시작</span>
  				<input class="form-control" id="strDate" name="strDate" type="date" placeholder="전시 시작 일자" required />
  				<span class="input-group-text">전시종료</span>
  				<input class="form-control" id="endDate" name="endDate" type="date" placeholder="전시 종료 일자" required />
			</div>
            <br>
            
            <div class="h6 fw-light mb-3 text-left">입장권 가격</div>
            <div class="input-group mb-4">
  				<span class="input-group-text">성인</span>
  				<input class="form-control" id="adultPrice" name="adultPrice" type="text" placeholder="성인 가격" required />
  				<span class="input-group-text">청소년</span>
  				<input class="form-control" id="youthPrice" name="youthPrice" type="text" placeholder="청소년 가격" required />
  				<span class="input-group-text">어린이</span>
  				<input class="form-control" id="childPrice" name="childPrice" type="text" placeholder="어린이 가격" required />
			</div>
            
            <!-- 전시 내용 -->
            <div class="h6 fw-light mb-3 text-left">전시 내용</div>
            <div class="form-floating mb-3">
            	<textarea class="form-control" style="width:100%; height:200px; resize:none;" cols="50" rows="5" id="content" name="content"></textarea>
              <label for="전시 내용">전시 내용</label>
            </div>
            
            <!-- 입장권 판매 수량 -->
            <div class="h6 fw-light mb-3 text-left">입장권 판매 수량</div>
            <div class="form-floating mb-3">
<!--               <label for="maxCnt">입장권 판매 수량</label> -->
              <input class="form-control" id="maxCnt" name="maxCnt" type="text" placeholder="입장권 판매 수량" required />
            </div>
            
            <!-- 전시 상태 -->
            <div class="d-flex mb-3 justify-content-start align-items-center">
            <div class="h6 fw-light mb-3 text-left">전시 상태</div>
            <div class="form-floating col-3 mb-3" style="margin-left: 15px;">
             
              <select class="form-select" id="exhStatus" name="exhStatus" > 				
  				<option ></option>
  				<option value="예정">예정</option>
  				<option value="진행">진행</option>
  				<option value="마감">마감</option>
  				<option value="종료">종료</option>			
				</select>
			</div> 
			<div class="h6 fw-light mb-3 text-left" style="margin-left: 15px;">게시 여부</div>
				<div class="form-floating col-3 mb-3" style="margin-left: 15px;">
				
				<select class="form-select" id="postStatus" name="postStatus" > 				
  				<option ></option>
  				<option value="T">게시</option>
  				<option value="F">미게시</option>			
				</select>	 
            </div>
            </div>
            
            <!-- 상품 대표 이미지 -->
            <div class="form-floating mb-3 ">
            <div class="input-group mb-4">
              <span class="input-group-text">상품 대표 이미지</span>
              <input class="form-control " id="image" name="image" type="file" placeholder="상품 대표 이미지"  />
            </div>
            </div>
            
            <!-- 상품 세부 이미지 -->
            <div class="form-floating mb-3">
            <div class="input-group mb-4">
              <span class="input-group-text">상품 세부 이미지</span>
              <input class="form-control" id="detailImage" name="detailImage" type="file" placeholder="상품 세부 이미지"  />
            </div>
            </div>
            
           
            <!-- 등록하기 -->
            <div class="d-grid">
              <button class="btn btn-secondary btn-lg" id="submit" type="submit" value="등록">등록</button>
            </div>
          </form>
          <!-- End of contact form -->

        </div>
      </div>
    </div>
  </div>
</div>

<%@ include file="/admins/adminFooter.jsp" %>