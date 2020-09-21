<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.contextPath}"></c:set>
	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>OH!늘의 집</title>

<script type="text/javascript">
	function pageMove(pg) {
		document.getElementById("pg").value = pg;
		
		document.getElementById("pageform").action = "${root}/shop.do";
		document.getElementById("pageform").submit();
	}
</script>
</head>
<body>
<%@ include file="/WEB-INF/views/static/header.jsp"%>
	<form name="pageform" id="pageform" method="GET" action="">
		<input type="hidden" name="act" id="act" value="list">
		<input type="hidden" name="pg" id="pg" value="">
		<input type="hidden" name="dong" id="dong" value="${dong}">
	</form>


		<div class="container" align="center">
			<div class="col-lg-8" align="center">
				<table class="table table-active">
					<tbody>
						<tr class="table-info">
							<c:forEach var="dong" items="${myList}" varStatus="status">
								<td align="center"><a
									href="${root}/shoplist?dong=${status.index}&pg=1">${dong}</a>
									</td>
							</c:forEach>
						</tr>
					</tbody>
				</table>
			</div>
		</div>


		<div class="container" align="center">
			<div class="col-lg-8" align="center">
				<c:forEach var="shop" items="${shopList}">
					<table class="table table-active">
						<tbody>
							<tr class="table-info">
								<td colspan="2">NO. ${shop.no}</td>
							</tr>
							<tr>
								<td class="table-danger"><strong><a
										href="${root}/shoplist/detail?no=${shop.no}"> 상호명 :
											${shop.shopName}</a></strong></td>
								<td class="table-danger" align="right">카테고리 :
									${shop.codename4}</td>
								<td class="table-danger" align="right">주소 :
									${shop.jibuaddress}</td>
							</tr>
						</tbody>
					</table>
				</c:forEach>
				<table>
					<tr>
						<td>
							<!-- page 출력부분 --> ${navigation.navigator}
						</td>
					</tr>
				</table>
			</div>
		</div>
	<%@ include file="/WEB-INF/views/static/footer.jsp"%>
</body>
</html>