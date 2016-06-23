<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html; charset=ISO-8859-1" language="java" pageEncoding="UTF-8" import="java.sql.*" errorPage="" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Alterar Noticia</title>
</head>
<body>
<form action="alterarNoticia" method="post">
	<input type="hidden" name="id_n" value="${noticia_form.id_noticia}" />
	<p>Título:</p> 
	<input type="text" name="titulo_n" value="${noticia_form.titulo}" /><br />
	<p>Subtitulo:</p> 
	<input type="text" name="subtitulo_n" value="${noticia_form.subtitulo}" /><br />
	<p>Texto:</p> 
	<textarea rows="20" cols="40" name="texto_n">${noticia_form.texto}</textarea><br/>
	<input type="submit" value="ALTERAR" />
</form>
</body>
</html>