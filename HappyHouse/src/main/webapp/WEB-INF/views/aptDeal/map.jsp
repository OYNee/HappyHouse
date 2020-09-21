<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html lang="ko">
  <head>
    <title>OH!늘의 집</title>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <link
      rel="stylesheet"
      href="https://maxcdn.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"
    />
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js"></script>
  </head>
  <body>
    <%@ include file="/WEB-INF/views/static/header.jsp"%>
    <div class="container">
      <!-- ======= Breadcrumbs ======= -->
      <section>
        <div>
          <div class="container">
            <div class="mt-2 mb-2">
              <h2>Search...</h2>
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
            <script>
              $(document).ready(function () {
                $.get(
                  "${pageContext.request.contextPath}/sido",
                  {
                    command: "sido",
                  },
                  function (data, status) {
                    $.each(data, function (index, vo) {
                      $("#sido").append(
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
                $("#sido").change(function () {
                  $.get(
                    "${pageContext.request.contextPath}/gugun",
                    {
                      command: "gugun",
                      sido: $("#sido").val(),
                    },
                    function (data, status) {
                      $("#gugun").empty();
                      $("#gugun").append('<option value="0">선택</option>');
                      $.each(data, function (index, vo) {
                        $("#gugun").append(
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
                $("#gugun").change(function () {
                  $.get(
                    "${pageContext.request.contextPath}/dong",
                    {
                      command: "dong",
                      gugun: $("#gugun").val(),
                    },
                    function (data, status) {
                      $("#dong").empty();
                      $("#dong").append('<option value="0">선택</option>');
                      $.each(data, function (index, vo) {
                        $("#dong").append(
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
                $("#dong").change(function () {
                  deleteMarkers();
                  deleteShopMarkers();
                  var checkArr = [];
                  $("#myFav").val("0");
                  $("input[name='shopType']:checked").each(function (i) {
                    checkArr.push($(this).val());
                  });
                  console.log(checkArr[0]);
                  $.get(
                    "${pageContext.request.contextPath}/shoplist",
                    {
                      dong: $("#dong").val(),
                      // 상권정보 타입 추가
                      shopType: checkArr,
                    },
                    function (data, status) {
                    	$("#shoplist").empty();
                    	$("#shoplist").append("<h5>검색 결과 : "+data.length+"</h5><hr>");
                      $.each(data, function (index, vo) {
                    	  // 여기부터
                    	  getList(vo);
                    	  //여기까지
                        
                      }); //each
                      if (!$("#showShoppingMall").is(":checked")) {
                        hideShopMarkers();
                      }
                    }, //function
                    "json"
                  ); //get

                  $.get(
                    "${pageContext.request.contextPath}/aptlist",
                    {
                      dong: $("#dong").val(),
                    },
                    function (data, status) {
                      setMapByCoord(data[0].lat, data[0].lng);

                      $("#searchResult").empty();
                      $.each(data, function (index, vo) {
                        let obj = {
                          x: vo.lat,
                          y: vo.lng,
                          title: vo.aptName,
                          cont: vo.aptName +"</h6><br>"+vo.jibun+"<br>"+vo.buildYear,
                        };
                        addMarkersOnMap($("#dong").val(), obj);

                        $("#searchResult").append(
                          "<h5>"+vo.aptName+"</h5>지번 : " + vo.jibun + "&ensp;&ensp;건축 연도 : " + vo.buildYear+"<br>"
                        );
                      }); //each
                    }, //function
                    "json"
                  ); //get
                }); //change
              }); //ready

              $(document).ready(function () {
                $("#showShoppingMall").change(function () {
                  if ($("#showShoppingMall").is(":checked")) {
                    showShopMarkers();
                  } else {
                    hideShopMarkers();
                  }
                });
              });

              $(document).ready(function () {
                $("#shopSearch").click(function () {
                  deleteShopMarkers();
                  var checkArr = [];
                  $("input[name='shopType']:checked").each(function (i) {
                    checkArr.push($(this).val());
                  });
                  $.get(
                    "${pageContext.request.contextPath}/shoplist",
                    {
                      dong: $("#dong").val(),
                      shopType: checkArr,
                    },
                    function (data, status) {
                    	$("#shoplist").empty();
                    	$("#shoplist").append("<h5>검색 결과 : "+data.length+"</h5><hr>");
                      
                    	 
                      $.each(data, function (index, vo) {
                    	  // 여기부터
                    	  getList(vo);
                    	  // 여기까지
                        
                      }); //each
                      if (!$("#showShoppingMall").is(":checked")) {
                        hideShopMarkers();
                      }
                    }, //function
                    "json"
                  ); //get
                });
                
              });
              
              $(document).ready(function () {
                  $("#myFav").change(function () {
                	  deleteMarkers();
                      deleteShopMarkers();
                      var checkArr = [];
                      $("#dong").val("0");
                      $("input[name='shopType']:checked").each(function (i) {
                        checkArr.push($(this).val());
                      });
                      console.log(checkArr[0]);
                      $.get(
                        "${pageContext.request.contextPath}/shoplist",
                        {
                          dong: $("#myFav").val(),
                          // 상권정보 타입 추가
                          shopType: checkArr,
                        },
                        function (data, status) {
                        	$("#shoplist").empty();
                        	$("#shoplist").append("<h5>검색 결과 : "+data.length+"</h5><hr>");
                          $.each(data, function (index, vo) {
                        	  // 여기부터
                        	  getList(vo);
                        	  //여기까지
                            
                          }); //each
                          if (!$("#showShoppingMall").is(":checked")) {
                            hideShopMarkers();
                          }
                        }, //function
                        "json"
                      ); //get

                      $.get(
                        "${pageContext.request.contextPath}/aptlist",
                        {
                          dong: $("#myFav").val(),
                        },
                        function (data, status) {
                          setMapByCoord(data[0].lat, data[0].lng);

                          $("#searchResult").empty();
                          $.each(data, function (index, vo) {
                            let obj = {
                              x: vo.lat,
                              y: vo.lng,
                              title: vo.aptName,
                              cont: vo.aptName +"</h6><br>"+vo.jibun+"<br>"+vo.buildYear,
                            };
                            addMarkersOnMap($("#myFav").val(), obj);

                            $("#searchResult").append(
                              "<h5>"+vo.aptName+"</h5>지번 : " + vo.jibun + "&ensp;&ensp;건축 연도 : " + vo.buildYear+"<br>"
                            );
                          }); //each
                        }, //function
                        "json"
                      ); //get
                  });
                });
              
            </script>
            <h5>
              시도 :
              <select id="sido">
                <option value="0">선택</option>
              </select>
              구군 :
              <select id="gugun">
                <option value="0">선택</option>
              </select>
              읍면동 :
              <select id="dong">
                <option value="0">선택</option>
              </select>
            </h5>
            <!-- here end -->
            
            <!-- 관심지역 -->
            <h5>관심지역 :
            <select id="myFav">
                <option value="0">선택</option>
                <c:if test="${user_session.myInterestArea.dong1 != null}">
                <option value="${user_session.myInterestArea.dong1}">${user_session.myInterestArea.dong1}</option>
                </c:if>
                <c:if test="${user_session.myInterestArea.dong2 != null}">
                <option value="${user_session.myInterestArea.dong2}">${user_session.myInterestArea.dong2}</option>
                </c:if>
                <c:if test="${user_session.myInterestArea.dong3 != null}">
                <option value="${user_session.myInterestArea.dong3}">${user_session.myInterestArea.dong3}</option>
                </c:if>
              </select>
</h5>
           <br />
            <table class="table table-hover">
            <thead>
             <tr>
             <th><label>소매</label>
            <input type="checkbox" name="shopType" value="D" id="D" checked /></th>
             <th>
            <label>학문/교육</label>
            <input type="checkbox" name="shopType" value="R" id="R" checked />
             </th>
             <th>
            <label>생활서비스</label>
            <input type="checkbox" name="shopType" value="F" id="F" checked />
             </th>
             <th>
            <label>음식</label>
            <input type="checkbox" name="shopType" value="Q" id="Q" checked />
             </th>
             
             <th>
            <label>관광/여가/오락</label>
            <input type="checkbox" name="shopType" value="N" id="N" checked />
             </th>
             
             </tr>
             <tr>
             <th>
            <label>부동산</label>
            <input type="checkbox" name="shopType" value="L" id="L" checked />
             </th>
             <th>
            <label>의료</label>
            <input type="checkbox" name="shopType" value="S" id="S" checked />
             </th>
             <th>
            <label>숙박</label>
            <input type="checkbox" name="shopType" value="O" id="O" checked />
             </th>
             <th>
            <label>스포츠</label>
            <input type="checkbox" name="shopType" value="P" id="P" checked />
             </th>
             <th> <button type="button" id="shopSearch" class="btn btn-secondary"
						style="float: right;">검색</button></th>
             </tr>
             </thead>
             </table>
          <label>상권정보 보기</label>
            <input
              type="checkbox"
              name="showShop"
              value=""
              id="showShoppingMall"
            />
           

            <br /><br />
            <div id="searchResult" style="float: left;"></div>

            <form name="searchform" id="searchform" method="GET" action="">
              <div class="container" align="center">
                <div class="col-lg-8" align="center">
                  <table class="table table-borderless">
                    <c:if test="${!empty testList}">
                      <select
                        name="selectBox"
                        id="selectBox"
                        style="width: 80px;"
                        class="select_02"
                      >
                        <c:forEach
                          var="testList"
                          items="${testList}"
                          varStatus="i"
                        >
                          <option value="${testList.name}"
                            >${testList.name}</option
                          >
                        </c:forEach>
                      </select>
                    </c:if>
                  </table>
                  <c:forEach var="houseDeal" items="${houseDealList}">
                    <table class="table table-active">
                      <tbody>
                        <tr class="table-info">
                          <td colspan="2">NO. ${houseDeal.no}</td>
                        </tr>
                        <tr>
                          <td class="table-danger">
                            <strong
                              ><a href="${root}/housedeal/apt/${houseDeal.no}">
                                아파트이름 : ${houseDeal.aptName}</a
                              ></strong
                            >
                          </td>
                          <td class="table-danger" align="right">
                            동 : ${houseDeal.dong}
                          </td>
                          <td class="table-danger" align="right">
                            거래금액 : ${houseDeal.dealAmount}
                          </td>
                          <td class="table-danger" align="right">
                            타입 : ${houseDeal.type}
                          </td>
                        </tr>
                      </tbody>
                    </table>
                  </c:forEach>
                </div>
              </div>
            </form>

            <!-- map 시작 -->

            <div
              id="map"
              style="width: 100%; height: 500px; margin: auto;"
            ></div>
            
            <hr><h3>상권 정보 리스트</h3>
            <div id="shoplist"></div>
            
            
            
            
            
            <script src="https://unpkg.com/@google/markerclustererplus@4.0.1/dist/markerclustererplus.min.js"></script>
            <script
              async
              defer
              src="https://maps.googleapis.com/maps/api/js?key=AIzaSyD83g3xR8odYyDHs1gVWcx0Oiia27gGy9k&callback=initMap"
            ></script>
            <script>
              let map;

              let multi = {
                lat: 37.5012743,
                lng: 127.039585,
              };

              let locations = new Array();

              function setMapByCoord(x, y) {
                var loc = new google.maps.LatLng(parseFloat(x), parseFloat(y));

                map.setCenter(loc);
              }

              var marker;
              var marker;
              var markers = [];
              var shopMarkers = [];

              function addMarkersOnMap(dong, obj) {
                marker = new google.maps.Marker({
                  position: new google.maps.LatLng(
                    parseFloat(obj.x),
                    parseFloat(obj.y)
                  ), //마커가 위치할 위도,경도
                  //icon: obj.img, // 마커로 사용할 이미지
                  title: obj.title, // 마커에 마우스 포인트를 갖다댔을 때 뜨는 타이틀
                  info: obj.cont,
                });

                //marker listener등록
                marker.addListener("click", function () {
                  //마커 클릭시 이벤트
                  //search_result2.rowClicked(obj.index);
                  // 여기에 거래내역 불러오는 이벤트 추가
                  getdeal(dong,this.title);
                  // + 인포 띄우기
                  map.setZoom(17);
                  map.setCenter(this.getPosition()); // map의 센터를 마커의 위치로 지정
                });

                marker.setMap(map); //map 그리기
                markers.push(marker); //마커 정보 배열 등록(삭제를 위한 배열)
              }

              // 초기 map 설정
              function initMap() {
                map = new google.maps.Map(document.getElementById("map"), {
                  zoom: 15,
                  center: multi,
                });

                var labels = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
                var marker = new google.maps.Marker({
                  position: multi,
                  map: map,
                });

                // marker 적용방식 -- 위에 marker 제대로 받아오는거가 안되있음
                //var markerCluster = new MarkerClusterer(map, markers, {
                //  imagePath:
                //   "https://developers.google.com/maps/documentation/javascript/examples/markerclusterer/m",
                // });
              }

              function getdeal(dong, title) {
                $.get(
                  "${pageContext.request.contextPath}/deal",
                  {
                    dong: dong,
                    aptname: title,
                  },
                  function (data, status) {
                    $("#searchResult").empty();
                    $("#searchResult").append("<h5>"+data[0].aptName+"</h5>");
                    $.each(data, function (index, vo) {
                      $("#searchResult").append(
                    	"거래금액 : " + vo.dealAmount + "&ensp;&ensp;전용면적 : " + vo.area+"&ensp;&ensp;거래구분 : 아파트 매매<br>"
                      );
                    }); //each
                  }, //function
                  "json"
                ); //get
              }

              function deleteMarkers() {
                for (var i = 0; i < markers.length; i++) {
                  markers[i].setMap(null);
                }

                markers = [];
              }

              function hideShopMarkers() {
                for (var i = 0; i < shopMarkers.length; i++) {
                  shopMarkers[i].setMap(null);
                }
              }

              function showShopMarkers() {
                for (var i = 0; i < shopMarkers.length; i++) {
                  shopMarkers[i].setMap(map);
                }
              }

              function addShopMarkersOnMap(obj2) {
                marker = new google.maps.Marker({
                  position: new google.maps.LatLng(
                    parseFloat(obj2.x),
                    parseFloat(obj2.y)
                  ), //마커가 위치할 위도,경도
                  //icon: obj.img, // 마커로 사용할 이미지
                  title: obj2.title, // 마커에 마우스 포인트를 갖다댔을 때 뜨는 타이틀
                  icon: obj2.img,
                  info: obj2.cont,
                });

                //marker listener등록
                // marker.addListener("click", function () {
                //마커 클릭시 이벤트
                //search_result2.rowClicked(obj.index);
                // 여기에 거래내역 불러오는 이벤트 추가
                // + 인포 띄우기
                //  });

                marker.setMap(map); //map 그리기
                shopMarkers.push(marker); //마커 정보 배열 등록(삭제를 위한 배열)
              }

              function deleteShopMarkers() {
                for (var i = 0; i < shopMarkers.length; i++) {
                  shopMarkers[i].setMap(null);
                }

                shopMarkers = [];
              }
              
              
              
              
              
              
              function getList(vo) {
            	  var img = "";
                  switch (vo.code1) {
                    case "D":
                      img = new google.maps.MarkerImage(
                        "./resources/img/shop.png",
                        null,
                        null,
                        null,
                        new google.maps.Size(30, 30)
                      );
                      break;
                    case "R":
                      img = new google.maps.MarkerImage(
                        "./resources/img/school.png",
                        null,
                        null,
                        null,
                        new google.maps.Size(30, 30)
                      );
                      break;
                    case "F":
                      img = new google.maps.MarkerImage(
                        "./resources/img/service.png",
                        null,
                        null,
                        null,
                        new google.maps.Size(30, 30)
                      );
                      break;
                    case "Q":
                      img = new google.maps.MarkerImage(
                        "./resources/img/food.png",
                        null,
                        null,
                        null,
                        new google.maps.Size(30, 30)
                      );
                      break;
                    case "N":
                      img = new google.maps.MarkerImage(
                        "./resources/img/game-pool.png",
                        null,
                        null,
                        null,
                        new google.maps.Size(30, 30)
                      );
                      break;
                    case "L":
                      img = new google.maps.MarkerImage(
                        "./resources/img/real-estate.png",
                        null,
                        null,
                        null,
                        new google.maps.Size(30, 30)
                      );
                      break;
                    case "S":
                      img = new google.maps.MarkerImage(
                        "./resources/img/medical-doctor.png",
                        null,
                        null,
                        null,
                        new google.maps.Size(30, 30)
                      );
                      break;
                    case "O":
                      img = new google.maps.MarkerImage(
                        "./resources/img/hotel-building.png",
                        null,
                        null,
                        null,
                        new google.maps.Size(30, 30)
                      );
                      break;
                    case "P":
                      img = new google.maps.MarkerImage(
                        "./resources/img/ping-pong.png",
                        null,
                        null,
                        null,
                        new google.maps.Size(30, 30)
                      );
                      break;
                  }

                  let obj2 = {
                    x: vo.lat,
                    y: vo.lng,
                    title: vo.shopName,
                    img: img,
                    cont: "<h6>"+vo.shopName +"</h6><br>"+vo.codename4+"<br>"+vo.address
                  };
                  addShopMarkersOnMap(obj2);
                  
                  $("#shoplist").append(
                          "<h5>"+vo.shopName+"</h5>업종 : " + vo.codename4 + "&ensp;&ensp;지번 : " + vo.address+"<br><br>"
                        );
              }
            </script>
          </div>
        </div>
      </section>
      <!-- section end -->
    </div>
    <%@ include file="/WEB-INF/views/static/footer.jsp"%>
  </body>
</html>
