<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html; charset=ISO-8859-1" language="java" pageEncoding="UTF-8" import="java.sql.*" errorPage="" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${classificado.titulo}</title>
</head>
<body>
<h2>${classificado.titulo}</h2>
<p>${classificado.texto }</p>
<img alt="${classificado.titulo}" src="<c:url value="/_resources/_imagens/_classificados/${classificado.titulo}.png"/>" />
<p>Preço: ${classificado.preco }</p>
<p>Telefone Contato: ${classificado.telefone }</p>

<p>Maior Lance: ${classificado.melhor_oferta } De: ${user.nome}</p>

<form action="ofertar" method="post">
	<input type="hidden" name="id_user" value="${leitor_logado.id_usuario}"/>
	<input type="hidden" name="id_cla" value="${classificado.id_classificado }" />
	<input type="number" name="preco_n" min="0"/>
	<input type="submit" value="Ofertar"/>
</form>
</body>
</html>