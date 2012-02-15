package org.eleicoesabertas.recursos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.eleicoesabertas.db.CandidatosDao;
import org.eleicoesabertas.model.Cargo;
import org.eleicoesabertas.model.Coligacao;
import org.eleicoesabertas.model.Ocupacao;
import org.eleicoesabertas.model.Partido;
import org.eleicoesabertas.model.ResultadoEleicao;
import org.eleicoesabertas.model.Situacao;
import org.eleicoesabertas.util.EmUtil;
import org.jboss.resteasy.annotations.providers.jaxb.Wrapped;

@Path("{anoEleicao}")
public class OutrosRecursos {

	CandidatosDao dao;
	@QueryParam("pagina")
	int pgNum;

	EntityManager em;

	public OutrosRecursos() {
		super();
		em = EmUtil.getEntityManager();
		dao = new CandidatosDao();
	}

	@Path("/cargo")
	@Produces(MediaType.APPLICATION_XML)
	@GET
	@Wrapped(element="cargos")
	public  List<Cargo> cargos() {
		@SuppressWarnings("unchecked")
		List<Cargo> cargos = (List<Cargo>) dao.executaQueryPaginada(
				em.createQuery("SELECT c FROM Cargo c"), pgNum);
		return cargos;
	}

	@Path("/partido")
	@Produces(MediaType.APPLICATION_XML)
	@Wrapped(element="partidos")
	@GET
	public List<Partido> partidos() {
		@SuppressWarnings("unchecked")
		List<Partido> partidos = (List<Partido>) dao.executaQueryPaginada(
				em.createQuery("SELECT p FROM Partido p"), pgNum);
		return partidos;
	}

	@Path("/coligacao")
	@Produces(MediaType.APPLICATION_XML)
	@GET
	@Wrapped(element="coligacoes")
	public List<Coligacao> coligacoes() {
		@SuppressWarnings("unchecked")
		List<Coligacao> coligacoes = (List<Coligacao>) dao
				.executaQueryPaginada(
						em.createQuery("SELECT c FROM Coligacao c"), pgNum);
		return coligacoes;

	}

	@Path("/situacao")
	@Produces(MediaType.APPLICATION_XML)
	@GET
	@Wrapped(element="situacoes")
	public  List<Situacao> situacoes() {
		@SuppressWarnings("unchecked")
		List<Situacao> situacoes = (List<Situacao>) dao.executaQueryPaginada(
				em.createQuery("SELECT s FROM Situacao s"), pgNum);
		return situacoes;
	}

	@Path("/resultadoEleicao")
	@Produces(MediaType.APPLICATION_XML)
	@GET
	@Wrapped(element="resultadosEleicao")
	public  List<ResultadoEleicao> resultadosEleicao() {
		@SuppressWarnings("unchecked")
		List<ResultadoEleicao> resultadoEleicoes = (List<ResultadoEleicao>) dao
				.executaQueryPaginada(
						em.createQuery("SELECT r FROM ResultadoEleicao r"),
						pgNum);
		return resultadoEleicoes;

	}

	@Path("/ocupacao")
	@Produces(MediaType.APPLICATION_XML)
	@GET
	@Wrapped(element="ocupacoes")	
	public  List<Ocupacao> ocupacoes() {
		@SuppressWarnings("unchecked")
		List<Ocupacao> ocupacoes = (List<Ocupacao>) dao.executaQueryPaginada(
				em.createQuery("SELECT o FROM Ocupacao o"), pgNum);
		return ocupacoes;

	}
}
