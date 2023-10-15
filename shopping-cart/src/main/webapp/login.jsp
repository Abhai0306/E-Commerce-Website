<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.*" %>
<%@page import="cn.techtutorial.Model.*"%>
	<% User auth = (User)request.getSession().getAttribute("auth");
	if(auth != null)
	{
		response.sendRedirect("index.jsp");
	}
	
	ArrayList<Cart> cart_list = (ArrayList<Cart>) session.getAttribute("cart-list");

	if (cart_list != null) {
		request.setAttribute("cart_list", cart_list);
	}
	%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login Page</title>
<%@include file="includes/header.jsp"%>
</head>
<body>
	<%@include file="includes/navbar.jsp" %>
	<div class="container">
		<div class="card w-50 mx-auto my-5">
			<div class="card-header text-center">User login</div>
			<div class="card-body">
				<form action="user-login" method="post">
					<div class="form-group">
						<label>Email Address</label>
						 <input type="email" class="form-control" name="login-email" placeholder="Enter your Email" required>
					</div>
					<div class="form-group">
						<label>Password</label> 
						<input type="password" class="form-control" name="login-password" placeholder="********" required>
					</div>
					<div class="text-center">
						<button type="submit" class="btn-primary">Login</button>
					</div>
				</form>
			</div>
		</div>
	</div>
	<%@include file="includes/footer.jsp"%>
</body>
</html>