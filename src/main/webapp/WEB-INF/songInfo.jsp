  
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
			<p>Rating: ${song.getAverageRating()}</p>
			<br>
		</div>
	</div>
	<iframe class="col mx-auto" width="1695" height="717" src="https://www.youtube.com/embed/${song.video}" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" allowfullscreen>
	</iframe>
	<div class="container">
		<div class="col mx-auto text-center">
			<a href="/dashboard" class="btn btn-dark btn-outline-success" >Home</a>
			<a href="/edit/song/${song.id}" class="btn btn-dark btn-outline-warning">Edit</a>
			<a href="/song/destroy/${song.id}" class="btn btn-dark btn-outline-danger">Add</a>
			<a href="/artist/show/${song.artist.id}" class="btn btn-dark btn-outline-info">More ${song.artist.name}</a>
			</div>
		</div>
		<div class="row ">
		<div class="col-sm-3 mt-5 mx-auto">
                <div class="card mx-auto">
                    <div class="card-header text-light bg-dark text-center" ><h2>Leave a Review</h2></div>
                    <div class="card-body">
                        <form:form action="/song/${song.id}/comment" method="post" modelAttribute="newComment">
                            <div class="row text-center">
                                <div class="col form-group">
                                    <label>Rating</label>
                                    <form:input type="number" path="rating" class="form-control" placeholder="rating between 0-100!"/>
                                    <form:errors path="rating" class="text-danger"/>
                                    <label>Comment</label>
                                    <form:textarea path="comment" class="form-control"/>
                                   	<form:errors path="comment" class="text-danger"/>
                                </div>
                            </div>
                            <input type="submit" value="Review ${song.title}" class="btn btn-primary btn-block" />
                        </form:form>
                    </div>
                </div>
              </div>
                <div class="col-sm-8 mx-auto">
                <ul class="list-group mt-5">
                    <li class="list-group-item bg-dark text-light">Reviews:</li>
                    <c:forEach items="${song.comments}" var="review">
                        <li class="list-group-item">
                        	<strong>${review.rating} out of 100</strong><br>
                            <strong>${review.user.userName}: ${review.comment}</strong><br>
                        </li>
                    </c:forEach>
                </ul>
                
		</div>
              </div>
</body>
</html>