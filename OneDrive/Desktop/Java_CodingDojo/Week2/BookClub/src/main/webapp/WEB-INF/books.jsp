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
	<link rel="stylesheet" type="text/css" href="css/style.css">
	<script type="text/javascript" src="js/main.js"></script>
</head>
<body class="container">
	<div class="flex align_center justify_between">
		<h1>Welcome ${user.name}</h1>
		<a href="/logout" class="btn">Logout</a>	
	</div>
	<div class="flex align_center justify_between">
		<p>Books from everyones shelves:</p>
		<a href="/books/new">+ Add book to shelf!</a>
	</div>
	<table>
		<thread>
			<tr>
				<td>ID</td>
				<td>Title</td>
				<td>Author Name</td>
				<td>Posted By</td>
			</tr>
		</thread>
		<tbody>
			<c:forEach var="book" items="${books}">
				<tr>
					<td>${book.id}</td>
					<td><a href="/books/${book.id}">${book.title}</a></td>
					<td>${book.author}</td>
					<td>${book.reader.name}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>