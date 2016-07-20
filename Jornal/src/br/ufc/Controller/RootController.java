package br.ufc.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import br.ufc.DAO.IClassificado;
import br.ufc.DAO.INoticia;
import br.ufc.DAO.ISecao;
import br.ufc.Model.Noticia;
import br.ufc.Model.Secao;

@Controller
public class RootController {

	@Autowired
	@Qualifier(value="sDAO")
	private ISecao sDAO;
	
	@Autowired
	@Qualifier(value="nDAO")
	private INoticia nDAO;
	
	@Autowired
	@Qualifier(value="claDAO")
	private IClassificado claDAO;
	
	public RootController() {
		// TODO Auto-generated constructor stub
	}
	
	@RequestMapping("/home")
	public String homeFilter(Model model, Model modelN){
		List<Secao> secoes = sDAO.listar();
		modelN.addAttribute("secoes", secoes);
		List<Noticia> noticias = nDAO.listar();
		model.addAttribute("noticias", noticias);
		return "home";
	}
	
	@RequestMapping("/")
	public String home(Model model, Model modelN){
		List<Secao> secoes = sDAO.listar();
		modelN.addAttribute("secoes", secoes);
		List<Noticia> noticias = nDAO.listar();
		model.addAttribute("noticias", noticias);
		return "home";
	}
}
