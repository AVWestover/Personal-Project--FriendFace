<!-- c:out ; c:forEach etc. --> 
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!-- Formatting (dates) -->
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!-- form:form -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!-- for rendering errors on PUT routes -->
<%@ page isErrorPage="true" %>
<!--  -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<!DOCTYPE html>
<head>
	<meta charset="UTF-8">
	<title>New Post</title>
	<!-- bootstrap link -->
	<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
	<!-- For any Bootstrap that uses JS or jQuery-->
	<script src="/webjars/jquery/jquery.min.js"></script>
	<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
	<!-- My CSS -->
	<link rel="stylesheet" href="/css/style.css"/>
	<!--  -->
</head>
<body>
<!-- nav bar -->
<nav class="navbar navbar-expand-xl navbar-dark bg-dark" id="nav-bar">
	<div class="collapse navbar-collapse show" id="navbarDark">
		<ul class="navbar-nav me-auto mb-2 mb-xl-0">
			<li class="nav-item">
				<a class="nav-link active" aria-current="page" href="/posts">Explore</a>
			</li>
			<li class="nav-item">
				<a class="nav-link active" aria-current="page" href="/logout">Logout</a>
			</li>
		</ul>
	</div>
</nav>
<!--  -->
<div id="outside-all">
	<h3>Share your message!</h3>
	<div id="new-cont">
		<form:form action="/posts/new" method="POST" modelAttribute="post">
			<form:hidden value="${ user.id }" path="creator"/>
			<!-- Post -->
			<div>
				<form:label path="postText">Your Message:</form:label>
				<form:input type="text" path="postText"/>
				<form:errors path="postText"/>
			</div>
			<!-- Button -->
			<div id="new-btn">
		    	<button class="btn btn-secondary btn-sm">Post It!</button>	
			</div>
		</form:form>
	</div>
</div>
</body>
