<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html lang="ko">
<head>

<title>OH!늘의 집</title>

</head>
<body>
<%@ include file="/WEB-INF/views/loading.jsp" %>
	<%@ include file="/WEB-INF/views/static/header.jsp"%>
	<div align="center">
		<div class="container" align="center">
			<div class="col-lg-6" align="center">
				<form id="memberform" method="post" action="">
					<div class="form-group" align="left">
						<label>이름 : </label> <input type="text" class="form-control"
							id="membername" name="memberName" placeholder="">
					</div>
					<div class="form-group" align="left">
						<label>아이디 : </label> <input type="text" class="form-control"
							id="memberid" name="memberId" placeholder="">
					</div>
					<div class="form-group" align="left">
						<label>핸드폰 : </label> <input type="text" class="form-control"
							id="memberphone" name="memberPhone" placeholder="">
					</div>
					<div class="form-group" align="left">
						<label>이메일 : </label> <input type="text" class="form-control"
							id="memberemail" name="memberEmail" placeholder="">
					</div>
					<div class="form-group" align="center">
						<button type="button" class="btn btn-primary" id="find_passwd">비밀번호
							찾기</button>
						<button type="reset" class="btn btn-warning">초기화</button>
					</div>
				</form>
			</div>
		</div>
	</div>

	<script>
		$(document).ready(function() {
			$('#loading:visible').hide();
			$("#find_passwd").click(function() {
				if ($.trim($("#memberid").val()) == '') {
					alert("ID를 입력해 주세요.");
					return;
				}
				if ($.trim($("#membername").val()) == '') {
					alert("Name을 입력해 주세요.");
					return;
				}
				if ($.trim($("#memberphone").val()) == '') {
					alert("CellPhone을 입력해 주세요.");
					return;
				}
				if ($.trim($("#memberemail").val()) == '') {
					alert("E-mail을 입력해 주세요.");
					return;
				}
				$('#loading').show();
				$.post("${root}/findpasswd", {
					act : 'findpasswd',
					userid : $("#memberid").val(),
					name : $("#membername").val(),
					phone : $("#memberphone").val(),
					email : $("#memberemail").val(),
				}, function(data, status) {
					if (status == "success") {
						if (data == "1") {
							 $('#loading:visible').hide();
							alert("mail 발송 완료");
							location.href = "${root}/";
						} else {
							alert("회원이 아닙니다!");
						}
					} else {
						alert("시스템 관리자에게 문의 바랍니다.");
					}
				});//post
			});//click
		});//ready
	</script>
	
	<%@ include file="/WEB-INF/views/static/footer.jsp"%>
</body>
</html>