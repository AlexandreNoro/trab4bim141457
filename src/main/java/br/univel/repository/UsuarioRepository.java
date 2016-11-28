package br.univel.repository;

/**
 * Alexandre H. Noro
 * 14/11/2016
 * 16:19
 */

import java.io.Serializable;

import javax.persistence.Query;

import br.univel.model.UsuarioModel;
import br.univel.repository.entity.UsuarioEntity;
import br.univel.uteis.Uteis;

 /**
  * Classe que implementa serializable e retorna o objeto do tipo usuario
  */
public class UsuarioRepository implements Serializable {


	private static final long serialVersionUID = 1L;

	/**
	 * Ap�s realizar consulta ao banco de dados, retorna um objeto UsuarioEntity caso exista um.
	 * @param usuarioModel
	 * @return
	 */


	public UsuarioEntity ValidaUsuario(UsuarioModel usuarioModel){

		try {

			//Define a query que ser� executada
			Query query = Uteis.JpaEntityManager().createNamedQuery("UsuarioEntity.findUser");

			//Par�metros da query que definem valor de usu�rio e senha
			query.setParameter("usuario", usuarioModel.getUsuario());
			query.setParameter("senha", usuarioModel.getSenha());

			//Retorna o usu�rio caso ele seja encontrado
			return (UsuarioEntity)query.getSingleResult();

		} catch (Exception e) {

			//Retorna null caso n�o seja encontrado nada.
			return null;
		}



	}
}
