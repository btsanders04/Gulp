<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<jsp:include page="NavBar.jsp"/>

	<div class="container">
		<h2>Edit Restaurant</h2>
		<form class="form-horizontal" role="form" action="EditRestaurant" method="post">
			${edit}
			
		</form>
		</div>
</body>
</html>