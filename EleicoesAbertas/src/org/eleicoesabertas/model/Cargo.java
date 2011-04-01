package org.eleicoesabertas.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "cargo")
@NamedQuery(
		name="buscaCargoPorNome",
		query="select c from Cargo c where c.cargo LIKE ?1"
)
@XmlRootElement(name = "cargo")
public class Cargo {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)	
	private int id;

	@Column(name = "cargo", nullable = false, unique=true, length=60)
	private String cargo;

	public Cargo(String cargo) {
		this.cargo = cargo;
	}

	public Cargo() {

	}
	@Override
	public String toString() {	
		return getCargo();
	}
	@XmlTransient
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

}
