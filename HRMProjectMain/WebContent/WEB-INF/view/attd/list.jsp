<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>

<!DOCTYPE html>
<html>
<head>
<title>근태 조회</title>
<script src="https://code.jquery.com/jquery-3.1.0.min.js"></script>
<script type="text/javascript">
$(function() { //바디가 로딩 다 되면 자동으로 실행하는 함수
		var url = "attdlist";
		$.ajax({
			method : "post",
			url : url,
			dataType : "json"
		}) //resopnse에 담긴 데이터를 json(배열)으로 포멧해서 받아옴
		.done(
				function(args) { //응답이 성공 상태 코드를 반환하면 호출되는 함수//가져온 데이터를 첫번째 매개변수에 넣어줌
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

function searchDate(){     
		$("#attdTable tr").each(function() {	
			$("#attdTable tr:eq(1)").remove();	
		});
		var startDate = $("#startDate").val();
		var endDate = $("#endDate").val(); 
		var params="startDate="+startDate+"&endDate="+endDate;
		var url="searchDate";

		$.ajax({
			type: "post"
			,url:url
			,data:params
			,dataType:"json"
			})
			.done(function(args){
				var rs;
				for(var i = 0; i < args.length; i++){
					if((args[i].attd_time) == (args[i].off_time)){
						rs="";
					} else {
						rs=args[i].off_time;
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
			}).fail(function(e){
				alert(e.responseTexst);
			})
		}
</script>
</head>
<body>
	<div class="col-lg-12">
		<div class="box-header with border">
			<h3 class="box-title">근태</h3>
		</div>
		<div class="box-body">
			<div style="display: inline">
			<!-- <form id="searchDate" action="searchDate" method="post" style="display: inline"> -->
			<div style="display: inline">
				<input id="startDate" name="startDate" type="date"/> ~ <span><input id="endDate" name="endDate" type="date"/></span>
			</div>
			<div style="display: inline">
				<button id="searchBtn" type="button" onclick="searchDate()">조회</button>
			</div>
			<!-- </form> -->
			<div style="display: inline">
			<form id="excelDown" action="excelDown" method="post" style="display: inline">
				<button id="excelDown" type="submit">엑셀 다운</button>
			</form>
			</div>
			</div>
						
			<table class="table table-bordered" id="attdTable" border="1">
				<tr>
					<th style="width: 30px">No</th>
					<th style="width: 50px">사번</th>
					<th style="width: 50px">부서명</th>
					<th style="width: 100px">사원명</th>
					<th style="width: 200px">출근 시간</th>
					<th style="width: 200px">퇴근 시간</th>
					<th style="width: 50px">지각</th>
				</tr>
			</table>
		</div>
	</div>
</body>
</html>