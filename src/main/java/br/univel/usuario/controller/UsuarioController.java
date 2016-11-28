package br.univel.usuario.controller;
/**
 * Alexandre H. Noro
 * 15/11/2016
 * 00:11
 */

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.lang3.StringUtils;

import br.univel.model.UsuarioModel;
import br.univel.repository.UsuarioRepository;
import br.univel.repository.entity.UsuarioEntity;
import br.univel.uteis.Uteis;

@Named(value = "usuarioController")//anotation que transforma a classe em um bean gerenciado pelo CDI.
@SessionScoped//define que o escopo do Bean é uma sessão.

/**
 * Classe com métodos de controle de usuário e injeção de dependencias nos objetos da classe.
 *
 */
public class UsuarioController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject //Realiza a injeção de depêndencia dos beans.
	private UsuarioModel usuarioModel;

	@Inject //Realiza a injeção de depêndencia dos beans.
	private UsuarioRepository usuarioRepository;

	@Inject //Realiza a injeção de depêndencia dos beans.
	private UsuarioEntity usuarioEntity;

	/**
	 * Retorna um objeto do tipo usuarioModel.
	 * @return
	 */
	public UsuarioModel getUsuarioModel() {
		return usuarioModel;
	}

	/**
	 * Passado o valor por parâmetro para o atributo da classe.
	 * @param usuarioModel
	 */
	public void setUsuarioModel(UsuarioModel usuarioModel) {
		this.usuarioModel = usuarioModel;
	}

	/**
	 * Método que irá retornar o usuário que está logado no sistema.
	 * @return
	 */
	public UsuarioModel GetUsuarioSession() {

		FacesContext facesContext = FacesContext.getCurrentInstance();

		return (UsuarioModel) facesContext.getExternalContext().getSessionMap().get("usuarioAutenticado");
	}

	/**
	 * Realiza a finalização da sessão do usuário e redireciona para a página de login.
	 * @return
	 */
	public String Logout() {

		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();

		return "/index.xhtml?faces-redirect=true";
	}

	/**
	 * Realiza a verificação e autenticação do usuário.
	 * @return
	 */
	public String EfetuarLogin() {

		if (StringUtils.isEmpty(usuarioModel.getUsuario()) || StringUtils.isBlank(usuarioModel.getUsuario())) {

			Uteis.Mensagem("Favor informar o login!");
			return null;
		} else if (StringUtils.isEmpty(usuarioModel.getSenha()) || StringUtils.isBlank(usuarioModel.getSenha())) {

			Uteis.Mensagem("Favor informara senha!");
			return null;
		} else {

			usuarioEntity = usuarioRepository.ValidaUsuario(usuarioModel);

			if (usuarioEntity != null) {

				usuarioModel.setSenha(null);
				usuarioModel.setCodigo(usuarioEntity.getCodigo());

				FacesContext facesContext = FacesContext.getCurrentInstance();

				facesContext.getExternalContext().getSessionMap().put("usuarioAutenticado", usuarioModel);

				return "sistema/home?faces-redirect=true";
			} else {

				Uteis.Mensagem("Não foi possível efetuar o login com esse usuário e senha!");
				return null;
			}
		}

	}

}
