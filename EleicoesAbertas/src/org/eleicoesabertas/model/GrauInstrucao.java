package org.eleicoesabertas.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name="grau_instrucao")
@NamedQuery(
		name="buscaGrauInstrucaoPorNome",
		query="select g from GrauInstrucao g where g.grauInstrucao LIKE ?1"
)
@XmlRootElement(name = "candidato")
public class GrauInstrucao {
	
	@Id
	@GeneratedValue
	private int id;
	
	@Column(name="grau_instrucao", unique=true, nullable=false, length=60)
	private String grauInstrucao;
	
	public GrauInstrucao() {

	}
	public GrauInstrucao(String grauInstrucao) {		
		setGrauInstrucao(grauInstrucao);
	}
	
	@Override
	public String toString() {	
		return getGrauInstrucao();
	}
	@XmlTransient
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getGrauInstrucao() {
		return grauInstrucao;
	}
	public void setGrauInstrucao(String grauInstrucao) {
		this.grauInstrucao = grauInstrucao;
	}
	
	
}
