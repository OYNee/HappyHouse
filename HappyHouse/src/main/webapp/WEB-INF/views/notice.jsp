<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> <%@
taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.contextPath}"></c:set>
<html>
  <head>
    <title>OH!늘의 집</title>
    <style type="text/css">
      td {
        padding: 5px;
      }

      th {
        padding: 5px;
      }

      .skyblue {
        background-color: skyblue;
        width: 80px;
        text-align: left;
      }
    </style>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script>
      $(document).ready(function () {
    	  let title="";
    	  let content="";
        list();

        $("#add").click(function () {
          const qs = $("form[name=mform]").serialize();
          $.ajax({
            type: "POST",
            url: "${root}/notice",
            data: qs,
            success: function (data) {
              $("#mform").each(function () {
                this.reset();
              });
              const htmlTxt = "<p>" + data + "</p>";
              $("#result").html(htmlTxt);
              list();
            },
          });
        });

        $("#save").click(function () {
        	
        	
          $.ajax({
            type: "PUT",
            url: "${root}/notice",
            headers: {
              "Content-Type": "application/json",
            },
            data: JSON.stringify(
              // JavaScript 객체를문자열로변환
              {
                id: $("#form input[name=id]").val(),
                title: $("#form input[name=title]").val(),
                content: $("#content").val(),
              }
            ),
            success: function (data) {
              $("#form").each(function () {
                this.reset();
              });
              const htmlTxt = "<p>" + data + "</p>";
              $("#result").html(htmlTxt);
              list();
            },
          });
        });

        $("#delete").click(function () {
        	if (confirm("정말 삭제하시겠습니까 ?") == true) {
          $.ajax({
            type: "DELETE",
            url: "${root}/notice/" + $("#id").val(),
            success: function (data) {
              $("#form").each(function () {
                this.reset();
              });
              const htmlTxt = "<p>" + data + "</p>";
              $("#result").html(htmlTxt);
              list();
            },
          });
        }
        });

        
        $("#modify").click(function () {
        	$('#content').attr('readonly', false);
        	$('#title').attr('readonly', false);
            $('#modify').hide();
            $('#conf').hide();
            $('#save').show();
            $('#modifycancel').show();
            title = $('#title').val();
            content = $('#content').val();
          });
        
        // 수정취소시 다시 데이터 복구
        $("#modifycancel").click(function () {
        	$('#content').attr('readonly', true);
        	$('#title').attr('readonly', true);
            $('#modify').show();
            $('#conf').show();
            $('#save').hide();
            $('#modifycancel').hide();
            $('#title').val(title);
            $('#content').val(content);
          });
        
        // 모달 닫힐때 원상태로
        $('#detailmodal').on('hidden.bs.modal', function () {
        	$('#content').attr('readonly', true);
        	$('#title').attr('readonly', true);
            $('#modify').show();
            $('#conf').show();
            $('#save').hide();
            $('#modifycancel').hide();
        	});


      });
    </script>
  </head>

  <body>
    <%@ include file="/WEB-INF/views/static/header.jsp"%>
    <div class="container">
      <header>
        <h1>공지사항</h1>
        <div id="list"></div>
      </header>

      <hr />
      <c:if test="${user_session.userid == 'admin'}">
      <div class="m-0 p-0">
        <a class="p-1" href="#">
          <span id="spanForAdd" data-toggle="modal" data-target="#addModal">
            <button
              type="button"
              class="btn btn-secondary"
              style="float: right;"
            >
              글쓰기
            </button>
          </span>
        </a>
      </div>
      </c:if><br><hr>
      <!-- 서치 -->
      <div class="form-group">
      <select id="option">
      <option value="all">all</option>
      <option value="title">title</option>
      <option value="content">content</option>
      </select><br><br>
	  <input
		type="text"
		name="keyword"
		id="keyword"
		class="form-control"
		placeholder="키워드를 입력하세요"
      /><br><button type="button"
              class="btn btn-secondary"
              style="float: right;" onclick="jsvascript:list();">검색</button>
      </div>
      
      
          <form name="pageform" id="pageform">
              <input type="hidden" name="pg" id="pg" value="" />
    </form><br>
    <table>
                <tr>
                  <td>
                    <div id="page">
                      <!-- page 출력부분 -->
                      <br />
                    </div>
                  </td>
                </tr>
              </table>
      <!-- 서치 -->
      
      <!--    <button id="add" style="display: inline">추가</button> -->
      <!--    <button id="modify" style="display: inline">수정</button> -->
      <!--    <button id="delete" style="display: inline">삭제</button> -->
    </div>

    
    
    <div class="modal" id="addModal">
      <div class="modal-dialog modal-md" style="vertical-align: middle;">
        <div class="modal-content">
          <!-- ModalHeader -->
          <div class="modal-header">
            <h4 class="modal-title">공지사항 글쓰기</h4>
            <button
              type="button"
              class="close dataDismiss"
              data-dismiss="modal"
            >
              &times;
            </button>
          </div>

          <form name="mform" id="mform" class="form-group">
            <div class="modal-body">
              <div class="form-group">
              
                <label for="mbr_id_header">제목</label>
                <input
                  type="text"
                  name="title"
                  id="mtitle"
                  class="form-control"
                  placeholder="제목을 입력하세요"
                />
              </div>
              <div class="form-group">
                <label for="mbr_pwd_header">내용</label>
                <textarea
                  name="content"
                  id="mcontent"
                  class="form-control"
                  placeholder="내용을 입력하세요"
                  rows="10"
                /></textarea>
              </div>
            </div>
          </form>
          <!-- 				Modal footer -->
          <div class="modal-footer">
            <button
              type="button"
              id="add"
              class="btn btn-primary"
              data-dismiss="modal"
            >
              완료
            </button>
            <button type="button" class="btn btn-danger" data-dismiss="modal">
              취소
            </button>
          </div>
        </div>
      </div>
    </div>
    
    
    
    
    
    
    <!-- detail modal -->
    <div class="modal" id="detailmodal" >
      <div class="modal-dialog modal-md" style="vertical-align: middle;">
        <div class="modal-content">
          <!-- ModalHeader -->
          <div class="modal-header">
            <h4 class="modal-title">Notice</h4>
            <button
              type="button"
              class="close dataDismiss"
              data-dismiss="modal"
            >
              &times;
            </button>
          </div>

          <form name="form" id="form" class="form-group">
            <div class="modal-body">
            <input
                  type="hidden"
                  name="id"
                  id="id"
                  class="form-control"
                />
              <div class="form-group">
                제목
                <input
                  type="text"
                  name="title"
                  id="title"
                  class="form-control"
                  readonly
                />
              </div>
              <div class="form-group">
                작성자
                <input
                  type="text"
                  name="userid"
                  id="userid"
                  class="form-control"
                  readonly
                />
              </div>
              <div class="form-group">
                작성날짜
                <input
                  type="text"
                  name="datetime"
                  id="datetime"
                  class="form-control"
                  readonly
                />
              </div>
              <hr />
              <div class="form-group">
                <label for="mbr_pwd_header">내용</label>
                <textarea
                  name="content"
                  id="content"
                  rows="10"
                  class="form-control"
                  readonly
                ></textarea>
              </div>
            </div>
          </form>
          <!-- 				Modal footer -->
          <div class="modal-footer">
       		<c:if test="${user_session.userid=='admin' }">
            <button type="button" id="modify" class="btn btn-primary">
              수정
            </button>
            <button type="button" id="save" class="btn btn-primary" data-dismiss="modal" style="display:none;">
       save
            </button>
            <button type="button" id="delete" class="btn btn-primary" data-dismiss="modal">
              삭제
            </button>
            </c:if>
            <button type="button" id="conf" class="btn btn-primary" data-dismiss="modal">
              확인
            </button>
            <button type="reset" id="modifycancel" class="btn btn-primary" style="display:none;">
              취소
            </button>
          </div>
        </div>
      </div>
    </div>
    
    <!-- detail modal -->
    
    <br />
    <br />

    <hr />

    
    

    <script>
    let num = 0;
      function list() {
    	  console.log("list");
    	  $("#list").html("");
    	  const qs = $("form[name=pageform]").serialize();
        $.ajax({
          type: "GET",
          url: "${root}/notice",
          dataType: "json",
          data: qs + "&option="+$("#option").val()+"&keyword="+$.trim($("#keyword").val()),
          success: function (data) {
        	  num = data.pageNavigation.totalCount - ((data.pageNavigation.currentPage-1)*10);
            let htmlTxt =
              '<table class="table table-hover" id="list"> <thead> <tr><th>글 번호</th> <th>작성자</th> <th>제목</th>    <th>등록일</th> </tr>   </thead>';
            $.each(data.list, function (index, value) {
              htmlTxt += "<tr>";
              htmlTxt += "<td>" + num + "</td>";
              htmlTxt += "<td>" + value.userid + "</td>";
              htmlTxt +=
                '<td><span class="textlink" id="' +
                value.id +
                '" data-toggle="modal" data-target="#detailModal">' +
                value.title +
                "</span></td>";
              htmlTxt += "<td>" + value.datetime + "</td>";
              htmlTxt += "</tr>";
              num--;
            });
            htmlTxt += "</table>";
            $("#list").html(htmlTxt);
            $("#page").html(data.pageNavigation.navigator);
            const td = document.getElementsByClassName("textlink");
            const length = td.length;

            for (let i = 0; i < length; i++) {
              td[i].onclick = function () {
                $.ajax({
                  type: "GET",
                  url: "${root}/notice/" + td[i].id,
                  data: {
                    id: td[i].id,
                  },
                  dataType: "json",
                  success: function (data) {
                    $("#id").val(data.id);
                    $("#userid").val(data.userid);
                    $("#title").val(data.title);
                    $("#datetime").val(data.datetime);
                    $("#content").val(data.content);
                    $("#result").html("조회 성공");
                  },
                });
              };
            }
          },
        });
      }
      
      
    </script>
<script type="text/javascript">
        function pageMove(pg) {
          document.getElementById("pg").value = pg;
          list();
        }
      </script>
  </body>
</html>
