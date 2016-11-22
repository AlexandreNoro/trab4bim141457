package br.univel.pessoa.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Produces;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.univel.model.PessoaModel;
import br.univel.repository.PessoaRepository;

/**
 * @author Alexandre H. Noro
 * @data 21 de nov de 2016 as 11:20:30
 */

@Named(value = "consultarPessoaController") // anotation que transforma a classe
											// em um bean gerenciado pelo CDI.

@ViewScoped // annotation em que o bean � mantido at� a aplica��o navegar para
			// outra p�gina.

/**
 * Classe com m�todos para consulta de pessoas e inje��o de dependencias nos
 * objetos da classe.
 */
public class ConsultarPessoaController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	transient private PessoaModel pessoaModel;

	@Produces
	private List<PessoaModel> pessoas;

	@Inject
	transient private PessoaRepository pessoaRepository;

	/**
	 * Retorna uma lista de pessoas
	 *
	 * @return
	 */
	public List<PessoaModel> getPessoas() {
		return pessoas;
	}

	/**
	 * Passado o valor da lista por par�metro para o atributo da classe.
	 *
	 * @param pessoas
	 */
	public void setPessoas(List<PessoaModel> pessoas) {
		this.pessoas = pessoas;
	}

	/**
	 * Retorna um objeto do tipo pessoaModel.
	 *
	 * @return
	 */
	public PessoaModel getPessoaModel() {
		return pessoaModel;
	}

	/**
	 * Passado o valor de "pessoaModel" por par�metro para o atributo da classe.
	 *
	 * @param pessoaModel
	 */
	public void setPessoaModel(PessoaModel pessoaModel) {
		this.pessoaModel = pessoaModel;
	}

	/***
	 * Realiza o carregamento de pessoas cadastradas no banco.
	 */
	@PostConstruct
	public void init() {

		// Retorna as pessoas cadastradas
		this.pessoas = pessoaRepository.GetPessoas();
	}

	/***
	 * M�todo que realiza o carregamento das informa��es de uma pessoa para ser editada
	 *
	 * @param pessoaModel
	 */
	public void Editar(PessoaModel pessoaModel) {

		// Vai usar apenas a primeira letra do sexo para mostrar no campo
		pessoaModel.setSexo(pessoaModel.getSexo().substring(0, 1));

		this.pessoaModel = pessoaModel;

	}

	/***
	 * M�todo que realiza a atualiza��o do registro que foi alterado
	 */
	public void AlterarRegistro() {

		this.pessoaRepository.AlterarRegistro(this.pessoaModel);

		// Recarrega os registros
		this.init();
	}

	/***
	 * M�todo que exclui um registro
	 *
	 * @param pessoaModel
	 */
	public void ExcluirPessoa(PessoaModel pessoaModel) {

		// Realiza a exclus�o da pessoa do banco de dados
		this.pessoaRepository.ExcluirRegistro(pessoaModel.getCodigo());

		// Remove a pessoa da lista e assim que � removida da lista o datatable � atualizado
		this.pessoas.remove(pessoaModel);

	}

}