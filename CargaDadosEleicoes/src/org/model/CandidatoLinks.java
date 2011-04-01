package org.model;

public class CandidatoLinks {
	private String seq;
	private String partido;
	private String nome;
	private String uf;
	private String numero;
	private String urlArrecadao;
	private String urlDespesa;
	private String urlPdf;
	private String urlPagina;

	public CandidatoLinks() {
		super();
	}

	@Override
	public String toString() {
		return seq + ";" + nome + ";" + partido + ";" + uf + ";" + numero + ";"
				+ urlArrecadao + ";" + urlDespesa + ";" + urlPdf + ";" + urlPagina;
	}



	public CandidatoLinks(String seq, String partido, String nome, String uf,
			String numero, String urlArrecadao, String urlDespesa,
			String urlPdf, String urlPagina) {
		super();
		this.seq = seq;
		this.partido = partido;
		this.nome = nome;
		this.uf = uf;
		this.numero = numero;
		this.urlArrecadao = urlArrecadao;
		this.urlDespesa = urlDespesa;
		this.urlPdf = urlPdf;
		this.urlPagina = urlPagina;
	}

	public String getSeq() {
		return seq;
	}

	public void setSeq(String seq) {
		this.seq = seq;
	}

	public String getPartido() {
		return partido;
	}

	public void setPartido(String partido) {
		this.partido = partido;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getUrlArrecadao() {
		return urlArrecadao;
	}

	public void setUrlArrecadao(String urlArrecadao) {
		this.urlArrecadao = urlArrecadao;
	}

	public String getUrlPdf() {
		return urlPdf;
	}

	public void setUrlPdf(String urlPdf) {
		this.urlPdf = urlPdf;
	}

	public String getUrlPagina() {
		return urlPagina;
	}

	public void setUrlPagina(String urlPagina) {
		this.urlPagina = urlPagina;
	}

	public String getUrlDespesa() {
		return urlDespesa;
	}

	public void setUrlDespesa(String urlDespesa) {
		this.urlDespesa = urlDespesa;
	}
	
	

}
