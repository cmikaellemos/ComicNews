package br.ufc.DAO;

import java.util.List;

import br.ufc.Model.Secao;

public interface ISecao {

	public void inserir(Secao secao);
	
	public void alterar(Secao secao);
	
	public Secao recuperar(Long id);
	
	public Secao recuperar(String nome);
	
	public void apagar(Long id);
	
	public List<Secao> listar();
	
}
