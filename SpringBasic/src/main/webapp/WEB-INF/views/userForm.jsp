<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="/userInfo" method="post">
		<input type="number" name="userNum" placeholder="Number"><br/>
		<input type="text" name="userId" placeholder="ID"><br/>
		<input type="password" name="userPw" placeholder="PW"><br/>
		<input type="text" name="userName" placeholder="Name"><br/>
		<input type="number" name="userAge" placeholder="Age"><br/>
		<input type="submit" value="제출" />
	</form>
</body>
</html>