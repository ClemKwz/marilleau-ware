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

<%-- <jsp:include page="header.jsp"></jsp:include> --%>



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
		<script src="js/main.js" type="text/javascript"></script> 
		<script src="js/functions.js" type="text/javascript"></script>
		<script src="js/game1.js" type="text/javascript"></script>
		<script src="js/gameCheckbox.js" type="text/javascript"></script>
		
		<title>Marilleau ware !</title>
	</head>

	<body onload="init();">
		<div id="header">
            <div id="logo"></div>
            <div id="title"><b>Marilleau Ware</b></div>
            <div id="tagline">Plateforme de mini-jeux !</div>

            <div id="nav">
                <div id="nbar">
                    <ul>
                        <li id="selected"><a href="index.php">Accueil</a></li>
                        <li><a href="partyCreation.jsp">Cr&eacute;er une partie</a></li> 
                        <li><a href="joinParty.jsp">Rejoindre une partie</a></li>
	                        <li><a href="#">Equipe</a></li>
                        <li><a href="#">Aide</a></li>
                    </ul>
                </div>
            </div>
		</div>
		

		



	<div id="container" style="margin: auto;">
		<div style="margin: 50px;">
			<p>Connected with pseudo <b><%=pseudo%></b>, id : <%=id%></p>
			<script src="js/functions.js" type="text/javascript"></script>
			<script src="js/main.js" type="text/javascript"></script>
			<script src="http://code.jquery.com/jquery-1.8.3.js"></script>
			<script src="http://code.jquery.com/ui/1.9.2/jquery-ui.js"></script>
			<script type="text/javascript"></script>
		</div>
		<div id="headConteneur">
			<!-- <div id="chrono"> </div>  -->
			<table>
				<tr>
					<td>
						<canvas id="canvasInfo" width="200" height="30"></canvas>
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

<jsp:include page="footer.jsp"></jsp:include>
	