<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html; charset=ISO-8859-1" language="java" pageEncoding="UTF-8" import="java.sql.*" errorPage="" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Comic News</title>
<link rel="stylesheet" href="../_resources/estilo.css"/>
</head>
<body>
<div>
	<header id="cabecalho">
		<nav id="menu">	
			<ul>
				<li><a href="home">Início</a></li>
				<li> Seções	
					<ul>
					<c:forEach var="s" items="${secoes }">
						<li><a href="listarNoticiaSec?id_secao=${s.id_secao }">${s.titulo}</a></li>
					</c:forEach>
					</ul>
				</li>	
				<li><a href="listarClassificados">Classificados</a></li>
				<li> Ações
					<ul>
						<li><a href="listarUsuario">Listar Usuarios</a></li>
						<li><a href="secaoFormulario">Cadastrar Secao</a></li>
						<li><a href="noticiaFormulario">Cadastrar Noticia</a></li>
						<li><a href="classificadoFormulario">Cadastrar Classificado</a><br/></li>
					</ul>
				</li>
			</ul>
		</nav>
	</header>
	
	<h2 align="center">MENU</h2>
	<p align="center">
	Já Possui conta?
	<a href="loginFormulario" >Efetuar Login</a><br>
	<a href="cadastroFormulario">Cadastre-se aqui</a>
	</p>

</div>	
</body>
</html>