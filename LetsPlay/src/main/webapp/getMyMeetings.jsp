<%@ page import="pl.letsplay.beans.Meeting" %>
<%@ page import="java.util.ArrayList" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%@include file="/_header.jsp" %>
	<%@include file="/_menu.jsp" %>
	<div class="frame">
	<div class="form">
	<h1>Spotkania</h1>
		<table id="menu"> 
    	<%
    	ArrayList<Meeting> listOfMeetings = (ArrayList<Meeting>) request.getSession().getAttribute("meetings");
    	for (Meeting m : listOfMeetings) {
    		int id = m.getId();
        	String city = m.getCity();
        	String date = m.getDate();
        	String attentions = m.getAttentions();
        	String address=m.getAddress();
        	if (city == null || date == null || attentions == null) {
        		continue;
        	}
        	%>
        	
        	<tr>
        	<form name="data" method="post" action="CreateMeeting">
<p>Spotkanie nr <%=id %></p>
	<p>Widoczność: *</p>
	<select name="priv">
	  <option value="publiczne">publiczne</option>
	  <option value="prywatne">prywatne</option>
	</select>
	<p>Miejscowość: *</p>
	<input type="text" name="city" value="<%=city %>"/>
	<p>Data: (Ostatnio: <%=date %> Nowa:</p>
	<input type="date" name="dat" />
	<p>Godzina: *</p>
	<input type="time" name="tim" value="<%=m.getTime() %>"/>
	<p>Adres</p>
	<input type="text" name="address" value="<%=address %>"/>
	<br>
	<input type="checkbox" name="address2"  >Adres widoczny tylko dla uczestników spotkania
	<p>Liczba graczy: (ostatnio: <%=m.getActualNumber() %>*</p>
	<input type="text" name="number" value="<%=m.getMaxNumber() %>"/>
	<p>Uwagi: </p>
	<textarea name="attentions" rows="10" cols="30">Tu wpisz uwagi</textarea>
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
        	<form method="post" action="GetMyMeetingsServlet">
        				<p>Spotkanie nr <%=id %></p>
        				<p>Miasto <%=city %></p>
        				<p>Data <%=date %></p>
        				<p>Uwagi: <%=attentions %></p>
        	        	<p><button type="submit" name="button" value=<%=id %>>Nowa edycja</button></p>
        	        	<hr>
        	        	<br/>
        	</form>     
        	</tr>
        	<%
   		}
    	%>    
		</table>
	</div>
	</div>
</body>
</html>