<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>OH!늘의 집</title>
<script>

function update() {
	document.location.href = "${root}/update-profile";
}

</script>

</head>
<body>
<%@ include file="/WEB-INF/views/loading.jsp" %>
	<%@ include file="/WEB-INF/views/static/header.jsp"%>
	<div align="center">
	<br><h4>회원 정보</h4><br>
		<div class="container" align="center">
			<div class="col-lg-6" align="center">
				<table class="table table-hover">
					<tbody>
						
						<tr class="table-info">
							<td align="left">아이디</td>
							<td>${user_session.userid}</td>
						</tr>
						<tr class="table-info">
							<td align="left">이름</td>
							<td>${user_session.name}</td>
						</tr>
						<tr class="table-info">
							<td align="left">전화번호</td>
							<td>${user_session.phone}</td>
						</tr>
						<tr class="table-info">
							<td align="left">주소</td>
							<td>${user_session.addr1}</td>
						</tr>
						<tr class="table-info">
							<td align="left">이메일</td>
							<td>${user_session.email}</td>
						</tr>
						
						
						<c:if test="${user_session.myInterestArea.dong1!=null}">
						<tr class="table-info">
							<td align="left">관심지역 1</td>
							<td>${user_session.myInterestArea.dong1}</td>
						</tr>
						</c:if>
						<c:if test="${user_session.myInterestArea.dong2!=null}">
						<tr class="table-info">
							<td align="left">관심지역 2</td>
							<td>${user_session.myInterestArea.dong2}</td>
						</tr>
						</c:if>
						<c:if test="${user_session.myInterestArea.dong3!=null}">
						<tr class="table-info">
							<td align="left">관심지역 3</td>
							<td>${user_session.myInterestArea.dong3}</td>
						</tr>
						</c:if>
					</tbody>
				</table>
			</div>
		</div>
		<h2><a href="${root}/move-myboard">내 게시물</a></h2><br>
		
		<ul class="m-0 p-0">
			<li><a class="p-1" href="#"> <span
					id="spanForUpdateProfile" data-toggle="modal" data-target="#checkPasswdModal">회원정보 수정</span>
			</a></li>
			
		</ul>
	</div>
	
	
				<!-- check passwd start -->
		<div class="modal" id="checkPasswdModal">
			<div class="modal-dialog modal-md" style="vertical-align: middle;">
				<div class="modal-content">
					<!-- ModalHeader -->
					<div class="modal-header">
						<h4 class="modal-title">비밀번호 확인</h4>
						<button type="button" class="close dataDismiss" data-dismiss="modal">&times;</button>
					</div>
					<!-- Modal body -->
					<div class="modal-body">
						<div class="form-group">
							<label for="passwd">Password:</label>
							<input type="password" id="passwd" class="form-control" placeholder="Enter Password">
						</div>
					</div>
					<!-- Modal footer -->
					<div class="modal-footer">
						<button type="button" id=modalFindPasswdBtn class="btn btn-primary" data-dismiss="modal">확인</button>
						<button type="button" class="btn btn-danger" data-dismiss="modal">취소</button>
					</div>
				</div>
			</div>
		</div>
		
		
		
		<!-- check passwd modal end -->
		<script>
		
		$(document).ready(function() {
			$('#loading:visible').hide();
			$("#modalFindPasswdBtn").click(function() {
				if($.trim($("#passwd").val()) == ''){
					
					alert("Password를 입력해 주세요.");
					return;
				}
				$('#loading:visible').show();
				$.post(
						"${pageContext.request.contextPath}/passwdCheck"
						,{
							passwd: $("#passwd").val()
						}
						,function(data, status) {
							$('#loading:visible').hide();
							if(status == "success"){
								if(data == 1) {
									location.href="${pageContext.request.contextPath}/update-profile";
								} else if(data == -1){
									$("#passwd").val("");
									alert("잘못된 Password 입니다.");
								} else {
									$("#passwd").val("");
									alert("시스템 관리자에게 문의 바랍니다.");
								}
							} else {
								$("#passwd").val("");
								alert("시스템 관리자에게 문의 바랍니다.");
							}
						}//function
						, "json"
				);//post
			});//click
		});//ready
		</script>
	<%@ include file="/WEB-INF/views/static/footer.jsp"%>
</body>
</html>