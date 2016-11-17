package br.univel.model;

/**
 * Alexandre H. Noro
 * 14/11/2016
 * 15:30
 */

import java.io.Serializable;

/**
 * Essa Classe serializada ser� utilizada pelo Managed Beans para receber os dados que s�o informados no sistema.
 */
public class UsuarioModel implements Serializable {

	private static final long serialVersionUID = 1L;

	private String codigo; //vari�vel codigo do tipo texto.
	private String usuario; //vari�vel usuario do tipo texto.
	private String senha; //vari�vel senha do tipo texto.


	/**
	 * Retorna um objeto do tipo codigo.
	 * @return
	 */
	public String getCodigo() {
		return codigo;
	}

	/**
	 * Passado o valor por par�metro para o atributo da classe.
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
	 * Passado o valor por par�metro para o atributo da classe.
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
	 * Passado o valor por par�metro para o atributo da classe.
	 * @param senha
	 */
	public void setSenha(String senha) {
		this.senha = senha;
	}

}