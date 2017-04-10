<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Let's play - Przypomnienie hasła</title>
<style>
	html
	{
		background-color: purple;
	}
	.form{
	margin: auto;
		text-align: center;
		border: 2px solid purple;
		background-color: yellow;
		width: 60%;
	}
	.form input{
		width=70%
	}
	
	.frame{
	padding: 100px;
		margin: 100px;
		background-color: purple;
	}
</style>
</head>
<body>
<div class="frame">
<div class="form">
<h1>Przypomnienie hasła</h1>
<form name="data" method="post">
	<p>Podaj email</p>
	<input type="text" name="email" />
	<input type="submit" name="remind" value="Przypomnij hasło">
</form>
</div></div>
</body>
</html>