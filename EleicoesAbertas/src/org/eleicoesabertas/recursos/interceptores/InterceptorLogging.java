package org.eleicoesabertas.recursos.interceptores;

import java.util.logging.Logger;

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
 * Simplesmente reliza o Logging das requests para análise posterior
 * 
 * @author William Antônio
 * 
 */
@Provider
@ServerInterceptor
public class InterceptorLogging implements PreProcessInterceptor {

	Logger logger = Logger.getLogger(this.getClass().getName());

	@Override
	public ServerResponse preProcess(HttpRequest request, ResourceMethod rm)
			throws Failure, WebApplicationException {

		logger.info("Entrando interceptor de validação");
		logger.info("URI: " + request.getUri().getPath() + "; Método: "
				+ rm.getMethod().getName());

		return null;
	}
}
