<%@ page import="pl.letsplay.beans.User" %>
<%@ page import="java.util.ArrayList" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="style.css">
<title>Let's play - Podsumowanie spotkania</title>
</head>
<body>
<%@include file="/_header.jsp" %>
<%@include file="/_menu.jsp" %>
<div class="frame">
<div class="form">

<h1>Podsumuj spotkanie</h1>
<form name="data" method="post" action="SummaryServlet">

	<p>Czy spotkanie się odbyło? </p>
	<select name="ifmeeting">
	  <option value="tak">Tak</option>
	  <option value="nie">Nie</option>
	</select>
	<p>Czy byłeś na spotkaniu?</p>
	<select name="ifyou">
	  <option value="tak">Tak</option>
	  <option value="nie">Nie</option>
	</select>

	<input type="submit" name="sum" value="Podsumuj spotkanie">
	<a href="index.jsp"><button type="button">Powrót</button></a>
</form>
</div></div>
</body>
</html>