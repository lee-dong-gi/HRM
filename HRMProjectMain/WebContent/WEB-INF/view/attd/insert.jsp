<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>출 근</title>

<!-- Custom fonts for this template-->
<link href="${pageContext.request.contextPath}/resources/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
<link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">
<!-- Custom styles for this template-->
<link href="${pageContext.request.contextPath}/resources/css/sb-admin-2.min.css" rel="stylesheet">

<script src="https://code.jquery.com/jquery-3.1.0.min.js"></script>

<!--Font Awesome-->
<script src="https://kit.fontawesome.com/2a30f5ceb7.js" crossorigin="anonymous"></script>
<style>
input[type="submit"] {
	font-family: FontAwesome;
	font-size: 1rem;
}
</style>

<script type="text/javascript">
<%
String username = (String)session.getAttribute("name");
int approval = (int)session.getAttribute("approval");
String userid = (String)session.getAttribute("id");
boolean flag;
if(approval==2){
	flag=true;
}else{
	flag=false;
}
%>
	$(function() {
		var url = "attdlist";
		$.ajax({
			method : "post",
			url : url,
			dataType : "json"
		}).done(
				function(args) { //가져온 데이터를 첫번째 매개변수args(배열)에 넣어줌
					var rs;
					for (var i = 0; i < args.length; i++) {
						if ((args[i].attd_time) == (args[i].off_time)) {
							rs = "";
						} else {
							rs = args[i].off_time;
						}

						$("#attdTable").append(
								"<tr><td>" + args[i].attd_no + "</td>" + "<td>"
										+ args[i].empno + "</td>" + "<td>"
										+ args[i].dname + "</td>" + "<td>"
										+ args[i].name + "</td>" + "<td>"
										+ args[i].attd_time + "</td>" + "<td>"
										+ rs + "</td>" + "<td>"
										+ args[i].emp_late + "</td></tr>");
					}
				}).fail(function(e) {
			alert(e.responseText);
		})
	});
</script>
</head>
<body id="page-top">

	<!-- Page Wrapper -->
	<div id="wrapper">

		<!-- Sidebar -->
		<ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">

			<br />
			<!-- Sidebar - Brand -->
			<a class="sidebar-brand d-flex align-items-center justify-content-center" href="/HRMProjectMain/jsp/user/main">
				<div class="sidebar-brand-text mx-3" style="font-size: 20px">
					HRM<sup>Project</sup>
				</div>
			</a>

			<!-- Divider -->
			<!-- <hr class="sidebar-divider my-0"> -->

			<br />

			<!-- Heading -->
			<div class="sidebar-heading">
       	 메뉴
      </div>
	 <hr class="sidebar-divider">
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
        <a class="nav-link collapsed" href="/HRMProjectMain/jsp/cal/calendar">
          <i class="fas fa-fw"></i>
          <span>캘린더</span>
        </a>
      </li>
      <!-- Nav Item - Utilities Collapse Menu -->
      <li class="nav-item">
        <a class="nav-link collapsed" href="/HRMProjectMain/jsp/attd/attd.do">
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
        <a class="nav-link collapsed" href="/HRMProjectMain/jsp/contact/list.do">
          <i class="fas fa-fw"></i>
          <span>연락처</span>
        </a>
      </li>
      <%if(flag){ %>
       <li class="nav-item">
        <a class="nav-link collapsed" href="/HRMProjectMain/jsp/dept/deptlist?pageNum=1">
          <i class="fas fa-fw"></i>
          <span>부서관리</span>
        </a>
      </li>
       <li class="nav-item">
        <a class="nav-link collapsed" href="/HRMProjectMain/jsp/emp">
          <i class="fas fa-fw"></i>
          <span>인사등록</span>
        </a>
      </li>
       <li class="nav-item">
        <a class="nav-link collapsed" href="/HRMProjectMain/jsp/manage">
          <i class="fas fa-fw"></i>
          <span>인사관리</span>
        </a>
      </li>
      <%}%>

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
				<nav class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">

					<!-- Sidebar Toggle (Topbar) -->
					<button id="sidebarToggleTop" class="btn btn-link d-md-none rounded-circle mr-3">
						<i class="fa fa-bars"></i>
					</button>

					<!-- Topbar Navbar -->
					<ul class="navbar-nav ml-auto">
						<!-- Nav Item - Search Dropdown (Visible Only XS) -->
						<li class="nav-item dropdown no-arrow d-sm-none">
							<div class="dropdown-menu dropdown-menu-right p-3 shadow animated--grow-in" aria-labelledby="searchDropdown">
								<form class="form-inline mr-auto w-100 navbar-search">
									<div class="input-group">
										<input type="text" class="form-control bg-light border-0 small" placeholder="Search for..." aria-label="Search" aria-describedby="basic-addon2">
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
						<li class="nav-item dropdown no-arrow"><a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> <span class="mr-2 d-none d-lg-inline text-gray-600 small">${name}</span> <img class="img-profile rounded-circle" src="${pageContext.request.contextPath}/resources/img/profile.png">
						</a> <!-- Dropdown - User Information -->
							<div class="dropdown-menu dropdown-menu-right shadow animated--grow-in" aria-labelledby="userDropdown">
								<a class="dropdown-item" href="/HRMProjectMain/jsp/emp/myinfo?id=${id}"> <i class="fas fa-user fa-sm fa-fw mr-2 text-gray-400"></i> 프로필
								</a>

								<div class="dropdown-divider"></div>
								<a class="dropdown-item" href="#" data-toggle="modal" data-target="#logoutModal"> <i class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i> 로그아웃
								</a>
							</div></li>
					</ul>
				</nav>
				<!-- End of Topbar -->

				<!-- Begin Page Content -->
				<div class="card shadow mb-4">
					<div class="card-header py-3">
						<h3>
							<a class="d-flex" href="/HRMProjectMain/jsp/attd/attd.do"> <strong>출 근 AttendanceManagement</strong>
							</a>
						</h3>
					</div>

					<div class="card-body">

						<div style="display: inline">
							<form id="excelDown" action="excelDown" method="post" style="display: inline">
								<input type="submit" class="btn btn-outline-secondary btn-sm" value="&#xf381;" />
							</form>
						</div>
						<div style="display: inline">
							<form id="pdf" action="pdf" method="get" style="display: inline">
								<input type="submit" class="btn btn-outline-secondary btn-sm" value="&#xf1c1;" />
							</form>
						</div>      

						<hr>

						<table id="attdTable" class="table table-bordered" style="padding-top: 50px; margin-left: auto; margin-right: auto; text-align: center">
							<thead bgcolor="gray" style="color: white;">
								<tr>
									<th style="width: 30px">No</th>
									<th style="width: 50px">사번</th>
									<th style="width: 50px">부서명</th>
									<th style="width: 50px">사원명</th>
									<th style="width: 100px">출근 시간</th>
									<th style="width: 100px">퇴근 시간</th>
									<th style="width: 50px">지각</th>
								</tr>
							</thead>
						</table>
					</div>
				</div>
			</div>
			<!--End of Main Content-->

			<!-- Footer -->
			<footer class="sticky-footer bg-white">
				<div class="container my-auto">
					<div class="copyright text-center my-auto">
						<span>Copyright &copy; HRM Project Website2020</span>
					</div>
				</div>
			</footer>
			<!-- End of Footer -->

		</div>
		<!-- End of Content Wrapper -->

	</div>
	<!-- End of Page Wrapper -->

	<!-- Scroll to Top Button-->
	<a class="scroll-to-top rounded" href="#page-top"> <i class="fas fa-angle-up"></i>
	</a>

	<!-- Logout Modal-->
	<div class="modal fade" id="logoutModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">정말 로그아웃 하시겠습니까?</h5>
				</div>
				<div class="modal-body">로그아웃을 하고싶으면 현재 창의 로그아웃 버튼을 클릭해주세요</div>
				<div class="modal-footer">
					<button class="btn btn-secondary" type="button" data-dismiss="modal">취소</button>
					<a class="btn btn-primary" href="/HRMProjectMain/jsp/user/logout">로그아웃</a>
				</div>
			</div>
		</div>
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