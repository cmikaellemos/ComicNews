<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html; charset=ISO-8859-1" language="java" pageEncoding="UTF-8" import="java.sql.*" errorPage="" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Listar Seções</title>
</head>
<body>
<table border="1">
	<c:forEach var="a" items="${secoes}">
	<tr>
		<td>${a.id_secao}</td>
		<td>${a.titulo}</td>
		<td>${a.descricao}</td>
		<td><a href="alterarSecaoForm?id=${a.id_secao}">ALTERAR</a></td>
		<td><a href="apagarSecao?id_secao=${a.id_secao}">APAGAR</a></td>
	</tr>
	</c:forEach>
</table>
<p><a href="home">Voltar</a></p>
</body>
</html>