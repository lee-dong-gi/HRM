<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>HRM Project Net - 공지사항 등록</title>
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
<link rel="stylesheet" type="text/css"
	href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="sb-admin-2.css" />
<style>
.col-mdx-1 {
	flex: 0 0 14.285714285714285714285714285714%;
	min-width: 14.285714285714285714285714285714%;
}

.cal_title {
	margin: 0 20px;
}

.cal_title_wrap {
	position: relative;
}

.cal_add {
	position: absolute;
	right: 0;
	top: 0;
}

.row {
	margin: 0;
	text-align: center;
}

.card-header {
	padding: 0;
}

a:hover {
	text-decoration: none;
}

tr td:not (:last-child ), tr td:not (:last-child ) a {
	color: #858796
}

tr td:not (:last-child ) .cal_date_content {
	background-color: #858796;
	border-color: #858796;
}

tr td:first-child, tr td:first-child a {
	color: #e74a3b
}

tr td:last-child, tr td:last-child a {
	color: #4E73DF
}

tr td:first-child .cal_date_content {
	background-color: #e74a3b;
	border-color: #e74a3b;
}

tr td:last-child .cal_date_content {
	background-color: #4E73DF;
	border-color: #4E73DF;
}

tbody td {
	height: 10em;
	padding: 10px;
	width: 14.285714285714285714285714285714%;
}

tbody tr td:not (:last-child ){
	box-sizing: border-box;
	border-right: 1px solid #e3e6f0;
}

tbody tr:not (:last-child ){
	border-bottom: 1px solid #e3e6f0;
}

.cal_date_wrap {
	display: flex;
	flex-direction: column;
	height: 100%;
	/*max-width: 5em;*/
}

.cal_date_content {
	color: #fff;
	margin-top: auto;
	overflow: hidden;
}
</style>
</head>
<script src="https://code.jquery.com/jquery-3.1.0.min.js"></script>
<script type="text/javascript">
<%String username = (String) session.getAttribute("name");
			String userid = (String) session.getAttribute("id");
			int userapproval = (int) session.getAttribute("approval");%>
$(function(){
	if(${approval} != 2){
		$("#add").hide();
	}
});
</script>

<body id="page-top">

  <!-- Page Wrapper -->
  <div id="wrapper">

    <!-- Sidebar -->
    <ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">

      <br/>
      <!-- Sidebar - Brand -->
      <a class="sidebar-brand d-flex align-items-center justify-content-center" href="/HRMProjectMain/jsp/user/mainadmin">
        	<div class="sidebar-brand-text mx-3" style="font-size: 20px">HRM<sup>Project</sup></div>
      </a>

      <!-- Divider -->
      <!-- <hr class="sidebar-divider my-0"> -->

	<br/>

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
								<a class="dropdown-item" href="#" data-toggle="modal"
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
						<h1 class="h3 mb-0 text-gray-800">캘린더</h1>
					</div>
					<hr class="sidebar-divider">
					<!-- Content Row -->

					<form>
						<div id="content-wrapper" class="d-flex flex-column"
							style="margin-top: 30px">
							<div class="container-fluid">
								<div class="h3 mb-4 text-gray-800 text-center cal_title_wrap">
									<a href="calendar?year=${today.bYear}&month=${today.bMonth}"
										class="btn btn-primary btn-circle"> <i
										class="fa fa-angle-left fa-2x"></i>
									</a> <span class="cal_title">${today.tYear}년
										${today.tMonth}월</span> <a
										href="calendar?year=${today.aYear}&month=${today.aMonth}"
										class="btn btn-primary btn-circle"> <i
										class="fa fa-angle-right fa-2x"></i>
									</a>
									<div id="add" class="cal_add">
										<a class="btn btn-success btn-circle" href="register"> <i
											class="fa fa-plus" style="color: white"></i>
										</a>
									</div>
								</div>
								<table class="card shadow mb-4">
									<thead class="card-header py-3">
										<tr class="m-0 font-weight-bold text-primary row">
											<td class="col">일</td>
											<td class="col">월</td>
											<td class="col">화</td>
											<td class="col">수</td>
											<td class="col">목</td>
											<td class="col">금</td>
											<td class="col">토</td>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="dateList" items="${list}" varStatus="status">
											<c:choose>
												<c:when test="${status.index%7==0}">

													<tr class="row">
														<td class="col-mdx-1">
															<div class="cal_date_wrap">
																${dateList.date}
																<c:forEach var="dto" items="${dateList.strings}">
																	<div class="cal_date_content" style="cursor: pointer;"
																		onClick="location.href='check?num=${dto.num}'">${dto.title}</div>
																</c:forEach>
															</div>
														</td>
												</c:when>
												<c:otherwise>
													<td class="col-mdx-1">
														<div class="cal_date_wrap">
															${dateList.date}
															<c:forEach var="dto" items="${dateList.strings}">
																<div class="cal_date_content" style="cursor: pointer;"
																	onClick="location.href='check?num=${dto.num}'">${dto.title}</div>
															</c:forEach>
														</div>
													</td>
												</c:otherwise>
											</c:choose>
										</c:forEach>
										</div>
										</div>
									</tbody>
								</table>
					</form>
					<div class="row">
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
							<h5 class="modal-title" id="exampleModalLabel">정말 로그아웃
								하시겠습니까?</h5>
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
