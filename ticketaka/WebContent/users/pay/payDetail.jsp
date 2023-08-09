<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/header.jsp" %>
<c:set var="exhPay" value="${exhPay.get(0)}" />
<script type="text/javascript">
		function calculateTotal() {
		var adultPrice = parseInt(document.getElementById("adultPrice").textContent);// 성인가격
		var youthPrice = parseInt(document.getElementById("youthPrice").textContent);// 성인가격
		var childPrice = parseInt(document.getElementById("childPrice").textContent);// 성인가격

	    var adultQnt = parseInt($('#adultTicket').val());
	    var youthQnt = parseInt($('#youthTicket').val());
	    var childQnt = parseInt($('#childTicket').val());
	    	
	    var total = (adultPrice * adultQnt) + (youthPrice * youthQnt) + (childPrice * childQnt);

	    $('input#totalResult1').val(total);

		}
</script>	
	
	<!-- 결제정보 입력 창 시작 -->
	
	<div class="container-fluid px-5 my-5">
	  <div class="row justify-content-center">
	    <div class="col-xl-10">
	      <div class="card border-0 rounded-3 shadow-lg overflow-hidden">
	        <div class="card-body p-0">
	          <div class="row g-0">
	            <div class="col-sm-6 d-none d-sm-block bg-image"><img src="${contextPath}/download.do?no=${exhPay.no}&image=${exhPay.image}" width="600px" ></div>
	            <div class="col-sm-6 p-4 align-items-center">
	              <div class="text-center">
	                <div class="h3 fw-light">${exhPay.name}</div>
	                <p class="mb-4 text-muted">전시장소 : ${exhPay.place}</p>
	                <p class="mb-4 text-muted">${exhPay.strDate} ~ ${exhPay.endDate}</p>
	              </div>
	
	          	
	              <form method="POST" name="userPay" action="${contextPath}/user/userOrderAdd.do?exhNo=${exhPay.no}">
	
	                <!-- 성인 Input -->
	                <div class="d-flex mb-3 justify-content-between align-items-center">
	                	<div class="form-group mb-3 ">
	                	<div class="h5 fw-light align-middle">성인</div>
	                		
	                	</div>
	                	<div class="form-group mb-3">
	                	<div class="h5 fw-light align-middle" id='adultPrice'>${exhPay.adultPrice} 원</div>
	                	
	                	</div>
	                	<div class="form-group mb-3">
	                	<div class="dropdown">
										<select class="form-select" id="adultTicket" name="ordAQnt" onchange='calculateTotal()'>
											<option value="0">0</option>
											<option value="1">1</option>
											<option value="2">2</option>
											<option value="3">3</option>
											<option value="4">4</option>
											<option value="5">5</option>
										</select>
									</div>
									</div>
	                </div>
	                
	                <!-- 청소년 Input -->
	                <div class="d-flex mb-3 justify-content-between align-items-center">
	                	<div class="form-group mb-3 ">
	                	<div class="h5 fw-light align-middle">청소년</div>
	                		
	                	</div>
	                	<div class="form-group mb-3">
	                	<div class="h5 fw-light align-middle" id='youthPrice'>${exhPay.youthPrice} 원</div>
	                	
	                	</div>
	                	<div class="form-group mb-3">
	                	<div class="dropdown">
										<select class="form-select" id="youthTicket" name="ordYQnt" onchange='calculateTotal()'>
											<option value="0">0</option>
											<option value="1">1</option>
											<option value="2">2</option>
											<option value="3">3</option>
											<option value="4">4</option>
											<option value="5">5</option>
										</select>
									</div>
									</div>
	                </div>
	                
	                <!-- 어린이 Input -->
	                <div class="d-flex mb-3 justify-content-between align-items-center">
	                	<div class="form-group mb-3 ">
	                	<div class="h5 fw-light align-middle">어린이</div>
	                		
	                	</div>
	                	<div class="form-group mb-3">
	                	<div class="h5 fw-light align-middle" id='childPrice'>${exhPay.childPrice} 원</div>
	                	<span id='adultPrice'></span>
	                	</div>
	                	<div class="form-group mb-3">
	                	<div class="dropdown">
										<select class="form-select" id="childTicket" name="ordCQnt" onchange='calculateTotal()'>
											<option value="0">0</option>
											<option value="1">1</option>
											<option value="2">2</option>
											<option value="3">3</option>
											<option value="4">4</option>
											<option value="5">5</option>
										</select>
									</div>
									</div>
	                </div>
	
	                
	                <div class="form-floating mb-3">
	                <div class="h5 fw-light align-middle" id='childPrice'>총 결제금액</div>
	                  <input class="form-control" id="totalResult1" name="ordPrice" type="text"  value="" readonly/>
<!-- 	                  <label for="total">총 결제금액</label>	                   -->
	                </div>
	
<!-- 	                Message Input -->
<!-- 	                <div class="form-floating mb-3"> -->
<!-- 	                  <textarea class="form-control" id="message" type="text" placeholder="Message" style="height: 10rem;" data-sb-validations="required"></textarea> -->
<!-- 	                  <label for="message">Message</label> -->
<!-- 	                  <div class="invalid-feedback" data-sb-feedback="message:required">Message is required.</div> -->
<!-- 	                </div> -->
	
	                
	
<!-- 	                Submit button -->
	                <div class="d-grid">
	                  <button class="btn btn-warning btn-lg" id="userOrder" type="submit">결제하기</button>
	                </div>
	              </form>
	              <!-- 입력 폼 끗 -->
	
	            </div>
	          </div>
	
	        </div>
	      </div>
	    </div>
	  </div>
	</div>
	


<%@ include file="/footer.jsp" %>

		
			
	
	