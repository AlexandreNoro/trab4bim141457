package br.univel.entity;

/**
 * Alexandre H. Noro
 * 14/11/2016
 * 15:21
 */

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Table(name = "tb_usuario")//Nome da tabela no banco de dados
@Entity //Declara��o da Entidade
@NamedQuery(name = "UsuarioEntity.findUser", query = "SELECT u FROM UsuarioEntity u WHERE u.usuario = :usuario AND u.senha = :senha")//Realiza autentica��o do usu�rio no banco de dados, filtrando por usu�rio e senha.
/**
 * Classe com os m�todos e annotations do objeto usuario.
 *
 */
public class UsuarioEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id //Identifica que essa � a primary key na tabela
	@GeneratedValue //Sempre que for inserido algo no banco de dados, ser� gerado uma chave.
	@Column(name = "id_usuario") //Nome de uma coluna no banco de dados referente ao ID do usu�rio.
	private String codigo; //vari�vel codigo do tipo texto.

	@Column(name = "ds_login")//Nome de uma coluna no banco de dados  referente ao login.
	private String usuario;//vari�vel usuario do tipo texto

	@Column(name = "ds_senha")//Nome de uma coluna no banco de dados referente a senha
	private String senha;//variavel senha do tipo texto.


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
	 * Retorna um objeto do tipo usuario.
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