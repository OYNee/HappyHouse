<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.contextPath}"></c:set>
<html>
<head>
<title>OH!늘의 집</title>
<style type="text/css">
td {
   padding: 5px
}

th {
   padding: 5px
}

.skyblue {
   background-color: skyblue;
   width: 80px;
   text-align: left
}
</style>

<script
   src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
   $(document).ready(function() {
      list();

      $('#add').click(function() {
         const qs = $("form[name=form]").serialize();
         $.ajax({
            type : 'POST',
            url : '${root}/qna/write-question',
            data : qs,
            success : function(data) {
               $('#form').each(function() {
                  this.reset();
               });
               const htmlTxt = "<p>" + data + "</p>";
               $('#result').html(htmlTxt);
               list();
            }
         });
      });

      $('#modifycancel').click(function() {
         $('#rcontent').attr('readonly', true);
         $('#modify').hide();
         $('#modifycancel').hide();
         $('#edit').show();
      });
      $('#modify').click(function() {
         $('#rcontent').attr('readonly', true);
         $('#modify').hide();
         $('#modifycancel').hide();
         $('#edit').show();
         $.ajax({
            type : 'PUT',
            url : '${root}/qna/updateReply',
            headers : {
               "Content-Type" : "application/json"
            },
            data : JSON.stringify( // JavaScript 객체를문자열로변환
            {
               id: $("#form2 input[name=id]").val(),
               replyContent : $("#form2 textarea[name=content]").val(),
            }),
            success : function(data) {
               $('#form2').each(function() {
                  this.reset();
               });
               const htmlTxt = "<p>" + data + "</p>";
               $('#result').html(htmlTxt);
               list();
            }
         });
      });

      $('#delete').click(function() {
         $.ajax({
            type : 'DELETE',
            url : '${root}/qna/' + $("#id").val(),
            success : function(data) {
               $('#form').each(function() {
                  this.reset();
               });
               const htmlTxt = "<p>" + data + "</p>";
               $('#result').html(htmlTxt);
               list();
            }
         });
      });

      $('#edit').click(function() {
         $('#rcontent').attr('readonly', false);
         $('#edit').hide();
         $('#modify').show();
         $('#modifycancel').show();
         
      });
      
      
   // 모달 닫힐때 원상태로
      $('#detailmodal').on('hidden.bs.modal', function () {
      	$('#rcontent').attr('readonly', true);
      	$('#modify').hide();
        $('#modifycancel').hide();
        $('#edit').show();
      	});
      
      
   });
</script>
</head>

<body>

   <%@ include file="/WEB-INF/views/static/header.jsp"%>
   <div class="container">
      <header>
         <h1>Q&A</h1>
         <div id="list"></div>
      </header>

      <hr />
      <div class="m-0 p-0">
         <a class="p-1" href="#">
            <span id="spanForAdd" data-toggle="modal" data-target="#addModal">
               <button type="button" class="btn btn-secondary"
                  style="float: right;">글쓰기</button>
            </span>
         </a>
      </div><br>
      <hr>
      
      <form name="pageform" id="pageform">
              <input type="hidden" name="pg" id="pg" value="" />
    </form>
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

   </div>
   
   
   
   
   <div class="modal" id="addModal">
      <div class="modal-dialog modal-md" style="vertical-align: middle;">
         <div class="modal-content">
            <!-- ModalHeader -->
            <div class="modal-header">
               <h4 class="modal-title">Q&A 질문하기</h4>
               <button type="button" class="close dataDismiss"
                  data-dismiss="modal">&times;</button>
            </div>

            <form name="form" id="form" class="form-group">
               <div class="modal-body">
                  <div class="form-group">
                     <label for="mbr_id_header">질문 제목</label> <input type="text"
                        name="title" id="title" class="form-control"
                        placeholder="제목을 입력하세요">
                  </div>
                  <div class="form-group">
                     <label for="mbr_pwd_header">내용</label>
                     <textarea name="content" id="content" class="form-control"
                        placeholder="내용을 입력하세요" rows="10"></textarea>
                  </div>
               </div>
            </form>
            <!--             Modal footer -->
            <div class="modal-footer">
               <button type="button" id="add" class="btn btn-primary"
                  data-dismiss="modal">완료</button>
               <button type="button" class="btn btn-danger" data-dismiss="modal">취소</button>
            </div>
         </div>
      </div>
   </div>
   <br>
   <br>
   <hr>



   <div class="modal" id="detailmodal">
      <div class="modal-dialog modal-md" style="vertical-align: middle;">
         <div class="modal-content">
            <!-- ModalHeader -->
            <div class="modal-header">
               <h4 class="modal-title">Question</h4>
               <button type="button" class="close dataDismiss"
                  data-dismiss="modal">&times;</button>
            </div>

            <form name="form2" id="form2" class="form-group">
               <div class="modal-body">

                  <div class="form-group">
                  <input
                  type="hidden"
                  name="id"
                  id="id"
                  class="form-control"
                />
                     제목 <input type="text" name="dtitle" id="dtitle"
                        class="form-control" readonly>
                  </div>
                  <div class="form-group">
                     작성자 <input type="text" name="userid" id="userid"
                        class="form-control" readonly>
                  </div>
                  <div class="form-group">
                     작성날짜 <input type="text" name="datetime" id="datetime"
                        class="form-control" readonly>
                  </div>
                  <div class="form-group">
                     <label for="mbr_pwd_header">내용</label>
                     <textarea name="dcontent" id="dcontent" rows="10" class="form-control"
                        readonly></textarea>
                  </div>
                  <hr>
                  <div class="form-group">
                     답변인 <input type="text" name="ruserid" id="ruserid"
                        class="form-control" readonly>
                  </div>
                  <div class="form-group">
                     답변날짜 <input type="text" name="rdatetime" id="rdatetime"
                        class="form-control" readonly>
                  </div>
                  <div class="form-group">
                     <label for="mbr_pwd_header">
                     답변</label> <textarea name="content"
                        id="rcontent" class="form-control" rows="10" readonly></textarea>
                  </div>
                  <c:if test="${user_session.userid == 'admin'}">
                     <button id="edit" type="button" class="btn btn-primary">
                        답변/수정</button>
                     <button type="button" id="modify" class="btn btn-primary"
                        data-dismiss="modal" style="display:none;">완료</button>
                     <button type="button" id="modifycancel" class="btn btn-danger" data-dismiss="modal" style="display:none;">취소</button>
                  </c:if>
               </div>
            </form>
            <!--             Modal footer -->
         </div>
      </div>
   </div>


   <!-- detail modal end -->

   <script>
   let num = 0;

      function list() {
    	  const qs = $("form[name=pageform]").serialize();
               $.ajax({
                  type : 'GET',
                  url : '${root}/qna',
                  dataType : 'json',
                  data: qs,
                  success : function(data) {
                	  console.log( data.pageNavigation.totalCount );
                	  console.log(((data.pageNavigation.currentPage-1)*10) );
                	  num = data.pageNavigation.totalCount - ((data.pageNavigation.currentPage-1)*10);
                     let htmlTxt = '<table class="table table-hover" id="list"> <thead> <tr><th>글 번호</th> <th>작성자</th> <th>제목</th>    <th>등록일</th> <th>답변일</th></tr>   </thead>';
                     $
                           .each(
                                 data.list,
                                 function(index, value) {
                                    htmlTxt += '<tr>';
                                    htmlTxt += '<td>' + num
                                          + '</td>';
                                    htmlTxt += '<td>'
                                          + value.userid
                                          + '</td>';
                                    htmlTxt += '<td><span class="textlink" id="' + value.id + '" data-toggle="modal" data-target="#detailModal">'
                                          + value.title
                                          + '</span></td>';
                                    htmlTxt += '<td>'
                                          + value.datetime
                                          + '</td>';
                                    if (value.replyDatetime == null) {
                                       htmlTxt += '<td>-</td>';
                                    } else {
                                       htmlTxt += '<td>'
                                             + value.replyDatetime
                                             + '</td>';
                                    }
                                    htmlTxt += '</tr>'
                                    num--;
                                 });
                     htmlTxt += '</table>';
                     $('#list').html(htmlTxt);
                     $("#page").html(data.pageNavigation.navigator);
                     const li = document.getElementsByClassName("textlink");
                     const length = li.length;

                     for (let i = 0; i < length; i++) {
                        li[i].onclick = function() {

                           $
                                 .ajax({
                                    type : 'GET',
                                    url : '${root}/qna/' + li[i].id,
                                    dataType : 'json',
                                    data : {
                                       "id" : li[i].id
                                    },
                                    success : function(data) {
                                       $('#id').val(data.id);
                                       $('#userid').val(
                                             data.userid);
                                       $('#dtitle')
                                             .val(data.title);
                                       $('#datetime').val(
                                             data.datetime);
                                       $('#dcontent').val(
                                             data.content);
                                       $('#ruserid').val(
                                             data.replyUserid);
                                       $('#rdatetime').val(
                                             data.replyDatetime);
                                       $('#rcontent').val(
                                             data.replyContent);
                                       $('#result').html("조회 성공");
                                    }
                                 });

                        };
                     }
                  }
               });
      };
   </script>
   <script type="text/javascript">
        function pageMove(pg) {
          document.getElementById("pg").value = pg;
          list();
        }
      </script>
</body>
</html>