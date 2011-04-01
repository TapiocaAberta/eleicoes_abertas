import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.eleicoesabertas.model.*;
import org.junit.After;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TesteEntidadesBasicas {
	EntityManagerFactory emf;
	EntityManager em;

	String teste1 = "T1";
	String teste2 = "T2";

	@Before
	public void initEmfAndEm() {
		emf = Persistence.createEntityManagerFactory("default");
		em = emf.createEntityManager();
	}

	@After
	public void cleanup() {
		em.close();
	}

	@Test
	public void testandoCargo() {
		acoesComuns(new Cargo(teste1), new Cargo(teste2),
				"select c from Cargo c");
	}

	@Test
	public void testandoEstado() {
		acoesComuns(new Estado(teste1, "TES"), new Estado(teste2, "TIS"),
				"select e from Estado e");
	}

	@Test
	public void testandoEstadoCivil() {
		acoesComuns(new EstadoCivil(teste1), new EstadoCivil(teste2),
				"select e from EstadoCivil e");
	}

	@Test
	public void testandoGrauInstrucao() {
		acoesComuns(new GrauInstrucao(teste1), new GrauInstrucao(teste2),
				"select g from GrauInstrucao g");
	}

	@Test
	public void testandoNacionalidade() {
		acoesComuns(new Nacionalidade(teste1), new Nacionalidade(teste2),
				"select n from Nacionalidade n");
	}

	@Test
	public void testandoOcupacao() {
		acoesComuns(new Ocupacao(teste1), new Ocupacao(teste2),
				"select o from Ocupacao o");
	}

	@Test
	public void testandoPartido() {
		acoesComuns(new Partido(teste1, "A"), new Partido(teste2, "B"),
				"select p from Partido p");
	}

	@Test
	public void testandoResultadoEleicao() {
		acoesComuns(new ResultadoEleicao(teste1), new ResultadoEleicao(teste2),
				"select r from ResultadoEleicao r");
	}
	

	@Test
	public void testandoSexo() {
		acoesComuns(new Sexo(teste1), new Sexo(teste2), "select s from Sexo s");
	}

	@Test
	public void testandoSituacao() {
		acoesComuns(new Situacao(teste1), new Situacao(teste2),
				"select s from Situacao s");
	}

	@Test
	public void testandoEleicao() {
		acoesComuns(new Eleicao(teste1), new Eleicao(teste2),
				"select e from Eleicao e");
	}

	@Test
	public void testandoCidade() {
		Estado e = new Estado("São Paulo", "SP");
		em.getTransaction().begin();
		em.persist(e);
		em.getTransaction().commit();
		acoesComuns(new Cidade(teste1, e), new Cidade(teste2, e),
				"select c from Cidade c");

		em.getTransaction().begin();
		em.remove(e);
		em.getTransaction().commit();
	}
	
	@Test
	public void testandoColigacao() {
		Partido p1 = new Partido("DD", "66");
		Partido p2 = new Partido("EE", "55");
		em.getTransaction().begin();
		em.persist(p1);
		em.persist(p2);
		em.getTransaction().commit();

		@SuppressWarnings("unchecked")
		List<Partido> partidos = em.createQuery("select p from Partido p")
				.getResultList();

		Coligacao c1 = new Coligacao(teste1, partidos);
		em.getTransaction().begin();
		em.persist(c1);
		em.getTransaction().commit();		
		
		em.getTransaction().begin();
		em.remove(c1);
		em.remove(p1);
		em.remove(p2);
		em.getTransaction().commit();
		
	}

	@SuppressWarnings("unchecked")
	public void acoesComuns(Object o1, Object o2, String busca) {
		em.getTransaction().begin();
		em.persist(o1);
		em.persist(o2);
		em.getTransaction().commit();
		List<Object> objetos = em.createQuery(busca).getResultList();
		assertTrue(objetos.size() == 2);

		em.getTransaction().begin();
		for (Object o : objetos) {
			assertTrue(o.toString().equals(teste1)
					|| o.toString().equals(teste2));
			em.remove(o);
		}
		em.getTransaction().commit();

		objetos = em.createQuery(busca).getResultList();
		assertTrue(objetos.size() == 0);
	}
}
