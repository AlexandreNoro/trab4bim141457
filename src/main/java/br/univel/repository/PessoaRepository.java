package br.univel.repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.univel.model.PessoaModel;
import br.univel.model.UsuarioModel;
import br.univel.repository.entity.PessoaEntity;
import br.univel.repository.entity.UsuarioEntity;
import br.univel.uteis.Uteis;

/**
 *
 * @author Alexandre H. Noro
 * @data 17 de nov de 2016 as 21:06:48
 */

/**
 * Classe responsável por persistir na entidade PessoaEntity
 */
public class PessoaRepository {

	@Inject // Realiza a injeção de depêndencia.
	PessoaEntity pessoaEntity;

	EntityManager entityManager;

	/**
	 * Método responsável por gravar uma nova pessoa
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

	/**
	 * Método responsável pela consulta de pessoas
	 *
	 * @return
	 */
	public List<PessoaModel> GetPessoas() {

		List<PessoaModel> pessoasModel = new ArrayList<PessoaModel>();

		entityManager = Uteis.JpaEntityManager();

		Query query = entityManager.createNamedQuery("PessoaEntity.findAll");

		@SuppressWarnings("unchecked") //Serve para não aparecer o warning no método.
		Collection<PessoaEntity> pessoasEntity = (Collection<PessoaEntity>) query.getResultList();

		PessoaModel pessoaModel = null;

		for (PessoaEntity pessoaEntity : pessoasEntity) {

			pessoaModel = new PessoaModel();
			pessoaModel.setCodigo(pessoaEntity.getCodigo());
			pessoaModel.setDataCadastro(pessoaEntity.getDataCadastro());
			pessoaModel.setEmail(pessoaEntity.getEmail());
			pessoaModel.setEndereco(pessoaEntity.getEndereco());
			pessoaModel.setNome(pessoaEntity.getNome());

			if (pessoaEntity.getOrigemCadastro().equals("X"))
				pessoaModel.setOrigemCadastro("XML");
			else
				pessoaModel.setOrigemCadastro("INPUT");

			if (pessoaEntity.getSexo().equals("M"))
				pessoaModel.setSexo("Masculino");
			else
				pessoaModel.setSexo("Feminino");

			UsuarioEntity usuarioEntity = pessoaEntity.getUsuarioEntity();

			UsuarioModel usuarioModel = new UsuarioModel();
			usuarioModel.setUsuario(usuarioEntity.getUsuario());

			pessoaModel.setUsuarioModel(usuarioModel);

			pessoasModel.add(pessoaModel);
		}

		return pessoasModel;

	}

	/***
	 * Método que realiza a consulta de uma pessoa cadastrada pelo código
	 * @param codigo
	 * @return
	 */
	private PessoaEntity GetPessoa(int codigo) {

		entityManager = Uteis.JpaEntityManager();

		return entityManager.find(PessoaEntity.class, codigo);
	}

	/***
	 * Método que realiza a alteração de um registro cadastrado no banco de dados.
	 *
	 * @param pessoaModel
	 */
	public void AlterarRegistro(PessoaModel pessoaModel) {

		entityManager = Uteis.JpaEntityManager();

		PessoaEntity pessoaEntity = this.GetPessoa(pessoaModel.getCodigo());

		pessoaEntity.setEmail(pessoaModel.getEmail());
		pessoaEntity.setEndereco(pessoaModel.getEndereco());
		pessoaEntity.setNome(pessoaModel.getNome());
		pessoaEntity.setSexo(pessoaModel.getSexo());

		entityManager.merge(pessoaEntity);
	}

	/***
	 * Método que realiza a exclusão de um registro do banco de dados.
	 *
	 * @param codigo
	 */
	public void ExcluirRegistro(int codigo) {

		entityManager = Uteis.JpaEntityManager();

		PessoaEntity pessoaEntity = this.GetPessoa(codigo);

		entityManager.remove(pessoaEntity);
	}
}