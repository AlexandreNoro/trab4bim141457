package br.univel.filters;

/**
 * Alexandre H. Noro
 * 14/11/2016
 * 14:10
 */
import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

/**
 * Toda vez que ocorrer uma requisi��o para o faces servlet, esse filter ser�
 * chamado.
 */
@WebFilter(servletNames = { "Faces Servlet" }) // Usado para definir que o nome do WebFilter ser� Faces Servlet.
public class JPAFilter implements Filter {

	private EntityManagerFactory entityManagerFactory;

	private String persistence_unit_name = "unit_app";

	/**
	 * Construtor
	 */
	public JPAFilter() {

	}

	/**
	 * Fecha conex�o
	 */
	public void destroy() {

		this.entityManagerFactory.close();
	}

	/**
	 * M�todo com as configura��es do protocolo Http. request, response e chain.
	 * IOException e ServletException em caso de algum erro.
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		/**
		 * Realizando a cria��o de uma EntityManager
		 */
		EntityManager entityManager = this.entityManagerFactory.createEntityManager();

		/**
		 * A EntityManager � adicionada na requisi��o
		 */
		request.setAttribute("entityManager", entityManager);

		/**
		 * Realiza o in�cio da Transa��o.
		 */
		entityManager.getTransaction().begin();

		/**
		 * Realiza o in�cio do Faces Servlet.
		 */
		chain.doFilter(request, response);

		try {

			/**
			 * Se n�o tiver erro, executa o commit.
			 */
			entityManager.getTransaction().commit();

		} catch (Exception e) {

			/**
			 * Se existir algum erro ele executa um Rollback.
			 */
			entityManager.getTransaction().rollback();
		} finally {

			/**
			 * Ap�s a verifica��o e execu��o do try catch ele encerra o
			 * EntityManager.
			 */
			entityManager.close();
		}
	}

	/**
	 * Realiza a cria��o do EntityManagerFactory baseado no arquivo
	 * persistence.xml. Par�metro fconfig � para configurar a persist�ncia.
	 * ServletException em caso de algum erro.
	 */
	public void init(FilterConfig fConfig) throws ServletException {

		this.entityManagerFactory = Persistence.createEntityManagerFactory(this.persistence_unit_name);
	}

}