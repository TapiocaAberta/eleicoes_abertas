package org.eleicoesabertas.recursos;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.eleicoesabertas.model.Candidato;
import org.eleicoesabertas.model.Resultados;

@Path("/{anoEleicao}/candidatos")
public interface CandidatoRecurso {

	@Path("/{id: [0-9]+}")
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Candidato obterCandidatoPorId(@PathParam("id") int id);


	@Path("/{estado: [A-Z]+}/")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@GET
	public Resultados obterCandidatoPorEstado(@PathParam("estado") String uf);


	@Path("/{estado: [A-Z]+}/eleitos")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@GET
	public Resultados obterCandidatosEleitosPorEstado(
			@PathParam("estado") String uf);


	@Path("/")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@GET
	public Resultados obterTodosCandidato();

	@Path("/eleitos")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@GET
	public Resultados obterTodosCandidatosEleitos();


	@Path("/{estado}/{cargo}")
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Resultados candidatosRegiaoCargo(@PathParam("estado") String strUf,
			@PathParam("cargo") String strCargo);


	@Path("/{estado}/{cargo}/{partido}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@GET
	public Resultados candidatosRegiaoCargoPartido(
			@PathParam("estado") String strUf,
			@PathParam("cargo") String strCargo,
			@PathParam("partido") String strPartido);


	@Path("/{estado}/{cargo}/{partido}/eleitos")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@GET
	public Resultados candidatosEleitosRegiaoCargoPartido(
			@PathParam("estado") String strUf,
			@PathParam("cargo") String strCargo,
			@PathParam("partido") String strPartido);


	@Path("{estado}/{cargo}/eleitos")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@GET
	public Resultados candidatosEleitosRegiaoCargo(
			@PathParam("estado") String strUf,
			@PathParam("cargo") String strCargo);

	@Path("/busca")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@GET
	public Resultados busca(@QueryParam("nome") String nome,
			@QueryParam("partido") String strPartido,
			@QueryParam("cargo") String strCargo,
			@QueryParam("uf") String strUf,
			@QueryParam("resultadoEleicao") String strResultado);
}
