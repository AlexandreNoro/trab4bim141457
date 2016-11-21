package br.univel.repository;

import java.time.LocalDateTime;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.univel.model.PessoaModel;
import br.univel.repository.entity.PessoaEntity;
import br.univel.repository.entity.UsuarioEntity;
import br.univel.uteis.Uteis;

/**
 *
 * @author Alexandre H. Noro
 * @data 17 de nov de 2016 as 21:06:48
 */

/**
 * Classe respons�vel por persistir na entidade PessoaEntity
 */
public class PessoaRepository {

	@Inject // Realiza a inje��o de dep�ndencia.
	PessoaEntity pessoaEntity;

	EntityManager entityManager;

	/**
	 * M�todo respons�vel por gravar uma nova pessoa
	 *
	 * @param pessoaModel
	 */
	public void SalvarNovoRegistro(PessoaModel pessoaModel) {

		entityManager = Uteis.JpaEntityManager();

		pessoaEntity = new PessoaEntity();
		pessoaEntity.setDataCadastro(LocalDateTime.now());
		pessoaEntity.setEmail(pessoaModel.getEmail());
		pessoaEntity.setEndereco(pessoaModel.getEndereco());
		pessoaEntity.setNome(pessoaModel.getNome());
		pessoaEntity.setOrigemCadastro(pessoaModel.getOrigemCadastro());
		pessoaEntity.setSexo(pessoaModel.getSexo());

		UsuarioEntity usuarioEntity = entityManager.find(UsuarioEntity.class,
				pessoaModel.getUsuarioModel().getCodigo());

		pessoaEntity.setUsuarioEntity(usuarioEntity);

		entityManager.persist(pessoaEntity);

	}
}