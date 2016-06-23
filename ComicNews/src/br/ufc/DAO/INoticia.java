package br.ufc.DAO;

import java.util.Date;
import java.util.List;

import br.ufc.Model.Noticia;

public interface INoticia {

	public void inserir(Noticia noticia);
	
	public void alterar(Noticia noticia);
	
	public Noticia recuperar(String titulo, String subtitulo);
	
	public Noticia recuperar(Long id);
	
	public Noticia recuperar(String titulo);
	
	public void deletar(Long id);
	
	public List<Noticia> listar(Long id_secao);
	
	public List<Noticia> listar();
	
	public List<Noticia> listarTitulo(String titulo);
}
