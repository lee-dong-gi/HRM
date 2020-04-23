<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">

<head>
<%
String username = (String)session.getAttribute("name");
String chattingname = (String)session.getAttribute("chatname");
%>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">

  <title>HRM Project Net - 채팅</title>
  <!-- Custom fonts for this template-->
  <link href="${pageContext.request.contextPath}/resources/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
  <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">

  <!-- Custom styles for this template-->
  <link href="${pageContext.request.contextPath}/resources/css/sb-admin-2.min.css" rel="stylesheet">

</head>
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>


<style type="text/css">
	*{
		font-family: 나눔고딕;
	}
	#messageWindow{
		background: black;
		color: greenyellow;
	}
	#inputMessage{
		width:80%;
		height:30px;
	}
	#btn-submit{
		background: #F7E600;
		width:15%;
		height:30px; 
		color:#607080;
		border:none;
	}
	
	#main-container{
		width:60%;
		height:100%;
		/* border:1px solid black; */
		margin:10px;
		display: inline-block;
		
	}
	#chat-container{
		vertical-align: left;
		border: 1px solid black;
		margin:0px;
		min-height: 600px;
		max-height: 600px;
		overflow: scroll;
		overflow-x:hidden; 
		background: #ffffff;
		/* background: #9bbbd4; */
/* 		background: #F6F6F6; */
	}
	#user-container{
		float: right;
		/* border: 1px solid black; */
		margin:0px;
		margin-top:10px;
		width: 37%;
		min-height: 600px;
		max-height: 600px;
		/* overflow: scroll; */
		overflow-x:hidden;
		/* background: #9bbbd4; */
  		background: #F6F6F6;
	}
	
	.chat{
		font-size: 20px;
		color:black;
		margin: 5px;
		min-height: 20px;
		padding: 5px;
		min-width: 50px;
		text-align: left;
        height:auto;
        word-break : break-all;
        background: #E7E7E7;
        width:auto;
        display:inline-block;
        border-radius: 10px 10px 10px 10px;
	}
	
	.notice{
		color:#607080;
		font-weight: bold;
		border : none;
		text-align: center;
		background-color: #ffffff;
		/* background-color: #9bbbd4; */
/* 		background-color: #F6F6F6; */
		display: block;
	}
	.usernotice{
		color:#607080;
		border : none;
		font-size: 17px; 
		text-align: center;
		background-color: #ffffff;
		/* border: 1px solid black; */
/* 		background-color: #F6F6F6; */
		display: block;
	}
	.userhead{
		color:#ffffff;
		font-weight: bold;
		border : none;
		text-align: center;
		background-color: gray;
/* 		background-color: #F6F6F6; */
		display: block;
	}

	.my-chat{
		text-align: right;
		background: #F7E600;
		border-radius: 10px 10px 10px 10px;
	}
	
	#bottom-container{
		margin:10px;
	}
	
	.chat-info{
		color:#556677;
		font-size: 10px;
		text-align: right;
		padding: 5px;
		padding-top: 0px;

	}
	
	.chat-box{
		text-align:left;
	}
	.my-chat-box{
		text-align: right;
	}
	
</style>
<body id="page-top" onresize="parent.resizeTo(800,900)" onload="parent.resizeTo(800,900)">
        <!-- Begin Page Content -->
        <div class="container-fluid">
       	<!-- Content Row -->
          <div class="card shadow mb-3">
            <div class="card-header py-3">
          <h6 class="m-0 font-weight-bold" style="color:gray;"><img style='height:30px; float:left;' class='img-profile rounded-circle' src="${pageContext.request.contextPath}/resources/img/chaticon.jpg"><%=chattingname%></h6>
            </div>
            	<div class="card-body">
              		<div class="table-responsive">    
							<div id="user-container">
								<h6 class="chat userhead">참여자</h6>
							</div>
						<div id="main-container">
							<div id="chat-container">
							</div>
						<div id="bottom-container">
							<input id="user" type="hidden" value="<%=username%>">
							<input id="inputMessage" type="text" class="form-control" style="display: inline;">
							<input id="btn-submit" type="submit" value="전송" >
						</div>
						</div>
						<br/>              	                    
					</div>
				</div>
			</div>
        </div>
        <!-- /.container-fluid -->
</body>
  <!-- Bootstrap core JavaScript-->
  <script src="${pageContext.request.contextPath}/resources/vendor/jquery/jquery.min.js"></script>
  <script src="${pageContext.request.contextPath}/resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

  <!-- Core plugin JavaScript-->
  <script src="${pageContext.request.contextPath}/resources/vendor/jquery-easing/jquery.easing.min.js"></script>

  <!-- Custom scripts for all pages-->
<script src="${pageContext.request.contextPath}/resources/js/sb-admin-2.min.js"></script>
<script src="https://code.jquery.com/jquery-3.1.0.min.js"></script>
<script type="text/javascript">
	var textarea = document.getElementById("messageWindow");
	var webSocket = new WebSocket('ws://192.168.0.43:8083/HRMProjectMain/broadsocket');
	
	var inputMessage = document.getElementById('inputMessage');
	
	webSocket.onerror = function(e){
		onError(e);
	};
	webSocket.onopen = function(e){
		onOpen(e);
	};
	webSocket.onmessage = function(e){
		onMessage(e);
	};
	
	webSocket.onclose = function(message) {
		var $chat = $("<div class='chat notice'>" + "서버와 연결이 끊겼습니다. 다시 로그인 해주세요!\n" + "</div>");
		$('#chat-container').append($chat);
	};
	
	function onMessage(e){
		var chatMsg = event.data;
		var date = new Date();
		var dateInfo = date.getHours() + ":" + date.getMinutes() + ":" + date.getSeconds();
			var $chat = $("<div class='chat notice'>" + chatMsg.substring(8,chatMsg.length) + "</div>");
		if(chatMsg.substring(0,6) == 'server'){
			$('#chat-container').append($chat);
		}else if(chatMsg.substring(0,6) == "userin"){
			$("#user-container span").each(function() {				
				$("#user-container span:eq(0)").remove();
			}); 
			var msgg = chatMsg.substring(8,chatMsg.length)
			var msggSplit = msgg.split(',');
			for ( var i in msggSplit ) {
				if(i==msggSplit.length-1){
				}else{
					$('#user-container').append("<span class='chat usernotice'><img style='height:30px; float:left;' class='img-profile rounded-circle' src='${pageContext.request.contextPath}/resources/img/Contact.PNG'>"+msggSplit[i]+"</span>");
				}
		      } 
		}else{
			var $chat = $("<div class='chat-box'><div class='chat'>" + chatMsg + "</div><div class='chat-info chat-box'>"+ dateInfo +"</div></div>");
			$('#chat-container').append($chat);
		}
		$('#chat-container').scrollTop($('#chat-container')[0].scrollHeight+20);
	}
	
	function onOpen(e){
		
	}
	
	function onError(e){
		alert(e.data);
	}
	
		
	function send(){
		var chatMsg = inputMessage.value;
		if(chatMsg == ''){
			return;
		}
		var date = new Date();
		var dateInfo = date.getHours() + ":" + date.getMinutes() + ":" + date.getSeconds();
		var $chat = $("<div class='my-chat-box'><div class='chat my-chat'>" + chatMsg + "</div><div class='chat-info'>"+ dateInfo +"</div></div>");
		$('#chat-container').append($chat);
		webSocket.send("{{" + user.value + "}}"+chatMsg+ "****${chatname}");
		inputMessage.value = "";
		$('#chat-container').scrollTop($('#chat-container')[0].scrollHeight+20);
	}
	
</script>

<script type="text/javascript">
	$(function(){
		$('#inputMessage').keydown(function(key){
			if(key.keyCode == 13){
				$('#inputMessage').focus();
				send();
			}
		});
		$('#btn-submit').click(function(){
			send();
		});
		
	})
</script>
</html>
