<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html; charset=ISO-8859-1" language="java" pageEncoding="UTF-8" import="java.sql.*" errorPage="" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Alterar Seção</title>
</head>
<body>
<h2>Alterar Seção</h2>

	<form action="alterarSecao" method="post">
		<input type="hidden" name="id_secao" value="${secao.id_secao}" />
		<p>Título:</p> 
		<input type="text" name="titulo" value="${secao.titulo}" /><br />
		<p>Descrição:</p> 
		<input type="text" name="descricao" value="${secao.descricao}" /><br />
		<input type="submit" value="ALTERAR" />
	</form>
</body>
</html>