<!DOCTYPE html>
<html lang="en">
<head>
<title>Gulp!</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</head>
<body>

	<nav class="navbar navbar-inverse">
		<div class="container-fluid">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target="#myNavbar">
					<span class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="index.jsp">Gulp!</a>
			</div>
			<div class="collapse navbar-collapse" id="myNavbar">
				<ul class="nav navbar-nav">
					<li class="active"><a href="index.jsp">Home</a></li>
					<li><a href="Restaurants">Restaurants</a></li>
					<%if((Boolean)session.getAttribute("loggedIn")){ %>
					<li><a href="Review">Review</a></li>
					<li><a href="Profile">Profile</a></li>
					<%} %>
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<li><a href="SignUp.jsp"><span class="glyphicon glyphicon-user"></span>
							Sign Up</a></li>
					<li><a href="SignIn.jsp"><span class="glyphicon glyphicon-log-in"></span>
							Login</a></li>
				</ul>
			</div>
		</div>
	</nav>

</body>
</html>
