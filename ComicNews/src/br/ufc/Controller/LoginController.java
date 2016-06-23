package br.ufc.Controller;


import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import br.ufc.DAO.ISecao;
import br.ufc.DAO.IUsuario;
import br.ufc.Model.Secao;
import br.ufc.Model.Usuario;
import br.ufc.Util.ArquivoUtil;

@Transactional
@Controller
public class LoginController {

	public LoginController() {
		// TODO Auto-generated constructor stub
	}

	@Autowired
	@Qualifier(value="uDAO")
	private IUsuario uDAO;
	
	@Autowired
	@Qualifier(value="sDAO")
	private ISecao sDAO;
	
	@Autowired
	private ServletContext servletContext;
	
	public String md5(String senha) throws NoSuchAlgorithmException, UnsupportedEncodingException{
		MessageDigest algorithm = MessageDigest.getInstance("MD5");
		byte messageDigest[] = algorithm.digest(senha.getBytes("UTF-8"));
		
		StringBuilder hexString = new StringBuilder();
		for (byte b : messageDigest) {
		  hexString.append(String.format("%02X", 0xFF & b));
		}
		String md5_senha = hexString.toString();
		return md5_senha;
	}
	
	@RequestMapping("/loginFormulario")
	public String loginTela(){
		return "login_tela";
	}
	
	@RequestMapping("/login")
	public String login(Usuario usuario, String papel, HttpSession session) throws NoSuchAlgorithmException, UnsupportedEncodingException{
		Usuario ref = this.uDAO.recuperar(usuario.getLogin());
		if(ref != null){
			if(usuario.getSenha().length() < 6)
				return "redirect:loginFormulario";
			String senha = md5(usuario.getSenha());
			if((ref.getSenha().equals(senha)) && (uDAO.buscar_user_paper(ref.getId_usuario(), papel) == true)){
				ref.setPapel_atual(papel);
				session.setAttribute("leitor_logado", ref);
				return "redirect:/";
			}			
		}
		return "redirect:/";
	}
	
	@RequestMapping("/logout")
	public String logout(HttpSession session){
		session.invalidate();
		return "redirect:/";
	}
	
	@RequestMapping("/cadastroFormulario")
	public String cadastroTela(Model model){
		List<Secao> secoes = sDAO.listar();
		model.addAttribute("secoes", secoes);
		return "cadastro_tela";
	}
	
	@RequestMapping("/cadastrar")
	public String cadastrar(Usuario usuario, BindingResult result, @RequestParam(value="imagem", required=false) MultipartFile imagem) 
							throws NoSuchAlgorithmException, UnsupportedEncodingException{
		
		if(result.hasFieldErrors("nome"))
			return "redirect:cadastroFormulario";
		if(usuario.getSenha().length() < 6)
			return "redirect:cadastroFormulario";
		
		String aux = md5(usuario.getSenha());
		usuario.setSenha(aux);
		
		String path = servletContext.getRealPath("/")+"WEB-INF/_resources/_imagens/_usuarios/" + usuario.getLogin() + ".png";
		ArquivoUtil.salvarImagem(path, imagem);
		
		this.uDAO.inserir(usuario); 
		
		return "redirect:/";
	}
}
