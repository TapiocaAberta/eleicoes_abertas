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
@Table(name = "sexo")
@NamedQuery(
		name="buscaSexoPorNome",
		query="select s from Sexo s where s.sexo LIKE ?1"
)
@XmlRootElement(name = "sexo")
public class Sexo {

	@Id
	@GeneratedValue
	private int id;
	@Column(name = "sexo", length = 45, nullable = false, unique = true)
	private String sexo;

	@Override
	public String toString() {
		return getSexo();
	}

	public Sexo() {
	}

	public Sexo(String sexo) {
		setSexo(sexo);
	}

	@XmlTransient
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

}
