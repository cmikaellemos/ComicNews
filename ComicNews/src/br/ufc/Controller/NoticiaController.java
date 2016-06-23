package br.ufc.Controller;

import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import br.ufc.DAO.IComentario;
import br.ufc.DAO.INoticia;
import br.ufc.DAO.ISecao;
import br.ufc.DAO.IUsuario;
import br.ufc.Model.Comentario;
import br.ufc.Model.Noticia;
import br.ufc.Model.Secao;
import br.ufc.Model.Usuario;
import br.ufc.Util.ArquivoUtil;

@Transactional
@Controller
public class NoticiaController {

	public NoticiaController() {
		// TODO Auto-generated constructor stub
	}
	
	@Autowired
	@Qualifier(value="nDAO")
	private INoticia nDAO;
	
	@Autowired
	@Qualifier(value="sDAO")
	private ISecao sDAO;
	
	@Autowired
	@Qualifier(value="uDAO")
	private IUsuario uDAO;
	
	@Autowired
	@Qualifier(value="cDAO")
	private IComentario cDAO;
	
	@Autowired
	private ServletContext servletContext;
	
	
	@RequestMapping("/noticiaFormulario")
	public String noticiaTela(Model model){
		List<Secao> secao = sDAO.listar();
		model.addAttribute("secoes", secao);
		return "noticia/noticia_cadastro";
	}
	
	@RequestMapping("/cadastrarNoticia")
	public String cadastrar(@Valid Noticia noticia, String secao_titulo, String login, BindingResult result,
							@RequestParam(value="imagem", required=false) MultipartFile imagem){
		if(result.hasFieldErrors("titulo"))
			return "redirect:cadastrarNoticia";
		Secao secao = sDAO.recuperar(secao_titulo);
		Usuario usuario = uDAO.recuperar(login);
		Date date = new Date();
		noticia.setData_noticia(date);
		noticia.setSession(secao);
		noticia.setUser(usuario);
		
		this.nDAO.inserir(noticia);
		
		noticia = this.nDAO.recuperar(noticia.getTitulo(), noticia.getSubtitulo());
		String path = servletContext.getRealPath("/")+"WEB-INF/_resources/_imagens/_noticias/" + noticia.getId_noticia() + ".png";
		ArquivoUtil.salvarImagem(path, imagem);
		
		return "redirect:listarNoticia";
	}
	
	@RequestMapping("/listarNoticiaSec")
	public String listarSecao(Long id_secao, Model model, Model models){
		List<Noticia> noticias = nDAO.listar(id_secao);
		model.addAttribute("noticias", noticias);
		List<Secao> secoes = sDAO.listar();
		models.addAttribute("secoes", secoes);
		return "noticia/listar_noticias";
	}
	
	@RequestMapping("/apagarNoticia")
	public String apagar(Long id){
		this.nDAO.deletar(id);
		return "redirect:listarNoticia";
	}
	
	@RequestMapping("/alterarNoticiaForm")
	public String alterarNoticiaForm(Long id, Model model, Model models){
		Noticia notice = this.nDAO.recuperar(id);
		model.addAttribute("noticia_form", notice);
		List<Secao> secoes = sDAO.listar();
		models.addAttribute("secoes", secoes);
		return "noticia/alterar_noticia_form";
	}
	
	@RequestMapping("/alterarNoticia")
	public String alterarNoticia(Noticia noticia, Long id_n, String texto_n, String titulo_n, String subtitulo_n){
		Noticia ref = this.nDAO.recuperar(id_n);
		noticia.setId_noticia(id_n);
		noticia.setTexto(texto_n);
		noticia.setTitulo(titulo_n);
		noticia.setSubtitulo(subtitulo_n);
		noticia.setData_noticia(ref.getData_noticia());
		noticia.setSession(ref.getSession());
		noticia.setUser(ref.getUser());
		noticia.setComentario_noticia(ref.getComentario_noticia());
		this.nDAO.alterar(noticia);
		return "redirect:listarNoticia";
	}

	@RequestMapping("/listarNoticiaCom")
	public String listarNoticiaCom(Long id, Model modelN, Model modelc, Model models){
		Noticia notice = this.nDAO.recuperar(id);
		modelN.addAttribute("noticia_com", notice);
		List<Comentario> comentarios = this.cDAO.listarNot(id);
		modelc.addAttribute("comentarios_not", comentarios);
		List<Secao> secoes = sDAO.listar();
		models.addAttribute("secoes", secoes);
		return "noticia/listar_noticia";
	}
	
	@RequestMapping("/listarNoticiaTitulo")
	public String buscarNoticias(String titulo, Model model, Model models){
		List<Noticia> noticias = nDAO.listarTitulo(titulo);
		model.addAttribute("noticias", noticias);
		List<Secao> secoes = sDAO.listar();
		models.addAttribute("secoes", secoes);
		return "noticia/listar_noticias";
	}
}
