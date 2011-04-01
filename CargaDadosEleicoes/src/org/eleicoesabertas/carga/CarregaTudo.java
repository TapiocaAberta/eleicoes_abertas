package org.eleicoesabertas.carga;

import java.io.IOException;

public class CarregaTudo {
	static int profundidade = 0;

	public static void main(String[] args) throws IOException {
		String arquivos[] = {};/*"vicepresidentes_BR.csv", "1ºSuplenteSenador_PR.csv",
				"DeputadoFederal_PR.csv", "Vice-Governador_PR.csv",
				"2ºSuplenteSenador_PR.csv", "Governador_PR.csv",
				"DeputadoEstadual_PR.csv", "Senador_PR.csv",
				"1ºSuplenteSenador_AC.csv",
				"DeputadoEstadual_MA.csv", "Governador_RN.csv",
				"1ºSuplenteSenador_AL.csv", "DeputadoEstadual_MG.csv",
				"Governador_RO.csv", "1ºSuplenteSenador_AM.csv",
				"DeputadoEstadual_MS.csv", "Governador_RR.csv",
				"1ºSuplenteSenador_AP.csv", "DeputadoEstadual_MT.csv",
				"Governador_RS.csv", "1ºSuplenteSenador_BA.csv",
				"DeputadoEstadual_PA.csv", "Governador_SC.csv",
				"1ºSuplenteSenador_CE.csv", "DeputadoEstadual_PB.csv",
				"Governador_SE.csv", "1ºSuplenteSenador_DF.csv",
				"DeputadoEstadual_PE.csv", "Governador_SP.csv",
				"1ºSuplenteSenador_ES.csv", "DeputadoEstadual_PI.csv",
				"Governador_TO.csv", "1ºSuplenteSenador_GO.csv",
				"DeputadoEstadual_RJ.csv", "Senador_AC.csv",
				"1ºSuplenteSenador_MA.csv", "DeputadoEstadual_RN.csv",
				"Senador_AL.csv", "1ºSuplenteSenador_MG.csv",
				"DeputadoEstadual_RO.csv", "Senador_AM.csv",
				"1ºSuplenteSenador_MS.csv", "DeputadoEstadual_RR.csv",
				"Senador_AP.csv", "1ºSuplenteSenador_MT.csv",
				"DeputadoEstadual_RS.csv", "Senador_BA.csv",
				"1ºSuplenteSenador_PA.csv", "DeputadoEstadual_SC.csv",
				"Senador_CE.csv", "1ºSuplenteSenador_PB.csv",
				"DeputadoEstadual_SE.csv", "Senador_DF.csv",
				"1ºSuplenteSenador_PE.csv", "DeputadoEstadual_SP.csv",
				"Senador_ES.csv", "1ºSuplenteSenador_PI.csv",
				"DeputadoEstadual_TO.csv", "Senador_GO.csv",
				"1ºSuplenteSenador_RJ.csv", "DeputadoFederal_AC.csv",
				"Senador_MA.csv", "1ºSuplenteSenador_RN.csv",
				"DeputadoFederal_AL.csv", "Senador_MG.csv",
				"1ºSuplenteSenador_RO.csv", "DeputadoFederal_AM.csv",
				"Senador_MS.csv", "1ºSuplenteSenador_RR.csv",
				"DeputadoFederal_AP.csv", "Senador_MT.csv",
				"1ºSuplenteSenador_RS.csv", "DeputadoFederal_BA.csv",
				"Senador_PA.csv", "1ºSuplenteSenador_SC.csv",
				"DeputadoFederal_CE.csv", "Senador_PB.csv",
				"1ºSuplenteSenador_SE.csv", "DeputadoFederal_DF.csv",
				"Senador_PE.csv", "1ºSuplenteSenador_SP.csv",
				"DeputadoFederal_ES.csv", "Senador_PI.csv",
				"1ºSuplenteSenador_TO.csv", "DeputadoFederal_GO.csv",
				"Senador_RJ.csv", "2ºSuplenteSenador_AC.csv",
				"DeputadoFederal_MA.csv", "Senador_RN.csv",
				"2ºSuplenteSenador_AL.csv", "DeputadoFederal_MG.csv",
				"Senador_RO.csv", "2ºSuplenteSenador_AM.csv",
				"DeputadoFederal_MS.csv", "Senador_RR.csv",
				"2ºSuplenteSenador_AP.csv", "DeputadoFederal_MT.csv",
				"Senador_RS.csv", "2ºSuplenteSenador_BA.csv",
				"DeputadoFederal_PA.csv", "Senador_SC.csv",
				"2ºSuplenteSenador_CE.csv", "DeputadoFederal_PB.csv",
				"Senador_SE.csv", "2ºSuplenteSenador_DF.csv",
				"DeputadoFederal_PE.csv", "Senador_SP.csv",
				"2ºSuplenteSenador_ES.csv", "DeputadoFederal_PI.csv",
				"Senador_TO.csv", "2ºSuplenteSenador_GO.csv",
				"DeputadoFederal_RJ.csv", "Vice-Governador_AC.csv",
				"2ºSuplenteSenador_MA.csv", "DeputadoFederal_RN.csv",
				"Vice-Governador_AL.csv", "2ºSuplenteSenador_MG.csv",
				"DeputadoFederal_RO.csv", "Vice-Governador_AM.csv",
				"2ºSuplenteSenador_MS.csv", "DeputadoFederal_RR.csv",
				"Vice-Governador_AP.csv", "2ºSuplenteSenador_MT.csv",
				"DeputadoFederal_RS.csv", "Vice-Governador_BA.csv",
				"2ºSuplenteSenador_PA.csv", "DeputadoFederal_SC.csv",
				"Vice-Governador_CE.csv", "2ºSuplenteSenador_PB.csv",
				"DeputadoFederal_SE.csv", "Vice-Governador_DF.csv",
				"2ºSuplenteSenador_PE.csv", "DeputadoFederal_SP.csv",
				"Vice-Governador_ES.csv", "2ºSuplenteSenador_PI.csv",
				"DeputadoFederal_TO.csv", "Vice-Governador_GO.csv",
				"2ºSuplenteSenador_RJ.csv", "Governador_AC.csv",
				"Vice-Governador_MA.csv", "2ºSuplenteSenador_RN.csv",
				"Governador_AL.csv", "Vice-Governador_MG.csv",
				"2ºSuplenteSenador_RO.csv", "Governador_AM.csv",
				"Vice-Governador_MS.csv", "2ºSuplenteSenador_RR.csv",
				"Governador_AP.csv", "Vice-Governador_MT.csv",
				"2ºSuplenteSenador_RS.csv", "Governador_BA.csv",
				"Vice-Governador_PA.csv", "2ºSuplenteSenador_SC.csv",
				"Governador_CE.csv", "Vice-Governador_PB.csv",
				"2ºSuplenteSenador_SE.csv", "Governador_DF.csv",
				"Vice-Governador_PE.csv", "2ºSuplenteSenador_SP.csv",
				"Governador_ES.csv", "Vice-Governador_PI.csv",
				"2ºSuplenteSenador_TO.csv", "Governador_GO.csv",
				"Vice-Governador_RJ.csv", "DeputadoEstadual_AC.csv",
				"Governador_MA.csv", "Vice-Governador_RN.csv",
				"DeputadoEstadual_AL.csv", "Governador_MG.csv",
				"Vice-Governador_RO.csv", "DeputadoEstadual_AM.csv",
				"Governador_MS.csv", "Vice-Governador_RR.csv",
				"DeputadoEstadual_AP.csv", "Governador_MT.csv",
				"Vice-Governador_RS.csv", "DeputadoEstadual_BA.csv",
				"Governador_PA.csv", "Vice-Governador_SC.csv",
				"DeputadoEstadual_CE.csv", "Governador_PB.csv",
				"Vice-Governador_SE.csv", "DeputadoEstadual_DF.csv",
				"Governador_PE.csv", "Vice-Governador_SP.csv",
				"DeputadoEstadual_ES.csv", "Governador_PI.csv",
				"Vice-Governador_TO.csv", "DeputadoEstadual_GO.csv",
				"Governador_RJ.csv", "DeputadoDistrital_DF.csv" };

		// /home/william/cargaCandidato/definicoes/estados.csv
		/*
		 * CarregaCSVCandidato.main("./cargaCandidato/br/utf8/candidatos.csv",
		 * "BR", true);
		 */

		for (int i = 0; i < arquivos.length; i++) {
			String arquivo = arquivos[i];
			String estado = arquivo.split("\\_")[1].split("\\.")[0];
			CarregaCSVCandidato.main("./cargaCandidato/todosArquivos/"
					+ arquivo, estado, false);
			System.out.println("\n\tArquivo " + arquivo
					+ " carregado com sucesso!\n\n");
		}
	}
}
