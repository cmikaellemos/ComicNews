package br.ufc.Model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity(name="noticia")
public class Noticia {

	@Id
	@Column(name="ID_NOTICIA", nullable=false)
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id_noticia;
	
	@Column(nullable=false)
	private String titulo;
	
	@Column(nullable=false)
	private String subtitulo;
	
	@Column(nullable=false, name="data")
	private Date data_noticia;
	
	@Column(name="ID_USUARIO", insertable=false, updatable=false, nullable=false)
	private Long id_autor;
	
	@Column(name="ID_SECAO", insertable=false, updatable=false, nullable=false)
	private Long id_secao; 
	
	@ManyToOne(optional=false, cascade=CascadeType.ALL)
	@JoinColumn(name="ID_SECAO", referencedColumnName="ID_SECAO")
	private Secao session; 
	
	@ManyToOne(optional=false, cascade=CascadeType.ALL)
	@JoinColumn(name="ID_USUARIO", referencedColumnName="ID_USUARIO")	
	private Usuario user;
	
	@OneToMany(mappedBy="noticia_id", targetEntity=Comentario.class, fetch=FetchType.EAGER)
	private List<Comentario> comentario_noticia;
	
	@Lob
	@Column(nullable=false)
	private String texto;

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getSubtitulo() {
		return subtitulo;
	}

	public void setSubtitulo(String subtitulo) {
		this.subtitulo = subtitulo;
	}

	public Date getData_noticia() {
		return data_noticia;
	}

	public void setData_noticia(Date data_noticia) {
		this.data_noticia = data_noticia;
	}

	public Long getId_secao() {
		return id_secao;
	}

	public void setId_secao(Long id_secao) {
		this.id_secao = id_secao;
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

	public List<Comentario> getComentario_noticia() {
		return comentario_noticia;
	}

	public void setComentario_noticia(List<Comentario> comentario_noticia) {
		this.comentario_noticia = comentario_noticia;
	}

	public Long getId_noticia() {
		return id_noticia;
	}

	public void setId_noticia(Long id_noticia) {
		this.id_noticia = id_noticia;
	}

	public Secao getSession() {
		return session;
	}

	public void setSession(Secao session) {
		this.session = session;
	}

	public Usuario getUser() {
		return user;
	}

	public void setUser(Usuario user) {
		this.user = user;
	}
}
