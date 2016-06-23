<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html; charset=ISO-8859-1" language="java" pageEncoding="UTF-8" import="java.sql.*" errorPage="" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Cadastrar Classificado</title>
</head>
<body>
<h1>Cadastrar como: ${leitor_logado.nome }</h1>
<form action="cadastrarClassificado" method="post" enctype="multipart/form-data">
	<input type="hidden" name="id_user" value="${leitor_logado.id_usuario}"/>
	<p>Titulo: </p><input type="text" name="titulo_n" size="40" placeholder="Digite o nome do produto"/><br/>
	<p>Descrição: </p><textarea rows="5" cols="40" name="texto_n" placeholder="Fale sobre o produto"></textarea><br/>
	<p>Telefone: </p><input type="tel" name="telefone_n" placeholder="Digite seu telefone"/><br/>
	<p>Valor: </p><input type="number" step="0.01" min="0" name="preco_n" placeholder="Digite o valor do produto"/><br/>
	<p>Imagem: </p><input type="file" name="imagem" /> <br />
	<input type="submit" value="Publicar Classificado">
	<input type="reset" value="Limpar">
</form>

</body>
</html>