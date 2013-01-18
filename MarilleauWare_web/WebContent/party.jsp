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
	out.print("<script  type=\"javascript\">init();</script>");
	out.print("<input name=\"message\" type=\"text\" value=\"\"/>" +
			"<input name=\"Valid\" type=\"submit\" onclick=sendMessage("+idParty+","+Integer.valueOf(id)+") />" +
			"<br>");
%>
	<p>La partie va commencer <%=idParty%></p>
	
	<div id="container" style="margin: auto;">
		<div style="margin: 50px;">
			<p>Connected with pseudo <b><%=pseudo%></b>, id : <%=id%></p>
			<script src="js/functions.js" type="text/javascript"></script>
			<script src="js/main.js" type="text/javascript"></script>
			<script src="http://code.jquery.com/jquery-1.8.3.js"></script>
			<script src="http://code.jquery.com/ui/1.9.2/jquery-ui.js"></script>
		</div>
		<div id="headConteneur">
			<div id="chrono"> </div>
		</div>
		<div id="bc_games">
			<canvas id="canvasElem" width="400" height="300">
			</canvas>
		</div>
		<div id="infos">
		</div>
	</div>
	
	<div id="chat">
	
	</div>
	
	
	