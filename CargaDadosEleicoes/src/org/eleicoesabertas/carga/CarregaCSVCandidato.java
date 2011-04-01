package org.eleicoesabertas.carga;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

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
import org.eleicoesabertas.util.EmUtil;

/*
 realizar o parse do CSV como abaixo

 Campo							Tabela Banco		Regra

 Nome Completo      			Candidato    		Sempre novo
 Nome para Urna Eletronica		Candidato			Sempre novo
 Numero do Candidato			Candidato    		Sempre novo
 Situacao						Situacao			Pegar existente ou criar
 Sigla do Partido				Partido				Pegar existente ou criar
 Numero do Partido				Partido				Atrelado ao partido buscado
 Nome da Coligacao				Coligacao			Pegar existente ou criar
 Composicao da Coligacao    	Coligacao			No objeto Coligação. Quando criar, usar cada partido
 Cargo							Cargo				Pegar existente ou criar
 Sexo							Sexo				Pegar existente ou criar
 Data de Nascimento				Candidato			Sempre novo
 Estado Civil					estado_civil		Pegar existente ou criar
 Ocupacao						ocupacao			Pegar existente ou criar
 Grau de Instrucao				grau_instrucao		Pegar existente ou criar
 Nacionalidade					nacionalidade		Pegar existente ou criar
 Municipio/UF de Nascimento		Cidade/Estado		Pegar existente ou criar
 Julgamento da Prest. de Contas	-
 Resultado da Eleicao			resultado_eleicao	Pegar existente ou criar
 Numero Protocolo				Candidato			Sempre novo
 Numero Processo				Candidato			Sempre novo
 CNPJ de Campanha				Candidato			Sempre novo
 */

public class CarregaCSVCandidato {

	static EntityManager em;

	final static String ELEICAO = "2010";

	/*
	 * - PArâmetros: 1 - Estado (Entrar BR para candidatos de todo o Brasil) 2 -
	 * path do a ser carregado arquivo 3 - (opcional) 1 para carregar os dados
	 * de definição
	 */
	public static void main(String caminhoArquivo, String ufDados,
			boolean carregarDefinicoes) throws IOException {
		/*
		 * try { ufDados = args[0]; caminhoArquivo = args[1]; } catch (Exception
		 * e1) { System.out.println("Entre os parâmetros corretos! Use:");
		 * System.out.println("caminhoArquivo ufDosCandidatos"); System.exit(0);
		 * }
		 */

		if (carregarDefinicoes) {
			CarregaDadosDefinicao.carga();
		}

		BufferedReader bf = new BufferedReader(new FileReader(new File(
				caminhoArquivo)));

		em = EmUtil.getEntityManager();
		em.getTransaction().begin();
		// Busca do estado para essa carga
		Estado estado = (Estado) buscaUm("buscaEstadoPorUF",
				ufDados.toUpperCase());

		try {
			String linha;

			// Pulando linha de cabeçalho
			bf.readLine();
			// linha por linha
			while ((linha = bf.readLine()) != null) {
				String[] colunas = new String(linha.getBytes(), "UTF-8")
						.split("\\;");
				Candidato c = new Candidato();
				for (int i = 0; i < colunas.length; i++) {
					colunas[i] = colunas[i].replaceAll("\\\"", "");
				}
				Situacao s = (Situacao) buscaUm("buscaSituacaoPorNome",
						colunas[3]);
				if (s == null) {
					em.persist(s = new Situacao(colunas[3]));
				}

				Partido p = (Partido) buscaUm("buscaPartidoPorSigla",
						colunas[4]);
				if (p == null) {
					em.persist(p = new Partido(colunas[4], colunas[5]));
				} else {
					// atualiza o número sempre, pois o partido pode ter sido
					// adicionado antes pela coligação
					p.setNumero(colunas[5]);
					em.persist(p);
				}

				Coligacao col = (Coligacao) buscaUm("buscaColigacaoPorNome",
						colunas[6]);
				if (col == null) {
					String strPartidos[] = colunas[7].split("\\/");
					List<Partido> partidos = new ArrayList<Partido>();
					for (int i = 0; i < strPartidos.length; i++) {
						partidos.add(buscarOuAddPartido(strPartidos[i].trim()));
					}
					em.persist(col = new Coligacao(colunas[6], partidos));
				}

				Cargo cargo = (Cargo) buscaUm("buscaCargoPorNome", colunas[8]);
				if (cargo == null) {
					em.persist(cargo = new Cargo(colunas[8]));
				}

				Sexo sexo = (Sexo) buscaUm("buscaSexoPorNome", colunas[9]);
				if (sexo == null) {
					em.persist(sexo = new Sexo(colunas[9]));
				}

				// para realizar o parse da data 1966-11-24
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				Date dataNascimento = formatter.parse(colunas[10]);

				EstadoCivil ec = (EstadoCivil) buscaUm(
						"buscaEstadoCivilPorNome", colunas[11]);
				if (ec == null) {
					em.persist(ec = new EstadoCivil(colunas[11]));
				}

				Ocupacao oc = (Ocupacao) buscaUm("buscaOcupacaoPorNome",
						colunas[12]);
				if (oc == null) {
					em.persist(oc = new Ocupacao(colunas[12]));
				}

				GrauInstrucao gi = (GrauInstrucao) buscaUm(
						"buscaGrauInstrucaoPorNome", colunas[13]);
				if (gi == null) {
					em.persist(gi = new GrauInstrucao(colunas[13]));
				}

				Nacionalidade nac = (Nacionalidade) buscaUm(
						"buscaNacionalidadePorNome", colunas[14]);
				if (nac == null) {
					em.persist(nac = new Nacionalidade(colunas[14]));
				}

				String municipioUF[] = colunas[15].split("\\/");
				// elimina ultimo espaço em branco
				String strMunicipio = municipioUF[0].substring(0,
						municipioUF[0].length() - 1);
				String strUF = municipioUF[1].trim();

				Cidade cid = (Cidade) buscaUm("buscaCidadePorNome",
						strMunicipio);
				if (cid == null) {
					// TODOS OS ESTADOS DEVERÃO SER CARREGADOS ANTES!
					Estado es = (Estado) buscaUm("buscaEstadoPorUF", strUF);
					cid = new Cidade(strMunicipio, es);
					em.persist(cid);
				}

				// não usaremos a coluna 17 Julgamento da Prest. de
				// Contas(indice 16)!

				ResultadoEleicao res = (ResultadoEleicao) buscaUm(
						"buscaResultadoEleicaoPorNome", colunas[17]);
				if (res == null) {
					em.persist(res = new ResultadoEleicao(colunas[17]));
				}

				c.setNome(colunas[0]);
				c.setNomeUrna(colunas[1]);
				c.setNumeroEleicao(colunas[2]);
				c.setSituacao(s);
				c.setPartido(p);
				c.setColigacao(col);
				c.setCargo(cargo);
				c.setSexo(sexo);
				c.setDataNascimento(dataNascimento);
				c.setEstadoCivil(ec);
				c.setOcupacao(oc);
				c.setGrauInstrucao(gi);
				c.setNacionalidade(nac);
				c.setCidadeNascimento(cid);
				c.setResultadoEleicao(res);
				c.setProtocolo(colunas[18]);
				c.setProcesso(colunas[19]);
				try {
					// As vezes a coluna CNPJ vem vazia, bem...
					c.setCnpjCampanha(colunas[20]);
				} catch (Exception e) {
					c.setCnpjCampanha(null);
				}
				c.setEstado(estado);
				c.setEleicao((Eleicao) buscaUm("buscaEleicaoPorAno", ELEICAO));
				em.persist(c);
				System.out.println(colunas[0] + " Carregado");

			}
			// Estamos no tudo ou nada aqui, ou subimos todo o arquivo ou volta
			// tudo
		} catch (Exception e) {
			em.getTransaction().rollback();
			System.out.println("Erro carregando arquivo" + caminhoArquivo);
			e.printStackTrace();
			System.exit(0);
		}
		em.getTransaction().commit();
		System.out.println("Dados carregados com sucesso ^^! ");
	}

	static Object buscaUm(String q, String v) {
		try {
			return em.createNamedQuery(q).setParameter(1, v).getSingleResult();
			// provavelmente para cair aqui não haverá resultados e tals;
		} catch (Exception e) {
			if (e instanceof javax.persistence.NoResultException)
				return null;
			else {
				e.printStackTrace();
				em.getTransaction().rollback();
				System.exit(0);
				// lol
				return null;
			}
		}
	}

	static Partido buscarOuAddPartido(String sigla) {
		Partido p = (Partido) buscaUm("buscaPartidoPorSigla", sigla);
		// Partido não tem no banco, iremos adicionar com um número que é a
		// sigla msm, para salvar
		if (p == null) {
			p = new Partido(sigla, "XXX");
			em.persist(p);
		}
		return p;
	}

}
