package br.ufc.Model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

@Entity(name="classificado")
public class Classificado {

	@Id
	@Column(name="ID_CLASSIFICADO",nullable=false)
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id_classificado;
	
	@Column(nullable=false)
	private String titulo;
	
	@Lob
	@Column(nullable=false)
	private String texto;
	
	@Column(nullable=false)
	private Double preco;
	
	@Column(nullable=false)
	private String telefone;
	
	@Column(name="MAIOR_LANCE")
	private Double melhor_oferta;
	
	@Column(nullable=false)
	private Date data_oferta;
	
	@Column(name="ID_USUARIO", insertable=false, updatable=false, nullable=false)
	private Long id_autor;
	
	@Column(name="ID_MAIOR_LANCE")
	private Long id_maior_autor;

	@ManyToOne(optional=false, cascade=CascadeType.ALL)
	@JoinColumn(name="ID_USUARIO", referencedColumnName="ID_USUARIO")
	private Usuario user;
	
	public Long getId_classificado() {
		return id_classificado;
	}

	public void setId_classificado(Long id_classificado) {
		this.id_classificado = id_classificado;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public Double getMelhor_oferta() {
		return melhor_oferta;
	}

	public void setMelhor_oferta(Double melhor_oferta) {
		this.melhor_oferta = melhor_oferta;
	}

	public Date getData_oferta() {
		return data_oferta;
	}

	public void setData_oferta(Date data_oferta) {
		this.data_oferta = data_oferta;
	}

	public Long getId_autor() {
		return id_autor;
	}

	public void setId_autor(Long id_autor) {
		this.id_autor = id_autor;
	}

	public Long getId_maior_autor() {
		return id_maior_autor;
	}

	public void setId_maior_autor(Long id_maior_autor) {
		this.id_maior_autor = id_maior_autor;
	}

	public Usuario getUser() {
		return user;
	}

	public void setUser(Usuario user) {
		this.user = user;
	}
	
}
