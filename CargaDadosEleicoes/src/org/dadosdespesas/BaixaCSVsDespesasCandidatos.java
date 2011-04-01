package org.dadosdespesas;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Node;
import org.eleicoesabertas.util.ArquivoUtil;
import org.eleicoesabertas.util.DocumentUtil;
import org.eleicoesabertas.util.URLsTSEUtl;

public class BaixaCSVsDespesasCandidatos {
	public static void main(String[] args) throws MalformedURLException,
			IOException, InterruptedException {
		File dir = new File("./candidatosDespesas/xml/");
		System.out.println("Iniciando processo...");
		String estado;
		for (File arquivo : dir.listFiles()) {
			estado = arquivo.getName().split("_")[0];
			// if (!estado.equals("AC")) continue;
			String xPathSqCand = "//response/sqCand";
			Document document = DocumentUtil.getDocument(arquivo.getPath());
			@SuppressWarnings("unchecked")
			List<Node> nodes = document.selectNodes(xPathSqCand);
			for (Node node : nodes) {
				String numeroCandidato = node.getText();
				String nomeArquivoSaida = "/home/william/csv/" + estado + "/"
						+ numeroCandidato + "_" + estado + ".csv";
				if (!(new File(nomeArquivoSaida)).exists())
					try {
						System.out.print("Iniciando arquivo "
								+ nomeArquivoSaida + "...");
						ArquivoUtil.baixaArquivo(
								URLsTSEUtl.obterUrlCSVArrecadao(numeroCandidato, estado),
								nomeArquivoSaida);
						System.out.println("Terminado todos CSVs");
					} catch (Exception e) {
						System.out.println(">>>Erro baixando/gravando arquivo "
								+ URLsTSEUtl.obterUrlCSVArrecadao(numeroCandidato, estado) + ", xml "
								+ arquivo.getName());
					}

			}

		}
		System.out.println("FIM!");

	}




}
