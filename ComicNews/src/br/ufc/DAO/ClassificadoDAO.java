package br.ufc.DAO;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.ufc.Model.Classificado;

@Repository
public class ClassificadoDAO implements IClassificado{

	public ClassificadoDAO() {
		// TODO Auto-generated constructor stub
	}
	
	@PersistenceContext
	private EntityManager manager;

	@Override
	public void inserir(Classificado classificado) {
		manager.persist(classificado);
	}
	
	@Override
	public Classificado recuperar_id(Long id) {
		return manager.find(Classificado.class, id);
	}

	@Override
	public void alterar(Classificado classificado) {
		Classificado ref = this.recuperar_id(classificado.getId_classificado());
		classificado.setData_oferta(ref.getData_oferta());
		classificado.setId_maior_autor(ref.getId_maior_autor());
		classificado.setMelhor_oferta(ref.getMelhor_oferta());
		classificado.setUser(ref.getUser());
		manager.merge(classificado);
	}
	
	@Override
	public void apagar(Long id) {
		Classificado ref = this.recuperar_id(id);
		if(ref != null)
			manager.remove(ref);
	}

	@Override
	public List<Classificado> recuperar_autor(Long id) {
		String hql = "select c from classificado as c where ID_USUARIO = :param_id";
		Query query = manager.createQuery(hql);
		return query.setParameter("param_id", id).getResultList();
	}

	@Override
	public List<Classificado> listar(String t_ordem, String ordem, String titulo) {
		String hql = null;
		String ref = "%" + titulo + "%";
		if(ordem.equals("desc")){
			if(t_ordem.equals("preco"))
				hql = "select c from classificado as c where titulo like :param_titulo order by preco desc";
			else if(t_ordem.equals("data_oferta"))
				hql = "select c from classificado as c where titulo like :param_titulo order by data_oferta desc";
			else if(t_ordem.equals("MAIOR_LANCE"))
				hql = "select c from classificado as c where titulo like :param_titulo order by MAIOR_LANCE desc";
			Query query = manager.createQuery(hql);
			return query.setParameter("param_titulo", ref).getResultList();																																																																																																																																																																																																																																																																		
		}
		if(t_ordem.equals("preco"))
			hql = "select c from classificado as c where titulo like :param_titulo order by preco";
		else if(t_ordem.equals("data_oferta"))
			hql = "select c from classificado as c where titulo like :param_titulo order by data_oferta";
		else if(t_ordem.equals("MAIOR_LANCE"))
			hql = "select c from classificado as c where titulo like :param_titulo order by MAIOR_LANCE";
		Query query = manager.createQuery(hql);
		return query.setParameter("param_titulo", ref).getResultList();
	}

	@Override
	public List<Classificado> listar() {
		return manager.createQuery("select c from classificado as c order by ID_CLASSIFICADO desc").getResultList();
	}

	@Override
	public void alterarOffer(Classificado classificado) {
		Classificado ref = this.recuperar_id(classificado.getId_classificado());
		classificado.setData_oferta(new Date());
		classificado.setPreco(ref.getPreco());
		classificado.setTelefone(ref.getTelefone());
		classificado.setTexto(ref.getTexto());
		classificado.setTitulo(ref.getTitulo());
		classificado.setUser(ref.getUser());
		manager.merge(classificado);
	}

}
