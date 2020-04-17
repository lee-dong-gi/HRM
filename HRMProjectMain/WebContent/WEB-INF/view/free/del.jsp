<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
</head>
<body>
삭제하시겠습니까?
<form action="del" method="post">
<input type="hidden" value="${del.num}" name="num">
<input type="submit" value="완료">
</form>
</body>
</html>