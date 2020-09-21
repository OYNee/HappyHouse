<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script>
function save() {
	document.getElementById("addAreaFrom").action = "${root}/shop";
	document.getElementById("addAreaFrom").submit();
}

</script>
<title>SSAFY</title>
</head>
<body>
<%@ include file="/WEB-INF/views/static/header.jsp"%>
<div align="center">
    <div class="container" align="center">
        <div class="col-lg-6" align="center">
            <form id="addAreaFrom" method="post" action="">
                <input type="hidden" name="act" id="act" value="save">
                <div class="form-group" align="left">
                    <label>지역1(법정동) : </label>
                    <input type="text" class="form-control" id="dong1" name="dong1" placeholder="" value="${myArea.dong1}">
                </div>
                <div class="form-group" align="left">
                    <label>지역2(법정동) : </label>
                    <input type="text" class="form-control" id="dong2" name="dong2" placeholder="" value="${myArea.dong2}">
                </div>
                <div class="form-group" align="left">
                    <label>지역3(법정동) : </label>
                    <input type="text" class="form-control" id="dong3" name="dong3" placeholder="" value="${myArea.dong3}">
                </div>
                <div class="form-group" align="center">
                	<button type="reset" class="btn btn-warning">RESET</button>
                	<button type="button" class="btn btn-primary"
                            onclick="javascript:save();">SAVE</button>
                    
                </div>
            </form>
        </div>
    </div>
</div>
<%@ include file="/WEB-INF/views/static/footer.jsp"%>
</body>