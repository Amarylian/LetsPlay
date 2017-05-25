<%@page import="pl.letsplay.beans.Meeting"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="style.css">
<title>Szczegóły spotkania</title>
</head>
<body>

<% Meeting meeting = (Meeting)session.getAttribute("meeting"); %>
<%@include file="/_header.jsp" %>
<%@include file="/_menu.jsp" %>
<div class="frame">
<div class="form">
<h1>Szczegóły spotkania</h1>

	<p><b>Miejscowość:</b></p>
	<p><%=meeting.getCity() %></p>
	<p><b>Data:</b></p>
	<p><%=meeting.getDate() %></p>
	<p><b>Godzina:</b></p>
	<p><%=meeting.getTime() %></p>
	<p><b>Adres:</b></p>
	<p><%=meeting.getAddress() %></p>
	<p><b>Liczba graczy:</b></p>
	<p><%=meeting.getActualNumber()+"/"+meeting.getMaxNumber() %></p>
	<p><b>Uwagi:</b></p>
	<p><%=meeting.getAttentions() %></p>
	<form action="ShowMeetingServlet" method="post">
		<input type="submit" value="Zapisz się na spotkanie" onclick="">
	</form>
</body>
</html>