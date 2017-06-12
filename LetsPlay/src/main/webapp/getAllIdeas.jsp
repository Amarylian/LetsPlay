<%@ page import="pl.letsplay.beans.Idea" %>
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
    	ArrayList<Idea> listOfIdeas = (ArrayList<Idea>) request.getSession().getAttribute("ideas");
    	for (Idea m : listOfIdeas) {
    		int id = m.getIdea_id();
        	String city = m.getCity();
        	String attentions = m.getAttentions();
        	if (city == null || attentions == null) {
        		continue;
        	}
        	%>
        	
        	<tr>
        	<form method="post" action="GetAllIdeasServlet">
        				<p>Pomysł nr <%=id %></p>
        				<p>Miasto <%=city %></p>
        				<p>Uwagi: <%=attentions %></p>
        	        	<p><button type="submit" name="button" value=<%=id %>>Utwórz spotkanie</button></p>
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