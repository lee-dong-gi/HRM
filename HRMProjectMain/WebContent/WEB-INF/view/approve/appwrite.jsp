<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">

<head>

  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">

  <title>HRM Project Net - 결재작성</title>
  <!-- Custom fonts for this template-->
  <link href="${pageContext.request.contextPath}/resources/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
  <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">

  <!-- Custom styles for this template-->
  <link href="${pageContext.request.contextPath}/resources/css/sb-admin-2.min.css" rel="stylesheet">
</head>
<script src="https://code.jquery.com/jquery-3.1.0.min.js"></script>
<script type="text/javascript">
<%String username = (String)session.getAttribute("name");
int approval = (int)session.getAttribute("approval");
String userid = (String)session.getAttribute("id");
boolean flagg;
if(approval==2){
   flagg=true;
}else{
   flagg=false;
}%>

$(function(){
	var	url="getkategorie";
	$.ajax({
		type:"post"		//응답 방식
		,url:url		//요청url = 기존경로 +
		,dataType:"json" }) //resopnse에 담긴 데이터를 json(배열)으로 포멧해서 받아오는타입
		.done(function(args){	//응답이 성공 상태 코드를 반환하면 호출되는 함수//가져온 데이터를 첫번째 매개변수에 넣어줌
			for(var i=0; i < args.length; i++) { 
				$("#kategories").append(
					"<option value="+args[i].kategoriename+">"+args[i].kategoriename+"</option>");
			 }
 			})
	    .fail(function(e) {
	    })
	var	url="getdept";    
	$.ajax({
		type:"post"		//응답 방식
		,url:url		//요청url = 기존경로 +
		,dataType:"json" }) //resopnse에 담긴 데이터를 json(배열)으로 포멧해서 받아오는타입
		.done(function(args){	//응답이 성공 상태 코드를 반환하면 호출되는 함수//가져온 데이터를 첫번째 매개변수에 넣어줌
			$("#deptall").append("<option>부서 선택</option>");
			for(var i=0; i < args.length; i++) { 
				$("#deptall").append(
					"<option value="+args[i].deptno+">"+args[i].dname+"</option>");
			 }
 			})
	    .fail(function(e) {
	    })
});

function checkwrite(){
    var subject = $('#subject').val();
    var content = $('#contenta').val();
    var approvedBy = $('#approvedBy option:selected').val();
    var kategories = $('#kategories option:selected').val();
    
    if(!subject){
        alert("제목을 입력해 주세요"); 
        $('#subject').focus(); 
        return false;
    }else if(!content){
        alert("내용을 입력해 주세요"); 
        $('#contenta').focus(); 
        return false;
    }else if(!approvedBy){
        alert("결재자를 지정해 주세요"); 
        return false;
    }else if(!kategories){
        alert("카테고리를 지정해 주세요"); 
        return false;
	}
    return true;
}

function txtMaxlength(){
	var subject = document.getElementById("subject");
	var contenta = document.getElementById("contenta");
	if((subject.value.length) >= 30){
		alert("제목은 30자 제한입니다.");
		subject.value = subject.value.substring(0,20);
		$("subject").focus();
	}
	if((contenta.value.length) >= 500){
		alert("해당글은 500자 제한입니다.");
		contenta.value = contenta.value.substring(0,500);
		$("contenta").focus();
	}
}
function addselect(){
	var dept = $("#deptall option:selected").val();
	var	url="getuserinfo";   
	var params="deptno="+dept;
	$.ajax({
		type:"post"		//응답 방식
		,url:url		//요청url = 기존경로 +
		,data:params
		,dataType:"json" }) //resopnse에 담긴 데이터를 json(배열)으로 포멧해서 받아오는타입
		.done(function(args){	//응답이 성공 상태 코드를 반환하면 호출되는 함수//가져온 데이터를 첫번째 매개변수에 넣어줌
			$("#approvedBy").remove();
					$("#approvedby").append("<select id='approvedBy' name='approvedBy' class='form-control-sm'>");
					$("#approvedBy").append("<option>결재자 선택</option>");
					for(var i=0; i < args.length; i++) {
						if("${writer}"!=args[i].name){
						$("#approvedBy").append("<option value="+args[i].empno+">"+args[i].name+"</option>");
							}
						} 
			 		
			$("#approvedby").append("</select>");
				
 			})
	    .fail(function(e) {
	    	$("#approvedBy").remove();
	    })
	
}
</script>

<body id="page-top">

  <!-- Page Wrapper -->
  <div id="wrapper">

    <!-- Sidebar -->
    <ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">

      <br/>
      <!-- Sidebar - Brand -->
      <a class="sidebar-brand d-flex align-items-center justify-content-center" href="/HRMProjectMain/jsp/user/main">
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
      <%if(flagg){ %>
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
            <li class="nav-item dropdown no-arrow">
              <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                <span class="mr-2 d-none d-lg-inline text-gray-600 small"><%=username%></span>
                <img class="img-profile rounded-circle" src="${pageContext.request.contextPath}/resources/img/profile.png">
              </a>
              <!-- Dropdown - User Information -->
              <div class="dropdown-menu dropdown-menu-right shadow animated--grow-in" aria-labelledby="userDropdown">
                <a class="dropdown-item" href="/HRMProjectMain/jsp/emp/myinfo?id=${id}">
                  <i class="fas fa-user fa-sm fa-fw mr-2 text-gray-400"></i>
                  	프로필
                </a>

                <div class="dropdown-divider"></div>
                <a class="dropdown-item" href="#" data-toggle="modal" data-target="#logoutModal">
                  <i class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>
              	   로그아웃
                </a>
              </div>
            </li>

          </ul>

        </nav>
        <!-- End of Topbar -->

        <!-- Begin Page Content -->
        <div class="container-fluid">

          <!-- Page Heading -->
          <div class="d-sm-flex align-items-center justify-content-between mb-4">
            <h1 class="h3 mb-0 text-gray-800">결재</h1>
          </div>
		<hr class="sidebar-divider">
       	<!-- Content Row -->
       <div class="row">
          <div class="card shadow mb-4" style="width:100%;"> 
            <div class="card-header py-3">
              <h6 class="m-0 font-weight-bold text-secondary"><img style='height:30px; float:left;' class='img-profile rounded-circle' src="${pageContext.request.contextPath}/resources/img/approve.jpg">결재 작성</h6>
            </div>
            <div class="card-body">
              <div class="table-responsive"> 
               <form action="appwrite" method="post" enctype="multipart/form-data" onsubmit='return checkwrite()'>
                <table class="table" id="writeapp">
					<tr>
					<td bgcolor="gray" style="color:white;">
					상신자
					</td>
					<td>
					<input type="hidden" name="writer" value="${writer}">${writer}
					</td>
					</tr>
					<tr>
					<td bgcolor="gray" style="color:white;">
					제목
					</td>
					<td>
					<input class="form-control" type="text" id="subject" name="subject" onkeypress="txtMaxlength()" style="width:90%;"> 
					</td>
					</tr>
					<tr>
					<td bgcolor="gray" style="color:white;">
					카테고리
					</td>
					<td>
						<select id="kategories" name="kategorie" class="form-control-sm"></select>
					</td>
					</tr>
					<tr> 
					<td bgcolor="gray" style="color:white;">
					결재자 선택
					</td>
					<td id="approvedby">
						<select id="deptall" name="deptall" onchange="addselect()" class="form-control-sm"></select>
					</td>
					</tr>
					<tr>
					<td bgcolor="gray" style="color:white;">
					파일 첨부
					</td>
					<td>
					<input type="file" id="appfiles" name="appfiles"><br>
					</td>
					</tr>
					<tr>
					<td bgcolor="gray" style="color:white;">
					내용
					</td>
					<td>
					<textarea class="form-control" id="contenta" rows="10" cols="40"  name="content" onkeypress="txtMaxlength()" style="width:90%;"></textarea><br> 
					</td>
					</tr>
                </table>
					<a href="appboard?pageNum=1&selectapp=0" class="btn btn-secondary btn-sm">목록</a>
					<button type="reset" class="btn btn-secondary btn-sm">다시쓰기</button>
					<button type="submit"  class="btn btn-secondary btn-sm" style="display:inline;float: right;">결재상신</button>
					</form>               
              </div>           
				</div>
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
  <a class="scroll-to-top rounded" href="#page-top">
    <i class="fas fa-angle-up"></i>
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
