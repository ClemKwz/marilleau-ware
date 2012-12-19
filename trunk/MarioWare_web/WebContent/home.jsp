<%@ page 
	language="java" 
	contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
%>

<% 
	if (session.getAttribute("sessionID")==null) {
		String message = "Error : Your session is terminated";
		getServletContext().getRequestDispatcher("/index.jsp?message="+message).forward(request,response);
		return;
	}
	
	String sessionID = session.getAttribute("sessionID").toString();
	if(!sessionID.equals(session.getId())) {
		String message = "Error : Your session ID doesn't exist";
		getServletContext().getRequestDispatcher("index.jsp?message="+message).forward(request,response);
		return;
	}

	String pseudo = session.getAttribute("pseudoUser").toString();
	String id = session.getAttribute("idUser").toString();
	
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="stylesheet" href="css/main.css" type="text/css" media="screen" charset="utf-8" />
		
		<title>Marilleau ware</title>
	</head>
	<body>
		<div id="header">
		</div>
		<div id="container">
			<p>Connected with pseudo <b><%=pseudo%></b></p>
			<p><%=id%></p>
		</div>
	</body>
</html>