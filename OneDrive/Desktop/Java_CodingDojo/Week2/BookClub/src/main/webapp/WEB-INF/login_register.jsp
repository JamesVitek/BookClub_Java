<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title></title>
	<link rel="stylesheet" type="text/css" href="/css/style.css">
	<script type="text/javascript" src="/js/main.js"></script>
</head>
<body class="container">
	<h1 class="col_purple">Book Club</h1>
	<p>A place for friends to share thoughts on books.</p>
	<div class="flex gap10 mt2">
		<div>
			<h1>Register</h1>
			<form:form class="grid" action="/register" method="POST" modelAttribute="userRegister">
				<form:errors path="name"></form:errors>
				<form:input path="name" placeholder="Username"/>
				
				<form:errors path="email"></form:errors>
				<form:input path="email" placeholder="Email"/>
				
				<form:errors path="password"></form:errors>
				<form:input type="password" path="password" placeholder="Password"/>
				
				<form:errors path="confirm"></form:errors>
				<form:input type="password" path="confirm" placeholder="Confirm Password"/>
				
				<input class="btn_submit" type="submit" value="Register"/>
			</form:form>
		</div>
		<div>
			<h1>Login</h1>
			<form:form class="grid" action="/login" method="POST" modelAttribute="userLogin">
				<form:errors path="email"></form:errors>
				<form:input path="email" placeholder="Email"/>
				
				<form:errors path="password"></form:errors>
				<form:input type="password" path="password" placeholder="Password"/>
				
				<input class="btn_submit" type="submit" value="Login"/>
			</form:form>
		</div>
	</div>
</body>
</html>