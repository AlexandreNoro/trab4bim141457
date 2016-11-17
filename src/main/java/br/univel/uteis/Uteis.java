package br.univel.uteis;
/**
 * Alexandre H. Noro
 * 14/11/2016
 * 15:14
 */

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;

/**
 * Classe com m�todos que ser�o usados no restante do projeto.
 */

public class Uteis {

	/**
	 * Realiza o retorno de uma conex�o que foi requisitada configurada com JPA.
	 */
	public static EntityManager JpaEntityManager() {

		FacesContext facesContext = FacesContext.getCurrentInstance();

		ExternalContext externalContext = facesContext.getExternalContext();

		HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();

		return (EntityManager) request.getAttribute("entityManager");
	}

	/**
	 * Mostra uma mensagem de alerta.
	 *
	 * @param mensagem
	 */
	public static void Mensagem(String mensagem) {

		FacesContext facesContext = FacesContext.getCurrentInstance();

		facesContext.addMessage(null, new FacesMessage("Alerta", mensagem));
	}

	/**
	 * Mostra uma mensagem para prestar aten��o.
	 *
	 * @param mensagem
	 */
	public static void MensagemAtencao(String mensagem) {

		FacesContext facesContext = FacesContext.getCurrentInstance();

		facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Aten��o:", mensagem));
	}

	/**
	 * Mostra uma mensagem aleat�ria ou de algum erro
	 *
	 * @param mensagem
	 */
	public static void MensagemInfo(String mensagem) {

		FacesContext facesContext = FacesContext.getCurrentInstance();

		facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", mensagem));
	}

}