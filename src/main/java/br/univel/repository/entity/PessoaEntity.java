package br.univel.repository.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 * @author Alexandre H. Noro
 * @data 17 de nov de 2016 as 20:24:58
 */

@Entity // Declaração da Entidade
@Table(name = "tb_pessoa") // Nome da tabela no banco de dados


/**
 * Responsável por retornar todos os registros cadastrados no nosso banco de dados.
 */
@NamedQueries({

	@NamedQuery(name = "PessoaEntity.findAll",query= "SELECT p FROM PessoaEntity p")

})

/**
 * Classe com os métodos e annotations do objeto Pessoa.
 */
public class PessoaEntity {

	@Id // Identifica que essa é a primary key na tabela
	@GeneratedValue // Sempre que for inserido algo no banco de dados, será gerado uma chave.
	@Column(name = "id_pessoa") // Nome de uma coluna no banco de dados referente ao ID da pessoa
	private Integer codigo;

	@Column(name = "nm_pessoa") // Nome de uma coluna no banco de dados referente ao nome da pessoa.
	private String nome;

	@Column(name = "fl_sexo") // Nome de uma coluna no banco de dados referente ao sexo.
	private String sexo;

	@Column(name = "dt_cadastro") // Nome de uma coluna no banco de dados referente a data do cadastro.
	private LocalDateTime dataCadastro;

	@Column(name = "ds_email") // Nome de uma coluna no banco de dados referente ao email.
	private String email;

	@Column(name = "ds_endereco") // Nome de uma coluna no banco de dados referente ao endereco.
	private String endereco;

	@Column(name = "fl_origemCadastro") // Nome de uma coluna no banco de dados referente a origem do cadastro.
	private String origemCadastro;

	@OneToOne // Identifica que é uma relação um para um
	@JoinColumn(name = "id_usuario_cadastro") // Nome de uma coluna no banco de dados referente a ligação das tabelas.

	private UsuarioEntity usuarioEntity;

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
	 * Passado o valor de "dataCadastro" por parâmetro para o atributo da classe.
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
	 * Passado o valor de "origemCadastro" por parâmetro para o atributo da classe.
	 *
	 * @param origemCadastro
	 */
	public void setOrigemCadastro(String origemCadastro) {
		this.origemCadastro = origemCadastro;
	}

	/**
	 * Retorna um objeto do tipo usuarioEntity
	 *
	 * @return
	 */
	public UsuarioEntity getUsuarioEntity() {
		return usuarioEntity;
	}

	/**
	 * Passado o valor de "usuarioEntity" por parâmetro para o atributo da classe
	 *
	 * @param usuarioEntity
	 */
	public void setUsuarioEntity(UsuarioEntity usuarioEntity) {
		this.usuarioEntity = usuarioEntity;
	}

}