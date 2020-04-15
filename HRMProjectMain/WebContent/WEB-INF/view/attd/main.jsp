<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<title>W3.CSS Template</title>
<meta charset="UTF-8">
<style>
</style>
<title>근태 메인 화면</title>
</head>
<body>
	<h2 id="main" style="align: center">근태 관리 Attendance Management</h2>
	<div id="main">
		<p id="userid">사 번 : ${empno}</p>
		<br>
		<p id="username">사원명 : ${name}</p>
	</div>
	<div style="display: inline-block">
		<form id="attd" action="insert" method="post">
			<button type="submit">출근</button>
		</form>
	</div>
	<div style="display: inline-block">
		<form id="off" action="off" method="post">
			<button type="submit">퇴근</button>
		</form>
	</div>
	<div style="display: inline-block">
		<form id="attdlist" action="list" method="post">
			<button type="submit">조회</button>
		</form>
	</div>
</body>
</html>