package br.ufc.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import br.ufc.DAO.IComentario;
import br.ufc.DAO.INoticia;
import br.ufc.DAO.IUsuario;
import br.ufc.Model.Comentario;
import br.ufc.Model.Noticia;
import br.ufc.Model.Usuario;

@Transactional
@Controller
public class ComentarioController {

	public ComentarioController() {
		// TODO Auto-generated constructor stub
	}
	
	@Autowired
	@Qualifier(value="cDAO")
	private IComentario cDAO;
	
	@Autowired
	@Qualifier(value="nDAO")
	private INoticia nDAO;
	
	@Autowired
	@Qualifier(value="uDAO")
	private IUsuario uDAO;
	

	@RequestMapping("/inserirComentario")
	public String inserirComentario(Long idnoticia, Long idusuario, Comentario comentario){
		Noticia notice = this.nDAO.recuperar(idnoticia);
		comentario.setNoticia_id(notice);
		Usuario user = this.uDAO.recuperar(idusuario);
		comentario.setUser_id(user);
		this.cDAO.inserir(comentario);
		return "redirect:listarNoticiaCom?id=" + notice.getId_noticia() +"#telaComentarios";
	}
	
	@RequestMapping("/apagarComentario")
	public String apagar(Long id, Long id_notice){
		this.cDAO.apagar(id);
		return "redirect:listarNoticiaCom?id=" + id_notice +"#telaComentarios";
			
	}
	
	
}
