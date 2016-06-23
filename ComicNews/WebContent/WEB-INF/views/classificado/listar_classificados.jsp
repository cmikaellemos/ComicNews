<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html; charset=ISO-8859-1" language="java" pageEncoding="UTF-8" import="java.sql.*" errorPage="" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Classificados</title>
</head>
<body>
<form action="listarClassificados" method="get">
	<input type="search" name="titulo_n" placeholder="Digite o titulo que deseja buscar" size="40"><br/>
	Ordenar por: 
	<select name="tipo">
		<ins>Escolha uma opção</ins>
		<option value="data_oferta">Data</option>
		<option value="MAIOR_LANCE">Maior Lance</option>
		<option value="preco">Preço</option>
	</select>
	<select name="ordem">
		<option value="desc">Maior / Mais Recente</option>
		<option value="cresc">Menor / Menos Recente</option>
	</select>
	<input type="submit" value="Buscar">
</form>

<table border="1">
	<c:forEach var="c" items="${classificados_listar}">
		<tr>
			<td>${c.titulo}</td>
			<td>${c.preco }</td>
			<td><a href="listarClassificado?id_class=${c.id_classificado}">Visualizar</a></td>
			<td><a href="alterarClassificadoForm?id=${c.id_classificado}">Alterar</a></td>
			<td><a href="apagarClassificado?id=${c.id_classificado}">Apagar</a></td>
		</tr>	
	</c:forEach>
</table>
</body>
</html>