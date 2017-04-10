<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="style.css">
<title>Let's play - Rejestracja</title>
</head>
<body>
<div class="frame">
<div class="form">
<h1>Rejestracja</h1>
<form action = "register" name="data" method="post">
	<p>Login</p>
	<input type="text" name="login" />
	<p>Imię</p>
	<input type="text" name="name" />
	<p>Nazwisko</p>
	<input type="text" name="surname" />
	<p>E-mail</p>
	<input type="text" name="email" />
	<p>Hasło</p>
	<input type="text" name="passwd" />
	<p>Powtórz hasło</p>
	<input type="text" name="passwd2" />
	<p />
	 <%
		Object passwd = request.getAttribute("password");
		String error = "";
		if(passwd != null) {
			if(Boolean.valueOf(passwd.toString()) == false) {
				error = "Błędne powtórzenie hasła";
			}
		}
 			Object success = request.getAttribute("success");
 			if(success != null) {
 				if(Boolean.valueOf(success.toString()) == false) {
 					error = "Rejestracja nie powiodła się";
 				}
 			}
 			
 	%>
	<input type="submit" name="register" value="Zarejestruj">
	<div class="errorMessage"><%=error%></div>
</form>
</div></div>
</body>
</html>