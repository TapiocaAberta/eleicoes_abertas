package org.eleicoesabertas.db;
 


import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.eleicoesabertas.model.Candidato;
import org.eleicoesabertas.model.Cargo;
import org.eleicoesabertas.model.Eleicao;
import org.eleicoesabertas.model.Erro;
import org.eleicoesabertas.model.Estado;
import org.eleicoesabertas.model.Partido;
import org.eleicoesabertas.model.ResultadoEleicao;
import org.eleicoesabertas.model.Resultados;
import org.eleicoesabertas.recursos.impl.DefinicoesServicos;
import org.eleicoesabertas.util.EmUtil;
import org.eleicoesabertas.util.RecursosUtil;

/**
 * 
 * This class is responsible to perfom all operation with the database in a stateles way. All.
 * @author William Ant�nio
 *
 */

public class CandidatosDao {

	EntityManager em;
	
	public CandidatosDao(){
		em = EmUtil.getEntityManager();
	}

	public ResultadoEleicao buscaResultadoEleicao(String strResultado) {
		ResultadoEleicao resultadoEleicao = null;
		try {
			resultadoEleicao = (ResultadoEleicao) em
					.createNamedQuery("buscaResultadoEleicaoPorNome")
					.setParameter(1, strResultado).getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
			lancaErroPorExcecao(e, "Resultado de eleição não encontrado!");
		}
		return resultadoEleicao;
	}

	public Eleicao buscaEleicao(String anoEleicao) {
		Eleicao eleicao = null;
		try {
			eleicao = (Eleicao) em.createNamedQuery("buscaEleicaoPorAno")
					.setParameter(1, anoEleicao).getSingleResult();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			lancaErroPorExcecao(e, "Dados para essa eleição não existentes!");
		}
		return eleicao;
	}

	public Object buscaUm(String oq, String campo, String valor,
			String nomeAmigavel) {
		Object obj = null;
		try {
			obj = em.createQuery(
					"SELECT o FROM " + oq + " WHERE o." + campo + " = ?1")
					.setParameter(1, valor).getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
			lancaErroPorExcecao(e, "Dados para " + nomeAmigavel + " igual a "
					+ valor + " não existentes!");
		}
		return obj;
	}

	public Candidato obterCandidato(int id) {
		Candidato candidato = null;
		try {
			candidato = (Candidato) em.createNamedQuery("buscaCandidatoPorId")
					.setParameter(1, id).getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
			lancaErroPorExcecao(e, "N�o encontrado candidato com esse ID");
		}
		return candidato;
	}

	public Partido buscaPartido(String strPartido) {
		Partido partido = null;
		try {
			partido = (Partido) em.createNamedQuery("buscaPartidoPorSigla")
					.setParameter(1, strPartido).getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
			lancaErroPorExcecao(e, "Partido não encontrado");
		}
		return partido;
	}

	public Estado buscaEstado(String uf) {
		Estado estado = null;
		try {
			estado = (Estado) em.createNamedQuery("buscaEstadoPorUF")
					.setParameter(1, uf).getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
			lancaErroPorExcecao(e, "Estado não encontrado");
		}
		return estado;
	}

	public Cargo buscaCargo(String strCargo) {
		Cargo cargo = null;
		try {
			cargo = (Cargo) em.createNamedQuery("buscaCargoPorNome")
					.setParameter(1, strCargo).getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
			lancaErroPorExcecao(e, "Cargo não encontrado");
		}
		return cargo;
	}

	public List<?> retornaDadosPorNamedQuery(String namedQuery, int pgNum,
			Object... parametros) {
		Query q = em.createNamedQuery(namedQuery);
		for (int i = 0; i < parametros.length; i++) {
			q.setParameter(i + 1, parametros[i]);
		}
		return executaQueryPaginada(q, pgNum);
	}

	public List<?> executaQueryPaginada(Query q, int pgNum) {
		pgNum -= 1;
		q.setMaxResults(DefinicoesServicos.TAMANHO_PAGINA);
		if (pgNum > 0)
			q.setFirstResult(pgNum * DefinicoesServicos.TAMANHO_PAGINA);
		return q.getResultList();
	}

	public long conta(String classe) {
		return (Long) em
				.createQuery("SELECT COUNT(c.id) FROM " + classe + " c")
				.getSingleResult();
	}

	public void lancaErro(String mensagem, Status status) {
		throw new WebApplicationException(Response.status(status)
				.entity(new Erro(mensagem)).build());
	}

	@SuppressWarnings("unchecked")
	public Resultados busca(String nome, String strPartido, String strCargo,
			String strUf, String strResultado, int pgNum) {	
		Partido p = null;
		Cargo c = null;
		Estado e = null;
		ResultadoEleicao rs = null; //rsrsrsrsrsrsrs
		ArrayList<Object> parametros = new ArrayList<Object>();

		if (RecursosUtil.checa(nome, 3))
			RecursosUtil
					.lancaErro("Erro no parâmetro Nome. Você deve informar um nome com largura maior que 3");

		// Verifica quais parâmetros foram enviados
		if (!RecursosUtil.checa(strPartido))
			p = buscaPartido(strPartido);
		if (!RecursosUtil.checa(strCargo))
			c = buscaCargo(strCargo);
		if (!RecursosUtil.checa(strUf))
			e = buscaEstado(strUf);
		if (!RecursosUtil.checa(strResultado))
			rs = buscaResultadoEleicao(strResultado);		
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
	
	
	public void lancaErro(String mensagem) {
		lancaErro(mensagem, Status.BAD_REQUEST);
	}

	public void lancaErroPorExcecao(Exception e, String mensagem) {
		if (e instanceof javax.persistence.NoResultException)
			lancaErro(mensagem, Status.BAD_REQUEST);
		else
			lancaErro(
					"Erro conectando ao banco de dados, desculpe-nos o incômodo. Contate o administrador do sistema se o erro persistir...",
					Status.INTERNAL_SERVER_ERROR);

	}
	


}
