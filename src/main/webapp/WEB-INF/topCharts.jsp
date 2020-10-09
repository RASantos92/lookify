  
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
<title>Song Chart</title>
<link rel="stylesheet"
	href="/webjars/bootstrap/4.5.2/css/bootstrap.min.css" />
<script src="/webjars/jquery/3.5.1/jquery.min.js"></script>
<script src="/webjars/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body style="background-color: gray">
	<div class="container">
		<div class="col mx-auto text-center">
			<h1>Top 10 songs</h1>
			<div style="box-shadow:5px 5px 5px 5px black; table-header: sticky">
        <a class="btn btn-dark btn-outline-info" href="/dashboard">Home</a>
                <table class="table table-dark table-striped table-hover">
                    <tr>
                        <th>Song Title</th>
                        <th>Artist</th>
                        <th>rate</th> 
                     <tr>    
                    <c:forEach items="${songs}" var="song">
                        <tr class="text-light">
                            <td>${song.title}</td>
                            <td>${song.artist}</td>
                            <td>${song.rating}</td>
                        </tr>
                    </c:forEach>
                </table>
			</div>
		</div>
	</div>  
</body>
</html>