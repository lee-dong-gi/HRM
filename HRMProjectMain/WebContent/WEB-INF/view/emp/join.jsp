<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>

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

<title>HRM Project Net - 회원가입</title>
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
<style>
#button {
	margin-left: 121px;
	margin-top: 1px;
	width: 80px;
	height: 30px;
	border: thin;
	font-size: 14px;
	position: relative;
	left: 115px;
	top: -33px;
}
#authbutton {
	margin-left: 121px;
	margin-top: 1px;
	width: 80px;
	height: 33px;
	border: thin;
	font-size: 14px;
	position: relative;
	left: 115px;
	top: -33px;
}
#eauth {
	margin-left: 121px;
	margin-top: 1px;
	width: 130px;
	height: 33px;
	border: thin;
	font-size: 14px;
	position: relative;
	left: 115px;
	top: -33px;
}
#authcomple {
	margin-left: 121px;
	margin-top: 1px;
	width: 130px;
	height: 33px;
	border: thin;
	font-size: 14px;
	position: relative;
	left: 115px;
	top: -33px;
}
</style>
<script type="text/javascript">
	
<%String username = (String) session.getAttribute("name");%>
	/* 아이디 중복확인 */
	var i = 0;
	
	function idCheck() {
		if ($("#id").val() == "") {
			alert("아이디를 입력하세요")
			return false;
		}
		var id = $("#id").val();
		var param = "id=" + id;
		$.ajax({
			type : "get",
			url : "chk11.do",
			data : param,
			dataType : "json"
		}).done(function(json) {
			console.log(json)
			if (json == "yes") {
				alert("이미 등록된 아이디입니다");
			} else {
				alert("사용가능한 아이디입니다");
				$("#hid").val("check");
			}
		}).fail(function() {
			alert("실패")
		})

	}

	/* 비밀번호 확인 */
	function pwCheck() {
		if ($("#pw1").val() == "") {
			alert("비밀번호를 입력하세요")
			return false;
		}
		if ($("#pw1").val() == $("#pw2").val()) {
			alert("비밀번호가 일치합니다");
			i = 1;
		} else {
			alert("비밀번호가 일치하지 않습니다")
			i = 0;
		}
	}

	/* 공백 입력시 알림 */
	function check() {
		if ($("#name").val() == "") {
			alert("이름을 입력하세요")
			return false;
		}

		else if ($("#id").val() == "") {
			alert("아이디를 입력하세요")
			return false;
		}

		else if ($("#pw1").val() == "") {
			alert("비밀번호를 입력하세요")
			return false;
		}

		else if ($("#birth").val() == "") {
			alert("생년월일을 입력하세요")
			return false;
		}

		else if ($("#email").val() == "") {
			alert("이메일을 입력하세요")
			return false;
		}

		else if ($("#phonenum").val() == "") {
			alert("전화번호를 입력하세요")
			return false;
		}

		else if ($("#deptno").val() == "") {
			alert("부서를 선택하세요")
			return false;
		}

		else if ($("#level").val() == "") {
			alert("직급을 선택하세요")
			return false;
		}

		else if ($("#hid").val() == "") {
			alert("아이디 중복검사를 하세요")
			return false;
		}

		else if (i == 0) {
			alert("비밀번호 일치확인을 해주세요")
			return false;
		}
		
		var authcomple = $('#authcompleval').val();
		
		if (authcomple==""|authcomple!="ok") {
			alert("이메일 인증을 진행해주세요!")
			return false;
		}

		else{
			alert("회원가입 되었습니다.")
			return true;
		}

	}
	
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
			$("#emailall").append("<span id='authcomple'>인증완료</span>");	
			$("#emailall").append("<input type='hidden' id='authcompleval' value='ok'/>");	
		}
	}).fail(function(e) {
		alert(e.responseTexst);
	})
}

function sendmail(){
	var email = $("#email").val();
	$("#authNum input").each(function() {
		$("#authNum input:eq(0)").remove();
	});
		$("#authbutton:eq(0)").remove();
	var url = "send";
	var params = "inputReceiver="+email;
	$.ajax({
		type : "post",
		url : url,
		data : params,
		dataType : "json"
	}).done(function(args) { 
				if(args=="메일이 발송되었습니다 확인해주세요!"){
					$("#fonta").show();
					$("#authNum").append("<input style='font-size: 10pt;' class='form-control'id='emailauthcheck' type='text' name='emailauthcheck' pattern='[0-9]{4}'placeholder='인증번호 4자리입력'>");
					$("#authNum").append("<button class='btn btn-secondary' type='button' id='authbutton' onclick='emailauth()'>인증확인</button>");	
				}
					alert(args);
	}).fail(function(e) {
		alert(e.responseTexst);
	})
	
}

</script>
</head>

<body id="page-top">

	<!-- Page Wrapper -->
	<div id="wrapper">

		<!-- Sidebar -->
		<ul
			class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion"
			id="accordionSidebar">

			<!-- Sidebar - Brand -->
			<a
				class="sidebar-brand d-flex align-items-center justify-content-center"
				href="/HRMProjectMain/jsp/user/main">
				<div class="sidebar-brand-icon rotate-n-15"></div>
				<div class="sidebar-brand-text mx-3">
					HRM<sup>Project</sup>
				</div>
			</a>

			<!-- Divider -->
			<!-- <hr class="sidebar-divider my-0"> -->

			<br>

			<!-- Heading -->
      <div class="sidebar-heading">
       	 메뉴
      </div>
	 <hr class="sidebar-divider"/>
      <!-- Nav Item - Pages Collapse Menu -->
      <li class="nav-item">
        <a class="nav-link collapsed" href="/HRMProjectMain/jsp/approve/appboard?pageNum=1&selectapp=0">
          <i class="fas fa-fw"></i>
          <span>결재</span>
        </a>
      </li>

      <!-- Nav Item - Utilities Collapse Menu -->
      <li class="nav-item">
        <a class="nav-link collapsed" href="/HRMProjectMain/jsp/chat/chatroom">
          <i class="fas fa-fw"></i>
          <span>채팅</span>
        </a>
      </li>
      <!-- Nav Item - Utilities Collapse Menu -->
		<li class="nav-item">
        <a class="nav-link collapsed" href="#">
          <i class="fas fa-fw"></i>
          <span>캘린더</span>
        </a>
      </li>
      <!-- Nav Item - Utilities Collapse Menu -->
      <li class="nav-item">
        <a class="nav-link collapsed" href="#">
          <i class="fas fa-fw"></i>
          <span>투표</span>
        </a>
      </li>
      <!-- Nav Item - Utilities Collapse Menu -->
      <li class="nav-item">
        <a class="nav-link collapsed" href="#">
          <i class="fas fa-fw"></i>
          <span>근태관리</span>
        </a>
      </li>
			<li class="nav-item"><a class="nav-link collapsed"
				href="/HRMProjectMain/jsp/notice/list?now=1"> <i
					class="fas fa-fw"></i> <span>공지사항</span>
			</a></li>
			<li class="nav-item"><a class="nav-link collapsed"
				href="/HRMProjectMain/jsp/free/list?now=1"> <i class="fas fa-fw"></i>
					<span>자유게시판</span>
			</a></li>
			<li class="nav-item">
        <a class="nav-link collapsed" href="#">
          <i class="fas fa-fw"></i>
          <span>조직도</span>
        </a>
      </li>
      <li class="nav-item">
        <a class="nav-link collapsed" href="/HRMProjectMain/jsp/dept/deptlist?pageNum=1">
          <i class="fas fa-fw"></i>
          <span>부서관리</span>
        </a>
      </li>
      
      
			<!-- Divider -->
			<hr class="sidebar-divider d-none d-md-block">

			<!-- Sidebar Toggler (Sidebar) -->
			<div class="text-center d-none d-md-inline">
				<button class="rounded-circle border-0" id="sidebarToggle"></button>
			</div>

		</ul>
		<!-- End of Sidebar -->

		<!-- Content Wrapper -->
		<div id="content-wrapper" class="d-flex flex-column">

			<!-- Main Content -->
			<div id="content">

				<!-- Topbar -->
				<nav
					class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">

					<!-- Sidebar Toggle (Topbar) -->
					<button id="sidebarToggleTop"
						class="btn btn-link d-md-none rounded-circle mr-3">
						<i class="fa fa-bars"></i>
					</button>

					<!-- Topbar Navbar -->
					<ul class="navbar-nav ml-auto">
						<!-- Nav Item - Search Dropdown (Visible Only XS) -->
						<li class="nav-item dropdown no-arrow d-sm-none">
							<div
								class="dropdown-menu dropdown-menu-right p-3 shadow animated--grow-in"
								aria-labelledby="searchDropdown">
								<form class="form-inline mr-auto w-100 navbar-search">
									<div class="input-group">
										<input type="text"
											class="form-control bg-light border-0 small"
											placeholder="Search for..." aria-label="Search"
											aria-describedby="basic-addon2">
										<div class="input-group-append">
											<button class="btn btn-primary" type="button">
												<i class="fas fa-search fa-sm"></i>
											</button>
										</div>
									</div>
								</form>
							</div>
						</li>

						<!-- Nav Item - User Information -->
						<li class="nav-item dropdown no-arrow"><a
							class="nav-link dropdown-toggle" href="#" id="userDropdown"
							role="button" data-toggle="dropdown" aria-haspopup="true"
							aria-expanded="false"> <span
								class="mr-2 d-none d-lg-inline text-gray-600 small"><%=username%></span>
								<img class="img-profile rounded-circle"
								src="${pageContext.request.contextPath}/resources/img/profile.png">
						</a> <!-- Dropdown - User Information -->
							<div
								class="dropdown-menu dropdown-menu-right shadow animated--grow-in"
								aria-labelledby="userDropdown">
								<a class="dropdown-item" href="#"> <i
									class="fas fa-user fa-sm fa-fw mr-2 text-gray-400"></i> 프로필
								</a>

								<div class="dropdown-divider"></div>
								<a class="dropdown-item" href="logout" data-toggle="modal"
									data-target="#logoutModal"> <i
									class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>
									로그아웃
								</a>
							</div></li>

					</ul>

				</nav>
				<!-- End of Topbar -->

				<!-- Begin Page Content -->
				<div class="container-fluid">

					<!-- Page Heading -->
					<div
						class="d-sm-flex align-items-center justify-content-between mb-4">
					</div>
					<!-- 여기부터 -->
					<form method="post" onsubmit="return check()"
						class="form-horizontal">

						<h3
							style="padding-top: 30px; padding-bottom: 10px; font-weight: bold;"
							align="center">회원가입</h3>
						<div class="form-group form-group-lg"
							style="padding-top: 50px; font-size: 13pt;" align="center">
							
							<div id=font>이름</div>
							<div style="padding-top: 10px;" class="col-sm-2">
								<input style="font-size: 10pt;" class="form-control" type="text"
									id="name" name="name" placeholder="이름 입력"><span
									id="nch"></span><br>
							</div>


							<div id=font>아이디</div>
							<div style="padding-top: 10px;" class="col-sm-2">
								<input style="font-size: 10pt;" class="form-control" type="text"
									id="id" name="id" placeholder="아이디 입력"> <input
									type="button" id="button" onclick="idCheck()" value="중복확인"
									class="btn btn-secondary"><br> <input
									type="hidden" id="hid" value="">
							</div>


							<div id=font>비밀번호</div>
							<div style="padding-top: 10px;" class="col-sm-2">
								<input style="font-size: 10pt;" class="form-control" id="pw1"
									type="password" name="passwd" placeholder="비밀번호 입력" /><br>
							</div>


							<div id=font>비밀번호 확인</div>
							<div style="padding-top: 10px;" class="col-sm-2">
								<input style="font-size: 10pt;" class="form-control" id="pw2"
									type="password" placeholder="비밀번호 확인" /> <input type="button"
									id="button" value="확인" onclick="pwCheck()"
									class="btn btn-secondary"><br>
							</div>


							<div id=font>생년월일</div>
							<div style="padding-top: 10px;" class="col-sm-2">
								<input style="font-size: 10pt;" class="form-control" id="birth"
									name="birth" type="text" placeholder="ex)19910101"><br>
							</div>


							<div id=font>이메일</div>
							<div style="padding-top: 10px;" class="col-sm-2" id="emailall" style="display:inline;">
								<input style="font-size: 10pt; display: inline;" class="form-control" id="email"
									name="email" type="email" placeholder="ex)abc@naver.com"><br>
								<button type="button" class='btn btn-secondary' id="eauth" name="eauth" onclick="sendmail()">인증번호 발송</button>
							</div>

							<div id=fonta style="display: none">이메일 인증</div>
							<div style="padding-top: 10px;" class="col-sm-2" id="authNum">
							</div>
 
							<div id=font>전화번호</div>
							<div style="padding-top: 10px;" class="col-sm-2">
								<input style="font-size: 10pt;" class="form-control"
									id="phonenum" type="text" name="phonenum" pattern="[0-9]{11}"
									placeholder="숫자만 입력하세요"><br>
							</div>


							<div id=font>부서</div>
							<div style="padding-top: 10px;" class="col-sm-2">
								<select id="deptno" name="deptno">
									<option value="">선택</option>
								<c:forEach var="item" items="${dept}">
									<option value="${item.deptno}">${item.dname}</option>
								</c:forEach>
								</select>
							</div>
							


							<div id=font>직급</div>
							<div style="padding-top: 10px;" class="col-sm-2">
								<select id="level" name="level">
									<option value="">선택</option>
									<option value="사원">사원</option>
									<option value="주임">주임</option>
									<option value="대리">대리</option>
									<option value="과장">과장</option>
									<option value="차장">차장</option>
									<option value="팀장">팀장</option>
								</select>
							</div>


							<input type="hidden" id="app" value="0" name="approval">
							
							<br> <br> <input style="width: 80px;" type="button"
								value="취소" class="btn btn-primary btn-sm"
								onclick="location.href='/HRMProjectMain/jsp/user/login'">
							<input style="width: 80px;" type="submit" value="가입"
								class="btn btn-primary btn-sm">


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

	<!-- Scroll to Top Button-->
	<a class="scroll-to-top rounded" href="#page-top"> <i
		class="fas fa-angle-up"></i>
	</a>

	<!-- Logout Modal-->
	<div class="modal fade" id="logoutModal" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">정말 로그아웃 하시겠습니까?</h5>
				</div>
				<div class="modal-body">로그아웃을 하고싶으면 현재 창의 로그아웃 버튼을 클릭해주세요</div>
				<div class="modal-footer">
					<button class="btn btn-secondary" type="button"
						data-dismiss="modal">취소</button>
					<a class="btn btn-primary" href="/HRMProjectMain/jsp/user/logout">로그아웃</a>
				</div>
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
