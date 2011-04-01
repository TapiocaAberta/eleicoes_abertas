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
@Table(name="estado_civil")
@NamedQuery(
		name="buscaEstadoCivilPorNome",
		query="select e from EstadoCivil e where e.estadoCivil LIKE ?1"
)
@XmlRootElement(name = "estadoCivil")
public class EstadoCivil {
	
	@Id
	@GeneratedValue
	private int id;
	
	@Column(name="estado_civil", nullable=false, unique=true, length=30)
	private String estadoCivil;	
	
	public EstadoCivil() {
		super();		
	}

	public EstadoCivil(String estadoCivil) {
		setEstadoCivil(estadoCivil);		
	}

	@Override
	public String toString() {	
		return getEstadoCivil();
	}
	@XmlTransient
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEstadoCivil() {
		return estadoCivil;
	}

	public void setEstadoCivil(String estadoCivil) {
		this.estadoCivil = estadoCivil;
	}

}
