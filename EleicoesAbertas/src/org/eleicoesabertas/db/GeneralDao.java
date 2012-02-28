package org.eleicoesabertas.db;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.eleicoesabertas.model.Candidato;
import org.eleicoesabertas.model.Cargo;
import org.eleicoesabertas.model.Eleicao;
import org.eleicoesabertas.model.Estado;
import org.eleicoesabertas.model.Partido;
import org.eleicoesabertas.model.ResultadoEleicao;
import org.eleicoesabertas.model.Resultados;
import org.eleicoesabertas.recursos.impl.DefinicoesServicos;
import org.eleicoesabertas.util.EmUtil;
import org.eleicoesabertas.util.RecursosUtil;

/**
 * 
 * This class is responsible to perfom all operation with the database in a
 * stateles way. All.
 * 
 * @author William Antônio
 * 
 */

public class GeneralDao {

	EntityManager em;
	Logger logger;

	public GeneralDao() {
		em = EmUtil.getEntityManager();
		logger = Logger.getLogger(this.getClass().getName());
	}

	/**
	 * Busca um resultado de eleição de acordo com o texto. Retorna nulo se nada
	 * for encontrado
	 * 
	 * @param strResultado
	 * @return
	 */
	public ResultadoEleicao buscaResultadoEleicao(String strResultado) {
		ResultadoEleicao resultadoEleicao = null;
		resultadoEleicao = (ResultadoEleicao) em
				.createNamedQuery("buscaResultadoEleicaoPorNome")
				.setParameter(1, strResultado).getSingleResult();
		return resultadoEleicao;
	}

	/**
	 *  Busca uma eleição. Retorna nulo se nada
	 * for encontrado
	 * @param anoEleicao
	 * @return
	 */
	public Eleicao buscaEleicao(String anoEleicao) {
		Eleicao eleicao = null;
		eleicao = (Eleicao) em.createNamedQuery("buscaEleicaoPorAno")
				.setParameter(1, anoEleicao).getSingleResult();
		return eleicao;
	}

	/**
	 * Busca um entidade qualquer, um resultado único, e retorna nulo se nada for encontrado
	 * @param oq
	 * @param campo
	 * @param valor
	 * @param nomeAmigavel
	 * @return
	 */
	public Object buscaUm(String oq, String campo, String valor,
			String nomeAmigavel) {
		Object obj = null;
		obj = em.createQuery(
				"SELECT o FROM " + oq + " WHERE o." + campo + " = ?1")
				.setParameter(1, valor).getSingleResult();
		return obj;
	}

	/**
	 * Tenta recuperar um candidato de ID id. Retorna nulo se nada for encontrado
	 * @param id
	 * @return
	 */
	public Candidato obterCandidato(Eleicao eleicao, int id) {
		Candidato candidato = null;
		candidato = (Candidato) em.createNamedQuery("buscaCandidatoPorId")
				.setParameter(1, eleicao).setParameter(2, id).getSingleResult();
		return candidato;
	}

	/**
	 * Tenta recuperar partido. Retorna nulo se nada for encontrado
	 * @param strPartido
	 * @return
	 */
	public Partido buscaPartido(String strPartido) {
		Partido partido = null;
		partido = (Partido) em.createNamedQuery("buscaPartidoPorSigla")
				.setParameter(1, strPartido).getSingleResult();
		return partido;
	}

	/**
	 * Tenta recuperar um estado. Retorna nulo se nada for encontrado
	 * @param uf
	 * @return
	 */
	public Estado buscaEstado(String uf) {
		Estado estado = null;
		estado = (Estado) em.createNamedQuery("buscaEstadoPorUF")
				.setParameter(1, uf).getSingleResult();
		return estado;
	}

	/**
	 * Tenta recuperar o cargo. Retorna nulo se nada for encontrado
	 * @param strCargo
	 * @return
	 */
	public Cargo buscaCargo(String strCargo) {
		Cargo cargo = null;
		cargo = (Cargo) em.createNamedQuery("buscaCargoPorNome")
				.setParameter(1, strCargo).getSingleResult();
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

	

	@SuppressWarnings("unchecked")
	public Resultados busca(String nome, String strPartido, String strCargo,
			String strUf, String strResultado, int pgNum) {
		Partido p = null;
		Cargo c = null;
		Estado e = null;
		ResultadoEleicao rs = null; // rsrsrsrsrsrsrs
		ArrayList<Object> parametros = new ArrayList<Object>();

		if (RecursosUtil.checa(nome, 3))
			RecursosUtil
					.lancaErro("Erro no parÃ¢metro Nome. VocÃª deve informar um nome com largura maior que 3");

		// Verifica quais parÃ¢metros foram enviados
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
		r.setTotalResultados(Long.parseLong(qContagem.getSingleResult()
				.toString()));
		return r;
	}	
}
