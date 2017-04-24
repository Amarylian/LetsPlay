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

	<p>Miejscowość: *</p>
	<p><%=meeting.getCity() %></p>
	<p>Data: *</p>
	<p><%=meeting.getDate() %></p>
	<p>Godzina: *</p>
	<p><%=meeting.getTime() %></p>
	<p>Adres</p>
	<p><%=meeting.getAddress() %></p>
	<p>Liczba graczy: *</p>
	<p><%=meeting.getActualNumber()+"/"+meeting.getMaxNumber() %></p>
	<p>Uwagi: </p>
	<p><%=meeting.getAttentions() %></p>
	<a href="index.jsp"><button type="button">Powrót</button></a>
	</div></div>
</body>
</html>