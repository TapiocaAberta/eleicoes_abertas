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
@Table(name = "partido")
@NamedQuery(
		name="buscaPartidoPorSigla",
		query="select p from Partido p where p.sigla = ?1"
)
@XmlRootElement(name = "partido")
public class Partido {
	@Id
	@GeneratedValue
	private int id;

	@Column(name = "sigla", length = 10, nullable = false, unique = true)
	private String sigla;
	
	@Column(name = "numero", length = 5, nullable = false)
	private String numero;
	
	@Override
	public String toString() {	
		return getSigla();
	}
	
	public Partido() {	
	}

	public Partido(String sigla, String numero) {
		setSigla(sigla);
		setNumero(numero);
	}

	@XmlTransient
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

}
