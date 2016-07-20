package br.ufc.DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.ufc.Model.Secao;

@Repository
public class SecaoDAO implements ISecao{

	public SecaoDAO() {
		// TODO Auto-generated constructor stub
	}
	
	@PersistenceContext
	private EntityManager manager;

	@Override
	public void inserir(Secao secao) {
		manager.persist(secao);
	}

	@Override
	public void alterar(Secao secao) {
		manager.merge(secao);
	}

	@Override
	public Secao recuperar(Long id) {
		return manager.find(Secao.class, id);
	}

	@Override
	public Secao recuperar(String nome) {
		String hql = "select s from secao as s where s.titulo = :param_nome";
		Query query = manager.createQuery(hql);
		Secao ref = (Secao) query.setParameter("param_nome", nome).getSingleResult();
		if(ref != null)
			return ref;
		return null;
	}

	@Override
	public void apagar(Long id) {
		Secao ref = this.recuperar(id);
		if(ref != null)
			manager.remove(ref);
	}

	@Override
	public List<Secao> listar() {
		return manager.createQuery("select s from secao as s", Secao.class).getResultList();
	}

}
