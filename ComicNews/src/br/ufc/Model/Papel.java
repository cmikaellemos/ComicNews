package br.ufc.Model;


import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;


@Entity(name="papel")
public class Papel {

	@Id
	@Column(name="ID_PAPEL", nullable=false)
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id_papel;
	
	@Column(nullable=false)
	@NotNull(message="papel.papel.vazio")
	private String papel;

	
	@ManyToMany(mappedBy="usuario_papel", fetch=FetchType.EAGER)
	private List<Usuario> papel_usuario;
	
	

	public String getPapel() {
		return papel;
	}

	public void setPapel(String papel) {
		this.papel = papel;
	}

	public Long getId_papel() {
		return id_papel;
	}

	public void setId_papel(Long id_papel) {
		this.id_papel = id_papel;
	}

	public List<Usuario> getPapel_usuario() {
		return papel_usuario;
	}

	public void setPapel_usuario(List<Usuario> papel_usuario) {
		this.papel_usuario = papel_usuario;
	}

	
}
