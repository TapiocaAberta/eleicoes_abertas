package org.eleicoesabertas.recursos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.eleicoesabertas.model.Candidato;
import org.eleicoesabertas.model.Cargo;
import org.eleicoesabertas.model.Eleicao;
import org.eleicoesabertas.model.Estado;
import org.eleicoesabertas.model.Partido;
import org.eleicoesabertas.model.ResultadoEleicao;
import org.eleicoesabertas.util.EmUtil;

public class AcessoRecursos {
	static EntityManager em;
	static {
		em = EmUtil.getEntityManager();
	}

	public static ResultadoEleicao buscaResultadoEleicao (String strResultado) {
		ResultadoEleicao resultadoEleicao = null;
		try {
			resultadoEleicao = (ResultadoEleicao) em.createNamedQuery("buscaResultadoEleicaoPorNome")
					.setParameter(1, strResultado).getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
			RecursosUtil.lancaErroPorExcecao(e, "Resultado de eleição não encontrado!");
		}
		return resultadoEleicao;
	}
	
	public static Eleicao buscaEleicao(String anoEleicao) {
		Eleicao eleicao = null;
		try {
			eleicao = (Eleicao) em.createNamedQuery("buscaEleicaoPorAno")
					.setParameter(1, anoEleicao).getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
			RecursosUtil.lancaErroPorExcecao(e, "Dados para essa eleição não existentes!");
		}
		return eleicao;
	}	

	// TODO: TESTAR!!
	public static Object buscaUm(String oq, String campo, String valor,
			String nomeAmigavel) {
		Object obj = null;
		try {
			obj = em.createQuery(
					"SELECT o FROM " + oq + " WHERE o." + campo + " = ?1")
					.setParameter(1, valor).getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
			RecursosUtil.lancaErroPorExcecao(e, "Dados para " + nomeAmigavel + " igual a "
					+ valor + " não existentes!");
		}
		return obj;
	}

	public static Candidato obterCandidato(int id) {
		Candidato candidato = null;
		try {
			candidato = (Candidato) em.createNamedQuery("buscaCandidatoPorId")
					.setParameter(1, id).getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
			RecursosUtil.lancaErroPorExcecao(e, "Não encontrado candidato com esse ID");
		}
		return candidato;
	}

	public static Partido buscaPartido(String strPartido) {
		Partido partido = null;
		try {
			partido = (Partido) em.createNamedQuery("buscaPartidoPorSigla")
					.setParameter(1, strPartido).getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
			RecursosUtil.lancaErroPorExcecao(e, "Partido não encontrado");
		}
		return partido;
	}

	public static Estado buscaEstado(String uf) {
		Estado estado = null;
		try {
			estado = (Estado) em.createNamedQuery("buscaEstadoPorUF")
					.setParameter(1, uf).getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
			RecursosUtil.lancaErroPorExcecao(e, "Estado não encontrado");
		}
		return estado;
	}

	public static Cargo buscaCargo(String strCargo) {
		Cargo cargo = null;
		try {
			cargo = (Cargo) em.createNamedQuery("buscaCargoPorNome")
					.setParameter(1, strCargo).getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
			RecursosUtil.lancaErroPorExcecao(e, "Cargo não encontrado");
		}
		return cargo;
	}
	
	public static List<?> retornaDadosPorNamedQuery(String namedQuery,
			int pgNum, Object... parametros) {
		Query q = em.createNamedQuery(namedQuery);
		for (int i = 0; i < parametros.length; i++) {
			q.setParameter(i + 1, parametros[i]);
		}
		return executaQueryPaginada(q, pgNum);
	}

	public static List<?> executaQueryPaginada(Query q, int pgNum) {
		pgNum -= 1;		
		q.setMaxResults(DefinicoesServicos.TAMANHO_PAGINA);
		if (pgNum > 0)
			q.setFirstResult(pgNum * DefinicoesServicos.TAMANHO_PAGINA);
		return q.getResultList();
	}

	public static long conta(String classe) {		
		return (Long) em.createQuery("SELECT COUNT(c.id) FROM " + classe + " c")
				.getSingleResult();
	}

}