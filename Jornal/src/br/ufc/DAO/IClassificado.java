package br.ufc.DAO;

import java.util.List;

import br.ufc.Model.Classificado;

public interface IClassificado {
	
	public void inserir(Classificado classificado);
	
	public void alterar(Classificado classificado);
	
	public void alterarOffer(Classificado classificado);
	
	public void apagar(Long id);
	
	public Classificado recuperar_id(Long id);
	
	public List<Classificado> recuperar_autor(Long id);
	
	public List<Classificado> listar(String t_ordem, String ordem, String titulo);
	
	public List<Classificado> listar();

}
