<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>사용자 생성 로그인 폼</h1>
	<h2><c:out value="${error }" /></h2>
	<h2><c:out value="${logout }" /></h2>
	
	<form action="/login" method="post">
		아이디: <input type="text" name="username" value="admin"><br/>
		비밀번호: <input type="text" name="password" value="admin"><br/>
		자동로그인 : <input type="checkbox" name="remember-me"><br/>
		<input type="submit" value="로그인하기">
		<input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }" />
		<!-- _csrf.token은 쿠키가 삭제될 때 마다 값이 바뀜
			 영어 26개(a~z) + 숫자 10개(0~9)로 조합된 32자리의 랜덤값 
			 악의적인 공격을 막을 수 있습니다.-->
	</form>
</body>
</html>