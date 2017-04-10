<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="style.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<form action="login" method="get" class="form">
 		<table id="login">
 		 	<%
 			Object login = request.getAttribute("login");
 			if(login == null) {
				request.setAttribute("login", "");
 			}
 			%>
 			<tr>
 				<td><span>Login:</span> <input type="text" name="login" value="<%= request.getAttribute("login") %>"></td>
 			</tr>
 			<tr>
 				<td><span>Hasło:</span> <input type="password" name="password"></td>
 			</tr>
 			<%
 			Object success = request.getAttribute("success");
 			String error = "";
 			if(success != null) {
 				if(Boolean.valueOf(success.toString()) == false) {
 					error = "Nie udało się zalogować";
 				}
 			}
 			%>
 			<tr>
 				<td><span class="errorMessage"><%=error%></span></td>
 			</tr>

 			<tr>
 				<td><input type="submit" value="Zaloguj się" onclick=""></td>
 			</tr>
 			<tr>
 				<td><a href="/LetsPlay/passwordReminder.jsp">Przypomnij hasło</a></td>
 			</tr>
 		</table>
	</form>
</body>
</html>