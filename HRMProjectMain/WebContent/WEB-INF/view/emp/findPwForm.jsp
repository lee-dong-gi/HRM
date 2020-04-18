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
	function check() {
		if ($("#id").val() == "") {
			alert("아이디를 입력하세요")
			return false;
		}

		if ($("#email").val() == "") {
			alert("이메일을 입력하세요")
			return false;
		}

		var id = $("#id").val();
		var email = $("#email").val();
		var param = "id=" + id + "&email=" + email;
		$.ajax({
			type : "get",
			url : "findPw",
			data : param,
			dataType : "json"
		}).done(function(json) {
			if (json == "yes") {
				alert("일치하는 정보가 없습니다");
			} else {
				window.location.href = "resetPwForm?" + param;
			}
		}).fail(function() {
			alert("실패")
		})
	}
</script>
</head>

<body id="page-top">

	<!-- Page Wrapper -->
	<div id="wrapper">

				<!-- Begin Page Content -->
				<div class="container-fluid">

					<!-- Page Heading -->
					<div
						class="d-sm-flex align-items-center justify-content-between mb-4">
					</div>
					<!-- 여기부터 -->
					<form class="form-horizontal">
						<div class="container">
							<h3 style="padding-top: 150px; font-weight: bold;" align="center">비밀번호
								찾기</h3>
							<div class="form-group"
								style="padding-top: 50px; font-size: 13pt;" align="center">
								<label for="inputEmail3" class="col-sm-2 control-label">아이디</label>
								<div class="col-sm-2">
									<input type="text" name="id" class="form-control" id="id"
										placeholder="아이디 입력">
								</div>
							</div>

							<div class="form-group"
								style="padding-top: 50px; font-size: 13pt;" align="center">
								<label for="inputPassword3" class="col-sm-2 control-label">이메일</label>
								<div class="col-sm-2">
									<input type="email" name="email" class="form-control"
										id="email" placeholder="이메일 입력">
								</div>
							</div>

							<div class="form-group"
								style="padding-top: 50px; font-size: 13pt;" align="center">
								<div class="col-sm-offset-2 col-sm-10">
									<input style="width: 80px;" type="button" value="취소"
										class="btn btn-secondary"
										onclick="location.href='/HRMProjectMain/jsp/user/login'">
									<input type="button" value="비밀번호 찾기" class="btn btn-secondary"
										onclick="check()">
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
