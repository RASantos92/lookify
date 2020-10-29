  
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
			<form:form action="/user/${user.id}/playlist" method="post" modelAttribute="newPlaylist">
        			<form:input placeholder="Playlist name" path="name" class="form-control"/>
        			<form:errors path="name" class="text-danger"/>
        			<input type="submit" value="Create Playlist" class="btn btn-primary btn-block" />
        		</form:form>
        		<a class="btn btn-dark btn-outline-light" href="/songs/top/10">Top 10</a>
        		<a href="/dashboard" class="btn btn-dark btn-outline-success" >Songs</a>
				<a href="/add/artist/" class="btn btn-dark btn-outline-info">Add Artist</a>
				<a class="btn btn-dark btn-outline-danger" href="/logout">Logout</a>
			<div class="mt-3" style="overflow-y: auto; height: 800px; box-shadow:5px 5px 5px 5px black; table-header: sticky">
				<table
					class="table table-dark table-striped table-hover">
					<tr>
						<th>Play Lists</th>
					</tr>
					<c:forEach items="${user.playlist}" var="playlist">
						<tr>
							<td><a href="/artist/show/${user.id}">${playlist.name}</a></td>
							
						</tr>
					</c:forEach>
				</table>
			</div>
		</div>
	</div>
</body>
</html>