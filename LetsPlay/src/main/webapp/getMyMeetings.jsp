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
        	if (city == null || date == null || attentions == null) {
        		continue;
        	}
        	%>
        	
        	<tr>
        	<form method="post" action="GetAllEndMeetingsServlet">
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