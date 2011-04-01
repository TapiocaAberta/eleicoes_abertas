package org.eleicoesabertas.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "cidade")
@NamedQueries(value = {
		@NamedQuery(name = "buscaCidadePorNome", query = "select c from Cidade c where c.cidade LIKE ?1"),
		@NamedQuery(name = "buscaCidadePorNomeEstado", query = "select c from Cidade c where c.estado.nome LIKE ?1") })
		@XmlRootElement(name = "cidadeNascimento")
public class Cidade {

	@Id
	@GeneratedValue
	private int id;
	@Column(name = "nome",  nullable = false)
	private String cidade;

	@ManyToOne(fetch = FetchType.EAGER)
	private Estado estado;

	@Override
	public String toString() {
		return getCidade();
	}

	public Cidade() {

	}

	public Cidade(String cidade, Estado estado) {
		setCidade(cidade);
		setEstado(estado);
	}

	@XmlTransient
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

}
