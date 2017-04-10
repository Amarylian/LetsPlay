<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="style.css">
<title>Let's play - Przypomnienie hasła</title>
</head>
<body>
<div class="frame">
<div class="form">
<h1>Przypomnienie hasła</h1>
<form name="passwordReminder" action="passwordReminder" method="get">
	<p>Podaj email</p>
	<input type="text" name="email" />
	<input type="submit" name="remind" value="Przypomnij hasło">
	<%
 		Object password = request.getAttribute("password");
		Object success = request.getAttribute("success");
		if(success != null) {
			if(password == null) {
				request.setAttribute("password", "Account with that email don't exist");
			}
		} else {
			request.setAttribute("password", "");
		}
 	%>
	<div><%=request.getAttribute("password") %></div>
</form>
</div></div>
</body>
</html>