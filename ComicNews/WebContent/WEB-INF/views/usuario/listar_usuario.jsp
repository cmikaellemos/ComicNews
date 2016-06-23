<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html; charset=ISO-8859-1" language="java" pageEncoding="UTF-8" import="java.sql.*" errorPage="" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Listar Usuário</title>
</head>
<body>
<form action="listarUsuario" method="get">
<input type="search" name="nome" size="40">
<input type="submit" value="Buscar">
</form>
<table border="1">
	<c:forEach var="a" items="${usuarios}">
	<tr>
		<td>${a.id_usuario}</td>
		<td>${a.nome}</td>
		<td>${a.email}</td>
		<td>${a.login}</td>
		<td><a href="alterarUsuarioForm?id=${a.id_usuario}">ALTERAR</a></td>
		<td><a href="apagarUsuario?id=${a.id_usuario}">APAGAR</a></td>
		<td><a href="alterarPapelForm?id=${a.id_usuario }">ADICIONAR PAPEL</a></td>
	</tr>
	</c:forEach>
</table>
</body>
</html>