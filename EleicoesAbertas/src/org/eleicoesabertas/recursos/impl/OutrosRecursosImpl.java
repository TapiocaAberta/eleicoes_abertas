package org.eleicoesabertas.recursos.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.ws.rs.QueryParam;

import org.eleicoesabertas.db.GeneralDao;
import org.eleicoesabertas.model.Cargo;
import org.eleicoesabertas.model.Coligacao;
import org.eleicoesabertas.model.Ocupacao;
import org.eleicoesabertas.model.Partido;
import org.eleicoesabertas.model.ResultadoEleicao;
import org.eleicoesabertas.model.Situacao;
import org.eleicoesabertas.recursos.OutrosRecursos;
import org.eleicoesabertas.util.EmUtil;

public class OutrosRecursosImpl implements OutrosRecursos {

	GeneralDao dao;
	@QueryParam("pagina")
	int pgNum;

	EntityManager em;

	public OutrosRecursosImpl() {
		super();
		em = EmUtil.getEntityManager();
		dao = new GeneralDao();
	}

	public List<Cargo> cargos() {
		@SuppressWarnings("unchecked")
		List<Cargo> cargos = (List<Cargo>) dao.executaQueryPaginada(
				em.createQuery("SELECT c FROM Cargo c"), pgNum);
		return cargos;
	}

	public List<Partido> partidos() {
		@SuppressWarnings("unchecked")
		List<Partido> partidos = (List<Partido>) dao.executaQueryPaginada(
				em.createQuery("SELECT p FROM Partido p"), pgNum);
		return partidos;
	}

	public List<Coligacao> coligacoes() {
		@SuppressWarnings("unchecked")
		List<Coligacao> coligacoes = (List<Coligacao>) dao
				.executaQueryPaginada(
						em.createQuery("SELECT c FROM Coligacao c"), pgNum);
		return coligacoes;

	}

	public List<Situacao> situacoes() {
		@SuppressWarnings("unchecked")
		List<Situacao> situacoes = (List<Situacao>) dao.executaQueryPaginada(
				em.createQuery("SELECT s FROM Situacao s"), pgNum);
		return situacoes;
	}

	public List<ResultadoEleicao> resultadosEleicao() {
		@SuppressWarnings("unchecked")
		List<ResultadoEleicao> resultadoEleicoes = (List<ResultadoEleicao>) dao
				.executaQueryPaginada(
						em.createQuery("SELECT r FROM ResultadoEleicao r"),
						pgNum);
		return resultadoEleicoes;

	}

	public List<Ocupacao> ocupacoes() {
		@SuppressWarnings("unchecked")
		List<Ocupacao> ocupacoes = (List<Ocupacao>) dao.executaQueryPaginada(
				em.createQuery("SELECT o FROM Ocupacao o"), pgNum);
		return ocupacoes;

	}
}
