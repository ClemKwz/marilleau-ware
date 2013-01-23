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
	int idUser = Integer.parseInt(session.getAttribute("idUser").toString());
	
	int idParty = Integer.parseInt(request.getParameter("idParty").toString());
	
	//out.print("<script  type=\"javascript\">initChat("+idParty+");</script>");
	out.print("<script  type=\"javascript\">init();</script>");
	out.print("<script  type=\"javascript\">initScoreParty("+idParty+");</script>");
	
%>

	<script src="js/functions.js" type="text/javascript"></script>
	<script src="js/main.js" type="text/javascript"></script>
	<script src="js/gameCheckbox.js" type="text/javascript"></script>
	<script src="http://code.jquery.com/jquery-1.8.3.js"></script>
	<script src="http://code.jquery.com/ui/1.9.2/jquery-ui.js"></script>
	
	<p>La partie va commencer <%=idParty%></p>
	
	
	<!-- On rajoute un champs permettants de dire à main.js que nous avons terminé -->
	<input type="hidden" id="finishGame" value="false"/>
	<input type="hidden" id="idGameValue" value="-1"/>
	<div id="container" style="margin: auto;">
		<div style="margin: 50px;">
			<p>Connected with pseudo <b><%=pseudo%></b>, id : <%=idUser%></p>
		</div>
		<div id="headConteneur">
			<!-- <div id="chrono"> </div>  -->
			<table>
				<tr>
					<td>
						<canvas id="canvasInfo" width="220" height="30"></canvas>
					</td>
					<td>
						<div id="chrono"></div>
					</td>
					<td>
						<div id="divScore"></div>
					</td>
				</tr>
			</table>
		</div>
		<div id="bc_games">
			<canvas id="canvasElem" width="400" height="300">
			</canvas>
		</div>
		<div id="infos">
		</div>
	</div>
	
	<%
	out.print("<input name=\"message\" type=\"text\" value=\"\"/>" +
		"<input name=\"Valid\" type=\"submit\" onclick=sendMessage("+idParty+","+idUser+") />" +
		"<br>");
	%>
	
	<div id="chat"></div>
	<div id="scoreparty"></div>