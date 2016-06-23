package br.ufc.DAO;

import java.util.List;

import br.ufc.Model.Usuario;

public interface IUsuario {

	
	public Usuario recuperar(Long id);
	
	public Usuario recuperar(String login);
	
	public void inserir(Usuario usuario);
	
	public void alterar(Usuario usuario);
	
	public void apagar(Long id);
	
	public List<Usuario> listar(String nome);
	
	public void addPapel(Long id, Long id_papel);
	
	public boolean buscar_user_paper(Long id_user, String id_paper);
}
