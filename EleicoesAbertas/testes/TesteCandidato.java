import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.eleicoesabertas.model.Candidato;
import org.eleicoesabertas.model.Cargo;
import org.eleicoesabertas.model.Cidade;
import org.eleicoesabertas.model.Coligacao;
import org.eleicoesabertas.model.Eleicao;
import org.eleicoesabertas.model.Estado;
import org.eleicoesabertas.model.EstadoCivil;
import org.eleicoesabertas.model.GrauInstrucao;
import org.eleicoesabertas.model.Nacionalidade;
import org.eleicoesabertas.model.Ocupacao;
import org.eleicoesabertas.model.Partido;
import org.eleicoesabertas.model.ResultadoEleicao;
import org.eleicoesabertas.model.Sexo;
import org.eleicoesabertas.model.Situacao;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class TesteCandidato {
	EntityManagerFactory emf;
	EntityManager em;

	Situacao situacao;
	Sexo sexo;
	Partido partido;
	Partido partido1;
	Cargo cargo;
	Coligacao coligacao;
	Nacionalidade nacionalidade;
	GrauInstrucao grauInstrucao;
	ResultadoEleicao resultadoEleicao;
	EstadoCivil estadoCivil;
	Cidade cidadeNascimento;
	Estado estado;
	Ocupacao ocupacao;
	Eleicao eleicao;

	@SuppressWarnings("unchecked")
	@Before
	public void initEmfAndEm() {
		emf = Persistence.createEntityManagerFactory("default");
		em = emf.createEntityManager();

		// preenche todas as classes básicas no banco para o candidato
		em.getTransaction().begin();
		em.persist(situacao = new Situacao("Situação!"));
		em.persist(sexo = new Sexo("SEXO!!"));
		em.persist(partido = new Partido("WW", "66"));
		em.persist(partido1 = new Partido("W1", "67"));
		List<Partido> partidos = em.createQuery("select p from Partido p")
				.getResultList();
		em.persist(coligacao = new Coligacao("COLIGACAOOO", partidos));
		em.persist(cargo = new Cargo("CARGO!!"));
		em.persist(nacionalidade = new Nacionalidade("NACIONALIDADE!!"));
		em.persist(grauInstrucao = new GrauInstrucao("GRAU INSTRUÇÃO!!"));
		em.persist(resultadoEleicao = new ResultadoEleicao("RESULTADO!!!"));
		em.persist(estadoCivil = new EstadoCivil("ESTADOO"));
		em.persist(estado = new Estado("SSAMPA", "SP"));
		em.persist(cidadeNascimento = new Cidade("", estado));
		em.persist(ocupacao = new Ocupacao("OCUPACAOOO"));
		em.persist(eleicao = new Eleicao("2010"));
		em.getTransaction().commit();

	}

	@After
	public void cleanup() {
		em.getTransaction().begin();
		em.remove(situacao);
		em.remove(sexo);
		em.remove(coligacao);
		em.remove(partido);
		em.remove(partido1);
		em.remove(cargo);
		em.remove(nacionalidade);
		em.remove(grauInstrucao);
		em.remove(resultadoEleicao);
		em.remove(estadoCivil);
		em.remove(cidadeNascimento);
		em.remove(estado);
		em.remove(ocupacao);
		em.remove(eleicao);
		em.getTransaction().commit();
		em.close();
	}

	@Test
	public void testaCandidato() {		
		Candidato c1 = new Candidato("João", "Jaummmm", "1233333", new Date().toString(),
				"123444444", "dklsdsd", "fdollk/32i3", situacao, sexo, partido,
				cargo, coligacao, nacionalidade, grauInstrucao,
				resultadoEleicao, estadoCivil, cidadeNascimento, estado,
				ocupacao, eleicao);

		//situacao = (Situacao) em.createQuery("select s from Situacao s").getResultList().get(0);
		Candidato c2 = new Candidato("Joãoff", "Jaummmmbb", "1233333vc",
				new Date().toString(), "123444444", "dklsdsd", "fdollk/32i3", situacao,
				sexo, partido, cargo, null, nacionalidade, grauInstrucao,
				resultadoEleicao, estadoCivil, cidadeNascimento, estado,
				ocupacao, eleicao);

		em.getTransaction().begin();
		em.persist(c1);
		em.persist(c2);
		em.getTransaction().commit();

		@SuppressWarnings("unchecked")
		List<Candidato> candidatos = em
				.createQuery("select c from Candidato c").getResultList();		
		
		assertTrue(candidatos.size() == 2);

		em.getTransaction().begin();
		em.remove(c1);
		em.remove(c2);
		em.getTransaction().commit();
	}
}
