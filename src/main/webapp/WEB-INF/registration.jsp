<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/semantic-ui@2.4.2/dist/semantic.min.css">
<script
	src="https://cdn.jsdelivr.net/npm/semantic-ui@2.4.2/dist/semantic.min.js"></script>
</head>
<body>




	<ui class="ui three column centered grid">
		<div class="column">
			<h1>Registration</h1>
			<form class="ui form" action="adduser" method="post">
				<div class="field">
					<label>Username</label> <input type="text" name="name"
						placeholder="Name">
				</div>
				<div class="field">
					<label>Password</label> <input type="text" name="password"
						placeholder="Password">
				</div>
				<button class="ui button" type="submit">Submit</button>
			</form>
		</div>
	</ui>

	${errorMsg}


</body>
</html>