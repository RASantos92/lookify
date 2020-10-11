  
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
			<h1>${song.title }</h1>
			<br>
			<h2>Artist: ${song.artist.name} </h2>
			<br>
			<p>Rating: ${song.rating}</p>
			<br>
		</div>
	</div>
	<iframe class="col mx-auto" width="1695" height="717" src="https://www.youtube.com/embed/${song.video}" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" allowfullscreen>
	</iframe>
	<div class="container">
		<div class="col mx-auto text-center">
			<a href="/dashboard" class="btn btn-dark btn-outline-success" >Home</a>
			<a href="/edit/song/${song.id}" class="btn btn-dark btn-outline-warning">Edit</a>
			<a href="/song/destroy/${song.id}" class="btn btn-dark btn-outline-danger">Delete</a>
			</div>
		</div>
</body>
</html>