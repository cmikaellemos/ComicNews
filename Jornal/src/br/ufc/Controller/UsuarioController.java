package br.ufc.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import br.ufc.DAO.ISecao;
import br.ufc.DAO.IUsuario;
import br.ufc.Model.Secao;
import br.ufc.Model.Usuario;

@Transactional
@Controller
public class UsuarioController {

	public UsuarioController() {
		// TODO Auto-generated constructor stub
	}
	
	@Autowired
	@Qualifier(value="uDAO")
	private IUsuario uDAO;
	
	@Autowired
	@Qualifier(value="sDAO")
	private ISecao sDAO;
	
	@RequestMapping("/listarUsuario")
	public String listar(Model model, String nome, Model modelN){
		List<Secao> secoes = sDAO.listar();
		modelN.addAttribute("secoes", secoes);
		List<Usuario> usuarios = uDAO.listar(nome);
		model.addAttribute("usuarios", usuarios);
		return "usuario/listar_usuario";
	}
	
	@RequestMapping("/verPerfil")
	public String verPerfil(Long id, Model model, Model modelN){
		Usuario ref = this.uDAO.recuperar(id);
		model.addAttribute("perfil", ref);
		List<Secao> secoes = sDAO.listar();
		modelN.addAttribute("secoes", secoes);
		return "usuario/ver_perfil";
	}
	@RequestMapping("/apagarUsuario")
	public String apagar(Long id){
		this.uDAO.apagar(id);
		return "redirect:listarUsuario";
	}
	
	@RequestMapping("/alterarUsuarioForm")
	public String alterarUsuarioForm(Long id, Model model, Model modelN){
		List<Secao> secoes = sDAO.listar();
		modelN.addAttribute("secoes", secoes);
		Usuario user = this.uDAO.recuperar(id);
		model.addAttribute("usuario", user);
		return "usuario/alterar_usuario_form";
	}
	
	@RequestMapping("/alterarUsuario")
	public String alterarUsuario(Usuario usuario){
		this.uDAO.alterar(usuario);
		return "redirect:listarUsuario";
	}

	@RequestMapping("/alterarPapelForm")
	public String alterarPapelForm(Long id, Model model, Model modelN){
		List<Secao> secoes = sDAO.listar();
		modelN.addAttribute("secoes", secoes);
		Usuario user = this.uDAO.recuperar(id);
		model.addAttribute("user", user);
		return "usuario/alterar_papel_form";
	}
	
	@RequestMapping("/alterarPapel")
	public String alterarPapel(Long id_user, Long id_papel){
		this.uDAO.addPapel(id_user, id_papel);
		return "redirect:listarUsuario";
	}
}
