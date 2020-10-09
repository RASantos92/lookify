  
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
			<h1>welcome to Lookify</h1>
			<div style="overflow-y: auto; height: 410px; box-shadow:5px 5px 5px 5px black; table-header: sticky">
			<form action="/" method="get">
        			<input type="text" placeholder="search" name="search"/>
        			<input type="submit" value="search" class="btn btn-dark btn-outline-light"/>
        		</form>
				<table
					class="table table-dark table-striped table-hover">
					<tr>
						<th>Song Name</th>
						<th>Artist</th>
						<th>rate</th>
						<th>Action</th>
					</tr>
					<c:forEach items="${lang}" var="language">
						<tr>
							<td><a href="/language/show/${song.id}">${song.name}</a></td>
							<td>${song.artist}</td>
							<td>${song.rate}</td>
							<td><a href="/edit/language/${song.id}">Edit</a> || <a
								href="/language/destroy/${song.id}">Delete</a></td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</div>
	</div>
</body>
</html>