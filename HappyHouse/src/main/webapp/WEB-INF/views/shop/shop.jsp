<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<title>SSAFY</title>
</head>
<body>
	<%@ include file="/WEB-INF/views/static/header.jsp"%>
	<div align="center">
	<br>
	<h2>상세 정보</h2>
	<br>
		<div class="container" align="center">
			<div class="col-lg-6" align="center">
				<table class="table table-active">
					<tbody>
						<tr class="table-info">
							<td align="left">상호명</td>
							<td>${shop.shopName}</td>
						</tr>
						<tr class="table-info">
							<td align="left">업종</td>
							<td>${shop.codename4}</td>
						</tr>
						<tr class="table-info">
							<td align="left">시</td>
							<td>${shop.city}</td>
						</tr>
						<tr class="table-info">
							<td align="left">구</td>
							<td>${shop.gu}</td>
						</tr>
						<tr class="table-info">
							<td align="left">법정동</td>
							<td>${shop.dong}</td>
						</tr>
						<tr class="table-info">
							<td align="left">도로명</td>
							<td>${shop.address}</td>
						</tr>
						<tr class="table-info">
							<td align="left">지번</td>
							<td>${shop.jibuaddress}</td>
						</tr>

					</tbody>
				</table>
			</div>
		</div>
	</div>
	<%@ include file="/WEB-INF/views/static/footer.jsp"%>
</body>
</html>