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

<!-- <h2>결제 상품</h2> -->
<!-- <!-- 결제화면에서 exhNO이든 뭐든 값을 받아와야함. --> -->
<!-- 	<table> -->
<!-- 		<tbody> -->
<!-- 			<tr> -->
<!-- 				<td>품명:</td> -->
<!-- 				<td></td> -->
<!-- 			</tr> -->
<!-- 			<tr> -->
<!-- 				<td>결제일: </td> -->
<!-- 				<td></td> -->
<!-- 			</tr> -->
<!-- 			<tr> -->
<!-- 				<td>총액:</td> -->
<!-- 				<td></td> -->
<!-- 			</tr> -->
<!-- 		</tbody> -->
<!-- 	</table> -->

<!-- 	<h2>주문 결제</h2> -->
<%-- <form method="POST" name="cardNumber" action="${contextPath}/admin/payResult.do"> --%>
<!-- 	<h3>카드번호</h3> -->
<!-- 	<input type="text" maxlength="4" oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');" required /> -->
<!-- 	<input type="password" maxlength="4" oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');" required /> -->
<!-- 	<input type="password" maxlength="4" oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');" required /> -->
<!-- 	<input type="text" maxlength="4" oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');" required /><br> -->
	
<!-- 	<h3>카드정보</h3> -->
<!-- 	<input type="password" maxlength="3" placeholder="cvc" oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');" required /> -->
<!-- 	<input type="text" maxlength="2" placeholder="월" oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');" required /> -->
<!-- 	<input type="text" maxlength="2" placeholder="연" oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');" required /><br> -->

<!-- 	<input type="reset" value="다시입력" /> -->
<!-- 	<input type="submit" value="결제하기" /> -->

<!-- </form> -->

<div class="container" style="margin-top: 100px">
		<!-- 코드 -->

		<div class="row">
			<div class="col-8">
				<p class="h1">
					<img src="${contextPath}/download.do?no=${exhPay.no}&image=${exhPay.image}" alt="test_img"
						class="d-block" style="width: 100%">
				</p>
			</div>

			
			<div class="col-4">
				<div class="container mt-5">
					<p class="h1">전시명 : ${exhPay.name}</p>
					<p class="h6">전시장소 : ${exhPay.place}</p>
					<p class="h6">전시기간 : ${exhPay.strDate} ~ ${exhPay.endDate}</p>
				<form method="POST" name="userPay" action="${contextPath}/user/userOrderAdd.do?exhNo=${exhPay.no}">
					<table class="table table-bordered">
						<thead>
							<tr style="border-top-width: 0; border-bottom-width: 2px;">
								<th
									style="text-align: center; vertical-align: middle; border: none;">예매구분</th>
								<th
									style="text-align: center; vertical-align: middle; border: none;">예매수량</th>
							</tr>
							
						</thead>
						<tbody>
							<tr style="border: none;">
								<td>성인 :</td>
								<td	id='adultPrice' style="text-align: center; vertical-align: middle; border: none;">${exhPay.adultPrice}</td>
								<td> 원</td>
								<td
									style="text-align: center; vertical-align: middle; border: none;">
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
								</td>
							</tr>
							<tr style="border: none;">
								<td>청소년 :</td>
								<td id='youthPrice'style="text-align: center; vertical-align: middle; border: none;">${exhPay.youthPrice} 원</td>
								<td> 원</td>
								<td
									style="text-align: center; vertical-align: middle; border: none;">
									<div class="dropdown">
										<select class="form-select" id="youthTicket" name="ordYQnt" onchange='calculateTotal()'>
											<option value="0">0</option>
											<option value="1">1</option>
											<option value="2">2</option>
											<option value="3">3</option>
											<option value="4">4</option>
											<option value="5">5</option>
										</select>
									</div></td>
							</tr>
							<tr style="border: none;">
							<td>어린이</td>
								<td id="childPrice" style="text-align: center; vertical-align: middle; border: none;">${exhPay.childPrice} 원</td>
								<td>원</td>
								<td
									style="text-align: center; vertical-align: middle; border: none;">
									<div class="dropdown">
										<select class="form-select" id="childTicket" name="ordCQnt" onchange='calculateTotal()'>
											<option value="0">0</option>
											<option value="1">1</option>
											<option value="2">2</option>
											<option value="3">3</option>
											<option value="4">4</option>
											<option value="5">5</option>
										</select>
									</div></td>
							</tr>
							<tr style="border-top-width: 2px; border-bottom-width: 0px;">
								<td style="text-align: center; vertical-align: middle; border: none;">총 수량</td>
								<td	style="text-align: center; vertical-align: middle; border: none;"></td>
								<td colspan="2" style="text-align: center; vertical-align: middle; border: none;">
        							<div>총 가격: </div>
        							<input type="text" id="totalResult1" name="ordPrice" value="q" readonly />
        							
    							</td>							
							</tr>

							
						</tbody>
						<div class="container">
								<input type="submit" id="userOrder" value="결제하기" />
<%-- 								<input type="hidden" id="작품번호" name="exhNo" value="<%= request.getParameter("exhNo") %>" /> --%>
						</div>
						
					</table>
				</form>
				</div>
			
			</div>
			
			<!-- 코드 -->
		</div>
		</div>
		
		
	
		


<%@ include file="/footer.jsp" %>

		
		