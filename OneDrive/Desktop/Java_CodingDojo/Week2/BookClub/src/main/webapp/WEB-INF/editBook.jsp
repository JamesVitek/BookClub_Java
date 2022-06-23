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
	<script type="text/javascript" src="js/main.js"></script>
</head>
<body class="container">
	<div class="flex align_center justify_between">
		<h1>${book.title}</h1>
		<a href="/logout" class="btn">Logout</a>	
	</div>
	<div class="flex align_center justify_between">
		<a href="/books">Back to the shelves</a>
	</div>
	<form:form class="grid gap1" action="/books/new" method="POST" modelAttribute="book">
		<div class="flex align_center mt2">
			<p class="mr2">Title:</p>
			<div>
				<form:input path="title"></form:input>
				<form:errors class="col_red" path="title"></form:errors>					
			</div>
		</div>
		<div class="flex align_center">
			<p class="mr2">Author:</p>
			<form:input path="author"></form:input>
			<form:errors class="col_red" path="author"></form:errors>		
		</div>
		<div class="flex align_center">
			<p class="mr2">Thoughts:</p>
			<form:input path="thoughts"></form:input>
			<form:errors class="col_red" path="thoughts"></form:errors>		
		</div>
		
		<form:input type="hidden" path="reader"></form:input>
		<form:input type="hidden" path="id"></form:input>
		
		<input class="btn_submit" type="submit" value="Submit" />
	</form:form>
</body>
</html>