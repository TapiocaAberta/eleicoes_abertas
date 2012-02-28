package rest;

import static org.junit.Assert.assertEquals;

import java.net.URISyntaxException;

import org.eleicoesabertas.recursos.impl.CandidatoRecursoImpl;
import org.eleicoesabertas.recursos.impl.OutrosRecursosImpl;
import org.jboss.resteasy.core.Dispatcher;
import org.jboss.resteasy.mock.MockDispatcherFactory;
import org.jboss.resteasy.mock.MockHttpRequest;
import org.jboss.resteasy.mock.MockHttpResponse;
import org.jboss.resteasy.plugins.server.resourcefactory.POJOResourceFactory;
import org.junit.Before;
import org.junit.Test;

public class TestaTodosRecursosREST {
	Dispatcher dispatcher;

	@Before
	public void antes() {
		dispatcher = MockDispatcherFactory.createDispatcher();

		POJOResourceFactory recursoCandidato = new POJOResourceFactory(
				CandidatoRecursoImpl.class);
		POJOResourceFactory outrosRecursos = new POJOResourceFactory(
				OutrosRecursosImpl.class);
		dispatcher.getRegistry().addResourceFactory(recursoCandidato);
		dispatcher.getRegistry().addResourceFactory(outrosRecursos);
	}

	@Test
	public void todosTestes() throws Exception {
		
		//404, typo on candidatos
		assertEquals(404, fazerRequest("/2010/candidatus"));

		//OK
		assertEquals(200, fazerRequest("/2010/candidatos"));
		assertEquals(200, fazerRequest("/2010/candidatos/1"));		
		
		//Not found
		assertEquals(404, fazerRequest("/2010/candidatos/99999"));
		
		//NOt found found because of the year and candidate
		assertEquals(404, fazerRequest("/3100/candidatos/6666"));
		
		//NOt found found because of the year
		assertEquals(404, fazerRequest("/3100/candidatos/"));
		
		//OK
		assertEquals(200, fazerRequest("/2010/candidatos/SP"));
		
		//404 because of the non existing state
		assertEquals(404, fazerRequest("/2010/candidatos/XZ"));
		
		//404, existing state with invalid year
		assertEquals(404, fazerRequest("/3900/candidatos/SP"));		
		
		//TODO: Continue tests
/*
		eleicoesful/{versao}/{anoEleicao}/candidato/{estado}/{numeroEleicao}

		//todos candidatos dessa região
		eleicoesful/{versao}/{anoEleicao}/{estado}/

		//candidatos por região e cargo 
		eleicoesful/{versao}/{anoEleicao}/{estado}/{cargo}/

		//candidatos eleitos para esse cargo
		eleicoesful/{versao}/{anoEleicao}/{estado}/{cargo}/eleitos/
		*/
	}

	private int fazerRequest(String uri) throws URISyntaxException {
		MockHttpRequest request = MockHttpRequest.get(uri);
		MockHttpResponse response = new MockHttpResponse();
		dispatcher.invoke(request, response);
		return (response.getStatus());

	}
}
