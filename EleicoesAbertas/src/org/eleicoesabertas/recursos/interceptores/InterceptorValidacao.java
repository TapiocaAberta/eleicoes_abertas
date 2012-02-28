package org.eleicoesabertas.recursos.interceptores;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.Provider;

import org.eleicoesabertas.db.GeneralDao;
import org.eleicoesabertas.util.RecursosUtil;
import org.jboss.resteasy.annotations.interception.ServerInterceptor;
import org.jboss.resteasy.core.ResourceMethod;
import org.jboss.resteasy.core.ServerResponse;
import org.jboss.resteasy.spi.Failure;
import org.jboss.resteasy.spi.HttpRequest;
import org.jboss.resteasy.spi.interception.PreProcessInterceptor;
import static org.eleicoesabertas.recursos.impl.DefinicoesServicos.*;

/**
 * Ester interceptor ir� validar todos os par�metros de entrada do usu�rio com o
 * banco de dados
 * 
 * @author William Ant�nio
 * 
 */
@Provider
@ServerInterceptor
public class InterceptorValidacao implements PreProcessInterceptor {

	private GeneralDao dao;

	public InterceptorValidacao() {
		dao = new GeneralDao();
	}

	@Override
	public ServerResponse preProcess(HttpRequest request, ResourceMethod rm)
			throws Failure, WebApplicationException {

		System.out.println("Entrando interceptor de valida��o");
		MultivaluedMap<String, String> paramsUrl = request.getUri()
				.getPathParameters();

		// validaremos cada par�metro enviado pela URL
		String ano, estado, cargo, partido;
		ano = paramsUrl.getFirst(ANO_ELEICAO);
		estado = paramsUrl.getFirst(ESTADO);
		cargo = paramsUrl.getFirst(CARGO);
		partido = paramsUrl.getFirst(PARTIDO);
		// Isso � muito feito, melhorar urgente :(
		try {
			// Valida par�metro por par�metro. Ano � obrigat�rio para todos
			dao.buscaEleicao(ano);
			//outros par�metros podem n�o estar presente...
			if (estado != null)
				dao.buscaEstado(estado);
			if (cargo != null)
				dao.buscaCargo(cargo);
			if (partido != null)
				dao.buscaPartido(partido);
		} catch (Exception e) {
			throw RecursosUtil.trataErro(e);
		}
		System.out.println("Saindo interceptor de valida��o");
		return null;
	}
}
