<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="style.css">
<title>Let's play - Tworzenie pomysłu spotkania</title>
</head>
<body>
<%@include file="/_header.jsp" %>
<%@include file="/_menu.jsp" %>
<div class="frame">
<div class="form">
<h1>Utwórz pomysł spotkania</h1>
<form name="data" method="post" action="CreateMeetingIdeaServlet">

	<p>Miejscowość: *</p>
	<input type="text" name="city" />
	<p>Uwagi: </p>
	<textarea name="attentions" rows="10" cols="30">Tu wpisz uwagi</textarea>

	<input type="submit" name="create" value="Utwórz pomysł spotkania">
	<input type="reset" name="cancel" value="Anuluj">
	<a href="index.jsp"><button type="button">Powrót</button></a>
</form>
</div></div>
</body>
</html>