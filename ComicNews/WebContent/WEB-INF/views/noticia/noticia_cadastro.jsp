<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html; charset=ISO-8859-1" language="java" pageEncoding="UTF-8" import="java.sql.*" errorPage="" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Cadastrar Noticia</title>
</head>
<body>
Editor logado: ${leitor_logado.nome}
<form action="cadastrarNoticia" method="post" enctype="multipart/form-data">
	<p>Titulo: </p>
	<input type="text" size="40" maxlength="40" name="titulo"/>
	<p>Subtitulo: </p>
	<input type="text" size="40" maxlength="40" name="subtitulo"/>
	<p>Descrição: </p>
	<input type="text" size="40" maxlength="60" name="descricao"/>
	<p>Texto: </p>
	<textarea rows="20" cols="40" name="texto"></textarea>
	<p>Imagem: </p>
	<input type="file" name="imagem"/>
	<p>Seção: </p>
	<select name="secao_titulo">
		<c:forEach var="s" items="${secoes}">
			<option>
				<c:out value="${s.titulo}"></c:out>
			</option>
		</c:forEach>
	</select>
	<input type="hidden" value="${leitor_logado.login}" name="login"/>
	<input type="submit" value="Publicar"/>
	<input type="reset" value="Limpar"/> 
	
</form>
</body>
</html>