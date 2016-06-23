package br.ufc.Controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import br.ufc.DAO.ISecao;
import br.ufc.Model.Secao;

@Transactional
@Controller
public class SecaoController {

	public SecaoController() {
		// TODO Auto-generated constructor stub
	}

	@Autowired
	@Qualifier(value="sDAO")
	private ISecao sDAO;
	
	@RequestMapping("/secaoFormulario")
	public String secaoTela(){
		return "secao/secao_cadastro";
	}
	
	@RequestMapping("/cadastrarSecao")
	public String cadastrar(@Valid Secao secao, BindingResult result){
		if(result.hasFieldErrors("titulo"))
			return "redirect:cadastrarSecao";
		this.sDAO.inserir(secao);
		return "redirect:listarSecao";
	}
	
	@RequestMapping("/listarSecao")
	public String listarSecao(Model model){
		List<Secao> secoes = sDAO.listar();
		model.addAttribute("secoes", secoes);
		return "secao/listar_secoes";
	}
	
	/*
	@RequestMapping("/listarSecaoMenu")
	public String listarSecaoMenu(Model model){
		List<Secao> secoes = sDAO.listar();
		model.addAttribute("secoes", secoes);
		return "home";
	}
	*/
	
	@RequestMapping("/apagarSecao")
	public String apagar(Long id_secao){
		this.sDAO.apagar(id_secao);
		return "redirect:listarSecao";
	}
	
	@RequestMapping("/alterarSecaoForm")
	public String alterarUsuarioForm(Long id, Model model){
		Secao secao = this.sDAO.recuperar(id);
		model.addAttribute("secao", secao);
		return "secao/alterar_secao_form";
	}
	
	@RequestMapping("/alterarSecao")
	public String alterarUsuario(Secao secao){
		this.sDAO.alterar(secao);
		return "redirect:listarSecao";
	}
}
