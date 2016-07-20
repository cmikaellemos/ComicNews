<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page contentType="text/html; charset=ISO-8859-1" language="java" pageEncoding="UTF-8" import="java.sql.*" errorPage="" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Comic News</title>
<spring:url value="_resources/_css/estilo.css" var="estilo"></spring:url>
<spring:url value="_resources/_css/noticia.css" var="noticia_estilo"></spring:url>
<link rel="stylesheet" href="${estilo}" type="text/css"/>
<link rel="stylesheet" href="${noticia_estilo}" type="text/css"/>
</head>
<body>
<div id="corpo">
	<div class="logo">
	<header id="cabecalho">
		<div class="formu">
			<form action="listarNoticiaTitulo">
				<input type="text" name="titulo" size="30" placeholder="Buscar Notícia"/>
				<input type="submit" value="Buscar"/>
			</form>
		</div>
		<nav>	
			<ul class="menu">
				<li><a href="home">Início</a></li>
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
						<c:if test="${leitor_logado.papel_atual == 'Editor' }">
							<li><a href="listarUsuario">Listar Usuários</a></li>
							<li><a href="secaoFormulario">Nova Seção</a></li>
							<li><a href="listarSecao">Listar Seções</a></li>
							<li><a href="noticiaFormulario">Nova Notícia</a></li>
							<li><a href="classificadoFormulario">Novo Classificado</a></li>
						</c:if>
						<c:if test="${leitor_logado.papel_atual == 'Jornalista' }">
							<li><a href="classificadoFormulario">Novo Classificado</a></li>
							<li><a href="noticiaFormulario">Nova Notícia</a></li>
						</c:if>
						<c:if test="${leitor_logado.papel_atual == 'Leitor' }">
							<li><a href="classificadoFormulario">Novo Classificado</a></li>
						</c:if>
					</ul>
				</li>
			</ul>
		</nav>
	</header>
</div>
 <section id="corpo">
	<c:forEach var="a" items="${noticias}">
		<ul>
			<li><h2>${a.titulo}</h2></li>
			<li><a href="listarNoticiaCom?id=${a.id_noticia}">
				<figure  class="foto-legenda">
				    <img src="<c:url value="_resources/_imagens/_noticias/${a.id_noticia}.png"/>">
				    <figcaption>
				        <p>${a.subtitulo}</p>
				        <p>Por: ${a.user.nome} Em: ${a.data_noticia}</p>
				    </figcaption>
				</figure>
			</a></li>
			<c:if test="${usuario_logado == a.user.id_usuario }">
				<li><a href="alterarNoticiaForm?id=${a.id_noticia}">Editar</a></li>
			</c:if>
		</ul>
	</c:forEach>
 
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
		</select><br/><br/>
		<input type="submit" value="Logar" />
	</form>
	<p>Ainda não possui cadastro?</p>
	<p><a href="cadastroFormulario">Cadastre-se aqu!</a></p>
</c:if>
<c:if test="${leitor_logado != null}">
	<br/> 
	<figure  class="foto-perfil">
	    <img src="<c:url value="_resources/_imagens/_usuarios/${leitor_logado.login}.png"/>">
	    <figcaption>
	        <p> ${leitor_logado.nome }</p>
	        <p><a class="link-p" href="verPerfil?id=${leitor_logado.id_usuario }">Ver perfil</a></p>
	    </figcaption>
	</figure>
	<p>Logado como: ${leitor_logado.papel_atual}</p>
	<p><a class="log" href="logout">Logout</a></p>
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