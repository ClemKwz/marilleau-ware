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
	
	String message = "";
	if (request.getParameter("message") != null) {
		message = request.getParameter("message");
	}
	
	int idParty = Integer.parseInt(request.getParameter("idParty").toString());
	out.print("<script  type=\"javascript\">initChat("+idParty+");</script>");
	out.print("<input name=\"message\" type=\"text\" value=\"\"/>" +
			"<input name=\"Valid\" type=\"submit\" onclick=sendMessage("+idParty+","+Integer.valueOf(id)+") />" +
			"<br>");
%>
	<p>La partie va commencer <%=idParty%></p>
	
	
	<div id="chat">
	
	</div>
	