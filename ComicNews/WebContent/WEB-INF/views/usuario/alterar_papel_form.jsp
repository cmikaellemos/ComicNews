<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html; charset=ISO-8859-1" language="java" pageEncoding="UTF-8" import="java.sql.*" errorPage="" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Adicionar Papel</title>
</head>
<body>
Alterar papel de: ${user.nome} <br/>

<form action="alterarPapel" method="post">
	<input type="hidden" name="id_user" value="${user.id_usuario}"/>
	<select name="id_papel">
		<option value="2">Jornalista</option>
		<option value="3">Editor</option>
	</select>
	<input type="submit" value="Alterar">
</form>
</body>
</html>