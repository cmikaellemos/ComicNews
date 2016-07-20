<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page contentType="text/html; charset=ISO-8859-1" language="java" pageEncoding="UTF-8" import="java.sql.*" errorPage="" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Comic News</title>
<spring:url value="_resources/_css/estilo.css" var="estilo"></spring:url>
<spring:url value="_resources/_css/listas.css" var="listas"></spring:url>
<link rel="stylesheet" href="${estilo}" type="text/css"/>
<link rel="stylesheet" href="${listas}" type="text/css"/>
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
 <div class="lista">
	<form action="listarClassificados" method="get">
		<input type="search" name="titulo_n" placeholder="Digite o titulo que deseja buscar" size="30">
		Ordenar por: 
		<select name="tipo">
			<ins>Escolha uma opção</ins>
			<option value="data_oferta">Data</option>
			<option value="MAIOR_LANCE">Maior Lance</option>
			<option value="preco">Preço</option>
		</select>
		<select name="ordem">
			<option value="desc">Maior / Mais Recente</option>
			<option value="cresc">Menor / Menos Recente</option>
		</select>
		<input type="submit" value="Buscar">
	</form>
	
	<table>
		<tr>
			<td>Nome</td>
			<td>Preço Inicial</td>
			<td>Maior Lance</td>
			<td>Foto</td>
		</tr>
		<c:forEach var="c" items="${classificados_listar}">
			<tr>
				<td class="nomes"><a href="listarClassificado?id_class=${c.id_classificado}">${c.titulo}</a></td>
				<td class="nomes">${c.preco }</td>
				<td class="nomes">${c.melhor_oferta }</td>
				<td class="nomes"><img id="classi" alt="${c.titulo}" src="<c:url value="/_resources/_imagens/_classificados/${c.titulo}.png"/>"/></td>
				<c:if test="${leitor_logado.id_usuario == c.user.id_usuario }">	
					<td class="nomes"><a href="alterarClassificadoForm?id=${c.id_classificado}">Alterar</td>
					<td class="nomes"><a href="apagarClassificado?id=${c.id_classificado}">Apagar</a></td>
				</c:if>
			</tr>	
		</c:forEach>
	</table>
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