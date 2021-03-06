<%@ page import="pl.letsplay.beans.Meeting" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="style.css">
<title>Let's play - Tworzenie spotkania</title>
</head>
<body>
<%@include file="/_header.jsp" %>
<%@include file="/_menu.jsp" %>
<div class="frame">
<div class="form">
<h1>Utwórz spotkanie</h1>
<form name="data" method="post" action="CreateMeeting">
<%
	Meeting m = (Meeting) request.getSession().getAttribute("CreateMeeting");
	String miasto = "";
	String data = "";
	String godzina = "";
	String adres = "";
	String gracze = "";
	String uwagi = "Tu wpisz uwagi";
	if(m != null) {
		miasto = m.getCity();
		if(miasto == null) {
			miasto = "";
		}
		data = m.getDate();
		if(data == null) {
			data = "";
		}
		godzina = m.getTime();
		if(godzina == null) {
			godzina = "";
		}
		adres = m.getAddress();
		if(adres == null) {
			adres = "";
		}
		gracze = Integer.toString(m.getMaxNumber());
		if(gracze == null) {
			gracze = "";
		}
		uwagi = m.getAttentions();
		if(uwagi == null) {
			uwagi = "Tu wpisz uwagi";
		}
	}
	System.out.println("test");
	System.out.println(adres);
	System.out.println(m);

%>
	<p>Widoczność: *</p>
	<select name="priv">
	  <option value="prywatne">prywatne</option>
	  <option value="publiczne">publiczne</option>
	</select>
	<p>Miejscowość: *</p>
	<input type="text" name="city" value="<%=miasto%>"/>
	<p>Data: *</p>
	<input type="date" name="dat" value="<%=data%>"/>
	<p>Godzina: *</p>
	<input type="time" name="tim" value="<%=godzina%>"/>
	<p>Adres</p>
	<input type="text" name="address" value="<%=adres%>"/>
	<br>
	<input type="checkbox" name="address2"  >Adres widoczny tylko dla uczestników spotkania
	<p>Liczba graczy: *</p>
	<input type="text" name="number" value="<%=gracze%>"/>
	<p>Uwagi: </p>
	<textarea name="attentions" rows="10" cols="30"><%=uwagi%></textarea>
	<br>
		 <%
		String error = "";
 		Object success = request.getAttribute("success");
 		if(success != null) {
 			if(Boolean.valueOf(success.toString()) == false) {
 				error = "Utworzenie nie powiodło się";
 			} else {
 				error = "Utworzenie spotkania powiodło się";
 			}
 		}
 			
 	%>
	<input type="submit" name="create" value="Utwórz spotkanie">
	<input type="reset" name="cancel" value="Anuluj">
	<a href="index.jsp"><button type="button">Powrót</button></a>
		<div class="errorMessage"><%=error%></div>
</form>
</div></div>
</body>
</html>