package org.eleicoesabertas.carga;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import javax.persistence.EntityManager;

import org.eleicoesabertas.model.Eleicao;
import org.eleicoesabertas.model.Estado;
import org.eleicoesabertas.util.EmUtil;

public class CarregaDadosDefinicao {
	static EntityManager em;

	public static void carga() throws IOException {

		Scanner sc = new Scanner(System.in);
		System.out
				.println("Entre o caminho para arquivo de Estados. Formato: nome;sigla");
		String path = sc.nextLine();
		// Carrega estados no banco de dados
		if (path == null || path.trim().equals("")) {
			System.out
					.println("Entre um caminho correto... Dados de estado não carregados. Saindo");
			System.exit(0);
		}
		BufferedReader bf = null;
		try {
			bf = new BufferedReader(new FileReader(new File(path)));
		} catch (Exception e) {
			System.out
					.println("Erro ao carregar arquivo em "+path);
			System.exit(0);
		}

		em = EmUtil.getEntityManager();
		em.getTransaction().begin();

		String linha;

		// linha por linha
		while ((linha = bf.readLine()) != null) {
			String dadosEstado[] = linha.split("\\;");
			Estado e = new Estado(dadosEstado[0], dadosEstado[1]);
			System.out.println(dadosEstado[0] + " - " + dadosEstado[1]);
			em.persist(e);
		}

		System.out.println("Estados Carregados com sucesso!");

		em.persist(new Eleicao("2010"));
		System.out.println("Eleição adicionada com sucesso!");
		em.getTransaction().commit();
	}

}
