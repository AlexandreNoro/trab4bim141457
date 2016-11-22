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
 * @data 22 de nov de 2016 as 20:37:13
 */

@Named(value = "consultarPessoaCarouselController") // anotation que transforma a classe em um bean gerenciado pelo CDI.

@ViewScoped // annotation em que o bean é mantido até a aplicação navegar para outra página.

/**
 * Classe criada para consulta utilizando Carousel PrimeFaces.
 */
public class ConsultarPessoaCarouselController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	transient private PessoaRepository pessoaRepository;

	@Produces
	private List<PessoaModel> pessoas;

	/**
	 * Método que retorna uma lista de pessoas
	 *
	 * @return
	 */
	public List<PessoaModel> getPessoas() {
		return pessoas;
	}

	/***
	 * Realiza o carregamento de pessoas cadastradas no banco.
	 */
	@PostConstruct
	private void init() {

		this.pessoas = pessoaRepository.GetPessoas();
	}

}