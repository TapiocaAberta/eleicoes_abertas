package org.eleicoesabertas.carga;

import java.io.IOException;
import java.net.MalformedURLException;

import org.eleicoesabertas.util.ArquivoUtil;
import org.eleicoesabertas.util.URLsTSEUtl;

public class BaixaArquivosTSE {

	/*
	 * Baixa todos os CSVs dos candidatos para as eleicoes de 2010
	 */
	public static void main(String[] args) throws MalformedURLException,
			IOException {
		String estados[] ={ "AC", "AL", "AP", "AM", "BA", "CE", "DF", "GO",
				"ES", "MA", "MT", "MS", "MG", "PA", "PB", "PR", "PE", "PI",
				"RJ", "RN", "RS", "RO", "RR", "SP", "SC", "SE", "TO", "DF" };
		/*
		 * Os cargos e textos: 3 - Governador 4 - Vice-Governador 5 - Senador 9
		 * - 1º Suplente Senador 10 - 2º Suplente Senador 6 - Deputado Federal 7
		 * - Deputado Estadual
		 */
		String cargos[] = { "3", "4", "5", "9", "10", "6", "7" };

		String cargosTexto[] = { "Governador", "Vice-Governador", "Senador",
				"1º Suplente Senador", "2º Suplente Senador",
				"Deputado Federal", "Deputado Estadual" };
		for (int i = 0; i < estados.length; i++) {			
			for (int j = 0; j < cargos.length; j++) {				
				System.out.println("Baixando dados dos "+cargosTexto[j]+ " do estado "+estados[i]);
				try {					
					ArquivoUtil.baixaArquivo(URLsTSEUtl.obterUrlCandidatura(estados[i], cargos[j]),
							"./cargaCandidato/PR/" + cargosTexto[j].replaceAll("\\ ", "")
									+ "_" + estados[i] + ".csv");
				} catch (Exception e) {
					System.out.println("Erro baixando dados dos "+cargosTexto[j]+ " do estado "+estados[i]);
				}
			}			
		}
		System.out.println("Fim!");
	}
}
