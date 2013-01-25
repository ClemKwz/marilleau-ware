<%
	String message = "";
	if (request.getParameter("message") != null) {
		message = request.getParameter("message");
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="stylesheet" href="css/main.css" type="text/css" media="screen" charset="utf-8" />
		<title>Marilleau ware !</title>
	</head>

	<body>
<div id="container">
<div style="padding: 10px; margin: auto; width:620px;"><h2>Bonjour, veuillez saisir un pseudo pour acc&eacute;der au site</h1></div>
	<div style="padding: 10px; margin: auto; width:300px;">
		<p>pseudo :</p>
		<form method="post" action="Login">
			<input name="pseudo" type="text" />
	           <input name="Valid" type="submit" />
		</form>
		<p><%=message%></p>
	</div>
</div>
<jsp:include page="footer.jsp"></jsp:include>
