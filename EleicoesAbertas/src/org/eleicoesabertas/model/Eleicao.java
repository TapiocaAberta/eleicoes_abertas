package org.eleicoesabertas.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "eleicao")@NamedQuery(
		name="buscaEleicaoPorAno",
		query="select e from Eleicao e where e.ano = ?1"
)
public class Eleicao {
	@Id
	@GeneratedValue
	private int id;

	@Column(name = "ano", nullable = false, length = 5, unique = true)
	private String ano;

	@Override
	public String toString() {
		return getAno();
	}

	public Eleicao() {

	}

	public Eleicao(String ano) {
		setAno(ano);
	}

	@XmlTransient
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAno() {
		return ano;
	}

	public void setAno(String ano) {
		this.ano = ano;
	}

}
