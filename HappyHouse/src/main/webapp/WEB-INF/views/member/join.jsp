<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <body>
  <%@ include file="/WEB-INF/views/loading.jsp" %>
    <%@ include file="/WEB-INF/views/static/header.jsp"%>
    <div class="container">
      <!-- ======= Breadcrumbs ======= -->
      <section>
        <div>
          <div class="container">
            <div class="mt-2 mb-2">
              <h2>Join Member</h2>
            </div>
          </div>
        </div>
      </section>
      <!-- End Breadcrumbs -->
      <!-- section start -->
      <section id="index_section">
        <div class="card col-sm-12 mt-1" style="min-height: 450px;">
          <div class="card-body">
            <!-- here start -->
            <script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
            <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
            <script>
              $(document).ready(function () {
            	  $('#loading:visible').hide();
                $("#addr1").click(function () {
                  //daum address
                  new daum.Postcode({
                    oncomplete: function (data) {
                      $("#addr1").val(data.address);
                    }, //oncomplete
                  }).open();
                }); //click
              }); //ready
              $(document).ready(function () {
                $("#userid").change(function () {
                  //id check
                  $("#id_chk_res").text("");
                  $("#id_chk_res").removeClass("text-primary");
                  $("#id_chk_res").removeClass("text-danger");
                  if ($("#userid").val() == "") {
                    return;
                  }
                  if (
                    !/^(?=.*[a-z])(?=.*[0-9]).{6,20}$/.test($("#userid").val())
                  ) {
                    $("#id_chk_res").text(
                      " 아이디의 형식이 잘못되었습니다. 형식:(영문 소문자/숫자, 6~20자)"
                    );
                    $("#id_chk_res").toggleClass("text-danger");
                    $("#userid").focus();
                    return;
                  } //id 형식
                  $.post(
                    "${pageContext.request.contextPath}/idCheck",
                    {
                      command: "id_check",
                      id_input: $("#userid").val(),
                    },
                    function (data, status) {
                      if (status == "success") {
                        if (data > 0) {
                          $("#id_chk_res").text(" 이미 존재하는 ID 입니다.");
                          $("#id_chk_res").addClass("text-danger");
                          $("#userid").focus();
                        } else {
                          $("#id_chk_res").text(" 사용 가능한 ID 입니다.");
                          $("#id_chk_res").addClass("text-primary");
                        }
                      } //success
                    } //function
                  );
                }); //change
                $("#passwd").change(function () {
                  $("#pwd_chk_res").text("");
                  if ($("#passwd").val() == "") {
                    return;
                  }
                  if (
                    !/^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{6,20}$/.test(
                      $("#passwd").val()
                    )
                  ) {
                    $("#pwd_chk_res").text(
                      " 비밀번호의 형식이 잘못되었습니다. 형식:(영문 대소문자+숫자+특수문자 전부 조합, 8~20자)"
                    );
                    $("#passwd").val("").focus();
                    return;
                  } //pwd 형식
                }); //change
                $("#mbr_pwd_re").change(function () {
                  $("#pwd_chk_res").text("");
                  if ($("#mbr_pwd_re").val() == "") {
                    return;
                  }
                  if ($("#mbr_pwd_re").val() != $("#passwd").val()) {
                    $("#pwd_chk_res").text(" 패스워드를 다시 확인해 주세요.");
                    $("#mbr_pwd_re").val("");
                  }
                }); //change
                $("#name").change(function () {
                  $("#nm_chk_res").text("");
                  if ($.trim($("#name").val()) == "") {
                    $("#nm_chk_res").text(
                      " 이름은 필수 입력입니다. 형식:(20자 이내)"
                    );
                    $("#name").val("").focus();
                    return;
                  }
                }); //change
                $("#phone").change(function () {
                  $("#tel_chk_res").text("");
                  if ($("#phone").val() == "") {
                    return;
                  }
                  var numStd = /^[0-9]+$/;
                  if ($("#phone").val().match(numStd)) {
                    //alert("ok");
                  } else {
                    $("#tel_chk_res").text(
                      " 숫자만 입력 가능한 필드 입니다. 형식:(01055557777, 6~20자)"
                    );
                    $("#phone").val("");
                    return;
                  }
                }); //change
                $("#email").change(function () {
                  $("#email_chk_res").text("");
                  if ($("#email").val() == "") {
                    return;
                  }
                  if (
                          !/^(?=.*[a-zA-Z0-9])(?=.*[@.]).{6,40}$/.test(
                            $("#email").val()
                    )
                  ) {
                    $("#email_chk_res").text(
                      " 이메일의 형식이 잘못되었습니다. 형식:(example@examle.com, 8~20자)"
                    );
                    $("#email").val("").focus();
                    return;
                  }
                }); //change
              }); //ready
            </script>
            <script>
              $(document).ready(function () {
                $("#join_btn").click(function () {
                  if ($.trim($("#userid").val()) == "") {
                    alert("ID를 입력해 주세요.");
                    return;
                  }
                  if ($.trim($("#passwd").val()) == "") {
                    alert("Password를 입력해 주세요.");
                    return;
                  }
                  if ($.trim($("#mbr_pwd_re").val()) == "") {
                    alert("Password Confirm을 입력해 주세요.");
                    return;
                  }
                  if ($.trim($("#name").val()) == "") {
                    alert("Name을 입력해 주세요.");
                    return;
                  }
                  if ($.trim($("#phone").val()) == "") {
                    alert("CellPhone을 입력해 주세요.");
                    return;
                  }
                  if ($.trim($("#email").val()) == "") {
                    alert("E-mail을 입력해 주세요.");
                    return;
                  }
                  if ($.trim($("#addr1").val()) == "") {
                    alert("Address를 입력해 주세요.");
                    return;
                  }
                  $('#loading').show();
                  let rp = $("form[name=join_form]").serialize();
                  rp += "&dong1="+$('#dong1').val()+"&dong2="+$('#dong2').val()+"&dong3="+$('#dong3').val();
                  console.log(rp);
                  $.ajax({
                    type: "POST",
                    url: "${root}/register",
                    data: rp,
                    success: function (data) {
                      if (data == -1) {
                        alert("시스템 관리자에게 문의 바랍니다.");
                      } else if (data >= 0) {
                    	  $('#loading').hide();
                        alert("메일을 발송했습니다. 인증을 완료해주세요.");
                        location.href = "${pageContext.request.contextPath}/";
                      } else {
                        alert(data + "잠시 후, 다시 시도해 주세요.");
                      }
                      location.href = "${root}/";
                    },
                  });
                }); //click
              }); //ready
            </script>
            <form id="join_form" name="join_form">
              <label for="userid"
                ><strong
                  >User ID (* 20자 이내) <span id="id_chk_res"></span></strong
              ></label>
              <input
                type="text"
                id="userid"
                name="userid"
                class="form-control mb-3"
                placeholder="ID"
                maxlength="20"
                required="required"
              />
              <label for="passwd"
                ><strong
                  >User Password (* 20자 이내)
                  <span id="pwd_chk_res" class="text-danger"></span> </strong
              ></label>
              <input
                type="password"
                id="passwd"
                name="passwd"
                class="form-control mb-3"
                placeholder="Password"
                maxlength="20"
                required="required"
              />
              <label for="mbr_pwd_re"
                ><strong>User Password Confirm (* 20자 이내)</strong></label
              >
              <input
                type="password"
                id="mbr_pwd_re"
                name="mbr_pwd_re"
                class="form-control mb-3"
                placeholder="Password"
                maxlength="20"
                required="required"
              />
              <label for="name"
                ><strong
                  >User Name (* 10자 이내)
                  <span id="nm_chk_res" class="text-danger"></span></strong
              ></label>
              <input
                type="text"
                id="name"
                name="name"
                class="form-control mb-3"
                placeholder="Name"
                maxlength="10"
                required="required"
              />
              <label for="phone"
                ><strong
                  >User CellPhone (* 20자 이내)
                  <span id="tel_chk_res" class="text-danger"></span> </strong
              ></label>
              <input
                type="text"
                id="phone"
                name="phone"
                class="form-control mb-3"
                placeholder="01055557777"
                maxlength="20"
                required="required"
              />
              <label for="email"
                ><strong
                  >User E-mail (* 50자 이내)
                  <span id="email_chk_res" class="text-danger"></span> </strong
              ></label>
              <input
                type="text"
                id="email"
                name="email"
                class="form-control mb-3"
                placeholder="example@example.com"
                maxlength="50"
                required="required"
              />
              <label for="addr1"><strong>User Address (*)</strong></label>
              <input
                type="text"
                id="addr1"
                name="addr1"
                class="form-control mb-3"
                readonly="readonly"
                placeholder="Click!"
                required="required"
              />
              <label for="addr2"
                ><strong>User Address 2 (50자 이내)</strong></label
              >
              <input
                type="text"
                id="addr2"
                name="addr2"
                class="form-control mb-3"
                placeholder="주소 상세"
                maxlength="50"
              />
              
              <!-- here start -->
            <script>
              $(document).ready(function () {
                $.get(
                  "${pageContext.request.contextPath}/sido",
                  {
                    command: "sido",
                  },
                  function (data, status) {
                    $.each(data, function (index, vo) {
                      $("#sido1").append(
                        "<option value='" +
                          vo.sido_code +
                          "'>" +
                          vo.sido_name +
                          "</option>"
                      );
                    }); //each
                  }, //function
                  "json"
                ); //get
              }); //ready
              $(document).ready(function () {
                $("#sido1").change(function () {
                  $.get(
                    "${pageContext.request.contextPath}/gugun",
                    {
                      command: "gugun",
                      sido: $("#sido1").val(),
                    },
                    function (data, status) {
                      $("#gugun1").empty();
                      $("#gugun1").append('<option value="0">선택</option>');
                      $.each(data, function (index, vo) {
                        $("#gugun1").append(
                          "<option value='" +
                            vo.gugun_code +
                            "'>" +
                            vo.gugun_name +
                            "</option>"
                        );
                      }); //each
                    }, //function
                    "json"
                  ); //get
                }); //change
                $("#gugun1").change(function () {
                  $.get(
                    "${pageContext.request.contextPath}/dong",
                    {
                      command: "dong",
                      gugun: $("#gugun1").val(),
                    },
                    function (data, status) {
                      $("#dong1").empty();
                      $("#dong1").append('<option value="">선택</option>');
                      $.each(data, function (index, vo) {
                        $("#dong1").append(
                          "<option value='" +
                            vo.dong +
                            "'>" +
                            vo.dong +
                            "</option>"
                        );
                      }); //each
                    }, //function
                    "json"
                  ); //get
                }); //change
              }); //ready


            </script>
            <h5>
            <label for="dong1"><strong>관심 지역1(00동)</strong></label>
              시도 :
              <select id="sido1">
                <option value="0">선택</option>
              </select>
              구군 :
              <select id="gugun1">
                <option value="0">선택</option>
              </select>
              읍면동 :
              <select id="dong1">
                <option value="">선택</option>
              </select>
            </h5>
            <!-- here end -->
              
               <!-- here start -->
            <script>
              $(document).ready(function () {
                $.get(
                  "${pageContext.request.contextPath}/sido",
                  {
                    command: "sido",
                  },
                  function (data, status) {
                    $.each(data, function (index, vo) {
                      $("#sido2").append(
                        "<option value='" +
                          vo.sido_code +
                          "'>" +
                          vo.sido_name +
                          "</option>"
                      );
                    }); //each
                  }, //function
                  "json"
                ); //get
              }); //ready
              $(document).ready(function () {
                $("#sido2").change(function () {
                  $.get(
                    "${pageContext.request.contextPath}/gugun",
                    {
                      command: "gugun",
                      sido: $("#sido2").val(),
                    },
                    function (data, status) {
                      $("#gugun2").empty();
                      $("#gugun2").append('<option value="0">선택</option>');
                      $.each(data, function (index, vo) {
                        $("#gugun2").append(
                          "<option value='" +
                            vo.gugun_code +
                            "'>" +
                            vo.gugun_name +
                            "</option>"
                        );
                      }); //each
                    }, //function
                    "json"
                  ); //get
                }); //change
                $("#gugun2").change(function () {
                  $.get(
                    "${pageContext.request.contextPath}/dong",
                    {
                      command: "dong",
                      gugun: $("#gugun2").val(),
                    },
                    function (data, status) {
                      $("#dong2").empty();
                      $("#dong2").append('<option value="">선택</option>');
                      $.each(data, function (index, vo) {
                        $("#dong2").append(
                          "<option value='" +
                            vo.dong +
                            "'>" +
                            vo.dong +
                            "</option>"
                        );
                      }); //each
                    }, //function
                    "json"
                  ); //get
                }); //change
              }); //ready


            </script>
            <h5>
            <label for="dong2"><strong>관심 지역2(00동)</strong></label>
              시도 :
              <select id="sido2">
                <option value="0">선택</option>
              </select>
              구군 :
              <select id="gugun2">
                <option value="0">선택</option>
              </select>
              읍면동 :
              <select id="dong2">
                <option value="">선택</option>
              </select>
            </h5>
            <!-- here end -->

               <!-- here start -->
            <script>
              $(document).ready(function () {
                $.get(
                  "${pageContext.request.contextPath}/sido",
                  {
                    command: "sido",
                  },
                  function (data, status) {
                    $.each(data, function (index, vo) {
                      $("#sido3").append(
                        "<option value='" +
                          vo.sido_code +
                          "'>" +
                          vo.sido_name +
                          "</option>"
                      );
                    }); //each
                  }, //function
                  "json"
                ); //get
              }); //ready
              $(document).ready(function () {
                $("#sido3").change(function () {
                  $.get(
                    "${pageContext.request.contextPath}/gugun",
                    {
                      command: "gugun",
                      sido: $("#sido3").val(),
                    },
                    function (data, status) {
                      $("#gugun3").empty();
                      $("#gugun3").append('<option value="0">선택</option>');
                      $.each(data, function (index, vo) {
                        $("#gugun3").append(
                          "<option value='" +
                            vo.gugun_code +
                            "'>" +
                            vo.gugun_name +
                            "</option>"
                        );
                      }); //each
                    }, //function
                    "json"
                  ); //get
                }); //change
                $("#gugun3").change(function () {
                  $.get(
                    "${pageContext.request.contextPath}/dong",
                    {
                      command: "dong",
                      gugun: $("#gugun3").val(),
                    },
                    function (data, status) {
                      $("#dong3").empty();
                      $("#dong3").append('<option value="">선택</option>');
                      $.each(data, function (index, vo) {
                        $("#dong3").append(
                          "<option value='" +
                            vo.dong +
                            "'>" +
                            vo.dong +
                            "</option>"
                        );
                      }); //each
                    }, //function
                    "json"
                  ); //get
                }); //change
              }); //ready


            </script>
            <h5>
            <label for="dong3"><strong>관심 지역3(00동)</strong></label>
              시도 :
              <select id="sido3">
                <option value="0">선택</option>
              </select>
              구군 :
              <select id="gugun3">
                <option value="0">선택</option>
              </select>
              읍면동 :
              <select id="dong3">
                <option value="">선택</option>
              </select>
            </h5>
            <!-- here end -->
              <div class="btn-group">
                <label for="mailing" class="btn btn-primary"
                  ><strong>관심 정보에 대한 이메일 서비스 동의</strong>
                  <input type="checkbox" name="mailing" id="mailing" autocomplete="off" value="on"
                /></label>
              </div>
              <button
                type="button"
                id="join_btn"
                class="btn btn-primary btn-block"
              >
                JOIN IN
              </button>
            </form>
            <!-- here end -->
          </div>
        </div>
      </section>
      <!-- section end -->
    </div>
    <%@ include file="/WEB-INF/views/static/footer.jsp"%>
  </body>
</html>
