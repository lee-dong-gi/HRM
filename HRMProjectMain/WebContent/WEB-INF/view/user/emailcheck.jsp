<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<title>HRM Project Net - 메일인증</title>
<!-- Custom fonts for this template-->
<link
	href="${pageContext.request.contextPath}/resources/vendor/fontawesome-free/css/all.min.css"
	rel="stylesheet" type="text/css">
<link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">

<!-- Custom styles for this template-->
<link href="${pageContext.request.contextPath}/resources/css/sb-admin-2.min.css" rel="stylesheet">
<style>
.layer{
  position:absolute;
  top:40%;
  left:40%;
  width:100%;
  height:100%;
  margin:-50px 0 0 -50px;
}
 </style>
<script src="https://code.jquery.com/jquery-3.1.0.min.js"></script>
<script type="text/javascript">
$(function(){
	var url = "/HRMProjectMain/jsp/getemail"; 
	$.ajax({
		type : "post",
		url : url,
		dataType : "json"
	}).done(function(args) {
	 	 $("#email").val(args);
	}).fail(function(e) {
	})
});
function emailauth(){
	var num = $("#emailauthcheck").val();
	var url = "/HRMProjectMain/jsp/checkmail";
	var params = "checkNum="+num;
	$.ajax({
		type : "post",
		url : url,
		data : params,
		dataType : "json"
	}).done(function(args) {
		alert(args);
		if(args=="인증되었습니다!"){
			$("#emailauthcheck:eq(0)").remove();
			$("#authbutton:eq(0)").remove();
			$("#eauth:eq(0)").remove();
			$("#fonta:eq(0)").remove();
			window.location.href = '/HRMProjectMain/jsp/user/main';
		}
	}).fail(function(e) {
		alert("인증이 실패하였습니다. 해당 페이지를 다시 열어주세요!");
	})
}

function sendmail(){
	var email = $("#email").val();
	$("#authNum input").each(function() {
		$("#authNum input:eq(0)").remove();
	});
		$("#authbutton:eq(0)").remove();
	var url = "/HRMProjectMain/jsp/send";
	var params = "inputReceiver="+email;
	$.ajax({
		type : "post",
		url : url,
		data : params,
		dataType : "json"
	}).done(function(args) { 
				if(args=="메일이 발송되었습니다 확인해주세요!"){
					$("#fonta").show();
					$("#authNum").append("<input style='font-size: 10pt; display:inline; width:200px;' class='form-control'id='emailauthcheck' type='text' name='emailauthcheck' pattern='[0-9]{4}'placeholder='인증번호 4자리입력'>");
					$("#authNum").append("<button style='display:inline; margin-left: 10px;' class='btn btn-secondary btn-sm' type='button' id='authbutton' onclick='emailauth()'>인증확인</button>");	
				}
					alert(args);
	}).fail(function(e) {
		alert("메일 발송이 실패하였습니다. 해당 페이지를 다시 열어주세요!");
	})
	
}
</script>
</head>
<body>


<div class="layer">
	<div class="card shadow mb-4" style="width:30%;">
          <div class="card-header py-3" style="display: block; text-align: center; color:black; align-content: center;">
          	<h6 style="color:BLACK;font-family: 'Jua', sans-serif;">메일 인증</h6>
          </div> 
         <div id="card-body" class="card-body" style="display: inline; align-content: center;">
			<div style="padding-top: 10px;" id="emailall">  
			<span>이메일</span><input style="width:250px; margin-left:10px; margin-bottom:20px; display:inline;" class="form-control" id="email" name="email" type="email" disabled>
				<button type="button" style="display:inline;" class="btn btn-secondary btn-sm" id="eauth" name="eauth" onclick="sendmail()">인증번호 발송</button>
			</div>
			<hr>
			<div id="fonta" style="display: none; float:left; margin-right: 10px;">이메일 인증</div>
			<div id="authNum"></div>
		</div>
	</div>
</div>
 
                 
      
<!-- Bootstrap core JavaScript-->
	<script
		src="${pageContext.request.contextPath}/resources/vendor/jquery/jquery.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

	<!-- Core plugin JavaScript-->
	<script
		src="${pageContext.request.contextPath}/resources/vendor/jquery-easing/jquery.easing.min.js"></script>

	<!-- Custom scripts for all pages-->
	<script
		src="${pageContext.request.contextPath}/resources/js/sb-admin-2.min.js"></script>
</body>
</html>