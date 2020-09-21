<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.contextPath}"></c:set>
<html>
<head>
	<title>OH!늘의 집</title> 
  <style type="text/css">
     td{padding: 5px}
     th{padding: 5px}
     
     .skyblue{background-color: skyblue;
              width: 80px;
              text-align: left}
     
  </style>
  
   <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script>
  
     $(document).ready(function(){
    	$('#showList').click(function(){
    			list();
    	});
    	
    	$('#add').click(function(){
    		const qs = $("form[name=form]").serialize();
    		$.ajax({
    			type:'POST',
    			url:'${root}/qna/write-question',
    			data: qs,
    			success:function(data){    	
    				$('#form').each(function(){
    					    this.reset();
    				});
    				const htmlTxt="<p>"+data+"</p>";
    				$('#result').html(htmlTxt);
    				list();
    			}
    		});
    	});
    	
    	$('#modify').click(function(){
    		const qs = $("form[name=form]").serialize();
    		$.ajax({
    			type:'PUT',
    			url:'${root}/qna/updateQnA',
    			headers:{"Content-Type":"application/json"},
    			data:  JSON.stringify( // JavaScript 객체를문자열로변환
    				 {
    				  id: $("#form input[name=id]").val(),
    				  title: $("#form input[name=title]").val(),
    				  content: $("#form input[name=content]").val(),
    			     })
    			     ,
    			success:function(data){    	
    				$('#form').each(function(){
    					    this.reset();
    				});
    				const htmlTxt="<p>"+data+"</p>";
    				$('#result').html(htmlTxt);
    				list();
    			}
    		});
    	});
    	
    	$('#delete').click(function(){
    		$.ajax({
    			type:'DELETE',
    			url:'${root}/qna/'+ $("#id").val(),
    			success:function(data){
    				$('#form').each(function(){
    					    this.reset();
    				});
    				const htmlTxt="<p>"+data+"</p>";
    				$('#result').html(htmlTxt);
    				list();
    			}
    		});
    	});
    	
    	$(document).on('click', '.showDetail', function(){
    		
    	});
     });
     
     
  </script>
</head>
 
<body>
  <h1>
	Notice
  </h1>
  
  <h3>공지사항 테스트</h3>
  <button id="showList">게시물 목록</button> 
  
  <hr>
  <div id="list"></div>  
  <button id="add" style="display:inline">추가</button>
  <button id="modify" style="display:inline">수정</button>
  <button id="delete" style="display:inline">삭제</button>
  <a href="/ssafy/hello">hello</a>
  <br><br>
  <form name="form" id="form">
    <table border="1">
      <tr>
         <td class="skyblue">ID</td>
         <td><input type="text" name="id" id="id"></td>
      </tr>
      <tr>
         <td class="skyblue">title</td>
         <td><input type="text" name="title" id="title"></td>
      </tr>
      <tr>
         <td class="skyblue">작성자</td>
         <td><input type="text" name="userid" id="userid"></td>
      </tr>
      <tr>
         <td class="skyblue">작성일</td>
         <td><input type="text" name="datetime" id="datetime"></td>
      </tr>
      <tr>
         <td class="skyblue">내용</td>
         <td><input type="text" name="content" id="content"></td>
      </tr>
      
    </table>
  </form>
   <hr>
  <div id="result"></div>
  <script>
  function list() {
		$.ajax({
			type:'GET',
			url:'${root}/myboard',
			dataType:'json',
			success:function(data){
				let htmlTxt='<ul>';
				$.each(data,function(index,value){
					 htmlTxt += '<li id="'+value.id+'">'+value.id+'</li>';    					 
				});
				htmlTxt+='</ul>'
				$('#list').html(htmlTxt);
				const li = document.getElementsByTagName("li");
				const length = li.length;

				for (let i = 0; i < length; i++) {
				    li[i].onclick = function () {
			    		$.ajax({
			    			type:'GET',
			    			url:'${root}/qna/' + $("#id").val(),
			    			data: {"id" : li[i].innerHTML},
			    			dataType:'json',
			    			success:function(data){  
			    				$('#id').val(data.id);
			    				$('#userid').val(data.userid);
			    				$('#title').val(data.title);
			    				$('#datetime').val(data.datetime);
			    				$('#content').val(data.content);
			    				$('#result').html("조회 성공");
			    			}
			    		});
			    		
				    };
				}
			}
		});
	};
	
  </script>
</body>
</html>
