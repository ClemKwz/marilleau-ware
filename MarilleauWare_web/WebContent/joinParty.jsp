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
		<div style="margin: 50px;">
			<p>Connected with pseudo <b><%=pseudo%></b>, id : <%=id%></p>
			<script src="js/functions.js" type="text/javascript"></script>
			<script src="js/main.js" type="text/javascript"></script>
			<script src="http://code.jquery.com/jquery-1.8.3.js"></script>
			<script src="http://code.jquery.com/ui/1.9.2/jquery-ui.js"></script>
			<script type="text/javascript">initJoinPartie(<%=id%>)</script>
		</div>
	</div>

<jsp:include page="footer.jsp"></jsp:include>
	