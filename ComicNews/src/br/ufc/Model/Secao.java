package br.ufc.Model;


import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

@Entity(name="secao")
public class Secao {

	@Id
	@Column(name="ID_SECAO", nullable=false)
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id_secao;
	
	@Column(nullable=false)
	@NotNull(message="secao.titulo.vazio")
	private String titulo;
	
	@Column(nullable=false)
	@NotNull(message="secao.descricao.vazio")
	private String descricao;
	
	@OneToMany(mappedBy="session", targetEntity=Noticia.class, fetch=FetchType.EAGER)
	private List<Noticia> noticia_secao;


	public Long getId_secao() {
		return id_secao;
	}

	public void setId_secao(Long id_secao) {
		this.id_secao = id_secao;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<Noticia> getNoticia_secao() {
		return noticia_secao;
	}

	public void setNoticia_secao(List<Noticia> noticia_secao) {
		this.noticia_secao = noticia_secao;
	}
	
}
