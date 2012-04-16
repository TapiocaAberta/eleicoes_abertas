package org.eleicoesabertas.recursos.impl;

import static org.eleicoesabertas.recursos.impl.DefinicoesServicos.ANO_ELEICAO;
import static org.eleicoesabertas.recursos.impl.DefinicoesServicos.CARGO;
import static org.eleicoesabertas.recursos.impl.DefinicoesServicos.ESTADO;
import static org.eleicoesabertas.recursos.impl.DefinicoesServicos.PARTIDO;
import static org.eleicoesabertas.util.RecursosUtil.trataErro;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;

import org.eleicoesabertas.db.GeneralDao;
import org.eleicoesabertas.model.Candidato;
import org.eleicoesabertas.model.Resultados;
import org.eleicoesabertas.recursos.CandidatoRecurso;

//TODO evitar esse monte de chamada ao dao.busca*, muito feio isso...

public class CandidatoRecursoImpl implements CandidatoRecurso {

	GeneralDao dao;

	@PathParam(ANO_ELEICAO)
	String anoEleicao;
	@QueryParam("pagina")
	int pgNum;

	public CandidatoRecursoImpl() {
		
		// TODO: you know... remove it and start using CDI
		dao = new GeneralDao();
	}

	public Candidato obterCandidatoPorId(@PathParam("id") int id) {
		try {
			return dao.obterCandidato(dao.buscaEleicao(anoEleicao),id);
		} catch (Exception e) {
			throw trataErro(e);
		}
	}

	@SuppressWarnings("unchecked")
	public Resultados obterCandidatoPorEstado(@PathParam(ESTADO) String uf) {
		try {
			uf = uf.toUpperCase();
			List<Candidato> candidatos = new ArrayList<Candidato>();
			Resultados r;
			candidatos = (List<Candidato>) dao.retornaDadosPorNamedQuery(
					"buscaCandidatoPorEleicaoEEstado", pgNum,
					dao.buscaEleicao(anoEleicao), dao.buscaEstado(uf));
			r = new Resultados(pgNum, candidatos.size(), "Todos Candidatos de "
					+ uf.toUpperCase(), candidatos);
			return r;
		} catch (Exception e) {
			throw trataErro(e);
		}
	}

	@SuppressWarnings("unchecked")
	public Resultados obterCandidatosEleitosPorEstado(
			@PathParam(ESTADO) String uf) {
		try {
			uf = uf.toUpperCase();
			List<Candidato> candidatos = new ArrayList<Candidato>();
			Resultados r;
			candidatos = (List<Candidato>) dao.retornaDadosPorNamedQuery(
					"buscaCandidatoEleitoPorEleicaoEEstado", pgNum,
					dao.buscaEleicao(anoEleicao), dao.buscaEstado(uf));
			r = new Resultados(pgNum, candidatos.size(),
					"Todos Candidatos eleitos de " + uf.toUpperCase(),
					candidatos);
			return r;
		} catch (Exception e) {
			throw trataErro(e);
		}
	}

	@SuppressWarnings("unchecked")
	public Resultados obterTodosCandidato() {
		try {
			List<Candidato> candidatos = new ArrayList<Candidato>();
			Resultados r;

			candidatos = (List<Candidato>) dao.retornaDadosPorNamedQuery(
					"buscaCandidatoPorEleicao", pgNum,
					dao.buscaEleicao(anoEleicao));
			r = new Resultados(pgNum, candidatos.size(), "Todos Candidatos",
					candidatos);
			r.setTotalResultados(dao.conta("Candidato"));
			return r;
		} catch (Exception e) {

			throw trataErro(e);
		}
	}

	@SuppressWarnings("unchecked")
	public Resultados obterTodosCandidatosEleitos() {
		try {
			List<Candidato> candidatos = new ArrayList<Candidato>();
			Resultados r;
			candidatos = (List<Candidato>) dao.retornaDadosPorNamedQuery(
					"buscaCandidatoEleitoPorEleicao", pgNum,
					dao.buscaEleicao(anoEleicao));
			r = new Resultados(pgNum, candidatos.size(),
					"Todos Candidatos eleitos", candidatos);
			r.setTotalResultados(dao.conta("Candidato"));
			return r;
		} catch (Exception e) {
			throw trataErro(e);
		}
	}

	@SuppressWarnings("unchecked")
	public Resultados candidatosRegiaoCargo(@PathParam(ESTADO) String strUf,
			@PathParam(CARGO) String strCargo) {
		try {
			List<Candidato> candidatos = new ArrayList<Candidato>();
			Resultados r;
			candidatos = (List<Candidato>) dao.retornaDadosPorNamedQuery(
					"buscaCandidatoPorEleicaoEEstadoECargo", pgNum,
					dao.buscaEleicao(anoEleicao),
					dao.buscaEstado(strUf.toUpperCase()),
					dao.buscaCargo(strCargo.toUpperCase()));
			r = new Resultados(pgNum, candidatos.size(),
					"Candidatos para o cargo " + strCargo + " ("
							+ strUf.toUpperCase() + ")", candidatos);

			return r;
		} catch (Exception e) {
			throw trataErro(e);
		}
	}

	@SuppressWarnings("unchecked")
	public Resultados candidatosRegiaoCargoPartido(
			@PathParam(ESTADO) String strUf, @PathParam(CARGO) String strCargo,
			@PathParam(PARTIDO) String strPartido) {
		try {
			List<Candidato> candidatos = new ArrayList<Candidato>();
			Resultados r;
			candidatos = (List<Candidato>) dao.retornaDadosPorNamedQuery(
					"buscaCandidatoPorEleicaoEEstadoECargoEPartido", pgNum,
					dao.buscaEleicao(anoEleicao),
					dao.buscaEstado(strUf.toUpperCase()),
					dao.buscaCargo(strCargo), dao.buscaPartido(strPartido));
			r = new Resultados(pgNum, candidatos.size(),
					"Candidatos para o cargo " + strCargo + " ("
							+ strUf.toUpperCase() + ") do partido "
							+ strPartido.toUpperCase(), candidatos);
			return r;
		} catch (Exception e) {
			throw trataErro(e);
		}
	}

	@SuppressWarnings("unchecked")
	public Resultados candidatosEleitosRegiaoCargoPartido(
			@PathParam(ESTADO) String strUf, @PathParam(CARGO) String strCargo,
			@PathParam(PARTIDO) String strPartido) {
		try {
			List<Candidato> candidatos = new ArrayList<Candidato>();
			Resultados r;
			candidatos = (List<Candidato>) dao.retornaDadosPorNamedQuery(
					"buscaCandidatoEleitoPorEleicaoEEstadoECargoEPartido",
					pgNum, dao.buscaEleicao(anoEleicao),
					dao.buscaEstado(strUf.toUpperCase()),
					dao.buscaCargo(strCargo), dao.buscaPartido(strPartido));
			r = new Resultados(pgNum, candidatos.size(),
					"Candidatos eleitos para o cargo " + strCargo + " ("
							+ strUf.toUpperCase() + ") do partido "
							+ strPartido.toUpperCase(), candidatos);

			return r;
		} catch (Exception e) {
			throw trataErro(e);
		}
	}

	@SuppressWarnings("unchecked")
	public Resultados candidatosEleitosRegiaoCargo(
			@PathParam(ESTADO) String strUf, @PathParam(CARGO) String strCargo) {
		try {
			List<Candidato> candidatos = new ArrayList<Candidato>();
			Resultados r;
			candidatos = (List<Candidato>) dao.retornaDadosPorNamedQuery(
					"buscaCandidatoEleitoPorEleicaoEEstadoECargo", pgNum,
					dao.buscaEleicao(anoEleicao),
					dao.buscaEstado(strUf.toUpperCase()),
					dao.buscaCargo(strCargo.toUpperCase()));
			r = new Resultados(pgNum, candidatos.size(),
					"Candidatos eleitos para o cargo " + strCargo + " ("
							+ strUf.toUpperCase() + ")", candidatos);

			return r;
		} catch (Exception e) {
			e.printStackTrace();
			throw trataErro(e);
		}
	}

	public Resultados busca(@QueryParam("nome") String nome,
			@QueryParam(PARTIDO) String strPartido,
			@QueryParam(CARGO) String strCargo, @QueryParam("uf") String strUf,
			@QueryParam("resultadoEleicao") String strResultado) {
		try {
			return dao.busca(nome, strPartido, strCargo, strUf, strResultado,
					pgNum);
		} catch (Exception e) {
			throw trataErro(e);
		}
	}

}
