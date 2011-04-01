package org.dadosdespesas;

import java.io.IOException;
import java.net.MalformedURLException;

import org.eleicoesabertas.util.ArquivoUtil;

public class BaixaXMLSPaginaArrecadacao {
	public static void main(String[] args) throws MalformedURLException,
			IOException {
		String estados[] = { "AC", "AL", "AM", "AP", "BA", "BR", "CE", "DF",
				"ES", "GO", "MA", "MG", "MS", "MT", "PA", "PB", "PE", "PI",
				"PR", "RJ", "RN", "RO", "RR", "RS", "SC", "SE", "SP", "TO" };

		String numeroCargos[] = { "1", "2", "3", "4", "5", "9", "10", "6",
				"7", "8" };

		String textos[] = { "Presidente", "Vice-Presidente", "Governador",
				"Vice-Governador", "Senador", "1º Suplente Senador",
				"2º Suplente Senador", "Deputado Federal", "Deputado Estadual",
				"Deputado Distrital" };

		for (int i = 0; i < estados.length; i++) {
			for (int j = 0; j < numeroCargos.length; j++) {
				String arquivo = "./candidatosDespesas/xml/" + estados[i] + "_"
				+ textos[j]+".xml"; 
				ArquivoUtil.baixaArquivo(obterUrl(estados[i], numeroCargos[j]),
						arquivo);
				System.out.println("Baixando "+arquivo);
			}
		}
	}

	public static String obterUrl(String uf, String numero) {
		return "http://spce2010.tse.gov.br/spceweb.consulta.receitasdespesas2010/"
				+ "candidatoAutoComplete.do?noCandLimpo=&&sgUe="
				+ uf
				+ "&cdCargo=" + numero + "&orderBy=cand.NM_CANDIDATO";
		/*
		 * http://spce2010.tse.gov.br/spceweb.consulta.receitasdespesas2010/
		 * candidatoAutoComplete
		 * .do?noCandLimpo=&&sgUe={UF}&cdCargo={Cargo}&orderBy=cand.NM_CANDIDATO
		 * ONDE {UF} = Uf com BR para Brasil {Cargo} = Cargo considerando:
		 * 
		 * 1 - Presidente 2 - Vice-Presidente 3 - Governador 4 - Vice-Governador
		 * 5 - Senador 9 - 1º Suplente Senador 10 - 2º Suplente Senador 6 -
		 * Deputado Federal 7 - Deputado Estadual 8 - Deputado Distrital
		 */
	}
}
