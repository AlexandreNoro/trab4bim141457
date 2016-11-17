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
 * Toda vez que ocorrer uma requisição para o faces servlet, esse filter será
 * chamado.
 */
@WebFilter(servletNames = { "Faces Servlet" }) // Usado para definir que o nome do WebFilter será Faces Servlet.
public class JPAFilter implements Filter {

	private EntityManagerFactory entityManagerFactory;

	private String persistence_unit_name = "unit_app";

	/**
	 * Construtor
	 */
	public JPAFilter() {

	}

	/**
	 * Fecha conexão
	 */
	public void destroy() {

		this.entityManagerFactory.close();
	}

	/**
	 * Método com as configurações do protocolo Http. request, response e chain.
	 * IOException e ServletException em caso de algum erro.
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		/**
		 * Realizando a criação de uma EntityManager
		 */
		EntityManager entityManager = this.entityManagerFactory.createEntityManager();

		/**
		 * A EntityManager é adicionada na requisição
		 */
		request.setAttribute("entityManager", entityManager);

		/**
		 * Realiza o início da Transação.
		 */
		entityManager.getTransaction().begin();

		/**
		 * Realiza o início do Faces Servlet.
		 */
		chain.doFilter(request, response);

		try {

			/**
			 * Se não tiver erro, executa o commit.
			 */
			entityManager.getTransaction().commit();

		} catch (Exception e) {

			/**
			 * Se existir algum erro ele executa um Rollback.
			 */
			entityManager.getTransaction().rollback();
		} finally {

			/**
			 * Após a verificação e execução do try catch ele encerra o
			 * EntityManager.
			 */
			entityManager.close();
		}
	}

	/**
	 * Realiza a criação do EntityManagerFactory baseado no arquivo
	 * persistence.xml. Parâmetro fconfig é para configurar a persistência.
	 * ServletException em caso de algum erro.
	 */
	public void init(FilterConfig fConfig) throws ServletException {

		this.entityManagerFactory = Persistence.createEntityManagerFactory(this.persistence_unit_name);
	}

}