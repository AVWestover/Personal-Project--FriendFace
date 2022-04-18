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
	<title>Login and Registration</title>
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
<div id="outside-all">
	<div id="outside-cont-reglogin">
	<!-- LEFT CONT (Registration)-->
	<div id="reg-cont">
	<div id="reg-form-cont">
		<h3>Register</h3>
		<form:form action="/register" method="POST" modelAttribute="user">
			<!-- First name -->
			<div class="form-group row">
				<form:label path="firstName">First Name:</form:label>
				<form:input type="text" path="firstName"/>
				<form:errors path="firstName"/>
			</div>
			<!-- Last name -->
			<div class="form-group row">
				<form:label path="lastName">Last Name:</form:label>
				<form:input type="text" path="lastName"/>
				<form:errors path="lastName"/>
			</div>
			<!-- Email -->
			<div class="form-group row">
				<form:label path="email">Email:</form:label>
				<form:input type="email" path="email"/>
				<form:errors path="email"/>
			</div>
			<!-- Password -->
			<div class="form-group row">
				<form:label path="password">Password:</form:label>
				<form:input type="password" path="password"/>
				<form:errors path="password"/>
			</div>
			<!-- Confirm password -->
			<div class="form-group row">
				<form:label path="passwordConfirmation">Confirm Password:</form:label>
				<form:input type="password" path="passwordConfirmation"/>
				<form:errors path="passwordConfirmation"/>
			</div>
			<!-- button -->
			<div id="reg-btn">
		    	<div id = "submit-cont">
		    		<button class="btn btn-secondary btn-sm">Submit</button>
		    	</div>	
			</div>
		</form:form>
	</div>
	</div>
	<!-- RIGHT CONT (login) -->
	<div id="log-cont">
	<div id="login-form-cont">
		<h3>Login</h3>
		
		<form action="/login" method="POST">
			<!-- Email -->
			<div class="form-group row">
				<label for="email">Email</label>
				<input type="email" id="email" name="email"/>
			</div>
			<!-- Password -->
			<div class="form-group row">
				<label for="password">Password</label>
				<input type="password" id="password" name="password"/>
			</div>
			<!-- Button -->
			<div id="login-btn">
		    	<div id = "submit-cont">
		    		<button class="btn btn-secondary btn-sm">Submit</button>
		    	</div>	
			</div>
		</form>
	</div>
	</div>
	
	</div>
</div>
</body>
