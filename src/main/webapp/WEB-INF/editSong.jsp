 <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.io.*,java.util.*"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Lookify</title>
<link rel="stylesheet"
	href="/webjars/bootstrap/4.5.2/css/bootstrap.min.css" />
<script src="/webjars/jquery/3.5.1/jquery.min.js"></script>
<script src="/webjars/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<link rel="stylesheet" type="text/css" href="css/style.css">
</head>

<body style="background-color: gray">
	<div class="container">
		<div class="col mx-auto text-center">
			<h1>Edit ${singleSong.title}</h1>
				<a class="btn btn-dark btn-outline-info" href="/dashboard">Home</a>
				<a class="btn btn-dark btn-outline-danger" href="/logout">Logout</a>
			<form:form action="/song/update/${singleSong.id}" method="post"
				modelAttribute="singleSong" style="margin-top: 50px">
				<div class="form-group">
					<form:input path="title" class="form-control text-center" placeholder="${singleSong.title})" />
					<form:errors path="title" class="text-danger" />
				</div>
				<div class="form-group">
					<form:input path="rating" type="number" class="form-control text-center" placeholder="${singleSong.rating }" />
					<form:errors path="rating" class="text-danger" />
				</div>
				<div class="form-group">
					<form:input path="video" class="form-control text-center" placeholder="${singleSong.video}" />
					<form:errors path="video" class="text-danger" />
				</div>
				<form:input path="artist" type="hidden" class="form-control text-center" value="${singleSong.artist.id}" />
				<input type="submit" value="Edit Song"
					class="btn btn-sm btn-dark" />
			</form:form>
		</div>
	</div>
</body>