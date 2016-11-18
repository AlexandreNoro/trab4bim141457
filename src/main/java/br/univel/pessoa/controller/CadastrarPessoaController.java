package br.univel.pessoa.controller;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.univel.model.PessoaModel;
import br.univel.repository.PessoaRepository;
import br.univel.usuario.controller.UsuarioController;
import br.univel.uteis.Uteis;
/**
 *
 * @author Alexandre H. Noro
 * @data 17 de nov de 2016 as 21:21:18
 */
@Named(value = "cadastrarPessoaController") //anotation que transforma a classe em um bean gerenciado pelo CDI.
@RequestScoped //Especifica que um bean � um escopo de solicita��o.

/**
 * Classe com m�todos de controle da pessoa e inje��o de dependencias nos objetos da classe.
 */
public class CadastrarPessoaController {

	@Inject //Realiza a inje��o de dep�ndencia dos beans.
	PessoaModel pessoaModel;

	@Inject //Realiza a inje��o de dep�ndencia dos beans.
	UsuarioController usuarioController;

	@Inject //Realiza a inje��o de dep�ndencia dos beans.
	PessoaRepository pessoaRepository;

	/**
	 * Retorna um objeto do tipo pessoaModel
	 * @return
	 */
	public PessoaModel getPessoaModel() {
		return pessoaModel;
	}

	/**
	 * Passado o valor de "pessoaModel" por par�metro para o atributo da classe.
	 * @param pessoaModel
	 */
	public void setPessoaModel(PessoaModel pessoaModel) {
		this.pessoaModel = pessoaModel;
	}

	/**
	 * Realiza a grava��o de um novo registro via input
	 */
	public void SalvarNovaPessoa() {

		pessoaModel.setUsuarioModel(this.usuarioController.GetUsuarioSession());

		// Aqui est� informando que o cadastro foi via input
		pessoaModel.setOrigemCadastro("I");

		pessoaRepository.SalvarNovoRegistro(this.pessoaModel);

		this.pessoaModel = null;

		Uteis.MensagemInfo("Registro cadastrado com sucesso");

	}

}