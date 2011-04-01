package org.eleicoesabertas.model;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Erro {
	private String mensagem;
	private Date dataErro ;
	
	public Erro() {
		super();		
	}

	public Erro(String mensagem) {		
		this.mensagem = mensagem;
		dataErro = new Date();
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public Date getDataErro() {
		return dataErro;
	}

	public void setDataErro(Date dataErro) {
		this.dataErro = dataErro;
	}
	
	
}
