<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page contentType="text/html; charset=ISO-8859-1" language="java" pageEncoding="UTF-8" import="java.sql.*" errorPage="" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Comic News</title>
<spring:url value="_resources/_css/estilo.css" var="estilo"></spring:url>
<spring:url value="_resources/_css/form.css" var="form"></spring:url>
<link rel="stylesheet" href="${estilo}" type="text/css"/>
<link rel="stylesheet" href="${form}" type="text/css"/>
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
<form action="cadastrarClassificado" method="post" enctype="multipart/form-data">
	<h2>Novo Classificado</h2>
	<input type="hidden" name="id_user" value="${leitor_logado.id_usuario}"/>
	<p>Titulo:</p>
	<input type="text" name="titulo_n" required="required" size="70" placeholder="Digite o nome do produto"/><br/>
	<p>Descrição:</p>
	<textarea rows="5" cols="60" required="required" name="texto_n" placeholder="Fale sobre o produto"></textarea><br/>
	<p>Telefone:</p>
	<input type="tel" name="telefone_n" required="required" size="70" placeholder="Digite seu telefone"/><br/>
	<p>Valor:</p>
	<input type="number" step="0.01" min="0" required="required" size="70" name="preco_n" placeholder="Digite o valor do produto"/><br/>
	<p>Imagem:</p>
	<input type="file" name="imagem" /> <br /><br />
	<input type="submit" value="Publicar Classificado">
	<input type="reset" value="Limpar">
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
		<br/>
		<input type="submit" value="Logar" />
	</form>
	<p>Ainda não possui cadastro?</p>
	<p><a href="cadastroFormulario">Cadastre-se aqui!</a></p>
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