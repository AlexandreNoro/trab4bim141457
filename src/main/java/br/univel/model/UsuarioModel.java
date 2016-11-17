package br.univel.model;

/**
 * Alexandre H. Noro
 * 14/11/2016
 * 15:30
 */

import java.io.Serializable;

/**
 * Essa Classe serializada será utilizada pelo Managed Beans para receber os dados que são informados no sistema.
 */
public class UsuarioModel implements Serializable {

	private static final long serialVersionUID = 1L;

	private String codigo; //variável codigo do tipo texto.
	private String usuario; //variável usuario do tipo texto.
	private String senha; //variável senha do tipo texto.


	/**
	 * Retorna um objeto do tipo codigo.
	 * @return
	 */
	public String getCodigo() {
		return codigo;
	}

	/**
	 * Passado o valor por parâmetro para o atributo da classe.
	 * @param codigo
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	/**
	 * Retorna um objeto do tipo usuario
	 * @return
	 */
	public String getUsuario() {
		return usuario;
	}

	/**
	 * Passado o valor por parâmetro para o atributo da classe.
	 * @param usuario
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	/**
	 * Retorna um objeto do tipo senha
	 * @return
	 */
	public String getSenha() {
		return senha;
	}

	/**
	 * Passado o valor por parâmetro para o atributo da classe.
	 * @param senha
	 */
	public void setSenha(String senha) {
		this.senha = senha;
	}

}