<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">

<title>HRM Project Net - 비밀번호 찾기</title>
<script src="https://code.jquery.com/jquery-3.1.0.min.js"></script>
<!-- Custom fonts for this template-->
<link
	href="${pageContext.request.contextPath}/resources/vendor/fontawesome-free/css/all.min.css"
	rel="stylesheet" type="text/css">
<link
	href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
	rel="stylesheet">

<!-- Custom styles for this template-->
<link
	href="${pageContext.request.contextPath}/resources/css/sb-admin-2.min.css"
	rel="stylesheet">
<script type="text/javascript">
	
<%String username = (String) session.getAttribute("name");%>
	var i = 0;
	function check() {

		/* if(i == 0){
			alert("비밀번호 일치확인을 해주세요")
			return false} */
		var passwd = $("#pw1").val();
		window.location.href = "up?id=test&passwd=" + passwd;
	}
	function pwCheck() {
		i = 1;
		if ($("#pw1").val() == $("#pw2").val()) {
			alert("비밀번호가 일치합니다.")
		} else {
			alert("동일하게 입력하세요.")
			i = 0
		}
	}
</script>
</head>

<body id="page-top">

	<!-- Page Wrapper -->
	<div id="wrapper">

		<!-- Content Wrapper -->
		<div id="content-wrapper" class="d-flex flex-column">

			<!-- Main Content -->
			<div id="content">


				<!-- Begin Page Content -->
				<div class="container-fluid">

					<!-- Page Heading -->
					<div
						class="d-sm-flex align-items-center justify-content-between mb-4">
						<h1 class="h3 mb-0 text-gray-800" style="font-weight: bold;">일정</h1>
					</div>
					<!-- 여기부터 -->
					<form method="post" onsubmit="return check()"
						class="form-horizontal">

						<div class="container">
							<h3 style="padding-top: 150px; font-weight: bold;" align="center">비밀번호
								재설정</h3>

							<div class="form-group"
								style="padding-top: 50px; font-size: 13pt;" align="center">
								<label class="col-sm-2 control-label">새 비밀번호</label>
								<div class="col-sm-2">
									<input type="password" id="pw1" name="passwd"
										class="form-control" placeholder="새 비밀번호 입력">
								</div>
							</div>

							<div class="form-group"
								style="padding-top: 50px; font-size: 13pt;" align="center">
								<label class="col-sm-2 control-label">비밀번호 재입력</label>
								<div class="col-sm-2">
									<input type="password" id="pw2" class="form-control"
										placeholder="비밀번호 재입력">
								</div>
							</div>

							<div class="form-group"
								style="padding-top: 50px; font-size: 13pt;" align="center">
								<div class="col-sm-offset-2 col-sm-10">
									<input style="width: 100px;" type="submit" value="확인"
										onclick="check()" class="btn btn-secondary">
								</div>
							</div>

						</div>
					</form>







				</div>
			</div>
		</div>
		<!-- /.container-fluid -->

	</div>
	<!-- End of Main Content -->

	<!-- Footer -->
	<footer class="sticky-footer bg-white">
		<div class="container my-auto">
			<div class="copyright text-center my-auto">
				<span>Copyright &copy; ERP Project Website2020</span>
			</div>
		</div>
	</footer>
	<!-- End of Footer -->

	</div>
	<!-- End of Content Wrapper -->

	</div>
	<!-- End of Page Wrapper -->

	
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
