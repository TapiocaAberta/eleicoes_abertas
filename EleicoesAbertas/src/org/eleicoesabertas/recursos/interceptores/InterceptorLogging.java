package org.eleicoesabertas.recursos.interceptores;

import java.util.logging.Logger;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.ext.Provider;

import org.jboss.resteasy.annotations.interception.ServerInterceptor;
import org.jboss.resteasy.core.ResourceMethod;
import org.jboss.resteasy.core.ServerResponse;
import org.jboss.resteasy.spi.Failure;
import org.jboss.resteasy.spi.HttpRequest;
import org.jboss.resteasy.spi.interception.PreProcessInterceptor;

/**
 * Simplesmente reliza o Logging das requests para an�lise posterior
 * 
 * @author William Ant�nio
 * 
 */
@Provider
@ServerInterceptor
public class InterceptorLogging implements PreProcessInterceptor {

	Logger logger = Logger.getLogger(this.getClass().getName());

	@Override
	public ServerResponse preProcess(HttpRequest request, ResourceMethod rm)
			throws Failure, WebApplicationException {		

		logger.info("Entrando interceptor de valida��o");
		logger.info("URI: " + request.getUri().getPath() + "; M�todo: "
				+ rm.getMethod().getName());

		return null;
	}
}
