<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
<%
HttpSession httpSession = request.getSession();
%>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">

  <!-- Custom styles for this template-->
  <link href="${pageContext.request.contextPath}/resources/css/sb-admin-2.min.css" rel="stylesheet">
  <title>HRM Project Net</title>
  <!-- Custom fonts for this template-->
  <link href="${pageContext.request.contextPath}/resources/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
  <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">

<script src="https://code.jquery.com/jquery-3.1.0.min.js"></script>
<script type="text/javascript">

  $(window).resize(function(){resizeYoutube();});
  $(function(){
	  resizeYoutube();
  });
  function resizeYoutube(){
	   $("iframe").each(function(){if(true){$(this).css("display","inline"); $(this).css("width","47%"); $(this).css("height",Math.ceil( parseInt($(this).css("width")) * 480 / 854 ) + "px");} });
  }

</script>
<style type="text/css"> 
@import url('https://fonts.googleapis.com/css2?family=Black+Han+Sans&display=swap'); 
@import url('https://fonts.googleapis.com/css2?family=Jua&display=swap');
/*GLOBALS*/
 *{margin:0; padding:0; list-style:none;}
a{text-decoration:none; color:#666;}
a:hover{color:#1bc1a3;}
body, hmtl{background: #ecf0f1; font-family: 'Anton', sans-serif;}

/*
#wrapper{
    width:600px;
    margin:50px auto;
    height:400px;
    position:relative;
    color:#fff;
    text-shadow:rgba(0,0,0,0.1) 2px 2px 0px;    
} */

#slider-wrap{
    width:100%;
    height:400px;
/*     width:600px;
    height:400px; */
    position:relative;
    overflow:hidden;
}

#slider-wrap ul#slider{
    width:100%;
    height:100%;
    
    position:absolute;
    top:0;
    left:0;     
}

#slider-wrap ul#slider li{
    float:left;
    position:relative;
    width:600px;
    height:400px;   
}

#slider-wrap ul#slider li > div{
    position:absolute;
    top:20px;
    left:35px;  
}

#slider-wrap ul#slider li > div h3{
    font-size:36px;
    text-transform:uppercase;   
}

#slider-wrap ul#slider li > div span{
    font-family: Neucha, Arial, sans serif;
    font-size:21px;
}

#slider-wrap ul#slider li img{
    display:block;
    width:100%;
  height: 100%;
}


/*btns*/
.btns{
    position:absolute;
    width:50px;
    height:60px;
    top:50%;
    margin-top:-25px;
    line-height:57px;
    text-align:center;
    cursor:pointer; 
    background:rgba(0,0,0,0.1);
    z-index:100;
    
    
    -webkit-user-select: none;  
    -moz-user-select: none; 
    -khtml-user-select: none; 
    -ms-user-select: none;
    
    -webkit-transition: all 0.1s ease;
    -moz-transition: all 0.1s ease;
    -o-transition: all 0.1s ease;
    -ms-transition: all 0.1s ease;
    transition: all 0.1s ease;
}

.btns:hover{
    background:rgba(0,0,0,0.3); 
}

#next{right:-50px; border-radius:7px 0px 0px 7px;}
#previous{left:-50px; border-radius:0px 7px 7px 7px;}
#counter{
    top: 30px; 
    right:35px; 
    width:auto;
    position:absolute;
}

#slider-wrap.active #next{right:0px;}
#slider-wrap.active #previous{left:0px;}

/*bar*/
#pagination-wrap{
    min-width:20px;
    margin-top:350px;
    margin-left: auto; 
    margin-right: auto;
    height:15px;
    position:relative;
    text-align:center;
}

#pagination-wrap ul {
    width:100%;
}

#pagination-wrap ul li{
    margin: 0 4px;
    display: inline-block;
    width:5px;
    height:5px;
    border-radius:50%;
    background:#fff;
    opacity:0.5;
    position:relative;
  top:0;
  
  
}

#pagination-wrap ul li.active{
  width:12px;
  height:12px;
  top:3px;
    opacity:1;
    box-shadow:rgba(0,0,0,0.1) 1px 1px 0px; 
}

/*Header*/
h1, h2{text-shadow:none; text-align:center;}
h1{ color: #666; text-transform:uppercase;  font-size:36px;}
h2{ color: #7f8c8d; font-family: Neucha, Arial, sans serif; font-size:18px; margin-bottom:30px;} 


/*ANIMATION*/
#slider-wrap ul, #pagination-wrap ul li{
    -webkit-transition: all 0.3s cubic-bezier(1,.01,.32,1);
    -moz-transition: all 0.3s cubic-bezier(1,.01,.32,1);
    -o-transition: all 0.3s cubic-bezier(1,.01,.32,1);
    -ms-transition: all 0.3s cubic-bezier(1,.01,.32,1);
    transition: all 0.3s cubic-bezier(1,.01,.32,1); 
}

</style>
</head>
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
var pos = 0;
//number of slides
var totalSlides = 5;
/* var totalSlides = $('#slider-wrap ul li').length; */
//get the slide width
var sliderWidth = 600;
/* var sliderWidth = $('#slider-wrap').width(); */
//set width to be 'x' times the number of slides
$(function(){
	 var url = "/HRMProjectMain/jsp/chat/chatlist";
	$.ajax({
		type:"post"		
		,url:url		
		,dataType:"json" }) 
		.done(function(args){
				 $("#chat").append(args[0].chatroomname);
				 $("#chat-room").append("<a href='/HRMProjectMain/jsp/chat/chatbroad?chatname="+args[0].chatroomname+"' style='color:gray;'><i class='fas fa-comments fa-2x text-gray-300'></i></a>");
			})
	    .fail(function(e) {
	    })
	    
	    url = "/HRMProjectMain/jsp/approve/mainapp?approval=<%=approval%>";
	$.ajax({
		type:"post"		
		,url:url		
		,dataType:"json" }) 
		.done(function(args){
			if(<%=approval%>==0){
				$("#apptop").append("대기중인 내 결재");
				$("#approve").append(args[0]+"개");
			}else{
				$("#apptop").append("대기중인 내 결재 / 타 결재");
				$("#approve").append(args[0] + "개 / " + args[1] + "개");
			}
		})
	    .fail(function(e) {
	    })


		// 근태 지각률
		var url = "/HRMProjectMain/jsp/attd/loginPost";
		$.ajax({
			type:"post"		
			,url:url		
			,dataType:"json" }) 
			.done(function(args){
					$("#attd").append(args + "%");
					$("#attd2").append("<div class='progress-bar bg-info' role='progressbar' style='width: "+args+"%' aria-valuenow='50' aria-valuemin='0' aria-valuemax='100'></div>");
			})
		    .fail(function(e) {
		    }) 

		    /*****************
		     BUILD THE SLIDER
		    *****************/
		    $('#slider-wrap ul#slider').width(sliderWidth*totalSlides);
		    
		    //next slide    
		    $('#next').click(function(){
		        slideRight();
		    });
		    
		    //previous slide
		    $('#previous').click(function(){
		        slideLeft();
		    });
		    
		    
		    
		    /*************************
		     //*> OPTIONAL SETTINGS
		    ************************/
		    //automatic slider
		    var autoSlider = setInterval(slideRight, 5000);
		    
		    //for each slide 
		    $.each($('#slider-wrap ul li'), function() { 

		       //create a pagination
		       var li = document.createElement('li');
		       $('#pagination-wrap ul').append(li);    
		    });
		    
		    //counter
		    countSlides();
		    
		    //pagination
		    pagination();
		    
		    //hide/show controls/btns when hover
		    //pause automatic slide when hover
		    $('#slider-wrap').hover(
		      function(){ $(this).addClass('active'); clearInterval(autoSlider); }, 
		      function(){ $(this).removeClass('active'); autoSlider = setInterval(slideRight, 5000); }
		    );
	    
});//ready()
/***********
SLIDE LEFT
************/
function slideLeft(){
   pos--;
   if(pos==-1){ pos = totalSlides-1; }
   $('#slider-wrap ul#slider').css('left', -(sliderWidth*pos));    
   
   //*> optional
   countSlides();
   pagination();
}

/************
SLIDE RIGHT
*************/
function slideRight(){
   pos++;
   if(pos==totalSlides){ pos = 0; }
   $('#slider-wrap ul#slider').css('left', -(sliderWidth*pos)); 
   
   //*> optional 
   countSlides();
   pagination();
}
/************************
//*> OPTIONAL SETTINGS
************************/
function countSlides(){
   $('#counter').html(pos+1 + ' / ' + totalSlides);
}

function pagination(){
   $('#pagination-wrap ul li').removeClass('active');
   $('#pagination-wrap ul li:eq('+pos+')').addClass('active');
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
              <!-- Dropdown - Messages -->
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
                <a class="dropdown-item" href="logout" data-toggle="modal" data-target="#logoutModal">
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
          <!--   <h1 class="h3 mb-0 text-gray-800" style="color:#FFE08C;font-family: 'Black Han Sans', sans-serif;" >한눈에 보기</h1> -->
          </div>

          <!-- Content Row -->
          <div class="row">

            <!-- Earnings (Monthly) Card Example -->
            <div class="col-xl-3 col-md-6 mb-4">
              <div class="card border-left-primary shadow h-100 py-2">
                <div class="card-body">
                  <div class="row no-gutters align-items-center">
                    <div  class="col mr-2">
                      <div id="apptop" class="text-xs font-weight-bold text-primary text-uppercase mb-1"></div>
                      <div id="approve" class="h5 mb-0 font-weight-bold text-gray-800"></div>
                    </div>
                    <div class="col-auto">
                    <a href="/HRMProjectMain/jsp/approve/appboard?pageNum=1&selectapp=0"> <i class="fas fa-calendar fa-2x text-gray-300"></i></a>
                    </div>
                  </div>
                </div>
              </div>
            </div>

            <!-- Earnings (Monthly) Card Example -->
            <div class="col-xl-3 col-md-6 mb-4">
              <div class="card border-left-success shadow h-100 py-2">
                <div class="card-body">
                  <div class="row no-gutters align-items-center">
                    <div class="col mr-2">
                      <div class="text-xs font-weight-bold text-success text-uppercase mb-1">대표 공지사항</div>
                      <div class="h5 mb-0 font-weight-bold text-gray-800">코로나19 조심</div><!--게시판에서 공지사항 받아오기 -->
                    </div>
                    <div class="col-auto">
                      <i class="fa fa-eye fa-2x text-gray-300"></i>
                    </div>
                  </div>
                </div>
              </div>
            </div>

            <!-- Earnings (Monthly) Card Example -->
            <div class="col-xl-3 col-md-6 mb-4">
              <div class="card border-left-info shadow h-100 py-2">
                <div class="card-body">
                  <div class="row no-gutters align-items-center">
                    <div class="col mr-2">
                      <div class="text-xs font-weight-bold text-info text-uppercase mb-1">지각률</div>
                      <div class="row no-gutters align-items-center">
                        <div class="col-auto">
                           <div class="h5 mb-0 mr-3 font-weight-bold text-gray-800"><span id="attd"></span></div>
                        </div>
                        <div class="col">
                          <div class="progress progress-sm mr-2" id="attd2">
                          </div>
                        </div>
                      </div>
                    </div>
                    <div class="col-auto">
                      <a href="/HRMProjectMain/jsp/attd/attd.do"><i class="fas fa-clipboard-list fa-2x text-gray-300"></i></a>
                    </div>
                  </div>
                </div>
              </div>
            </div>

            <!-- Pending Requests Card Example -->
            <div class="col-xl-3 col-md-6 mb-4">
              <div class="card border-left-warning shadow h-100 py-2">
                <div class="card-body">
                  <div class="row no-gutters align-items-center">
                    <div class="col mr-2">
                      <div class="text-xs font-weight-bold text-warning text-uppercase mb-1">NEW 채팅방</div>
                      <div id="chat"  class="h5 mb-0 font-weight-bold text-gray-800"></div><!-- 채팅수가 가장 많은 채팅방 올리기 -->
                    </div>
                    <div id="chat-room" class="col-auto">
                  
                    </div>
                  </div>
                </div>
              </div> 
            </div>
          </div>

<hr>
              <!-- Project Card Example -->
              <div class="card shadow mb-4">
                <div class="card-header py-3" style="display: block; text-align: center; color:black; align-content: center;">
                  <h6 style="color:BLACK;font-family: 'Jua', sans-serif;">HRM 소개 영상</h6>
                </div> 
                <div id="card-body" class="card-body" style="display: inline; align-content: center;">
					<iframe allow="autoplay" style="display: inline; margin-left: 3%;" width="880" height="480" src="https://ak7.picdn.net/shutterstock/videos/1033263017/preview/stock-footage-close-up-business-people-shaking-hands-successful-corporate-partnership-deal-welcoming-opportunity.webm" frameborder="0" allowfullscreen=""></iframe>
					<iframe allow="autoplay" style="display: inline;" width="880" height="480" src="https://ak1.picdn.net/shutterstock/videos/1039968611/preview/stock-footage-asia-businessmen-and-businesswomen-meeting-brainstorm-ideas-about-new-paperwork-project-colleague.webm" frameborder="0" allowfullscreen=""></iframe>
                </div> 
              </div> 
			<div class="card shadow mb-4">
                <div class="card-header py-3" style="display: block; text-align: center; color:black; align-content: center;">
                  <h6 style="color:BLACK;font-family: 'Jua', sans-serif;" >HRM 슬라이드</h6>
                </div> 
    <div id="card-body" class="card-body" style="display: inline; align-content: center;">

      <div id="slider-wrap">
          <ul id="slider">
             <li data-color="#1abc9c">
                <div>
                    <h3 style="color:#FFE08C;font-family: 'Black Han Sans', sans-serif;">2020년 런던 지사 설립</h3>
                    <span style="color:white;font-family: 'Black Han Sans', sans-serif;">더 높은 곳으로!</span>
                </div>                
<img src="${pageContext.request.contextPath}/resources/img/building.jpg">
             </li>
             
             <li data-color="#3498db">
                <div>
                    <h3 style="color:#FFE08C;font-family: 'Black Han Sans', sans-serif;">기숙사 제공</h3>
                    <span style="color:white;font-family: 'Black Han Sans', sans-serif;">월세 전세 걱정 끝!</span>
                </div>
<img src="${pageContext.request.contextPath}/resources/img/dormitory.jpg">
             </li>
             
             <li data-color="#9b59b6">
                <div>
                    <h3 style="color:#FFE08C;;font-family: 'Black Han Sans', sans-serif;">편안한 휴게공간</h3>
                    <span style="color:white;font-family: 'Black Han Sans', sans-serif;">마음편하게 일하자!</span>
                </div>
<img src="${pageContext.request.contextPath}/resources/img/park.jpg">
             </li> 
             
             <li data-color="#34495e">
                <div>
                    <h3 style="color:#FFE08C;;font-family: 'Black Han Sans', sans-serif;">전국 30개 대학 취업연계</h3>
                    <span style="color:white;font-family: 'Black Han Sans', sans-serif;">보다 전문성있게!</span>
                </div>
<img src="${pageContext.request.contextPath}/resources/img/univ.jpg">
             </li>
             
             <li data-color="#e74c3c">
                <div>
                    <h3 style="color:#FFE08C;font-family: 'Black Han Sans', sans-serif;">HRM 사원이라면?</h3>
                    <span style="color:BLACK;font-family: 'Black Han Sans', sans-serif;"> &nbsp;&nbsp;&nbsp;&nbsp;삼성 테블릿 지원!</span>
                </div>
<img src="${pageContext.request.contextPath}/resources/img/undraw_posting_photo.svg">
             </li>
             
			<li data-color="#e74c3c">
                <div>
                    <h3 style="color:blue;">Slide #6</h3>
                    <span style="color:blue;">Sub-title #5</span>
                </div>
<img style="display: inline;" src="${pageContext.request.contextPath}/resources/img/undraw_posting_photo.svg">
             </li>
          </ul>
           <!--controls-->
          <div class="btns" id="next"><i class="fa fa-arrow-right"></i></div>
          <div class="btns" id="previous"><i class="fa fa-arrow-left"></i></div>
          <div id="counter"></div>
          
          <div id="pagination-wrap">
            <ul>
            </ul>
          </div>
          <!--controls-->  
            </div> 
   
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
          <a class="btn btn-primary" href="login.jsp">로그아웃</a>
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

  <!-- Page level plugins -->
 <%--  <script src="${pageContext.request.contextPath}/resources/vendor/chart.js/Chart.min.js"></script>

  <!-- Page level custom scripts -->
<%--   <script src="${pageContext.request.contextPath}/resources/js/demo/chart-area-demo.js"></script>
  <script src="${pageContext.request.contextPath}/resources/js/demo/chart-pie-demo.js"></script> --%>
  <!--JavaScript at end of body for optimized loading-->
  <!-- Compiled and minified JavaScript -->
</body>
</html>
