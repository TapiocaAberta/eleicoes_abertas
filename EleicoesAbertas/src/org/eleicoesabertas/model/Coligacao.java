package org.eleicoesabertas.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "coligacao")
@NamedQuery(
		name="buscaColigacaoPorNome",
		query="select c from Coligacao c where c.coligacao LIKE ?1"
)
@XmlRootElement(name = "coligacao")
public class Coligacao {

	@Id
	@GeneratedValue
	private int id;

	@Column(name = "coligacao", length = 60, nullable = false, unique = true)
	private String coligacao;

	@ManyToMany
	@JoinTable(name = "coligacao_partido", joinColumns = @JoinColumn(name = "coligacao_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "partido_id", referencedColumnName = "id"))	
	private List<Partido> partidos;

	@Override
	public String toString() {
		return getColigacao();
	}

	public Coligacao() {
	}

	public Coligacao(String coligacao, List<Partido> partidos) {
		setColigacao(coligacao);
		setPartidos(partidos);
	}

	@XmlTransient
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getColigacao() {
		return coligacao;
	}

	public void setColigacao(String coligacao) {
		this.coligacao = coligacao;
	}

	@XmlElement(name="partido")
	public List<Partido> getPartidos() {
		return partidos;
	}

	public void setPartidos(List<Partido> partidos) {
		this.partidos = partidos;
	}

}
