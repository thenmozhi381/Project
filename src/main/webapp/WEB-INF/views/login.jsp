<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
<link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<jsp:include page="/WEB-INF/views/header.jsp"></jsp:include> 
<div class="container">
<center><h3>Welcome to Login page</h3></center>
<div class="card card-container">
<h2 class='login_title text-center'>Login</h2>
<hr>
<form action="${pageContext.request.contextPath}/login" class="form-signin" method="post">
<span id="reauth-email" class="reauth-email"></span>
<h4 class="input_title">Email</h4>

<input type="text" id="inputEmail"  name="username" placeholder="Enter Mail Id..."  class="form-control"  required>

<h4 class="input_title">Password</h4>

<input type="password" id="inputPassword" name="password" placeholder="Enter Password..."  class="form-control" required>
<button class="btn btn-lg btn-primary" type="submit">Login</button> 
<button class="btn btn-lg btn-primary" type="reset">Reset</button> 
 <!-- <a href="#">Lost yourpassword?</a> -->
  <a href="register">Register</a>
 
</form>


 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 </div>
</div>
 </body>
</html>