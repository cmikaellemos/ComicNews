package br.ufc.Model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name="comentario")
public class Comentario {

	@Id
	@Column(name="ID_COMENTARIO", nullable=false)
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id_comentario;
	
	@Column(name="ID_NOTICIA", insertable=false, updatable=false, nullable=false)
	private Long id_noticia;
	
	@Column(name="ID_USUARIO", insertable=false, updatable=false, nullable=false)
	private Long id_autor;
	
	@Column(nullable=false)
	private String texto;
	
	@ManyToOne(optional=false)
	@JoinColumn(name = "ID_NOTICIA", referencedColumnName="ID_NOTICIA")
	private Noticia noticia_id;
	
	@ManyToOne(optional=false)
	@JoinColumn(name = "ID_USUARIO", referencedColumnName="ID_USUARIO")
	private Usuario user_id;

	public Long getId_comentario() {
		return id_comentario;
	}

	public void setId_comentario(Long id_comentario) {
		this.id_comentario = id_comentario;
	}

	public Long getId_noticia() {
		return id_noticia;
	}

	public void setId_noticia(Long id_noticia) {
		this.id_noticia = id_noticia;
	}

	public Long getId_autor() {
		return id_autor;
	}

	public void setId_autor(Long id_autor) {
		this.id_autor = id_autor;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public Noticia getNoticia_id() {
		return noticia_id;
	}

	public void setNoticia_id(Noticia noticia_id) {
		this.noticia_id = noticia_id;
	}

	public Usuario getUser_id() {
		return user_id;
	}

	public void setUser_id(Usuario user_id) {
		this.user_id = user_id;
	}
	
}
