<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html lang="ko">
<head>
<title>OH!늘의 집</title>
<script type="text/javascript">
	function logout() {
		document.location.href = "${root}/logout";
	}
	

</script>
</head>
<body>
<%@ include file="/WEB-INF/views/static/header.jsp"%>
<div align="center">
		<h3>OH!늘의 집</h3>
		<!-- End Breadcrumbs -->
      <!-- section start -->
      <section id="index_section">
        <div class="card col-sm-12 mt-1" style="min-height: 450px;">
          <div class="card-body">
          
          	<div id="map" style="width: 100%; height: 500px; margin: auto;"></div>
	<script
		src="https://unpkg.com/@google/markerclustererplus@4.0.1/dist/markerclustererplus.min.js"></script>
	<script async defer
		src="https://maps.googleapis.com/maps/api/js?key=AIzaSyD83g3xR8odYyDHs1gVWcx0Oiia27gGy9k&callback=initMap"></script>
	<script>
		var multi = {
			lat : 37.5012743,
			lng : 127.039585
		};
		var map;
		var locations = new Array(); // https://maps.googpeapis.com/maps/api/geocode/json

		// google에서 위치 정보 제공 하는 aPI // 데이터 수신아 안됨
		//       for(var i=0;i<${houseDealList.length};i++)
		//          {
		//          alert(i);
		//             $(document).ready(function() {
		//             $.get("https://maps.googleapis.com/maps/api/geocode/json"
		//                   ,{   key:'AIzaSyD83g3xR8odYyDHs1gVWcx0Oiia27gGy9k'
		//                   , address: ${houseDealList[i].no}+'+'+${houseDealList[i].aptName};
		//                   }
		//                   , function(data, status) {//alert(data.results[0].geometry.location.lat);
		//                      for (let i = 0; i < data.results.length; i++) {
		//                         var location= new Array(2);
		//                         location[0]=data.results[i].geometry.location.lat;
		//                         location[1]=data.results[i].geometry.location.lng;
		//                         locations.push(location);
		//                         alert(location[0]+" "+location[1]);
		//                      }
		//                   }
		//                   , "json"
		//                );//get
		//             });//ready
		//          }
		//alert(locations.size);
		
		
		// 초기 map 설정
		function initMap() {
			map = new google.maps.Map(document.getElementById('map'), {
				center : multi,
				zoom : 13
			});
			var labels = 'ABCDEFGHIJKLMNOPQRSTUVWXYZ';
			var markers = locations.map(function(location, i) {
				return new google.maps.Marker({
					position : location,
					label : labels[i % labels.length]
				});
			});
			
			// marker 적용방식 -- 위에 marker 제대로 받아오는거가 안되있음
			var markerCluster = new MarkerClusterer(
					map,
					markers,
					{
						imagePath : 'https://developers.google.com/maps/documentation/javascript/examples/markerclusterer/m'
					});
		}
	</script>
	
	

          
          
          <br><br><hr>
          <c:if test="${user_session == null}">
          <div class="form-group form-check" align="right">
					<label> <a href="${root}/findpasswd">비밀번호 찾기</a>
					</label>
				</div>
				</c:if>
		<c:if test="${user_session != null}">
			<div>
				<strong><a href="${root}/move-profile">(${user_session.userid})</a></strong>님 환영합니다.
				<br>
				
			</div>
		</c:if>
		</div>
		</div>
		</section>
	</div>
	<%@ include file="/WEB-INF/views/static/footer.jsp"%>
</body>
</html>