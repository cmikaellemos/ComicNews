<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html; charset=ISO-8859-1" language="java" pageEncoding="UTF-8" import="java.sql.*" errorPage="" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Inicio</title>
</head>
<body>
<form action="alterarClassificado" method="post">
	<input type="hidden" name="id_user" value="${classificado_alterar.id_classificado}"/>
	<p>Titulo: </p><input type="text" name="titulo_n" size="40" value="${classificado_alterar.titulo }"/><br/>
	<p>Descrição: </p><textarea rows="5" cols="40" name="texto_n">${classificado_alterar.texto }</textarea><br/>
	<p>Telefone: </p><input type="tel" name="telefone_n" value="${classificado_alterar.telefone }"/><br/>
	<p>Valor: </p><input type="number" min="0" name="preco_n" value="${classificado_alterar.preco }"/><br/>
	<p>Imagem: </p><input type="file" name="imagem" /> <br />
	<input type="submit" value="Publicar Classificado">
	<input type="reset" value="Limpar">
</form>
</body>
</html>