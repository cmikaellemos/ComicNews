package br.ufc.DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.ufc.Model.Comentario;

@Repository
public class ComentarioDAO implements IComentario{

	public ComentarioDAO() {
		// TODO Auto-generated constructor stub
	}
	
	@PersistenceContext
	private EntityManager manager;

	@Override
	public void inserir(Comentario comentario) {
		manager.persist(comentario);
	}

	@Override
	public Comentario recuperar(Long id) {
		return manager.find(Comentario.class, id);
	}
	
	@Override
	public void apagar(Long id) {
		Comentario ref = this.recuperar(id);
		if(ref != null)
			manager.remove(ref);
		
	}

	@Override
	public void alterar(Comentario comentario) {
		manager.merge(comentario);
	}

	@Override
	public List<Comentario> listar() {
		return manager.createQuery("select c from comentario as c", Comentario.class).getResultList();
	}

	@Override
	public List<Comentario> listarNot(Long id) {
		String hql = "select c from comentario as c where ID_NOTICIA = :param_id order by ID_COMENTARIO desc";
		Query query = manager.createQuery(hql);
		return query.setParameter("param_id", id).getResultList();
	}

}
