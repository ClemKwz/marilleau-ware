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
		
		<title>Marilleau ware</title>
	</head>


	<body>
		<div id="header">
                <div id="logo"></div>
                <div id="title">Marilleau Ware</div>
                <div id="tagline">Site de tamere</div>

            
	            <div id="nav">
	                <div id="nbar">
	                    <ul>
	                        <li id="selected"><a href="index.php">Accueil</a></li>
	                        <li><a href="./PartyCreation">Créer une partie</a></li> 
	                        <li><a href="./ViewAllParties">Rejoindre une partie</a></li>
 	                        <li><a href="#">Equipe</a></li>
	                        <li><a href="#">Aide</a></li>
	                    </ul>
	                </div>
	            </div>
		<%
		if(isLogged)
			out.print("Tu es Logg&eacute; mon pote!!");
		else
			out.print("Tu n'es pas Logg&eacute; p&eacute;d&eacute;!!");
		%>
		</div>
		<div id="container">
		

		