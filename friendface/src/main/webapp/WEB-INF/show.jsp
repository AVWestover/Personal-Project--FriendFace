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
	<title>Users Posts</title>
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
				<a class="nav-link active" aria-current="page" href="/posts">Home</a>
			</li>
			<li class="nav-item">
				<a class="nav-link active" aria-current="page" href="/logout">Logout</a>
			</li>
		</ul>
	</div>
</nav>
<!--  -->
<div id="outside-all">
	<!-- show outside cont -->
	<div id="show-outside-cont">
		<!-- Header -->
		<h3><c:out value="${userselected.firstName} ${userselected.lastName}"/>'s Posts</h3>
		<div id="show-posts-cont">
			<!-- Posts -->
			<c:forEach items="${userselected.postsCreated}" var="post">
				<div class="show-post-cont">
					<p class="show-post-username"><c:out value="${userselected.firstName} ${userselected.lastName}"/></p>
					<p class="show-post-posttext"><c:out value="${post.postText}"/></p>
				</div>
			</c:forEach>
		</div>
	</div>
</div>
</body>
