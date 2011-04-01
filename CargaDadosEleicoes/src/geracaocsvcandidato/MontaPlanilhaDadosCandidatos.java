package geracaocsvcandidato;

import static org.eleicoesabertas.util.URLsTSEUtl.obterPaginaCandidato;
import static org.eleicoesabertas.util.URLsTSEUtl.obterUrlCSVArrecadao;
import static org.eleicoesabertas.util.URLsTSEUtl.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Node;
import org.eleicoesabertas.util.BlankRemover;
import org.eleicoesabertas.util.DocumentUtil;
import org.model.CandidatoLinks;

public class MontaPlanilhaDadosCandidatos {
	// seq + ";" + nome + ";" + partido + ";" + uf + ";" + numero + ";"
	// + urlArrecadao + ";" + urlDespesa + ";" + urlPdf + ";" + urlPagina;

	static String HEADER_CSV = "Número Sequencial;Nome do Candidato;Partido;UF;Número de eleição;Dados de arrecadação;Dados de despesas;Dados em PDF;Página no TSE";

	public static void main(String[] args) throws IOException {
		File dir = new File("./candidatosDespesas/xml/");
		System.out.println("Iniciando processo...");
		List<CandidatoLinks> candidatos = new ArrayList<CandidatoLinks>();

		for (File arquivo : dir.listFiles()) {

			Document document = DocumentUtil.getDocument(arquivo.getPath());

			/*
			 * Elementos do XML: sqCand - sgUe - name - numero - partido
			 */
			String nome = null, partido = null, uf = null, numero = null, seq = null;
			for (@SuppressWarnings("unchecked")
			Iterator<CandidatoLinks> iterator = document.getRootElement()
					.elementIterator(); iterator.hasNext();) {
				Node node = (Node) iterator.next();

				if (node.getName().equals("sqCand")) {
					seq = BlankRemover.lrtrim(BlankRemover.lrtrim(node
							.getText()));
				}
				if (node.getName().equals("name")) {
					nome = BlankRemover.lrtrim(node.getText());
				}
				if (node.getName().equals("sgUe")) {
					uf = BlankRemover.lrtrim(node.getText());
				}
				if (node.getName().equals("numero")) {
					numero = BlankRemover.lrtrim(node.getText());
				}
				if (node.getName().equals("partido")) {
					partido = BlankRemover.lrtrim(node.getText());

					if (partido.equals(".")) {
						continue;
					} else {
						candidatos.add(new CandidatoLinks(seq, partido, nome,
								uf, numero, obterUrlCSVArrecadao(seq, uf),
								obterUrlCSVDespesa(seq, uf),
								obterUrlPDFCandidato(seq, uf),
								obterPaginaCandidato(seq, uf)
								));
					}
				}

			}
		}

		// Gravando arquivo CSV
		FileOutputStream fos = new FileOutputStream(new File(
				"candidatosArrecadao.csv"));

		fos.write((HEADER_CSV + System.getProperty("line.separator"))
				.getBytes());
		for (CandidatoLinks candidatoDespesa : candidatos) {
			System.out.println("Gravando: " + candidatoDespesa);
			fos.write((candidatoDespesa.toString() + System
					.getProperty("line.separator")).getBytes());
		}
		fos.close();
		System.out.println("FIM!");

	}
}
