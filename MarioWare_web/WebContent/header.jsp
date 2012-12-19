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
                <div id="title">E-enchÃ¨re</div>
                <div id="tagline">Site d'enchÃ¨res en ligne</div>

            
	            <div id="nav">
	                <div id="nbar">
	                    <ul>
	                        <li id="selected"><a href="index.php">Accueil</a></li>
	                        <li><a href="#">EnchÃ¨res en cours</a></li> 
	                        <li><a href="#">EnchÃ¨res terminÃ©es</a></li>
	                        <li><a href="#">Comment Ã§a marche</a></li>
	                         <?php if (isset($_SESSION['idUser'])) { ?>
	                        <li><a href="enchere.php">Création</a></li>
	                        <?php } ?>
	                        <?php if (!isset($_SESSION['idUser'])) { ?>
	                        <li><a href="inscription.php">Inscription</a></li>
	                        <?php } ?>
	                        <li><a href="sitemap.php">Plan du site</a></li>
	                    </ul>
	                </div>
	            </div>
		<%
		if(isLogged)
			out.print("Tu es Logg&eacute; mon pote!!");
		%>
		</div>
		<div id="container">
		

		