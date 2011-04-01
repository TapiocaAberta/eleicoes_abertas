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
@Table(name = "nacionalidade")
@NamedQuery(
		name="buscaNacionalidadePorNome",
		query="select n from Nacionalidade n where n.nacionalidade LIKE ?1"
)
@XmlRootElement(name = "nacionalidade")
public class Nacionalidade {
	@Id
	@GeneratedValue
	private int id;

	@Column(name = "nacionalidade", unique = true, nullable = false, length = 60)
	private String nacionalidade;

	public Nacionalidade() {
	}

	public Nacionalidade(String nacionalidade) {
		setNacionalidade(nacionalidade);
	}

	@Override
	public String toString() {
		return getNacionalidade();
	}

	@XmlTransient
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNacionalidade() {
		return nacionalidade;
	}

	public void setNacionalidade(String nacionalidade) {
		this.nacionalidade = nacionalidade;
	}

}
