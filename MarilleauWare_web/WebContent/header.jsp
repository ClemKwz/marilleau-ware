<%@ page 
	language="java" 
	contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
%>
<%
	boolean isLogged = false;
	if(request.getSession() != null)
		isLogged = true;
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="stylesheet" href="css/main.css" type="text/css" media="screen" charset="utf-8" />
		<script src="js/functions.js" type="text/javascript"></script>
		<script src="js/main.js" type="text/javascript"></script>
		<script src="js/chat.js" type="text/javascript"></script>
		<script src="js/scores.js" type="text/javascript"></script>
		
		<script src="js/gameCheckbox.js" type="text/javascript"></script>
		<script src="js/gameFindTheDot.js" type="text/javascript"></script>
		<script src="js/gameBuildPath.js" type="text/javascript"></script>
		
		<script src="http://code.jquery.com/jquery-1.8.3.js"></script>
		<script src="http://code.jquery.com/ui/1.9.2/jquery-ui.js"></script>
		<title>Marilleau ware !</title>
	</head>

	<body>
		<div id="header">
            <div id="logo"></div>
            <span id="title"><b>Marilleau Ware</b></span>
            <span id="tagline">Plateforme de mini-jeux !</span>

            <div id="nav">
                <div id="nbar">
                    <ul>
                        <li id="selected"><a href="home.jsp">Accueil</a></li>
                        <li><a href="partyCreation.jsp">Cr&eacute;er une partie</a></li> 
                        <li><a href="joinParty.jsp">Rejoindre une partie</a></li>
	                    <li><a href="equipe.jsp">Equipe</a></li>
                        <li><a href="aide.jsp">Aide</a></li>
                        <li><a href="Logout">Se d&eacute;connecter</a></li>
                    </ul>
                </div>
            </div>
		<%
		if(isLogged) {
			String pseudo = session.getAttribute("pseudoUser").toString();
			String id = session.getAttribute("idUser").toString();
			out.print("Connected with pseudo <b>"+pseudo+"</b> ("+id+")");
		}else {
			out.print("Tu n'es pas Logg&eacute !");
		}
		%>
		</div>
		

		