<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="com.fdmgroup.StockAnalyzer.Stock, java.util.ArrayList"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Stock Analyzer</title>

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/semantic-ui@2.4.2/dist/semantic.min.css">
<script
	src="https://cdn.jsdelivr.net/npm/semantic-ui@2.4.2/dist/semantic.min.js"></script>
</head>
<body>

	<div class="ui menu">
		<div class="item" id="globe">
			<i class="globe icon"></i>
		</div>
		<div class="item">
			<div class="ui input">
				<form class="ui form" action="getstock" method="post">
					<input id="search-box" type="text" placeholder="Search Stock..."
						name="name" required="required">
				</form>
			</div>
		</div>
		<div class="right menu">

			<%
				if (session.getAttribute("user") == null) {
			%>
			<a class="item"><button class="ui blue button" id="login">Login</button></a>
			<%
				} else {
			%>

			<a class="item"><c:out value="${user.name}" /></a> <a class="item"><button
					class="ui blue button" id="logout">Logout</button></a>
			<%
				} ;
			%>

			<a class="item"> <i class="grid layout icon"></i> Browse
			</a>
		</div>
	</div>

	<c:if test="${errorUsername == true || errorPassword == true}">
		<div class="ui error message">
			<i class="close icon"></i>
			<div class="header">There were some errors with your Login
				Credentials</div>
			<ul class="list">
				<c:if test="${errorUsername == true}">
					<li>Incorrect user name.</li>
				</c:if>
				<c:if test="${errorPassword == true && errorUserName != true}">
					<li>Incorrect Password</li>
				</c:if>
			</ul>
		</div>
	</c:if>

	<c:if test="${noSuchStockError == true}">
		<div class="ui error message">
			<i class="close icon"></i>
			<p>No such stock found</p>
		</div>
	</c:if>

	<a href="/StockAnalyzer/getstocks">Get All Stocks</a>

	<form action="addstock" method="post">
		<label>AddStock</label><input type="text" name="name"> <input
			type="submit" value="Submit" name="submit" />
	</form>

	<div id="login-modal" class="ui modal">
		<div class="ui placeholder segment">
			<div class="ui two column very relaxed stackable grid">
				<div class="column">

					<form class="ui form" action="login" method="post">
						<div class="field">
							<label>Username</label>
							<div class="ui left icon input">
								<input type="text" placeholder="Username" name="name"> <i
									class="user icon"></i>
							</div>
						</div>
						<div class="field">
							<label>Password</label>
							<div class="ui left icon input">
								<input type="password" name="password"> <i
									class="lock icon"></i>
							</div>
						</div>
						<div class="actions">
							<button class="ui blue submit button" type="submit">Login</button>
						</div>
					</form>
				</div>

				<div class="middle aligned column">
					<div class="ui big button">
						<i class="signup icon"></i> <a href="/StockAnalyzer/registration">Registration</a>
					</div>
				</div>
			</div>
			<div class="ui vertical divider">Or</div>
		</div>
	</div>




	<script type="text/javascript">
		$(document).ready(function() {
			$('#login').click(function() {
				$('#login-modal').modal('show');
			});
			
			$('#logout').click(function() {
				location.href = "./logout";
			});
			
			$('#back-button').click(function() {
				$('#login-modal').modal('hide');
			});
			
			$('.message .close').on('click', function() {
				$(this).closest('.message').transition('fade');
			});

			$('#globe').click(function() {
				location.href = "./";
			});
			
			
			
			
			
		});


	</script>




</body>
</html>