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
	<header id="cabecalho">
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
	<div  class="noticia">
	<h1>${noticia_com.titulo}</h1>
	<h2>${noticia_com.subtitulo}</h2>
	<p>${noticia_com.texto}</p>
	<figure>	
		<img alt="${noticia_com.titulo }" src="<c:url value="_resources/_imagens/_noticias/${noticia_com.id_noticia}.png"/>">
	</figure>
	<p>Por: ${noticia_com.user.nome} &nbsp; Em: ${noticia_com.data_noticia}</p>
	</div>
	<form action="inserirComentario" method="post">
		<input type="hidden" value="${noticia_com.id_noticia}" name="idnoticia"/>
		<input type="hidden" value="${leitor_logado.id_usuario}" name="idusuario"/>
		<input type="text" name="texto" size="40"/>
		<input type="submit" value="Comentar"/>
	</form> 
	<div class="comentarios">
	<c:forEach var="a" items="${comentarios_not}">
		<h3>${a.user_id.nome}</h3>
		<p>${a.texto}</p>
		<a href="apagarComentario?id=${a.id_comentario}&id_notice=${noticia_com.id_noticia}">APAGAR</a>
	</c:forEach>
	</div>
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