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
@Table(name="ocupacao")
@NamedQuery(
		name="buscaOcupacaoPorNome",
		query="select o from Ocupacao o where o.ocupacao LIKE ?1"
)
@XmlRootElement
public class Ocupacao {
	
	@Id
	@GeneratedValue
	private int id;
	
	@Column(name="ocupacao", nullable=false, unique=true, length=100)
	private String ocupacao;

	@Override
	public String toString() {	
		return getOcupacao();
	}
	
	public Ocupacao() {		
	}

	public Ocupacao(String ocupacao) {
		setOcupacao(ocupacao);
	}

	@XmlTransient
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getOcupacao() {
		return ocupacao;
	}

	public void setOcupacao(String ocupacao) {
		this.ocupacao = ocupacao;
	}

}
