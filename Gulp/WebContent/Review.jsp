<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Review</title>
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
	<jsp:include page="NavBar.jsp" />
	<div class="container">
		<h2>Review Restaurant</h2>
		<form class="form" role="form" action="${action}" method = "POST">
			<div class="form-group">
				<label for="restaurant">Select Restaurant:</label> <select
					class="form-control" id="restaurant" name="restaurant">
					${restaurants}
				</select>
			</div>
			<div class="form-group">
				<label class="radio-inline"> <input type="radio"
					name="optradio" value=1>1 Star
				</label> <label class="radio-inline"> <input type="radio"
					name="optradio" value=2>2 Star
				</label> <label class="radio-inline"> <input type="radio"
					name="optradio" value=3>3 Star
				</label><label class="radio-inline"> <input type="radio"
					name="optradio" value=4>4 Star
				</label><label class="radio-inline"> <input type="radio"
					name="optradio" value=5>5 Star
				</label>
			</div>
			<div class="form-group">
				<div class="form-group">
					<label for="desc">Description:</label>
					<textarea class="form-control" rows="5" id="desc" name="desc" placeholder="${description}"></textarea>
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-10">
					<button type="submit" class="btn btn-default">Submit</button>
				</div>
			</div>
		</form>
	</div>

</body>
</html>