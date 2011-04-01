package org.eleicoesabertas.model;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="resultados")
public class OutrosResultados {	
	private List<?> dados;
	private String recurso;
	private int pagina;
	
	
	public OutrosResultados(List<?> dados, String recurso, int pagina) {
		super();
		this.dados = dados;
		this.recurso = recurso;
		this.pagina = pagina;
	}


	public OutrosResultados() {
		super();
	}


	public List<?> getDados() {
		return dados;
	}

	public void setDados(List<?> dados) {
		this.dados = dados;
	}


	public String getRecurso() {
		return recurso;
	}


	public void setRecurso(String recurso) {
		this.recurso = recurso;
	}


	public int getPagina() {
		return pagina;
	}


	public void setPagina(int pagina) {
		this.pagina = pagina;
	}
	
	
}
