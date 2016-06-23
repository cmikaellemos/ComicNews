<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page contentType="text/html; charset=ISO-8859-1" language="java" pageEncoding="UTF-8" import="java.sql.*" errorPage="" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style>
	h2{
		color: black;
		text-align: center;
		text-shadow: 2px 2px 2px gray;
	}
	p{
		text-align: center;
		font-size: 12pt;
	}
</style>
<title>Cadastro Secao</title>
</head>
<body>
	<h2 align="center">Cadastro de Seção</h2>
	<form action="cadastrarSecao" method="post">
		<p>
		Titulo:<br/> 
		<input type="text" name="titulo" size="40" placeholder="Digite o titulo da seção" required="required"><br/>
		Descrição:<br/> 
		<input type="text" name="descricao" size="40" height="3" placeholder="Digite a descrição da seção" required="required"><br/>
		<input type="submit" value="Enviar">
		<input type="reset" value="Limpar"><br/>
		</p>
	</form>
</body>
</html>