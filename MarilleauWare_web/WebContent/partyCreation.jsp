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
	
%>

<jsp:include page="header.jsp"></jsp:include>

	<div id="container" style="margin: auto;">
		<div style="margin: 50px;">
			
			<p>Connected with pseudo <b><%=pseudo%></b>, id : <%=id%></p>
			<p>Cr&eacute;ation d'une nouvelle partie</p>
			
			<form action="/MarilleauWare_web/PartyCreation" method="POST">
				<table>
					<tr>
						<td>Nom :</td>
						<td><input type="text" name="nameParty" maxlength="20"></input></td>
					</tr>
					<tr>
						<td>Description :</td>
						<td><textarea rows="4" cols="40" name="descriptionParty" maxlength="100" style="resize: none;"></textarea></td>
					</tr>
					<tr>
						<td colspan=2><input value="Cr&eacute;er" type="submit"></input></td>
					</tr>
				</table>
				<input type="hidden" name="action" value="10"></input>
			</form>
			<p><%=message%></p>
		</div>
	</div>

<jsp:include page="footer.jsp"></jsp:include>
	