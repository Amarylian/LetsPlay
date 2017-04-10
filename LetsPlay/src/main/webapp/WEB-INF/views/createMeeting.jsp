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
<div class="frame">
<div class="form">
<h1>Utwórz spotkanie</h1>
<form name="data" method="post">
	<p>Widoczność: *</p>
	<select name="priv">
	  <option value="prywatne">prywatne</option>
	  <option value="publiczne">publiczne</option>
	</select>
	<p>Miejscowość: *</p>
	<input type="text" name="city" />
	<p>Data: *</p>
	<input type="date" name="dat" />
	<p>Godzina: *</p>
	<input type="time" name="tim" />
	<p>Adres</p>
	<input type="text" name="address" />
	<br>
	<input type="checkbox" name="address2"  >Adres widoczny tylko dla uczestników spotkania
	<p>Liczba graczy: *</p>
	<input type="text" name="number" />
	<p>Uwagi: </p>
	<textarea name="attentions" rows="10" cols="30">Tu wpisz uwagi</textarea>
	<br>
	<input type="submit" name="create" value="Utwórz spotkanie">
	<input type="submit" name="cancel" value="Anuluj">
</form>
</div></div>
</body>
</html>