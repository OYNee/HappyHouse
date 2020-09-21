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
        $("#loading:visible").hide();
        list();
      });
    </script>
  </head>

  <body>
    <%@ include file="/WEB-INF/views/loading.jsp" %> <%@ include
    file="/WEB-INF/views/static/header.jsp"%>
    <div class="container">
      <!-- ======= Breadcrumbs ======= -->
      <section>
        <div>
          <div class="container">
            <div class="mt-2 mb-2">
              <h2>News</h2>
            </div>
          </div>
        </div>
      </section>
      <!-- End Breadcrumbs -->
      <!-- section start -->
      <section id="index_section">
        <div class="card col-sm-12 mt-1" style="min-height: 450px;">
          <div class="card-body">
            <form name="pageform" id="pageform">
              <input type="hidden" name="pg" id="pg" value="" /> 키워드 입력:
              <input type="text" name="keyword" id="keyword" />
              <button type="button" onclick="javascript:pageMove(1);">
                검색
              </button>
            </form>
            <hr />
            <div id="list"></div>
            <br />
            <br />
            <div>
              <div id="result"></div>
              <hr />

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
          </div>
        </div>
      </section>

      <script>
        let status = false;
        function list() {
          $("#loading").show();
          const qs = $("form[name=pageform]").serialize();
          $.ajax({
            type: "GET",
            url: "${root}/newslist",
            dataType: "json",
            data: qs,
            success: function (data) {
              $("#loading:visible").hide();
              let htmlTxt = "<ul>";
              $.each(data.list, function (index, value) {
                htmlTxt +=
                  "<li><span>" +
                  value.title +
                  '</span><br><a href="' +
                  value.link +
                  '" style="display: none;">' +
                  value.description +
                  "</a></li><p style='font-size:15px'>"+value.pubdate+"</p><br>";
              });
              htmlTxt += "</ul>";
              $("#list").html(htmlTxt);
              $("#page").html(data.pageNavigation.navigator);

              const span = document.getElementsByTagName("span");
              const length = span.length;
              for (let i = 0; i < length; i++) {
                span[i].onclick = function () {
                  const br = span[i].nextSibling;
                  const div = br.nextSibling;
                  if (status == false) {
                    div.style.display = "inline";
                    status = true;
                  } else {
                    div.style.display = "none";
                    status = false;
                  }
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
      <%@ include file="/WEB-INF/views/static/footer.jsp"%>
    </div>
  </body>
</html>
