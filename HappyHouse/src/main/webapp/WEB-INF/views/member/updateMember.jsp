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
	<%@ include file="/WEB-INF/views/loading.jsp"%>
	<%@ include file="/WEB-INF/views/static/header.jsp"%>
	<div class="container">
		<section>
			<div>
				<div class="container">
					<div class="mt-2 mb-2">
						<h2>회원 정보 수정</h2>
					</div>
				</div>
			</div>
		</section>
		<script>
			$(document).ready(function() {
				$('#loading:visible').hide();
				$("#addr1").click(function() {
					//daum address
					new daum.Postcode({
						oncomplete : function(data) {
							$("#addr1").val(data.address);
						}, //oncomplete
					}).open();
				}); //click
			}); //ready
			$(document)
					.ready(
							function() {

								$("#memberpswd")
										.change(
												function() {
													$("#pwd_chk_res").text("");
													if ($("#memberpswd").val() == "") {
														return;
													}
													if (!/^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{6,20}$/
															.test($("#memberpswd")
																	.val())) {
														$("#pwd_chk_res")
																.text(
																		" 비밀번호의 형식이 잘못되었습니다. 형식:(영문 대소문자+숫자+특수문자 전부 조합, 8~20자)");
														$("#memberpswd").val("")
																.focus();
														return;
													} //pwd 형식
												}); //change
								$("#pwdcheck").change(
										function() {
											$("#pwd_chk_res").text("");
											if ($("#pwdcheck").val() == "") {
												return;
											}
											if ($("#pwdcheck").val() != $(
													"#memberpswd").val()) {
												$("#pwd_chk_res").text(
														" 패스워드를 다시 확인해 주세요.");
												$("#pwdcheck").val("");
											}
										}); //change

								$("#phone")
										.change(
												function() {
													$("#tel_chk_res").text("");
													if ($("#phone").val() == "") {
														return;
													}
													var numStd = /^[0-9]+$/;
													if ($("#phone").val()
															.match(numStd)) {
														//alert("ok");
													} else {
														$("#tel_chk_res")
																.text(
																		" 숫자만 입력 가능한 필드 입니다. 형식:(01055557777, 6~20자)");
														$("#phone").val("");
														return;
													}
												}); //change
							}); //ready
		</script>
		<section id="index_section">
			<div class="card col-sm-12 mt-1" style="min-height: 450px;">
				<div class="card-body">
					<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
					<script
						src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
					<div class="form-group" align="left">
						<label>아이디 : ${user_session.userid }</label>
					</div>
					<div class="form-group" align="left">
						<label>이름 : ${user_session.name }</label>
					</div>
					<div class="form-group" align="left">
						<label>이메일 : ${user_session.email }</label>
					</div>
					<form id="form" action="">



						<label for="passwd"><strong>User Password (* 20자
								이내) <span id="pwd_chk_res" class="text-danger"></span>
						</strong></label> <input type="password" id="memberpswd" name="memberpswd"
							class="form-control mb-3" placeholder="Password" maxlength="20"
							required="required" /> <label for="mbr_pwd_re"><strong>User
								Password Confirm (* 20자 이내)</strong></label> <input type="password" id="pwdcheck"
							name="pwdcheck" class="form-control mb-3" placeholder="Password"
							maxlength="20" required="required" /> <label for="phone"><strong>User
								CellPhone (* 20자 이내) <span id="tel_chk_res" class="text-danger"></span>
						</strong></label> <input type="text" id="phone" name="phone"
							class="form-control mb-3" value=${user_session.phone }
							maxlength="20" required="required" /> <label for="addr1"><strong>User
								Address (*)</strong></label> <input type="text" id="addr1" name="addr1"
							class="form-control mb-3" readonly="readonly"
							placeholder="Click!" value="${user_session.addr1 }"
							required="required" /> <label for="addr2"><strong>User
								Address 2 (50자 이내)</strong></label> <input type="text" id="addr2" name="addr2"
							class="form-control mb-3" placeholder="주소 상세"
							value="${user_session.addr2 }" maxlength="50" />

						<!-- here start -->
						<p>관심지역: ${user_session.myInterestArea.dong1 } ${user_session.myInterestArea.dong2 } ${user_session.myInterestArea.dong3 }<br>
						<script>
							$(document)
									.ready(
											function() {
												$
														.get(
																"${pageContext.request.contextPath}/sido",
																{
																	command : "sido",
																},
																function(data,
																		status) {
																	$
																			.each(
																					data,
																					function(
																							index,
																							vo) {
																						$(
																								"#sido1")
																								.append(
																										"<option value='" +
                          vo.sido_code +
                          "'>"
																												+ vo.sido_name
																												+ "</option>");
																					}); //each
																}, //function
																"json"); //get
											}); //ready
							$(document)
									.ready(
											function() {
												$("#sido1")
														.change(
																function() {
																	$
																			.get(
																					"${pageContext.request.contextPath}/gugun",
																					{
																						command : "gugun",
																						sido : $(
																								"#sido1")
																								.val(),
																					},
																					function(
																							data,
																							status) {
																						$(
																								"#gugun1")
																								.empty();
																						$(
																								"#gugun1")
																								.append(
																										'<option value="0">선택</option>');
																						$
																								.each(
																										data,
																										function(
																												index,
																												vo) {
																											$(
																													"#gugun1")
																													.append(
																															"<option value='" +
                            vo.gugun_code +
                            "'>"
																																	+ vo.gugun_name
																																	+ "</option>");
																										}); //each
																					}, //function
																					"json"); //get
																}); //change
												$("#gugun1")
														.change(
																function() {
																	$
																			.get(
																					"${pageContext.request.contextPath}/dong",
																					{
																						command : "dong",
																						gugun : $(
																								"#gugun1")
																								.val(),
																					},
																					function(
																							data,
																							status) {
																						$(
																								"#dong1")
																								.empty();
																						$(
																								"#dong1")
																								.append(
																										'<option value="">선택</option>');
																						$
																								.each(
																										data,
																										function(
																												index,
																												vo) {
																											$(
																													"#dong1")
																													.append(
																															"<option value='" +
                            vo.dong +
                            "'>"
																																	+ vo.dong
																																	+ "</option>");
																										}); //each
																					}, //function
																					"json"); //get
																}); //change
											}); //ready
						</script>
						<h5>
							<label for="dong1"><strong>관심 지역1(00동)</strong></label> 시도 : <select
								id="sido1">
								<option value="0">선택</option>
							</select> 구군 : <select id="gugun1">
								<option value="0">선택</option>
							</select> 읍면동 : <select id="dong1">
								<option value="">선택</option>
							</select>
						</h5>
						<!-- here end -->

						<!-- here start -->
						<script>
							$(document)
									.ready(
											function() {
												$
														.get(
																"${pageContext.request.contextPath}/sido",
																{
																	command : "sido",
																},
																function(data,
																		status) {
																	$
																			.each(
																					data,
																					function(
																							index,
																							vo) {
																						$(
																								"#sido2")
																								.append(
																										"<option value='" +
                          vo.sido_code +
                          "'>"
																												+ vo.sido_name
																												+ "</option>");
																					}); //each
																}, //function
																"json"); //get
											}); //ready
							$(document)
									.ready(
											function() {
												$("#sido2")
														.change(
																function() {
																	$
																			.get(
																					"${pageContext.request.contextPath}/gugun",
																					{
																						command : "gugun",
																						sido : $(
																								"#sido2")
																								.val(),
																					},
																					function(
																							data,
																							status) {
																						$(
																								"#gugun2")
																								.empty();
																						$(
																								"#gugun2")
																								.append(
																										'<option value="0">선택</option>');
																						$
																								.each(
																										data,
																										function(
																												index,
																												vo) {
																											$(
																													"#gugun2")
																													.append(
																															"<option value='" +
                            vo.gugun_code +
                            "'>"
																																	+ vo.gugun_name
																																	+ "</option>");
																										}); //each
																					}, //function
																					"json"); //get
																}); //change
												$("#gugun2")
														.change(
																function() {
																	$
																			.get(
																					"${pageContext.request.contextPath}/dong",
																					{
																						command : "dong",
																						gugun : $(
																								"#gugun2")
																								.val(),
																					},
																					function(
																							data,
																							status) {
																						$(
																								"#dong2")
																								.empty();
																						$(
																								"#dong2")
																								.append(
																										'<option value="">선택</option>');
																						$
																								.each(
																										data,
																										function(
																												index,
																												vo) {
																											$(
																													"#dong2")
																													.append(
																															"<option value='" +
                            vo.dong +
                            "'>"
																																	+ vo.dong
																																	+ "</option>");
																										}); //each
																					}, //function
																					"json"); //get
																}); //change
											}); //ready
						</script>
						<h5>
							<label for="dong2"><strong>관심 지역2(00동)</strong></label> 시도 : <select
								id="sido2">
								<option value="0">선택</option>
							</select> 구군 : <select id="gugun2">
								<option value="0">선택</option>
							</select> 읍면동 : <select id="dong2">
								<option value="">선택</option>
							</select>
						</h5>
						<!-- here end -->

						<!-- here start -->
						<script>
							$(document)
									.ready(
											function() {
												$
														.get(
																"${pageContext.request.contextPath}/sido",
																{
																	command : "sido",
																},
																function(data,
																		status) {
																	$
																			.each(
																					data,
																					function(
																							index,
																							vo) {
																						$(
																								"#sido3")
																								.append(
																										"<option value='" +
                          vo.sido_code +
                          "'>"
																												+ vo.sido_name
																												+ "</option>");
																					}); //each
																}, //function
																"json"); //get
											}); //ready
							$(document)
									.ready(
											function() {
												$("#sido3")
														.change(
																function() {
																	$
																			.get(
																					"${pageContext.request.contextPath}/gugun",
																					{
																						command : "gugun",
																						sido : $(
																								"#sido3")
																								.val(),
																					},
																					function(
																							data,
																							status) {
																						$(
																								"#gugun3")
																								.empty();
																						$(
																								"#gugun3")
																								.append(
																										'<option value="0">선택</option>');
																						$
																								.each(
																										data,
																										function(
																												index,
																												vo) {
																											$(
																													"#gugun3")
																													.append(
																															"<option value='" +
                            vo.gugun_code +
                            "'>"
																																	+ vo.gugun_name
																																	+ "</option>");
																										}); //each
																					}, //function
																					"json"); //get
																}); //change
												$("#gugun3")
														.change(
																function() {
																	$
																			.get(
																					"${pageContext.request.contextPath}/dong",
																					{
																						command : "dong",
																						gugun : $(
																								"#gugun3")
																								.val(),
																					},
																					function(
																							data,
																							status) {
																						$(
																								"#dong3")
																								.empty();
																						$(
																								"#dong3")
																								.append(
																										'<option value="">선택</option>');
																						$
																								.each(
																										data,
																										function(
																												index,
																												vo) {
																											$(
																													"#dong3")
																													.append(
																															"<option value='" +
                            vo.dong +
                            "'>"
																																	+ vo.dong
																																	+ "</option>");
																										}); //each
																					}, //function
																					"json"); //get
																}); //change
											}); //ready
						</script>
						<h5>
							<label for="dong3"><strong>관심 지역3(00동)</strong></label> 시도 : <select
								id="sido3">
								<option value="0">선택</option>
							</select> 구군 : <select id="gugun3">
								<option value="0">선택</option>
							</select> 읍면동 : <select id="dong3">
								<option value="">선택</option>
							</select>
						</h5>
						<!-- here end -->
						<div class="btn-group">
							<label for="mailing" class="btn btn-primary"> <strong>관심
									정보에 대한 이메일 서비스 동의</strong> <input type="checkbox" name="mailing"
								id="mailing" autocomplete="off" value="on"
								<c:if test='${user_session.myInterestArea.mailing == "on"}'>checked</c:if> /></label>
						</div><br>

						<button type="button" class="btn btn-primary" id="update_profile">회원정보
							수정</button>
						<button type="reset" class="btn btn-warning">초기화</button>
						<button type="reset" class="btn btn-danger" id="delete">회원정보
							삭제</button>
					</form>
				</div>
			</div>
		</section>
	</div>
	<script>
	let checked = "";
		$(document).ready(function() {
			$("#update_profile").click(function() {
				if ($.trim($("#memberpswd").val()) == '') {
					alert("비밀 번호를 입력해주세요.");
					return;
				}
				if ($.trim($("#pwdcheck").val()) == "") {
					alert("Password Confirm을 입력해 주세요.");
					return;
				}
				if ($.trim($("#phone").val()) == '') {
					alert("CellPhone을 입력해 주세요.");
					return;
				}
				if ($.trim($("#addr1").val()) == '') {
					alert("주소를 입력해 주세요.");
					return;
				}
				$('#loading:visible').show();
				if ($("#mailing").is(":checked")) {
                    checked = "on";
                  } else {
                	  checked = null;
                  }
				$.ajax({
					type : 'PUT',
					url : '${root}/update-profile',
					headers : {
						"Content-Type" : "application/json"
					},
					data : JSON.stringify({
						userid : "${user_session.userid }",
						passwd : $("#form input[name=memberpswd]").val(),
						name : "${user_session.name }",
						addr1 : $("#form input[name=addr1]").val(),
						addr2 : $("#form input[name=addr2]").val(),
						email : "${user_session.email }",
						phone : $("#form input[name=phone]").val(),
						myInterestArea : {
							dong1 : $("#dong1").val(),
							dong2 : $("#dong2").val(),
							dong3 : $("#dong3").val(),
							mailing : checked,
						}
					}),
					success : function(data) {
						$('#loading:visible').hide();
						alert(data);
						location.href = "${root}/move-profile";
					}
				});
			});//click
			$("#delete").click(function() {
				if (confirm("정말 탈퇴하시겠습니까 ?") == true) {
					$.ajax({
						type : 'DELETE',
						url : '${root}/deactivate',
						success : function(data) {
							alert("더 좋은 서비스로 다시 찾아뵙겠습니다. 감사합니다.");
							location.href = "${root}/";
						}
					});
				} else {
					return;
				}

			});
		});//ready
	</script>

	<%@ include file="/WEB-INF/views/static/footer.jsp"%>
</body>
</html>