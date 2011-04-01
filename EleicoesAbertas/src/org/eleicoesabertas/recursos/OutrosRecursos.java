package org.eleicoesabertas.recursos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.eleicoesabertas.model.Cargo;
import org.eleicoesabertas.model.Coligacao;
import org.eleicoesabertas.model.Ocupacao;
import org.eleicoesabertas.model.Partido;
import org.eleicoesabertas.model.ResultadoEleicao;
import org.eleicoesabertas.model.Situacao;
import org.eleicoesabertas.util.EmUtil;

@Path("/{anoEleicao}")
public class OutrosRecursos {
	@QueryParam("pagina")
	int pgNum;

	EntityManager em;

	public OutrosRecursos() {
		super();
		em = EmUtil.getEntityManager();
	}

	@Path("/cargo")
	@Produces(MediaType.APPLICATION_XML)
	@GET
	public Cargo[] cargos() {
		@SuppressWarnings("unchecked")
		List<Cargo> cargos = (List<Cargo>) AcessoRecursos.executaQueryPaginada(
				em.createQuery("SELECT c FROM Cargo c"), pgNum);
		return cargos.toArray(new Cargo[cargos.size()]);
	}

	@Path("/partido")
	@Produces(MediaType.APPLICATION_XML)
	@GET
	public Partido[] partidos() {
		@SuppressWarnings("unchecked")
		List<Partido> partidos = (List<Partido>) AcessoRecursos
				.executaQueryPaginada(
						em.createQuery("SELECT p FROM Partido p"), pgNum);
		return partidos.toArray(new Partido[partidos.size()]);
	}

	@Path("/coligacao")
	@Produces(MediaType.APPLICATION_XML)
	@GET
	public Coligacao[] coligacoes() {
		@SuppressWarnings("unchecked")
		List<Coligacao> coligacoes = (List<Coligacao>) AcessoRecursos
				.executaQueryPaginada(
						em.createQuery("SELECT c FROM Coligacao c"), pgNum);
		return coligacoes.toArray(new Coligacao[coligacoes.size()]);

	}

	@Path("/situacao")
	@Produces(MediaType.APPLICATION_XML)
	@GET
	public Situacao[] situacoes() {
		@SuppressWarnings("unchecked")
		List<Situacao> situacoes = (List<Situacao>) AcessoRecursos
				.executaQueryPaginada(
						em.createQuery("SELECT s FROM Situacao s"), pgNum);
		return situacoes.toArray(new Situacao[situacoes.size()]);
	}

	@Path("/resultadoEleicao")
	@Produces(MediaType.APPLICATION_XML)
	@GET
	public ResultadoEleicao[] resultadosEleicao() {
		@SuppressWarnings("unchecked")
		List<ResultadoEleicao> resultadoEleicoes = (List<ResultadoEleicao>) AcessoRecursos
				.executaQueryPaginada(
						em.createQuery("SELECT r FROM ResultadoEleicao r"),
						pgNum);
		return resultadoEleicoes.toArray(new ResultadoEleicao[resultadoEleicoes
				.size()]);

	}
	
	@Path("/ocupacao")
	@Produces(MediaType.APPLICATION_XML)
	@GET
	public Ocupacao[] ocupacoes() {
		@SuppressWarnings("unchecked")
		List<Ocupacao> ocupacoes = (List<Ocupacao>) AcessoRecursos
				.executaQueryPaginada(
						em.createQuery("SELECT o FROM Ocupacao o"),
						pgNum);
		return ocupacoes.toArray(new Ocupacao[ocupacoes
				.size()]);

	}
}
