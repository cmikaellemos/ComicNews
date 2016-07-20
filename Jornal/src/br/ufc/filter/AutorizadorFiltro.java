package br.ufc.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ufc.Model.Usuario;

@WebFilter("/*")
public class AutorizadorFiltro implements Filter
{

	@Override
	public void destroy() {
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException 
	{		
		
		String URI = ((HttpServletRequest)request).getRequestURI();
		
		System.out.println("URI: " + URI);
		
		if(URI.startsWith("/Jornal/_resources")){
			chain.doFilter(request, response);
			return;
		}
		
		Usuario usuario = null;
		String papel = null;
		if(((HttpServletRequest) request).getSession().getAttribute("leitor_logado") != null){
			usuario = (Usuario) ((HttpServletRequest) request).getSession().getAttribute("leitor_logado");
			papel = usuario.getPapel_atual();
		}
		
		
		if(URI.endsWith("cadastroFormulario") || URI.endsWith("cadastrar") || URI.endsWith("listarNoticiaSec") ||
				URI.endsWith("listarNoticiaCom") ||	URI.endsWith("login") || URI.endsWith("listarClassificados") ||
				URI.endsWith("listarClassificado") || URI.endsWith("logout") || URI.endsWith("verPerfil") || URI.endsWith("home") 
				|| URI.endsWith("Jornal/")){
			chain.doFilter(request, response);
			return;
		}
		else if(papel != null){
			if(papel.equals("Leitor")){
				if( URI.endsWith("alterarUsuarioForm") || URI.endsWith("alterarUsuario") || 
						URI.endsWith("inserirComentario") || URI.endsWith("apagarComentario") ||
						URI.endsWith("ofertar") || URI.endsWith("alterarClassificadoForm") || URI.endsWith("alterarClassificado") || 
						URI.endsWith("cadastrarClassificado") || URI.endsWith("classificadoFormulario") || URI.endsWith("apagarClassificado")){
					chain.doFilter(request, response);
					return;
				}
				((HttpServletResponse) response).sendRedirect("home");
				return;
			}else if(papel.equals("Jornalista")){
				if(	URI.endsWith("noticiaFormulario") || URI.endsWith("cadastrarNoticia") || URI.endsWith("apagarNoticia") ||
						URI.endsWith("alterarNoticiaForm") || URI.endsWith("alterarNoticia") || URI.endsWith("inserirComentario") || 
						URI.endsWith("apagarComentario") || URI.endsWith("ofertar") || URI.endsWith("alterarUsuarioForm") || 
						URI.endsWith("alterarUsuario") || URI.endsWith("ofertar") || URI.endsWith("alterarClassificadoForm") || 
						URI.endsWith("alterarClassificado") || URI.endsWith("cadastrarClassificado") || 
						URI.endsWith("classificadoFormulario") || URI.endsWith("apagarClassificado")){
					chain.doFilter(request, response);
					return;
				}
				((HttpServletResponse) response).sendRedirect("home");
				return;
			}else if(papel.equals("Editor")){
					if(URI.endsWith("listarUsuario") || URI.endsWith("apagarUsuario") || URI.endsWith("alterarUsuarioForm") ||
						URI.endsWith("alterarUsuario") || URI.endsWith("secaoFormulario") || URI.endsWith("cadastrarSecao") 
						|| URI.endsWith("listarSecao") || URI.endsWith("noticiaFormulario") || 
						URI.endsWith("apagarSecao") || URI.endsWith("alterarSecaoUsuarioFormulario") || URI.endsWith("alterarSecao") ||
						URI.endsWith("apagarNoticia") || URI.endsWith("alterarNoticiaForm") || URI.endsWith("alterarNoticia") || 
						URI.endsWith("inserirComentario") || URI.endsWith("apagarComentario") ||
						URI.endsWith("ofertar") || URI.endsWith("alterarClassificadoForm") || URI.endsWith("alterarClassificado") || 
						URI.endsWith("cadastrarClassificado") || URI.endsWith("classificadoFormulario") || URI.endsWith("apagarClassificado")
						|| URI.endsWith("alterarPapelForm") || URI.endsWith("alterarPapel")){
					chain.doFilter(request, response);
					return;
				}
				((HttpServletResponse) response).sendRedirect("home");
				return;
			}
			else{
				((HttpServletResponse) response).sendRedirect("home");
				return;
			}
		}
		else{
			((HttpServletResponse) response).sendRedirect("home");
			return;
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		
	}

}
