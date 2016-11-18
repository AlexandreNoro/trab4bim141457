package br.univel.model;

import java.time.LocalDateTime;

/**
 *
 * @author Alexandre H. Noro
 * @data 17 de nov de 2016 as 20:48:15
 */

/**
 * Classe que vai receber os valores dos campos que vamos informar na tela de
 * cadastro.
 */
public class PessoaModel {

	private Integer codigo;
	private String nome;
	private String sexo;
	private LocalDateTime dataCadastro;
	private String email;
	private String endereco;
	private String origemCadastro;
	private UsuarioModel usuarioModel;

	/**
	 * Retorna um objeto do tipo codigo.
	 *
	 * @return
	 */
	public Integer getCodigo() {
		return codigo;
	}

	/**
	 * Passado o valor de "codigo" por parâmetro para o atributo da classe.
	 *
	 * @param codigo
	 */
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	/**
	 * Retorna um objeto do tipo nome.
	 *
	 * @return
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * Passado o valor de "nome" por parâmetro para o atributo da classe.
	 *
	 * @param nome
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * Retorna um objeto do tipo sexo
	 *
	 * @return
	 */
	public String getSexo() {
		return sexo;
	}

	/**
	 * Passado o valor de "sexo" por parâmetro para o atributo da classe.
	 *
	 * @param sexo
	 */
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	/**
	 * Retorna um objeto do tipo dataCadastro.
	 *
	 * @return
	 */
	public LocalDateTime getDataCadastro() {
		return dataCadastro;
	}

	/**
	 * Passado o valor de "dataCadastro" por parâmetro para o atributo da
	 * classe.
	 *
	 * @param dataCadastro
	 */
	public void setDataCadastro(LocalDateTime dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	/**
	 * Retorna um objeto do tipo email.
	 *
	 * @return
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Passado o valor de "email" por parâmetro para o atributo da classe.
	 *
	 * @param email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Retorna um objeto do tipo endereco.
	 *
	 * @return
	 */
	public String getEndereco() {
		return endereco;
	}

	/**
	 * Passado o valor de "endereco" por parâmetro para o atributo da classe.
	 *
	 * @param endereco
	 */
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	/**
	 * Retorna um objeto do tipo origemCadastro.
	 *
	 * @return
	 */
	public String getOrigemCadastro() {
		return origemCadastro;
	}

	/**
	 * Passado o valor de "origemCadastro" por parâmetro para o atributo da
	 * classe.
	 *
	 * @param origemCadastro
	 */
	public void setOrigemCadastro(String origemCadastro) {
		this.origemCadastro = origemCadastro;
	}

	/**
	 * Retorna um objeto do tipo usuarioModel
	 *
	 * @return
	 */
	public UsuarioModel getUsuarioModel() {
		return usuarioModel;
	}

	/**
	 * Passado o valor de "usuarioModel" por parâmetro para o atributo da classe
	 *
	 * @param usuarioModel
	 */
	public void setUsuarioModel(UsuarioModel usuarioModel) {
		this.usuarioModel = usuarioModel;
	}

}
