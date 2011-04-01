package org.eleicoesabertas.recursos;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * Servlet Filter implementation class FiltroServicos
 */
public class FiltroServicos implements Filter {
	static int quantidadeAcessos = 0;
	
	public FiltroServicos() {}

	public void destroy() {}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		quantidadeAcessos++;
		System.out.println("Acesso número "+quantidadeAcessos);
		chain.doFilter(request, response);
	}	
	public void init(FilterConfig fConfig) throws ServletException {
	
	}

}
