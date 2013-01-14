<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="css/main.css" type="text/css" media="screen" charset="utf-8" />
<script src="js/main.js" type="text/javascript"></script> 
<script src="js/functions.js" type="text/javascript"></script>
<%
	out.print("<script src=\"js/game1.js\" type=\"text/javascript\"></script>");
%>

<script type="text/javascript">

</script>


<title>Marilleau ware</title>
</head>
<body onload="init();">
	<div id="header">
	</div>
	<div id="container">
		<div id="headConteneur">
		<div id="chrono"></div>
		</div>
		<div id="bc_games">
			<canvas id="canvasElem" width="400" height="300">
			</canvas>
		</div>
		<div id="infos">
		</div>
		<input type="button" value="valider" onclick="game();">
	</div>
</body>
</html>