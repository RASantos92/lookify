  
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
			<h1>${artist.name}</h1>
			<br>
			<p>Hometown: ${artist.hometown}</p>
			<br>
		</div>
	</div>
	<div class="container">
		<div class="col mx-auto text-center">
			<a href="/dashboard" class="btn btn-dark btn-outline-success" >Songs</a>
			<a href="/artist/dashboard" class="btn btn-dark btn-outline-light" >Artist</a>
			<a href="/edit/artist/${artist.id}" class="btn btn-dark btn-outline-warning">Edit</a>
			<a href="/add/artist/" class="btn btn-dark btn-outline-info">Add Artist</a>
			<a href="https://www.stubhub.com/find/s/?q=${artist.name}" class="btn btn-dark btn-outline-info">Ticket Info</a>
			<a class="btn btn-dark btn-outline-danger" href="/logout">Logout</a>
			</div>
		</div>
		<div class="container">
			<div class="row d-flex justify-content-around">
				<div class="col-4 text-center">
					<h2>Add Song</h2>
					<form:form action="/create/song" method="post" modelAttribute="newSong">
					<div class="form-group">
		        		<form:input path="title" class="form-control text-center" placeholder="Title"/>
		        		<form:errors path="title" class="text-danger" />
		        	</div>
		        	<div class="form-group">
						<form:input path="genresInput" class="form-control text-center" placeholder="Add genre" />
						<form:errors path="genresInput" class="text-danger" />
					</div>
					<div class="form-group">
						<form:input path="video" class="form-control text-center" placeholder="Add youtube embed" />
						<form:errors path="video" class="text-danger"/>
					</div>
		        		<form:input type="hidden" path="artist"  value="${artist.id}"/>
		        		<input type="hidden" name="artistId" value="${artist.id}">
		        		<input type="submit" value="Add" class="btn btn-sm btn-dark"/>
		        	</form:form>
				</div>
				<table
					class="col-6 m-3 table table-dark table-striped table-hover">
					<tr>
						<th>Song Names</th>
						<th>Rating</th>
						<th>Action</th>
					</tr>
					<c:forEach items="${artist.songs}" var="song">
						<tr>
							<td><a href="/song/show/${song.id}/${user.id}"><b>${song.title}</b></a></td>
							<td>${song.rating}</td>
							<td><a class="btn btn-dark btn-outline-warning" href="/edit/song/${song.id}">Edit</a>
							<a class="btn btn-dark btn-outline-danger" href="/artist/song/${song.id}">Add</a></td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</div>
		
</body>
</html>
















