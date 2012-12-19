<%
	String message = "";
	if (request.getParameter("message") != null) {
		message = request.getParameter("message");
	}
%>

<div id="container">

	<p>Enter your pseudo :</p>
	<form method="post" action="Login">
		<input name="pseudo" type="text" />
           <input name="Valid" type="submit" />
	</form>
	<p><%=message%></p>
	
</div>

