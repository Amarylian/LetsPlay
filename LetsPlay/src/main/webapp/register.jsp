<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Let's play - Rejestracja</title>
<style>
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
<h1>Rejestracja</h1>
<form name="data" method="post">
	<p>Login</p>
	<input type="text" name="login" />
	<p>E-mail</p>
	<input type="text" name="email" />
	<p>Hasło</p>
	<input type="text" name="passwd" />
	<p>Powtórz hasło</p>
	<input type="text" name="passwd2" />
	<p />
	<input type="submit" name="register" value="Zarejestruj">
</form>
</div></div>
</body>
</html>