/**
 * 
 */
package com.test.model.pojo;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author rsouza
 *
 */

@XmlRootElement
public final class UsuarioTO {

	private Long id;
	
	private String nome;

	private String email;

	private String telefone;

	private String sexo;

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * @param nome the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the telefone
	 */
	public String getTelefone() {
		return telefone;
	}

	/**
	 * @param telefone the telefone to set
	 */
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	/**
	 * @return the sexo
	 */
	public String getSexo() {
		return sexo;
	}

	/**
	 * @param sexo the sexo to set
	 */
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public UsuarioTO() {
	}

	public UsuarioTO(Long id, String nome, String email, String telefone, String sexo) {
		this(nome, email, telefone, sexo);
		this.id = id;
	
	}

	public UsuarioTO(String nome, String email, String telefone, String sexo) {
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
		builder.append("UsuarioTO [id=");
		builder.append(id);
		builder.append(", nome=");
		builder.append(nome);
		builder.append(", email=");
		builder.append(email);
		builder.append(", telefone=");
		builder.append(telefone);
		builder.append(", sexo=");
		builder.append(sexo);
		builder.append("]");
		return builder.toString();
	}

}
