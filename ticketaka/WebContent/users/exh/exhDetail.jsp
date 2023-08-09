<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/header.jsp"%>
<% request.setCharacterEncoding("UTF-8"); %> 	
<c:set var="exh" value="${exhsListDetail.get(0)}" />
<script type="text/javascript">
	function fn_payment(exhNo){
		var url = "${contextPath}/user/userPay.do?exhNo=" + exhNo;
	    document.location.href = url;
	}
// 	document.getElementById("userPay").addEventListener("click", function() {
//     window.location.href = "${contextPath}/user/userPay.do?no=" + ${exh.no}
//   	});

</script>	

	<div class="container" style="margin-top: 100px">

		<!--  이 부분에 내용이 들어갑니다. -->
		<!-- 상세보기 시작 -->
		<div class="container">
			<c:forEach var="exh" items="${exhsListDetail}">

				<!-- 전시 작품명 -->
				<h1 class="my-4">${exh.name}</h1>
			
				<!-- Portfolio Item Row -->
				<div class="row">	
					<div class="col-md-5">
						<img class="img-fluid" src="${contextPath}/download.do?no=${exh.no}&image=${exh.image}" alt="상품 이미지 입니다." />
					
					</div>	
	
					<div class="col-md-6">
						<h3 class="my-3">장소</h3>
						<h5>${exh.place}</h5>
						
						<h3 class="my-3">전시기간</h3>
						<h5>${exh.strDate} ~ ${exh.endDate}</h5>
						
						<h3 class="my-3">관람연령 : 전체관람가</h3>
						<h5>전체관람가</h5>
						
						<h3 class="my-3">가격</h3>
						<ul>
							<li>성인 : ${exh.adultPrice} 원</li>
							<li>청소년 : ${exh.youthPrice} 원</li>
							<li>어린이 : ${exh.childPrice} 원</li>
						</ul>
<!-- 						<input type="button" class="btn btn-warning" value="예매하기"> -->
						<input type="button" class="btn btn-warning" value="예매하기" 
						style="margin-left:20px;" onclick="fn_payment(${exh.no})">
<!-- 						<input type="button" class="btn btn-warning" value="결제하기" id="userPay"> -->
						<input type="button" class="btn btn-outline-warning" value="찜 ♥">
						<a href="${contextPath}/user/listExhs.do">
							<input type="button" class="btn btn-outline-warning" value="전체 목록 보기">
						</a>
					</div>	
				</div>
			</c:forEach>
			<!-- /.row -->



		</div>
		<!-- /.container -->
	</div>



	<%@ include file="/footer.jsp"%>