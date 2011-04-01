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
@Table(name = "situacao")
@NamedQuery(
		name="buscaSituacaoPorNome",
		query="select s from Situacao s where s.situacao LIKE ?1"
)
@XmlRootElement(name = "situacao")
public class Situacao {

	@Id
	@GeneratedValue
	private int id;

	@Column(name = "situacao", length = 60, nullable = false, unique = true)
	private String situacao;

	@Override
	public String toString() {
		return getSituacao();
	}

	public Situacao() {	}

	public Situacao(String situacao) {
		setSituacao(situacao);
	}

	@XmlTransient
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

}
