<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page contentType="text/html; charset=ISO-8859-1" language="java" pageEncoding="UTF-8" import="java.sql.*" errorPage="" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Comic News</title>
<spring:url value="_resources/_css/estilo.css" var="estilo"></spring:url>
<spring:url value="_resources/_css/form.css" var="formulario"></spring:url>
<link rel="stylesheet" href="${estilo}" type="text/css"/>
<link rel="stylesheet" href="${formulario}" type="text/css"/>
</head>
<body>
<div id="corpo">
	<header id="cabecalho">
	<h1 class="logo">Comic News</h1>
	<form action="listarNoticiaTitulo">
		<input type="text" name="titulo" size="30" placeholder="Buscar Notícia"/>
		<input type="submit" value="Buscar"/>
	</form>
		<nav>	
			<ul class="menu">
				<li><a href="/Jornal/">Início</a></li>
				<li> <a>Seções</a>	
					<ul>
						<c:forEach var="s" items="${secoes }">
							<li><a href="listarNoticiaSec?id_secao=${s.id_secao}">${s.titulo}</a></li>
						</c:forEach>
					</ul>
				</li>	
				<li><a href="listarClassificados">Classificados</a></li>
				<li> <a>Ações</a>
					<ul>
						<li><a href="listarUsuario">Listar Usuarios</a></li>
						<li><a href="secaoFormulario">Cadastrar Secao</a></li>
						<li><a href="noticiaFormulario">Cadastrar Noticia</a></li>
						<li><a href="classificadoFormulario">Cadastrar Classificado</a></li>
					</ul>
				</li>
			</ul>
		</nav>
	</header>
 <section id="corpo">
	
	<form  action="cadastrar" method="post" enctype="multipart/form-data">
		<h2>Cadastro</h2>
			<p>Nome:</p> 
			<input type="text" size="40" name="nome" placeholder="Digite seu nome" required="required">
			<p>Email:</p> 
			<input type="email" size="40" name="email" placeholder="Digite seu email" required="required">
			<p>Login:</p>
			<input class="senha" size="40" type="text" name="login" placeholder="Digite seu login" required="required">
			<p>Senha:</p>
			<input type="password" size="40" name="senha" placeholder="A senha deve ter no minimo 6 digitos" required="required">
			<p>Foto:</p> 
			<input type="file" name="imagem"><br/><br/>
			<input type="submit" value="Enviar">
			<input type="reset" value="Limpar">
		</p>
	</form>
</section>
<aside id="lateral">
<c:if test="${leitor_logado == null}">
	<form action="login" method="post">
		<p>Login:</p>
		<input type="text" name="login" required="required" placeholder="Digite seu login"/> <br/>
		<p>Senha: </p>
		<input type="password" name="senha" required="required" placeholder="Digite a sua senha"/> <br/>
		<p>Selecione:</p> 
		<select name="papel" >
			<option value="Leitor">Leitor</option>
			<option value="Jornalista">Jornalista</option>
			<option value="Editor">Editor</option>
		</select><br/>
		<input type="submit" value="Logar" />
	</form>
	<p>Ainda não possui cadastro?</p>
	<p><a href="cadastroFormulario">Cadastre-se aqui!</a></p>
</c:if>
<c:if test="${leitor_logado != null}">
	<br/>
	<p>Logado como: ${leitor_logado.nome }</p> <br/>
	<p>Papel: ${leitor_logado.papel_atual}</p>
	<p><a href="logout">Logout</a></p>
</c:if>
</aside>

<footer>
<br/><br/>
	<p>Desenvolvido por Mikael Lemos</p>
<br/><br/>
</footer>
</div>
</body>
</html>