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

<jsp:include page="header.jsp"></jsp:include>

	<div id="container" style="margin: auto;">
		
	</div>

<jsp:include page="footer.jsp"></jsp:include>
	