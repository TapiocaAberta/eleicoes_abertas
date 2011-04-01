package org.eleicoesabertas.util;

public class URLsTSEUtl {

	public static String obterUrlCandidatura(String uf, String cargo) {
		return "http://divulgacand2010.tse.jus.br/divulgacand2010/jsp/"
				+ "exportarDadosCandidatoCSV.action?siglaUF=" + uf + "&"
				+ "cdCargoCand=" + cargo
				+ "&situacao=3&ordenacao=cand.NM_CANDIDATO&campoPesquisa=";
	}

	public static String obterUrlCSVArrecadao(String sq, String uf) {
		return "http://spce2010.tse.gov.br/spceweb.consulta.receitasdespesas2010/"
				+ "exportaReceitaCsvCandidato.action?sqCandidato="
				+ sq
				+ "&sgUe=" + uf + "&cpfCnpjDoador=";
	}

	public static String obterUrlPDFCandidato(String sq, String uf) {
		return "http://divulgacand2010.tse.jus.br/divulgacand2010/jsp/"
				+ "gerarRelatorioDetalheCandidatoPDF?sqCandidato="
				+ sq
				+ "&sgUe=" + uf;
	}

	public static String obterPaginaCandidato(String sq, String uf) {
		return "http://divulgacand2010.tse.jus.br/divulgacand2010/"
				+ "jsp/abrirTelaDetalheCandidato.action?sqCand=" + sq + "&sgUe=" + uf;
	}
	
	public static String obterUrlCSVDespesa(String sq, String uf) {
		return "http://spce2010.tse.gov.br/spceweb.consulta.receitasdespesas2010/"
		+ "exportaDespesaCsvCandidato.action?sqCandidato="
		+ sq
		+ "&sgUe=" + uf + "&cpfCnpjDoador=";
	}

}
