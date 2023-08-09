<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="utf-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />
<% request.setCharacterEncoding("UTF-8"); %>
<%@ include file="/admins/adminHeader.jsp" %>



<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
<script type="text/javascript" src="./resources/js/validation.js"></script>
<script type="text/javascript">

	function backToList(obj) {
		obj.action = "${contextPath}/admin/listExhs.do";
		obj.submit();
	}
	
	function fn_modify_exh(obj) {
		obj.action = "${contextPath}/admin/modExh.do";
		obj.submit();
	}

</script>

<title>exhModForm.jsp : 전시 수정 폼</title>

<style>
      * {
        font-size: 16px;
        font-family: Consolas, sans-serif;
      }
</style>
    
</head>
<body>
${exh.no} <br/>
${exh.image} <br/>
${exh.detailImage} <br/>
	<form id="frmExhId" name="frmExh" method="post" enctype="multipart/form-data">
	<div class="jumbotron">
		<div class="container">
			<h1 class="display-3">
				전시 세부내용 수정
			</h1>
		</div>
	</div>
	
	<div class="container">					
				<div class="form-group row">
					<label class="col-sm-2">전시명</label>
					<div class="com-sm-3">
						<input type="hidden" name="no" value="${exh.no}">
						<input type="text" name="name" value="${exh.name}" class="form-control" />
					</div>
				</div>
				
				<div class="form-group row">
					<label class="col-sm-2">전시 장소</label>
					<div class="com-sm-3">
						<input type="text" name="place" value="${exh.place}" class="form-control" />
					</div>
				</div>
				
				<div class="form-group row">
					<label class="col-sm-2">전시장 주소</label>
					<div class="com-sm-3">
						<input type="text" id="address" name="address" value="${exh.address}" class="form-control" />
					</div>
				</div>
				
				<div class="form-group row">
					<label class="col-sm-2">전시 시작 일자</label>
					<div class="com-sm-3">
						<p><input type="text" name="strDate" value="${exh.strDate}"></p>
					</div>
				</div>
				
				<div class="form-group row">
					<label class="col-sm-2">전시 종료 일자</label>
					<div class="com-sm-3">
						<p><input type="text" name="endDate" value="${exh.endDate}" ></p>
					</div>
				</div>	
				
				<div class="form-group row">
					<label class="col-sm-2">입장권 가격</label>
					<div class="com-sm-3">
						<th>성인</th>
							<td width="70">
								<input type="text" id="adultPrice" name="adultPrice" value="${exh.adultPrice}" />
							</td>
						<th>청소년</th>
							<td width="70">
								<input type="text" id="youthPrice" name="youthPrice" value="${exh.youthPrice}" />
							</td>
						<th>어린이</th>
							<td width="70">
								<input type="text" id="childPrice" name="childPrice" value="${exh.childPrice}" />
							</td>															
					</div>
				</div>
				
				<div class="form-group row">
					<label class="col-sm-2">전시 내용</label>
					<div class="com-sm-5">
						<textarea name="content" cols="50" rows="2" class="form-control" required>${exh.content}</textarea>
					</div>
				</div>
				
				<div class="form-group row">
					<label class="col-sm-2">입장권 판매 수량</label>
					<div class="com-sm-3">
						<input type="text" id="maxCnt" name="maxCnt" value="${exh.maxCnt}" class="form-control" required />
					</div>
				</div>
				
				<div class="form-group row">
					<label class="col-sm-2">전시 상태</label>
					<div class="com-sm-5">
						<input type="radio" name="exhStatus" value="진행" ${(exh.exhStatus == '진행') ? 'checked' : ''}> 진행
						<input type="radio" name="exhStatus" value="마감" ${(exh.exhStatus == '마감') ? 'checked' : ''}> 마감
						<input type="radio" name="exhStatus" value="종료" ${(exh.exhStatus == '종료') ? 'checked' : ''}> 종료
					</div>
				</div>			
				<div class="form-group row">
					<label class="col-sm-2">게시 여부</label>
					<div class="com-sm-5">
						<input type="radio" name="postStatus" value="T" ${(exh.postStatus == 'T') ? 'checked' : ''}> 게시
						<input type="radio" name="postStatus" value="F" ${(exh.postStatus == 'F') ? 'checked' : ''}> 미게시
					</div>
				</div>
				<c:if test="${not empty exh.image && exh.image != 'null' && empty exh.detailImage && exh.detailImage == 'null'}">
					<div class="form-group row">
						<label class="col-sm-2">상품 대표 이미지</label>
						<div>						
							<input  type= "hidden"  name="originalFileName" value="${exh.image}" />
    							<img src="${contextPath}/download.do?no=${exh.no}&image=${exh.image}"  /><br>
						</div>
					</div>
				</c:if>
				<c:if test="${not empty exh.detailImage && exh.detailImage != 'null' && empty exh.image && exh.image == 'null'}">
<!-- 					<div class="form-group row"> -->
<!-- 						<label class="col-sm-2">상품 세부 이미지</label> -->
<!-- 						<div> -->
<%-- 							<input  type= "hidden"  name="originalFileName1" value="${exh.detailImage}" /> --%>
<%--     							<img src="${contextPath}/download.do?no=${exh.no}&detailImage=${exh.detailImage}"  /><br> --%>
<!-- 						</div> -->
<!-- 					</div> -->
				</c:if>
				<c:if test="${not empty exh.detailImage && exh.detailImage != 'null' && not empty exh.image && exh.image != 'null'}">
					<div class="form-group row">
						<label class="col-sm-2">상품 대표 이미지</label>
						<div>
							<input  type= "hidden"  name="originalFileName" value="${exh.image}" />
    							<img src="${contextPath}/download.do?no=${exh.no}&image=${exh.image}" /><br>
						</div>
						
					</div>			
<!-- 					<div class="form-group row"> -->
<!-- 						<label class="col-sm-2">상품 세부 이미지</label> -->
<!-- 						<div> -->
<%-- 								<input  type= "hidden"  name="originalFileName1" value="${exh.detailImage}" /> --%>
<%-- 		    						<img src="${contextPath}/download.do?no=${exh.no}&detailImage=${exh.detailImage}" /><br>							 --%>
<!-- 						</div> -->
<!-- 					</div> -->
				</c:if>	
			<div class="form-group row">
				<div class="col-sm-offset-2 col-sm-10">
					<tr align = "center">
						<input type=button value="수정반영하기" onClick="fn_modify_exh(frmExh)" >
           				<input type=button value="취소" onClick="backToList(frmExh)">
			  		</tr>
				</div>
			</div>						
		</div>
	</form>
</body>
</html>
<%@ include file="/admins/adminFooter.jsp" %>