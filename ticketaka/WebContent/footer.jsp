<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<c:choose>
	<c:when test='${sessionScope.user == null}'>
		<script>
		function fn_admin_page(){
			document.location = '${contextPath}/user/adminPage.do';
		}
	</script>
	</c:when>
	<c:when test='${sessionScope.user != null}'>
		<script>
		function fn_admin_page(){
			confirm('로그아웃됩니다.\n이동하시겠습니까?');
			if(confirm){
				document.location = '${contextPath}/user/adminPage.do';
			}
		}
	</script>
	</c:when>
</c:choose>

<div class="container-fluid bg-warning text-black d-flex justify-content-center align-items-center" style="margin-top: 50px; padding-top: 20px; padding-bottom: 20px;">
    <div class="container">
        <div class="row justify-content-center align-items-center">
            <div class="col-6 text-center">
                <p class="mb-0">ⓒ Copyright 2023 team Ticketaka All rights reserved.<br>Supported by 전총명 김민지 최호준 박기철</p>
            </div>
            <div class="col-3 text-center">
                <p class="mb-0">
                    <button type="button" class="btn btn-outline-warning text-dark" onclick="fn_admin_page()">
                        <img src="${contextPath}/image/icon_ticketaka.png" width="100px" alt="subIcon">
                    </button>
                </p>
                <input type="hidden" id="checkSession" value="${sessionScope.user}" />
            </div>
        </div>
    </div>
</div>

</body>
</html>