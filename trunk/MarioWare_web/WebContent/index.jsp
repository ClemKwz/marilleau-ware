<%@ page 
	language="java" 
	contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="stylesheet" href="css/main.css" type="text/css" media="screen" charset="utf-8" />
		
		<title>Marilleau ware</title>
	</head>

<%
	String message = "";
	if (request.getParameter("message") != null) {
		message = request.getParameter("message");
	}
%>

	<body>
		<div id="header">
		</div>
		<div id="container">
			<p>Enter your pseudo :</p>
			<form method="post" action="Login">
				<input name="pseudo" type="text" />
	            <input name="Valid" type="submit" />
			</form>
			<p><%=message%></p>
		</div>
	</body>
</html>
		