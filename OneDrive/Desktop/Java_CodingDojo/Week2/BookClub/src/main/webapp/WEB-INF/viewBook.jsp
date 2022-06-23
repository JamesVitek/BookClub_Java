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
	<div class="mt2">
		<c:choose>
			<c:when test="${book.reader.id == user.id}">
				<h3>You read ${book.title} by ${book.author}</h3>
				<p>Here are your thoughts:</p>
			</c:when>
			<c:otherwise>
				<h3>${book.reader.name} read ${book.title} by ${book.author}</h3>
				<p>Here are ${book.reader.name}'s thoughts:</p>
			</c:otherwise>
		</c:choose>
	</div>
	<div class="mt2">
		<p>${book.thoughts}</p>
	</div>
	<c:if test="${book.reader.id == user.id}">
		<div class="flex align_center justify_between mt2">
			<form action="/books/delete/${book.id}" method="POST">
				<input class="btn_submit" type="submit" value="Delete" />
			</form>
			<form action="/books/${book.id}/edit" method="GET">
				<input class="btn_submit" type="submit" value="Edit" />
			</form>
		</div>
	</c:if>
	
</body>
</html>