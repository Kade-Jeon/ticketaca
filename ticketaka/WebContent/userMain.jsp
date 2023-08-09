<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/header.jsp" %>


<!-- 그림 메인 -->
<div class="container" style="margin-top: 30px">
<div id="demo" class="carousel slide " data-bs-ride="carousel">

  <!-- 가운데 도트 구현부 -->
  <div class="carousel-indicators justify-content-center">
    <button type="button" data-bs-target="#demo" data-bs-slide-to="0" class="active"></button>
    <button type="button" data-bs-target="#demo" data-bs-slide-to="1"></button>
    <button type="button" data-bs-target="#demo" data-bs-slide-to="2"></button>
  </div>
  
  <!-- 그림/ 텍스트 -->
  <div class="carousel-inner d-flex ">
    <div class="carousel-item active justify-content-center">
      <img src="${contextPath}/image/title3.jpg" alt="Los Angeles" class="d-block" style="width:100%">
      <div class="carousel-caption">
        <h3>수준 높은 전시 컬렉션</h3>
        <p>저희 회사는 수준 높은 공연 컬렉션을 보유하고 있습니다.</p>
      </div>
    </div>
    <div class="carousel-item justify-content-center">
      <img src="${contextPath}/image/title2.jpg" alt="Chicago" class="d-block" style="width:100%">
      <div class="carousel-caption">
        <h3>합리적인 가격대</h3>
        <p>모두가 즐길 수 있는 보편적인 전시 문화를 선도합니다.</p>
      </div> 
    </div>
    <div class="carousel-item justify-content-center">
      <img src="${contextPath}/image/title3.jpg" alt="New York" class="d-block" style="width:100%">
      <div class="carousel-caption">
        <h3>만족스런 구매</h3>
        <p>저희 회사는 만족스러운 서비스를 제공하려 노력합니다.</p>
      </div>  
    </div>
  </div>
  
 
  
  <!-- 왼쪽 / 오른쪽 넘어가기  -->
  <button class="carousel-control-prev" type="button" data-bs-target="#demo" data-bs-slide="prev">
    <span class="carousel-control-prev-icon"></span>
  </button>
  <button class="carousel-control-next" type="button" data-bs-target="#demo" data-bs-slide="next">
    <span class="carousel-control-next-icon"></span>
  </button>
</div>
</div>

<!--  <div class="container"> -->
<!--  	<h1>테스트 확인용 - 유저페이지</h1>	 -->
<%-- 	세션: ${sessionScope.user} --%>
<%-- 	세션: ${sessionScope.user.userId} --%>
<%-- 	경로: ${contextPath} --%>
<!--  </div> -->



<%@ include file="/footer.jsp" %>
