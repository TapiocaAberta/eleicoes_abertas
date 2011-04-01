import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.persistence.EntityManager;

import org.eleicoesabertas.model.*;
import org.eleicoesabertas.util.EmUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TesteNamedQuery {
	EntityManager em;

	final String T1 = "T1";
	final String T2 = "T2";

	@Before
	public void antes() {
		em = EmUtil.getEntityManager();
	}

	@After
	public void depois() {
		em.close();
	}

	@Test
	public void testes() {
		testeComum(new Cargo(T1), new Cargo(T2), "buscaCargoPorNome");
		testeComum(new Estado(T1, "A"), new Estado(T2, "B"),
				"buscaEstadoPorNome");
		testeComum(new Estado(T1, T1), new Estado(T2, T2),
		"buscaEstadoPorUF");
		testeComum(new Eleicao(T1), new Eleicao(T2),
		"buscaEleicaoPorAno");
		testeComum(new EstadoCivil(T1), new EstadoCivil(T2),
				"buscaEstadoCivilPorNome");
		testeComum(new GrauInstrucao(T1), new GrauInstrucao(T2),
				"buscaGrauInstrucaoPorNome");
		testeComum(new Nacionalidade(T1), new Nacionalidade(T2),
				"buscaNacionalidadePorNome");
		testeComum(new Ocupacao(T1), new Ocupacao(T2), "buscaOcupacaoPorNome");
		testeComum(new ResultadoEleicao(T1), new ResultadoEleicao(T2),
				"buscaResultadoEleicaoPorNome");
		testeComum(new Partido(T1, "321"), new Partido(T2, "123"),
				"buscaPartidoPorSigla");
		testeComum(new Sexo(T1), new Sexo(T2), "buscaSexoPorNome");
		testeComum(new Situacao(T1), new Situacao(T2), "buscaSituacaoPorNome");
		testaNamedQueriesCidade();
	}

	@SuppressWarnings("unchecked")
	public void testaNamedQueriesCidade() {
		Estado e1 = new Estado(T1, "A");
		em.getTransaction().begin();
		em.persist(e1);
		em.getTransaction().commit();

		Cidade c1 = new Cidade(T1, e1);
		Cidade c2 = new Cidade(T2, e1);

		testeComum(c1, c2, "buscaCidadePorNome");
		
		c1 = new Cidade(T1, e1);
		c2 = new Cidade(T2, e1);		
		em.getTransaction().begin();
		em.persist(c1);
		em.persist(c2);
		em.getTransaction().commit();

		List<Cidade> cidades = em.createNamedQuery("buscaCidadePorNomeEstado")
				.setParameter(1, T1).getResultList();
		System.out.println(cidades.size());
		assertTrue(cidades.size() == 2);
		em.getTransaction().begin();
		em.remove(c1);	em.remove(c2);
		em.remove(e1);
		em.getTransaction().commit();
	}

	private void testeComum(Object o1, Object o2, String namedQuery) {
		em.getTransaction().begin();
		em.persist(o1);
		em.persist(o2);
		em.getTransaction().commit();

		o1 = em.createNamedQuery(namedQuery).setParameter(1, T1)
				.getSingleResult();
		o2 = em.createNamedQuery(namedQuery).setParameter(1, T2)
				.getSingleResult();

		assertTrue(o1.toString().equals(T1));
		assertTrue(o2.toString().equals(T2));
		em.getTransaction().begin();
		em.remove(o1);
		em.remove(o2);
		em.getTransaction().commit();

	}
}
