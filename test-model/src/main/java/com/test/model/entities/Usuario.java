/**
 * 
 */
package com.test.model.entities;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.xml.bind.annotation.XmlRootElement;

import com.test.model.pojo.UsuarioTO;

/**
 * @author rsouza
 *
 */

@Entity
@Table(name = "USUARIOS")
@XmlRootElement
public final class Usuario {

	public static final int MAX_LENGTH_NOME = 40;
	public static final int MAX_LENGTH_EMAIL = 40;
	public static final int MAX_LENGTH_TELEFONE = 20;
	public static final int MAX_LENGTH_SEXO = 1;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "nome", nullable = false, length = Usuario.MAX_LENGTH_NOME)
	private String nome;

	@Column(name = "mail", nullable = false, length = Usuario.MAX_LENGTH_EMAIL)
	private String email;

	@Column(name = "telefone", nullable = false, length = Usuario.MAX_LENGTH_TELEFONE)
	private String telefone;

	@Column(name = "sexo", nullable = false, length = Usuario.MAX_LENGTH_SEXO)
	private String sexo;
	
	
	@Version
	private long version;
	
	public Usuario(UsuarioTO usuario) {
		this.id = usuario.getId();
		this.nome = usuario.getNome();
		this.email = usuario.getEmail();
		this.telefone = usuario.getTelefone();
		this.sexo = usuario.getSexo();
	}


	public Usuario(Long id, String nome, String email, String telefone, String sexo) {
		this(nome, email, telefone, sexo);
		this.id = id;
	
	}

	public Usuario(String nome, String email, String telefone, String sexo) {
		this.email = email;
		this.nome = nome;
		this.sexo = sexo;
		this.telefone = telefone;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Usuario [id=");
		builder.append(id);
		builder.append(", nome=");
		builder.append(nome);
		builder.append(", email=");
		builder.append(email);
		builder.append(", telefone=");
		builder.append(telefone);
		builder.append(", sexo=");
		builder.append(sexo);
		builder.append(", version=");
		builder.append(version);
		builder.append("]");
		return builder.toString();
	}
	

	
}
