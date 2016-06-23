package br.ufc.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import br.ufc.DAO.IUsuario;
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
	
	@RequestMapping("/listarUsuario")
	public String listar(Model model, String nome){
		List<Usuario> usuarios = uDAO.listar(nome);
		model.addAttribute("usuarios", usuarios);
		return "usuario/listar_usuario";
	}
	
	@RequestMapping("/apagarUsuario")
	public String apagar(Long id){
		this.uDAO.apagar(id);
		return "redirect:listarUsuario";
	}
	
	@RequestMapping("/alterarUsuarioForm")
	public String alterarUsuarioForm(Long id, Model model){
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
	public String alterarPapelForm(Long id, Model model){
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
