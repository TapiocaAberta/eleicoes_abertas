package org.eleicoesabertas.model;

import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Resultados {
	
	private int pagina;	
	private int quantidade;	
	private String busca;	
	private Date data;
	private long totalResultados;
	private List<Candidato> candidatos;
	
	public Resultados() {
	}

	public Resultados(int pagina, int quantidade, String busca,
			List<Candidato> candidatos) {
		super();
		if(pagina < 1) pagina=1;
		this.pagina = pagina;
		this.candidatos = candidatos;
		this.quantidade = quantidade;
		this.busca = busca;
		this.data = new Date();
		totalResultados = -1;
		
	}

	@XmlAttribute
	public int getPagina() {
		return pagina;
	}

	public void setPagina(int pagina) {
		this.pagina = pagina;
	}

	@XmlAttribute
	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	@XmlAttribute
	public String getBusca() {
		return busca;
	}

	public void setBusca(String busca) {
		this.busca = busca;
	}

	@XmlAttribute
	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	@XmlElement(name="candidato")
	public List<Candidato> getCandidatos() {
		return candidatos;
	}

	public void setCandidatos(List<Candidato> candidatos) {
		this.candidatos = candidatos;
	}

	@XmlAttribute
	public long getTotalResultados() {
		return totalResultados;
	}

	public void setTotalResultados(long  totalResultados) {
		this.totalResultados = totalResultados;
	}
	
	
}
