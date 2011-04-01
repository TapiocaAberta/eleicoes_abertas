package org.eleicoesabertas.recursos;

import static org.eleicoesabertas.recursos.AcessoRecursos.buscaCargo;
import static org.eleicoesabertas.recursos.AcessoRecursos.buscaEleicao;
import static org.eleicoesabertas.recursos.AcessoRecursos.buscaEstado;
import static org.eleicoesabertas.recursos.AcessoRecursos.buscaPartido;
import static org.eleicoesabertas.recursos.AcessoRecursos.executaQueryPaginada;
import static org.eleicoesabertas.recursos.AcessoRecursos.obterCandidato;
import static org.eleicoesabertas.recursos.AcessoRecursos.retornaDadosPorNamedQuery;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.eleicoesabertas.model.Candidato;
import org.eleicoesabertas.model.Cargo;
import org.eleicoesabertas.model.Estado;
import org.eleicoesabertas.model.Partido;
import org.eleicoesabertas.model.ResultadoEleicao;
import org.eleicoesabertas.model.Resultados;
import org.eleicoesabertas.util.EmUtil;

@Path("/{anoEleicao}/candidatos")
public class CandidatoRecurso {

	EntityManager em;
	@PathParam("anoEleicao")
	String anoEleicao;
	@QueryParam("pagina")
	int pgNum;

	public CandidatoRecurso() {
		em = EmUtil.getEntityManager();
	}

	@Path("/{id: [0-9]+}")
	@GET
	public Candidato obterCandidatoPorId(@PathParam("id") int id) {
		return obterCandidato(id);
	}

	@SuppressWarnings("unchecked")
	@Path("/{estado: [A-Z]+}/")
	@Produces(MediaType.APPLICATION_XML)
	@GET
	public Resultados obterCandidatoPorEstado(@PathParam("estado") String uf) {
		uf = uf.toUpperCase();
		List<Candidato> candidatos = new ArrayList<Candidato>();
		Resultados r;
		candidatos = (List<Candidato>) retornaDadosPorNamedQuery(
				"buscaCandidatoPorEleicaoEEstado", pgNum,
				buscaEleicao(anoEleicao), buscaEstado(uf));
		r = new Resultados(pgNum, candidatos.size(), "Todos Candidatos de "
				+ uf.toUpperCase(), candidatos);
		return r;
	}
	@SuppressWarnings("unchecked")
	@Path("/{estado: [A-Z]+}/eleitos")
	@Produces(MediaType.APPLICATION_XML)
	@GET
	public Resultados obterCandidatosEleitosPorEstado(@PathParam("estado") String uf) {
		uf = uf.toUpperCase();
		List<Candidato> candidatos = new ArrayList<Candidato>();
		Resultados r;
		candidatos = (List<Candidato>) retornaDadosPorNamedQuery(
				"buscaCandidatoEleitoPorEleicaoEEstado", pgNum,
				buscaEleicao(anoEleicao), buscaEstado(uf));
		r = new Resultados(pgNum, candidatos.size(), "Todos Candidatos eleitos de "
				+ uf.toUpperCase() , candidatos);
		return r;
	}
	

	@SuppressWarnings("unchecked")
	@Path("/")
	@Produces(MediaType.APPLICATION_XML)
	@GET
	public Resultados obterTodosCandidato() {
		List<Candidato> candidatos = new ArrayList<Candidato>();
		Resultados r;

		candidatos = (List<Candidato>) retornaDadosPorNamedQuery(
				"buscaCandidatoPorEleicao", pgNum, buscaEleicao(anoEleicao));
		r = new Resultados(pgNum, candidatos.size(), "Todos Candidatos",
				candidatos);
		r.setTotalResultados(AcessoRecursos.conta("Candidato"));
		return r;
	}
	@SuppressWarnings("unchecked")
	@Path("/eleitos")
	@Produces(MediaType.APPLICATION_XML)
	@GET
	public Resultados obterTodosCandidatosEleitos() {
		List<Candidato> candidatos = new ArrayList<Candidato>();
		Resultados r;
		candidatos = (List<Candidato>) retornaDadosPorNamedQuery(
				"buscaCandidatoEleitoPorEleicao", pgNum, buscaEleicao(anoEleicao));
		r = new Resultados(pgNum, candidatos.size(), "Todos Candidatos eleitos",
				candidatos);
		r.setTotalResultados(AcessoRecursos.conta("Candidato"));
		return r;
	}

	@SuppressWarnings("unchecked")
	@Path("/{estado}/{cargo}")
	@GET
	public Resultados candidatosRegiaoCargo(@PathParam("estado") String strUf,
			@PathParam("cargo") String strCargo) {
		List<Candidato> candidatos = new ArrayList<Candidato>();
		Resultados r;
		candidatos = (List<Candidato>) retornaDadosPorNamedQuery(
				"buscaCandidatoPorEleicaoEEstadoECargo", pgNum,
				buscaEleicao(anoEleicao), buscaEstado(strUf.toUpperCase()),
				buscaCargo(strCargo.toUpperCase()));
		r = new Resultados(pgNum, candidatos.size(), "Candidatos para o cargo "
				+ strCargo + " (" + strUf.toUpperCase() + ")", candidatos);

		return r;
	}

	@SuppressWarnings("unchecked")
	@Path("/{estado}/{cargo}/{partido}")
	@GET
	public Resultados candidatosRegiaoCargoPartido(
			@PathParam("estado") String strUf,
			@PathParam("cargo") String strCargo,
			@PathParam("partido") String strPartido) {
		List<Candidato> candidatos = new ArrayList<Candidato>();
		Resultados r;
		candidatos = (List<Candidato>) retornaDadosPorNamedQuery(
				"buscaCandidatoPorEleicaoEEstadoECargoEPartido", pgNum,
				buscaEleicao(anoEleicao), buscaEstado(strUf.toUpperCase()),
				buscaCargo(strCargo), buscaPartido(strPartido));
		r = new Resultados(pgNum, candidatos.size(), "Candidatos para o cargo "
				+ strCargo + " (" + strUf.toUpperCase() + ") do partido "
				+ strPartido.toUpperCase(), candidatos);
		return r;
	}
	
	@SuppressWarnings("unchecked")
	@Path("/{estado}/{cargo}/{partido}/eleitos")
	@GET
	public Resultados candidatosEleitosRegiaoCargoPartido(
			@PathParam("estado") String strUf,
			@PathParam("cargo") String strCargo,
			@PathParam("partido") String strPartido) {
		List<Candidato> candidatos = new ArrayList<Candidato>();
		Resultados r;
		candidatos = (List<Candidato>) retornaDadosPorNamedQuery(
				"buscaCandidatoEleitoPorEleicaoEEstadoECargoEPartido", pgNum,
				buscaEleicao(anoEleicao), buscaEstado(strUf.toUpperCase()),
				buscaCargo(strCargo), buscaPartido(strPartido));
		r = new Resultados(pgNum, candidatos.size(),
				"Candidatos eleitos para o cargo " + strCargo + " ("
						+ strUf.toUpperCase() + ") do partido "
						+ strPartido.toUpperCase(), candidatos);

		return r;
	}

	@SuppressWarnings("unchecked")
	@Path("{estado}/{cargo}/eleitos")
	@GET
	public Resultados candidatosEleitosRegiaoCargo(
			@PathParam("estado") String strUf,
			@PathParam("cargo") String strCargo) {
		List<Candidato> candidatos = new ArrayList<Candidato>();
		Resultados r;
		candidatos = (List<Candidato>) retornaDadosPorNamedQuery(
				"buscaCandidatoEleitoPorEleicaoEEstadoECargo", pgNum,
				buscaEleicao(anoEleicao), buscaEstado(strUf.toUpperCase()),
				buscaCargo(strCargo.toUpperCase()));
		r = new Resultados(pgNum, candidatos.size(),
				"Candidatos eleitos para o cargo " + strCargo + " ("
						+ strUf.toUpperCase() + ")", candidatos);

		return r;
	}

	@Path("/busca")
	@GET
	@SuppressWarnings("unchecked")
	public Resultados busca(@QueryParam("nome") String nome,
			@QueryParam("partido") String strPartido,
			@QueryParam("cargo") String strCargo, @QueryParam("uf") String strUf, @QueryParam("resultadoEleicao") String strResultado) {

		Partido p = null;
		Cargo c = null;
		Estado e = null;
		ResultadoEleicao rs = null; //rsrsrsrsrsrsrs
		ArrayList<Object> parametros = new ArrayList<Object>();

		if (checa(nome, 3))
			RecursosUtil
					.lancaErro("Erro no parâmetro Nome. Você deve informar um nome com largura maior que 3");

		// Verifica quais parâmetros foram enviados
		if (!checa(strPartido))
			p = buscaPartido(strPartido);
		if (!checa(strCargo))
			c = buscaCargo(strCargo);
		if (!checa(strUf))
			e = buscaEstado(strUf);
		if (!checa(strResultado))
			rs = AcessoRecursos.buscaResultadoEleicao(strResultado);		
		StringBuffer sbQuery = new StringBuffer();
		nome = "%" + nome + "%";
		sbQuery.append("SELECT c FROM Candidato c WHERE c.nome like ?1");
		parametros.add(nome);
		int parIndice = 1;
		if (p != null) {
			sbQuery.append(" AND c.partido = ?");
			sbQuery.append(++parIndice);
			parametros.add(p);
		}
		if (c != null) {
			sbQuery.append(" AND c.cargo= ?");
			sbQuery.append(++parIndice);
			parametros.add(c);
		}
		if (e != null) {
			sbQuery.append(" AND c.estado = ?");
			sbQuery.append(++parIndice);
			parametros.add(e);
		}
		if (rs != null) {
			sbQuery.append(" AND c.resultadoEleicao = ?");
			sbQuery.append(++parIndice);
			parametros.add(rs);
		}
		sbQuery.append(" ORDER BY c.nome");
		String queryContagem = sbQuery.toString().replace("SELECT c",
				"SELECT COUNT(c)");
		Query qContagem = em.createQuery(queryContagem);
		Query q = em.createQuery(sbQuery.toString());

		for (int i = 1; i <= parametros.size(); i++) {
			qContagem.setParameter(i, parametros.get(i - 1));
			q.setParameter(i, parametros.get(i - 1));
		}

		List<Candidato> candidatos = new ArrayList<Candidato>();
		Resultados r;
		candidatos = (List<Candidato>) executaQueryPaginada(q, pgNum);
		r = new Resultados(pgNum, candidatos.size(), "Busca de candidatos",
				candidatos);
		r.setTotalResultados(Long.parseLong( qContagem
				.getSingleResult().toString()));
		return r;
	}

	private boolean checa(String str, int lenght) {
		return checa(str) || str.length() < lenght;
	}

	private boolean checa(String str) {
		return str == null || str.isEmpty();
	}
}
