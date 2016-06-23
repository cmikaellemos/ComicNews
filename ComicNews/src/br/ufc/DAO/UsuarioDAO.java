package br.ufc.DAO;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.ufc.Model.Noticia;
import br.ufc.Model.Papel;
import br.ufc.Model.Usuario;

@Repository
public class UsuarioDAO implements IUsuario{

	@PersistenceContext
	private EntityManager manager;
	
	public UsuarioDAO() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Usuario recuperar(Long id) {
		return manager.find(Usuario.class, id);
	}

	@Override
	public Usuario recuperar(String login) {
		String hql = "select u from usuario as u where u.login = :param_login";
		Query query = manager.createQuery(hql);
		Usuario user = (Usuario) query.setParameter("param_login", login).getSingleResult();
		if(user != null)
			return user;
		return null;
	}

	@Override
	public void inserir(Usuario usuario) {
		List<Papel> papeis = new ArrayList<Papel>();
		papeis.add(manager.find(Papel.class, 1L));
		usuario.setUsuario_papel(papeis);
		manager.persist(usuario);
	}

	@Override
	public void alterar(Usuario usuario){
		usuario.setClassificados(this.recuperar(usuario.getId_usuario()).getClassificados());
		usuario.setComentario_autor(this.recuperar(usuario.getId_usuario()).getComentario_autor());
		usuario.setUsuario_papel(this.recuperar(usuario.getId_usuario()).getUsuario_papel());
		manager.merge(usuario);
		
	}

	@Override
	public void apagar(Long id) {
		Usuario ref = this.recuperar(id);
		if(ref != null)
			manager.remove(ref);	
	}

	@Override
	public List<Usuario> listar(String nome) {
		if(nome != null){
			String hql = "select u from usuario u where nome like :param_nome order by nome";
			String ref = "%" + nome + "%";
			Query query = manager.createQuery(hql);
			return query.setParameter("param_nome", ref).getResultList();
		}
		return manager.createQuery("select u from usuario as u", Usuario.class).getResultList();
	}
	
	public boolean buscar_user_paper(Long id_user, String id_paper){
		Usuario ref = this.recuperar(id_user);
		List<Papel> papeis = ref.getUsuario_papel();
		for (Papel aux : papeis) {
			if((aux.getPapel()).equals(id_paper)){
				return true;
			}
		}
		return false;
	}

	@Override
	public void addPapel(Long id, Long id_papel) {
		Usuario ref = this.recuperar(id);
		List<Papel> papeis = ref.getUsuario_papel();
		for (Papel papel : papeis) {
			if(papel.getId_papel() == id_papel)
				return;
		}
		papeis.add(manager.find(Papel.class, id_papel));
		ref.setUsuario_papel(papeis);
		manager.merge(ref);
	}

}
