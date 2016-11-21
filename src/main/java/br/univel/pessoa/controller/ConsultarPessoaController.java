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

@Named(value="consultarPessoaController") //anotation que transforma a classe em um bean gerenciado pelo CDI.
@ViewScoped //annotation em que o bean é mantido até a aplicação navegar para outra página.

/**
 * Classe com métodos para consulta de pessoas e injeção de dependencias nos objetos da classe.
 */
public class ConsultarPessoaController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject transient
	private PessoaModel pessoaModel;

	@Produces
	private List<PessoaModel> pessoas;

	@Inject transient
	private PessoaRepository pessoaRepository;

	/**
	 * Retorna uma lista de pessoas
	 * @return
	 */
	public List<PessoaModel> getPessoas() {
		return pessoas;
	}

	/**
	 * Passado o valor da lista por parâmetro para o atributo da classe.
	 * @param pessoas
	 */
	public void setPessoas(List<PessoaModel> pessoas) {
		this.pessoas = pessoas;
	}

	/**
	 * Retorna um objeto do tipo pessoaModel.
	 * @return
	 */
	public PessoaModel getPessoaModel() {
		return pessoaModel;
	}

	/**
	 * Passado o valor de "pessoaModel" por parâmetro para o atributo da classe.
	 * @param pessoaModel
	 */
	public void setPessoaModel(PessoaModel pessoaModel) {
		this.pessoaModel = pessoaModel;
	}

	/***
	 * Realiza o carregamento de pessoas cadastradas no banco.
	 */
	@PostConstruct
	public void init(){

		//Retorna as pessoas cadastradas
		this.pessoas = pessoaRepository.GetPessoas();
	}


}