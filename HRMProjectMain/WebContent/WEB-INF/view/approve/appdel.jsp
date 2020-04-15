<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">
<title>HRM Project Net - 결재 삭제</title>
<!-- Custom fonts for this template-->
  <link href="${pageContext.request.contextPath}/resources/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
  <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">

  <!-- Custom styles for this template-->
  <link href="${pageContext.request.contextPath}/resources/css/sb-admin-2.min.css" rel="stylesheet">
</head>

<script type="text/javascript">
function delcheck(){
	var passwd = document.getElementById("passwd").value;
	var url = "appdel";
	var params = "appnum=${appnum}&passwd="+passwd;
	$.ajax({
		type:"post"		//응답 방식	
		,url:url		//요청url = 기존경로 +
		,data:params
		,dataType:"json"}) //resopnse에 담긴 데이터를 json(배열)으로 포멧해서 받아오는타입
		.done(function(args){	//응답이 성공 상태 코드를 반환하면 호출되는 함수//가져온 데이터를 첫번째 매개변수에 넣어줌
			if(args=="삭제처리 되었습니다!"){
				alert(args);
				opener.location.href = "http://192.168.0.43:8083/HRMProjectMain/jsp/approve/appboard?pageNum=1&selectapp=0";
				window.open("about:blank", "_self").close();
			}else{
				alert(args);
			}
 		})
	    .fail(function(request, error) {
	    	alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
	    })
}
</script>

<body>
<div class="navbar-nav bg-gradient-primary" style="color:white;">
<i class="fas fa-fw"></i>
관리자 또는 작성자만 삭제 가능합니다.<br> 보안을 위해 계정 비밀번호를 한번 더 입력 해주세요
</div>
<hr class="sidebar-divider my-0">
<br>
<div class="input-group" style="color:black;">
	<span class="input-group-addon"> &nbsp비밀번호  &nbsp</span>
	<input type="password" id="passwd" name="passwd" class="form-control-sm">&nbsp
	<input type="hidden" name="appnum" value="${appnum}">
	<button class="btn btn-danger btn-sm" type="button" onclick="return delcheck()">확인</button>&nbsp
	<button class="btn btn-secondary btn-sm" type="button" onclick="window.close()">취소</button>
</div>

 <!-- Bootstrap core JavaScript-->
  <script src="${pageContext.request.contextPath}/resources/vendor/jquery/jquery.min.js"></script>
  <script src="${pageContext.request.contextPath}/resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

  <!-- Core plugin JavaScript-->
  <script src="${pageContext.request.contextPath}/resources/vendor/jquery-easing/jquery.easing.min.js"></script>

  <!-- Custom scripts for all pages-->
  <script src="${pageContext.request.contextPath}/resources/js/sb-admin-2.min.js"></script>
</body>

</html>
