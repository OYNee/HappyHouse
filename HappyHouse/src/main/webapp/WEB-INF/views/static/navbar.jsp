<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- Navigation -->



<nav class="navbar navbar-expand-lg navbar-light fixed-top" id="mainNav">

	<div class="container">



		<a class="navbar-brand" href="${root}/">Happy House</a>
		<button class="navbar-toggler navbar-toggler-right" type="button"
			data-toggle="collapse" data-target="#navbarResponsive"
			aria-controls="navbarResponsive" aria-expanded="false"
			aria-label="Toggle navigation">
			Menu <i class="fas fa-bars"></i>
		</button>
		<div class="collapse navbar-collapse" id="navbarResponsive">
			<ul class="navbar-nav ml-auto">
				<li class="nav-item"><a class="nav-link"
					href="${root}/move-notice">공지사항</a></li>
				<li class="nav-item"><a class="nav-link"
					href="${root}/move-housedeal">실거래가</a></li>
				<li class="nav-item"><a class="nav-link"
					href="${root}/move-qna">Q&A</a></li>
				<li class="nav-item"><a class="nav-link"
					href="${root}/move-news">최신 뉴스</a></li>
			</ul>
		</div>

	</div>
	<nav id="index_nav_login">
		<ul class="m-0 p-0">
			<li class="float-right"><a class="p-1"
				href="${root}/register"><span id="spanForJoin">SignUp</span></a></li>
			<li class="float-right"><a class="p-1" href="#"> <span
					id="spanForLogin" data-toggle="modal" data-target="#loginModal">Login</span>
			</a></li>
			<li class="float-right"><a class="p-1" href="#"> <span
					id="spanForLogout" style="display: none;">Logout</span></a></li>
			<li class="float-right"><a class="p-1" href="${root}/move-profile"><span
					id="spanForID" style="display: none;"></span></a></li> 
		</ul>
	</nav>
</nav>


		<!-- login modal start -->
		<div class="modal" id="loginModal">
			<div class="modal-dialog modal-md" style="vertical-align: middle;">
				<div class="modal-content">
					<!-- ModalHeader -->
					<div class="modal-header">
						<h4 class="modal-title">LogIn</h4>
						<button type="button" class="close dataDismiss" data-dismiss="modal">&times;</button>
					</div>
					<!-- Modal body -->
					<div class="modal-body">
						<div class="form-group">
							<label for="mbr_id_header">ID:</label>
							<input type="text" id="mbr_id_header" class="form-control" placeholder="Enter ID">
						</div>
						<div class="form-group">
							<label for="mbr_pwd_header">Password:</label>
							<input type="password" id="mbr_pwd_header" class="form-control" placeholder="Enter Password">
						</div>
					</div>
					<!-- Modal footer -->
					<div class="modal-footer">
						<button type="button" id="modalLogInBtn" class="btn btn-primary" data-dismiss="modal">LogIn</button>
						<button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
					</div>
				</div>
			</div>
		</div>
		
		
		
		<!-- login modal end -->
		<!-- login script start -->
		<script>
		$(document).ready(function() {
			if(${user_session != null && user_session.userid != null && user_session.userid != ''}) {
				$("#spanForJoin").css("display","none");
				$("#spanForLogin").css("display","none");
				$("#spanForID").text(" ${user_session.userid}님 ");
				$("#spanForID").css("display","inline");
				$("#spanForLogout").css("display","inline");
			} else {
				
				$("#spanForJoin").css("display","inline");
				$("#spanForLogin").css("display","inline");
				$("#spanForID").text("");
				$("#spanForID").css("display","none");
				$("#spanForLogout").css("display","none");
			}
		});
		$(document).ready(function() {
			$("#modalLogInBtn").click(function() {
				if($.trim($("#mbr_id_header").val()) == ''){
					
					alert("ID를 입력해 주세요.");
					return;
				}
				if($.trim($("#mbr_pwd_header").val()) == ''){
					
					alert("Password를 입력해 주세요.");
					return;
				}
				$.post(
						"${pageContext.request.contextPath}/login"
						,{
							act:'login'
							,mbr_id:$("#mbr_id_header").val()
							,mbr_pwd:$("#mbr_pwd_header").val()
						}
						,function(data, status) {
							if(status == "success"){
								if(data == "0"){
									$("#mbr_pwd_header").val("");
									$("#mbr_id_header").val("");
									alert("이메일 인증을 완료해주세요.");
								} else if (data == "1"){
									location.href="${pageContext.request.contextPath}/";
								} else {
									$("#mbr_pwd_header").val("");
									$("#mbr_id_header").val("");
									alert("잘못된 ID 또는 Password 입니다.");
								}
							} else {
								$("#mbr_pwd_header").val("");
								$("#mbr_id_header").val("");
								alert("시스템 관리자에게 문의 바랍니다.");
							}
						}//function
						, "json"
				);//post
			});//click
			$("#spanForLogout").click(function() {
				$.post(
						"${pageContext.request.contextPath}/"
						,{ act:'logout' }
						, function(data, status) {
							if(status == "success"){
								if(data == 1) {
									$("#spanForLogin").css("display","inline");
									$("#spanForID").text("");
									$("#spanForID").css("display","none");
									$("#spanForLogout").css("display","none");
									alert("로그아웃 되었습니다.");
									location.href="${pageContext.request.contextPath}/";
								} else {
									alert("시스템 관리자에게 문의 바랍니다.");
								}
							} else {
								alert("시스템 관리자에게 문의 바랍니다.");
							}
						}//function
						, "json"
				);//post
			});//click
		});//ready
		</script>
		<!-- login script end -->