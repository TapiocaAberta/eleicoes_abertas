package org.eleicoesabertas.recursos.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;

import org.eleicoesabertas.db.CandidatosDao;
import org.eleicoesabertas.model.Candidato;
import org.eleicoesabertas.model.Resultados;
import org.eleicoesabertas.recursos.CandidatoRecurso;

public class CandidatoRecursoImpl implements CandidatoRecurso {

	CandidatosDao dao;

	@PathParam("anoEleicao")
	String anoEleicao;
	@QueryParam("pagina")
	int pgNum;

	public CandidatoRecursoImpl() {
		// TODO: you know... remove it and start using CDI
		dao = new CandidatosDao();
	}

	public Candidato obterCandidatoPorId(@PathParam("id") int id) {
		return dao.obterCandidato(id);
	}

	@SuppressWarnings("unchecked")
	public Resultados obterCandidatoPorEstado(@PathParam("estado") String uf) {
		uf = uf.toUpperCase();
		List<Candidato> candidatos = new ArrayList<Candidato>();
		Resultados r;
		candidatos = (List<Candidato>) dao.retornaDadosPorNamedQuery(
				"buscaCandidatoPorEleicaoEEstado", pgNum,
				dao.buscaEleicao(anoEleicao), dao.buscaEstado(uf));
		r = new Resultados(pgNum, candidatos.size(), "Todos Candidatos de "
				+ uf.toUpperCase(), candidatos);
		return r;
	}

	@SuppressWarnings("unchecked")
	public Resultados obterCandidatosEleitosPorEstado(
			@PathParam("estado") String uf) {
		uf = uf.toUpperCase();
		List<Candidato> candidatos = new ArrayList<Candidato>();
		Resultados r;
		candidatos = (List<Candidato>) dao.retornaDadosPorNamedQuery(
				"buscaCandidatoEleitoPorEleicaoEEstado", pgNum,
				dao.buscaEleicao(anoEleicao), dao.buscaEstado(uf));
		r = new Resultados(pgNum, candidatos.size(),
				"Todos Candidatos eleitos de " + uf.toUpperCase(), candidatos);
		return r;
	}

	@SuppressWarnings("unchecked")
	public Resultados obterTodosCandidato() {
		List<Candidato> candidatos = new ArrayList<Candidato>();
		Resultados r;

		candidatos = (List<Candidato>) dao
				.retornaDadosPorNamedQuery("buscaCandidatoPorEleicao", pgNum,
						dao.buscaEleicao(anoEleicao));
		r = new Resultados(pgNum, candidatos.size(), "Todos Candidatos",
				candidatos);
		r.setTotalResultados(dao.conta("Candidato"));
		return r;
	}

	@SuppressWarnings("unchecked")
	public Resultados obterTodosCandidatosEleitos() {
		List<Candidato> candidatos = new ArrayList<Candidato>();
		Resultados r;
		candidatos = (List<Candidato>) dao.retornaDadosPorNamedQuery(
				"buscaCandidatoEleitoPorEleicao", pgNum,
				dao.buscaEleicao(anoEleicao));
		r = new Resultados(pgNum, candidatos.size(),
				"Todos Candidatos eleitos", candidatos);
		r.setTotalResultados(dao.conta("Candidato"));
		return r;
	}

	@SuppressWarnings("unchecked")
	public Resultados candidatosRegiaoCargo(@PathParam("estado") String strUf,
			@PathParam("cargo") String strCargo) {
		List<Candidato> candidatos = new ArrayList<Candidato>();
		Resultados r;
		candidatos = (List<Candidato>) dao.retornaDadosPorNamedQuery(
				"buscaCandidatoPorEleicaoEEstadoECargo", pgNum,
				dao.buscaEleicao(anoEleicao),
				dao.buscaEstado(strUf.toUpperCase()),
				dao.buscaCargo(strCargo.toUpperCase()));
		r = new Resultados(pgNum, candidatos.size(), "Candidatos para o cargo "
				+ strCargo + " (" + strUf.toUpperCase() + ")", candidatos);

		return r;
	}

	@SuppressWarnings("unchecked")
	public Resultados candidatosRegiaoCargoPartido(
			@PathParam("estado") String strUf,
			@PathParam("cargo") String strCargo,
			@PathParam("partido") String strPartido) {
		List<Candidato> candidatos = new ArrayList<Candidato>();
		Resultados r;
		candidatos = (List<Candidato>) dao.retornaDadosPorNamedQuery(
				"buscaCandidatoPorEleicaoEEstadoECargoEPartido", pgNum,
				dao.buscaEleicao(anoEleicao),
				dao.buscaEstado(strUf.toUpperCase()), dao.buscaCargo(strCargo),
				dao.buscaPartido(strPartido));
		r = new Resultados(pgNum, candidatos.size(), "Candidatos para o cargo "
				+ strCargo + " (" + strUf.toUpperCase() + ") do partido "
				+ strPartido.toUpperCase(), candidatos);
		return r;
	}

	@SuppressWarnings("unchecked")
	public Resultados candidatosEleitosRegiaoCargoPartido(
			@PathParam("estado") String strUf,
			@PathParam("cargo") String strCargo,
			@PathParam("partido") String strPartido) {
		List<Candidato> candidatos = new ArrayList<Candidato>();
		Resultados r;
		candidatos = (List<Candidato>) dao.retornaDadosPorNamedQuery(
				"buscaCandidatoEleitoPorEleicaoEEstadoECargoEPartido", pgNum,
				dao.buscaEleicao(anoEleicao),
				dao.buscaEstado(strUf.toUpperCase()), dao.buscaCargo(strCargo),
				dao.buscaPartido(strPartido));
		r = new Resultados(pgNum, candidatos.size(),
				"Candidatos eleitos para o cargo " + strCargo + " ("
						+ strUf.toUpperCase() + ") do partido "
						+ strPartido.toUpperCase(), candidatos);

		return r;
	}

	@SuppressWarnings("unchecked")
	public Resultados candidatosEleitosRegiaoCargo(
			@PathParam("estado") String strUf,
			@PathParam("cargo") String strCargo) {
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
	}

	public Resultados busca(@QueryParam("nome") String nome,
			@QueryParam("partido") String strPartido,
			@QueryParam("cargo") String strCargo,
			@QueryParam("uf") String strUf,
			@QueryParam("resultadoEleicao") String strResultado) {
		return dao
				.busca(nome, strPartido, strCargo, strUf, strResultado, pgNum);
	}

}
