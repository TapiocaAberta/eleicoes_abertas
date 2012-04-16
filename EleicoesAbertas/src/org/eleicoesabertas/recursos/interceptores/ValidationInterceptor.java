package org.eleicoesabertas.recursos.interceptores;

import java.util.logging.Logger;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MultivaluedMap;

import org.eleicoesabertas.db.GeneralDao;
import org.eleicoesabertas.util.RecursosUtil;
import org.jboss.resteasy.core.ResourceMethod;
import org.jboss.resteasy.core.ServerResponse;
import org.jboss.resteasy.spi.Failure;
import org.jboss.resteasy.spi.HttpRequest;
import org.jboss.resteasy.spi.interception.PreProcessInterceptor;
import static org.eleicoesabertas.recursos.impl.DefinicoesServicos.*;


//TODO: Propagar valores pegos no BD até o Recurso JAX-RS
public class ValidationInterceptor  implements PreProcessInterceptor {
	Logger logger = Logger.getLogger(this.getClass().getName());
	private GeneralDao dao;

	public ValidationInterceptor() {
		dao = new GeneralDao();
	}

	@Override
	public ServerResponse preProcess(HttpRequest request, ResourceMethod rm)
			throws Failure, WebApplicationException {

		logger.info("Entrando interceptor de validação...");

		MultivaluedMap<String, String> paramsUrl = request.getUri()
				.getPathParameters();

		// validaremos cada parâmetro enviado pela URL
		String ano, estado, cargo, partido;
		ano = paramsUrl.getFirst(ANO_ELEICAO);
		estado = paramsUrl.getFirst(ESTADO);
		cargo = paramsUrl.getFirst(CARGO);
		partido = paramsUrl.getFirst(PARTIDO);
		// Isso é muito feito, melhorar urgente :(
		try {
			// Valida parâmetro por parâmetro. Ano é obrigatório para todos
			dao.buscaEleicao(ano);
			// outros parâmetros podem não estar presente...
			if (estado != null)
				dao.buscaEstado(estado);
			if (cargo != null)
				dao.buscaCargo(cargo);
			if (partido != null)
				dao.buscaPartido(partido);
		} catch (Exception e) {
			throw RecursosUtil.trataErro(e);
		}
		logger.info("Saindo interceptor de validação");
		
		return null;
	}
}
