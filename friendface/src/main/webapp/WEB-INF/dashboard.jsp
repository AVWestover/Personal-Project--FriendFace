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
	<title>Explore Friendface!</title>
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
	<!-- Dashboard outside cont -->
	<div id="explore-outside-cont">
		<!-- Header -->
		<h3>Explore</h3>
				<!-- button -->
		<div id="explore-new-post-btn">
			<a class= "btn btn-secondary text-decoration-none" href="/posts/new">Share your message</a>
		</div>
		<div id="explore-posts-cont">
			<!-- Posts -->
			<c:forEach items="${posts}" var="post">
				<div class="explore-post-cont">
					<p class="explore-post-username"><a href="/users/${post.creator.id}"><c:out value="${post.creator.firstName} ${post.creator.lastName}"/></a></p>
					<p class="explore-post-posttext"><c:out value="${post.postText}"/></p>
					<div class="explore-post-edit-cont">
						<c:choose>
							<c:when test="${ post.creator.id == user.id }">
						    	<a href="/posts/${post.id}/edit">Edit</a> |
						        <a href="/posts/${post.id}/delete">Delete</a> 
					        </c:when>
					        <c:otherwise>
							<p class="explore-post-space"> ------</p>
							</c:otherwise>
						</c:choose>
					</div>
				</div>
			</c:forEach>
		</div>
	</div>
</div>
</body>
