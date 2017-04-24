<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%@include file="/_header.jsp" %>
<%@include file="/_menu.jsp" %>

<div class="frame">
	<div class="form">
		<h1>Znajdź spotkanie</h1>
		<form name="data" method="get" action="FindMeeting">
			<p>Miejscowość:</p>
			<input type="text" name="city" />
			<p>od:</p>
			<input type="date" name="date" />
			<p>do:</p>
			<input type="date" name="date" />
			<p>Godzina:</p>
			<input type="time" name="time" />
			<p>Adres</p>
			<input type="text" name="address" />
			<p>Uwagi: </p>
			<textarea name="attentions" rows="10" cols="30"></textarea>
			<br>
			<input type="submit" name="create" value="Szukaj spotkania">
		</form>
	</div>
</div>

</body>
</html>