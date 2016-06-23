package br.ufc.Controller;

import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import br.ufc.DAO.IClassificado;
import br.ufc.DAO.IUsuario;
import br.ufc.Model.Classificado;
import br.ufc.Model.Usuario;
import br.ufc.Util.ArquivoUtil;

@Transactional
@Controller
public class ClassificadoController {

	public ClassificadoController() {
		// TODO Auto-generated constructor stub
	}

	@Autowired
	@Qualifier(value="claDAO")
	private IClassificado claDAO;
	
	@Autowired
	@Qualifier(value="uDAO")
	private IUsuario uDAO;
	
	@Autowired
	private ServletContext servletContext;
	
	@RequestMapping("/classificadoFormulario")
	public String classTela(){
		return "classificado/classificado_cadastro";
	}
	
	@RequestMapping("/cadastrarClassificado")
	public String cadastrar(Classificado classificado, Long id_user, String titulo_n, String texto_n, 
							Double preco_n, String telefone_n, BindingResult result,
							@RequestParam(value="imagem", required=false) MultipartFile imagem ){
		if(result.hasFieldErrors("titulo"))
			return "redirect:cadastrarClassificadoFormulario";
		Usuario usuario = this.uDAO.recuperar(id_user);
		Date data = new Date();
		classificado.setUser(usuario);
		classificado.setData_oferta(data);
		classificado.setPreco(preco_n);
		classificado.setMelhor_oferta(0.0);
		classificado.setTelefone(telefone_n);
		classificado.setTexto(texto_n);
		classificado.setTitulo(titulo_n);
		
		String path = servletContext.getRealPath("/")+"WEB-INF/_resources/_imagens/_classificados/" + titulo_n + ".png";
		ArquivoUtil.salvarImagem(path, imagem);
		
		this.claDAO.inserir(classificado);
		return "redirect:listarClassificados";
	}
	
	@RequestMapping("/listarClassificados")
	public String listarClassificados(Model model, String tipo, String titulo_n, String ordem){
		if((tipo != null) && (ordem != null)){
			List<Classificado> ref = this.claDAO.listar(tipo, ordem, titulo_n);
			model.addAttribute("classificados_listar", ref);
		}
		else{
			List<Classificado> ref = this.claDAO.listar();
			model.addAttribute("classificados_listar", ref);
		}
		return "classificado/listar_classificados";
	}
	
	@RequestMapping("/alterarClassificadoForm")
	public String alterarClassificadoForm(Long id, Model model){
		Classificado ref = this.claDAO.recuperar_id(id);
		model.addAttribute("classificado_alterar", ref);
		return "classificado/alterar_classificado_form";
	}
	
	@RequestMapping("/alterarClassificado")
	public String alterarClassificado(Classificado classificado, Long id_user, String titulo_n, String texto_n, 
										Double preco_n, String telefone_n){
		Classificado ref = this.claDAO.recuperar_id(id_user);
		classificado.setId_autor(ref.getId_autor());
		classificado.setId_classificado(ref.getId_classificado());
		classificado.setPreco(preco_n);
		classificado.setTelefone(telefone_n);
		classificado.setTexto(texto_n);
		classificado.setTitulo(titulo_n);
		claDAO.alterar(classificado);
		return "redirect:listarClassificados";
	}
	
	@RequestMapping("/ofertar")
	public String ofertar(Classificado classificado, Long id_cla, Long id_user, Double preco_n){
		Classificado ref = this.claDAO.recuperar_id(id_cla);
		classificado.setId_autor(ref.getId_autor());
		classificado.setId_classificado(id_cla);
		classificado.setData_oferta(ref.getData_oferta());
		if(preco_n > ref.getMelhor_oferta()){
			classificado.setMelhor_oferta(preco_n);
			classificado.setId_maior_autor(id_user);
			claDAO.alterarOffer(classificado);
		}
		return "redirect:listarClassificado?id_class=" + id_cla;
	}
	
	@RequestMapping("/listarClassificado")
	public String listarClassificado(Long id_class, Model model, Model modelu){
		Classificado classificado = this.claDAO.recuperar_id(id_class);
		model.addAttribute("classificado", classificado);
		if(classificado.getId_maior_autor() != null){	
			Usuario user = this.uDAO.recuperar(classificado.getId_maior_autor());
			modelu.addAttribute("user", user);
		}
		return "classificado/ver_classificado";
	}
	
	@RequestMapping("/apagarClassificado")
	public String apagar(Long id){
		this.claDAO.apagar(id);
		return "redirect:listarClassificados";
	}
}
