<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html; charset=ISO-8859-1" language="java" pageEncoding="UTF-8" import="java.sql.*" errorPage="" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Alterar Usuário</title>
</head>
<body>
<h2>Alterar Usuário</h2>

<form action="alterarUsuario" method="post">
	<input type="hidden" name="id_usuario" value="${usuario.id_usuario}" />
	<p>Nome:</p> 
	<input type="text" name="nome" value="${usuario.nome}" /><br />
	<p>Email:</p> 
	<input type="email" name="email" value="${usuario.email}" /><br />
	<input type="hidden" name="login" value="${usuario.login}"><br />
	<input type="hidden" name="senha" value="${usuario.senha}"><br />
	<input type="submit" value="ALTERAR" />
</form>
</body>
</html>