<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Map" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Menu</title>
<link rel="stylesheet" type="text/css" href="style.css">
</head>
<body class="reset-body">

	<%
	boolean zalogowany = false;
	Object user = request.getSession().getAttribute("user");
	Map<String, String> arrayOfLinks = new HashMap<String, String>();
	if(user != null) {
		arrayOfLinks.put("Logout", "Wyloguj");
		arrayOfLinks.put("CreateMeeting", "Utwórz spotkanie");
		arrayOfLinks.put("GetAllMeetings", "Pokaż wszystkie spotkania");
		arrayOfLinks.put("FindMeeting", "Wyszukaj spotkanie");
		arrayOfLinks.put("getAllEndMeetings", "Pokaż zakończone spotkania");
		arrayOfLinks.put("getMyMeetings","Pokaż moje spotkania");
	} else {
		arrayOfLinks.put("Register", "Rejestracja");
		arrayOfLinks.put("Login", "Logowanie");
	}
	request.setAttribute("arrayOfLinks", arrayOfLinks);
	%>
	<form  method="get" action="MenuServlet" >
		<table id="menu"> 
    	<%
    	for (Map.Entry<String, String> entry : arrayOfLinks.entrySet()) {
        	String key = entry.getKey();
        	String value = entry.getValue();
        	%>
        	<tr><td><button type="submit" name="button" class="links" value=<%=key%>><%=value%></button></td></tr>
        	<%
   		}
    	%>    
		</table>
	</form>	
	<div id="menu-padding"></div>

</body>
</html>