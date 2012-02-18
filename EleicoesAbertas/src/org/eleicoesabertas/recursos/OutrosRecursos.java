package org.eleicoesabertas.recursos;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eleicoesabertas.model.Cargo;
import org.eleicoesabertas.model.Coligacao;
import org.eleicoesabertas.model.Ocupacao;
import org.eleicoesabertas.model.Partido;
import org.eleicoesabertas.model.ResultadoEleicao;
import org.eleicoesabertas.model.Situacao;
import org.jboss.resteasy.annotations.providers.jaxb.Wrapped;

@Path("{anoEleicao}")
public interface OutrosRecursos {

	@Path("/cargo")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@GET
	@Wrapped(element = "cargos")
	public List<Cargo> cargos();

	@Path("/partido")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Wrapped(element = "partidos")
	@GET
	public List<Partido> partidos();

	@Path("/coligacao")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@GET
	@Wrapped(element = "coligacoes")
	public List<Coligacao> coligacoes();

	@Path("/situacao")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@GET
	@Wrapped(element = "situacoes")
	public List<Situacao> situacoes();

	@Path("/resultadoEleicao")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@GET
	@Wrapped(element = "resultadosEleicao")
	public List<ResultadoEleicao> resultadosEleicao();

	@Path("/ocupacao")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@GET
	@Wrapped(element = "ocupacoes")
	public List<Ocupacao> ocupacoes();
}
