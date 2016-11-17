package br.univel.filters;

/**
 * Alexandre H. Noro
 * 14/11/2016
 * 00:33
 */

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.univel.model.UsuarioModel;

/**
 * Filter para verificação e validação se usuário está logado para ter acesso as
 * páginas do sistema
 */
@WebFilter("/sistema/*") // filtra as requisições de todos os itens que tiverem em /sistema/*.
public class AutenticacaoFilter implements Filter {

	/**
	 * Construtor
	 */
	public AutenticacaoFilter() {

	}

	/**
	 * Fecha a conexão
	 */
	public void destroy() {

	}

	/**
	 * Método que realiza os filtros via protocolo http e verifica se o usuário
	 * está logado, se não estiver será redirecionado para realizar o login.
	 *
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpSession httpSession = ((HttpServletRequest) request).getSession();

		HttpServletRequest httpServletRequest = (HttpServletRequest) request;

		HttpServletResponse httpServletResponse = (HttpServletResponse) response;

		if (httpServletRequest.getRequestURI().indexOf("index.xhtml") <= -1) {

			UsuarioModel usuarioModel = (UsuarioModel) httpSession.getAttribute("usuarioAutenticado");

			if (usuarioModel == null) {

				httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/index.xhtml");

			} else {

				chain.doFilter(request, response);
			}
		} else {

			chain.doFilter(request, response);
		}
	}

	/**
	 * Método vazio da interface Filter
	 *
	 * ServletException em caso de algum erro.
	 */
	public void init(FilterConfig fConfig) throws ServletException {

	}

}