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
@Table(name = "resultado_eleicao")
@NamedQuery(
		name="buscaResultadoEleicaoPorNome",
		query="select r from ResultadoEleicao r where r.resultadoEleicao LIKE ?1"
)
@XmlRootElement(name = "resultadoEleicao")
public class ResultadoEleicao {

	@Id
	@GeneratedValue
	private int id;

	@Column(name = "resultado_eleicao", length = 60, nullable = false, unique = true)
	private String resultadoEleicao;

	@Override
	public String toString() {
		return getResultadoEleicao();
	}

	public ResultadoEleicao() {
	}

	public ResultadoEleicao(String resultadoEleicao) {
		setResultadoEleicao(resultadoEleicao);
	}

	@XmlTransient
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getResultadoEleicao() {
		return resultadoEleicao;
	}

	public void setResultadoEleicao(String resultadoEleicao) {
		this.resultadoEleicao = resultadoEleicao;
	}

}
