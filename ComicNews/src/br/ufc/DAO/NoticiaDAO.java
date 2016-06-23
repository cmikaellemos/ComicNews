package br.ufc.DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.ufc.Model.Noticia;

@Repository
public class NoticiaDAO implements INoticia{
	
	public NoticiaDAO() {
		// TODO Auto-generated constructor stub
	}
	
	@PersistenceContext
	private EntityManager manager;

	@Override
	public void inserir(Noticia noticia) {
		manager.persist(noticia);
	}

	@Override
	public Noticia recuperar(Long id) {
		return manager.find(Noticia.class, id);
	}
	
	@Override
	public void alterar(Noticia noticia) {
		manager.merge(noticia);
	}
	
	@Override
	public Noticia recuperar(String titulo, String subtitulo) {
		String hql = "select n from noticia as n where titulo like :param_titulo and subtitulo like :param_sub";
		Query query = manager.createQuery(hql);
		query.setParameter("param_titulo", titulo);
		Noticia ref = (Noticia) query.setParameter("param_sub", subtitulo).getSingleResult();
		if(ref != null){
			return ref;
		}
		return null;
	}

	@Override
	public Noticia recuperar(String titulo) {
		String hql = "select n from noticia as n where titulo like :param_titulo";
		Query query = manager.createQuery(hql);
		Noticia ref = (Noticia) query.setParameter("param_titulo", titulo);
		if(ref != null){
			return ref;
		}
		return null;
	}

	@Override
	public void deletar(Long id) {
		Noticia ref = this.recuperar(id);
		if(ref != null)
			manager.remove(ref);
	}

	@Override
	public List<Noticia> listar(Long id_secao) {
		String hql = "select n from noticia as n where ID_SECAO = :param_secao order by data desc";
		Query query = manager.createQuery(hql);
		return query.setParameter("param_secao", id_secao).getResultList();		
	}

	@Override
	public List<Noticia> listar() {
		return manager.createQuery("select n from noticia as n order by id_noticia desc").getResultList();
	}

	@Override
	public List<Noticia> listarTitulo(String titulo) {
		String hql = "select n from noticia as n where titulo like :param_titulo order by data desc";
		String novo_tit = "%" + titulo + "%";
		Query query = manager.createQuery(hql);
		return query.setParameter("param_titulo", novo_tit).getResultList();
	}

}
