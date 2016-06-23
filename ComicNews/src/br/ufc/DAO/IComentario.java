package br.ufc.DAO;

import java.util.List;

import br.ufc.Model.Comentario;

public interface IComentario {

	public void inserir(Comentario comentario);
	
	public void apagar(Long id);
	
	public void alterar(Comentario comentario);
	
	public Comentario recuperar(Long id);
	
	public List<Comentario> listar();
	
	public List<Comentario> listarNot(Long id);
}
