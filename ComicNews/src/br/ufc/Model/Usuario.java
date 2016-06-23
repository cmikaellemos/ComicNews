package br.ufc.Model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity(name="usuario")
public class Usuario {

	@Id
	@Column(name="ID_USUARIO", nullable=false)
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id_usuario;
	
	@Column(nullable=false)
	@NotNull(message="usuario.nome.vazio")
	private String nome;
	
	@Column(nullable=false)
	@NotNull(message="usuario.emails.vazio")
	private String email;
	
	@Column(nullable=false,unique=true)
	@Size(min=3, message="{usuario.login.min}")
	@NotNull(message="usuario.login.vazio")
	private String login;
	
	@Column(nullable=false)
	@NotNull(message="usuario.senha.vazio")
	@Size(min=8, message="{usuario.senha.min}")
	private String senha;
	
	private String papel_atual;
	
	@OneToMany(mappedBy="user", targetEntity=Classificado.class, fetch=FetchType.EAGER)
	private List<Classificado> classificados;
	
	@OneToMany(mappedBy="user", targetEntity=Noticia.class, fetch=FetchType.EAGER)
	private List<Noticia> noticia;
	
	@OneToMany(mappedBy="user_id", targetEntity=Comentario.class, fetch=FetchType.EAGER)
	private List<Comentario> comentario_autor;
	
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name="PAPEL_USUARIO", joinColumns=@JoinColumn(name="ID_USUARIO",
								referencedColumnName="ID_USUARIO"),
		inverseJoinColumns=@JoinColumn(name="PAPEL_ID",
									   referencedColumnName="ID_PAPEL"))
	private List<Papel> usuario_papel;


	public Long getId_usuario() {
		return id_usuario;
	}

	public void setId_usuario(Long id_usuario) {
		this.id_usuario = id_usuario;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public List<Classificado> getClassificados() {
		return classificados;
	}

	public void setClassificados(List<Classificado> classificados) {
		this.classificados = classificados;
	}

	public List<Noticia> getNoticia() {
		return noticia;
	}

	public void setNoticia(List<Noticia> noticia) {
		this.noticia = noticia;
	}

	public List<Comentario> getComentario_autor() {
		return comentario_autor;
	}

	public void setComentario_autor(List<Comentario> comentario_autor) {
		this.comentario_autor = comentario_autor;
	}

	public List<Papel> getUsuario_papel() {
		return usuario_papel;
	}

	public void setUsuario_papel(List<Papel> usuario_papel) {
		this.usuario_papel = usuario_papel;
	}

	public String getPapel_atual() {
		return papel_atual;
	}

	public void setPapel_atual(String papel_atual) {
		this.papel_atual = papel_atual;
	}
}
