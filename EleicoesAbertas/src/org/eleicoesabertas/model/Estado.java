package org.eleicoesabertas.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "estado")
@NamedQueries(value = {
 @NamedQuery(name = "buscaEstadoPorNome", query = "select e from Estado e where e.nome LIKE ?1"),
 @NamedQuery(name = "buscaEstadoPorUF", query = "select e from Estado e where e.uf LIKE ?1"),
 
}
		
)
@XmlRootElement(name = "estado")
public class Estado {
	@Id
	@GeneratedValue
	private int id;

	@Column(name = "nome", unique = true, nullable=false, length=50)
	private String nome;

	@Column(name = "UF", unique = true, nullable=false, length=3)
	private String uf;
	
	public Estado() {
	}
	
	public Estado(String nome, String uf) {		
		setNome(nome);
		setUf(uf);
	}
	
	@Override
	public String toString() {	
		return getNome();
	}

	@XmlTransient
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getUf() {
		return uf;
	}


	public void setUf(String uf) {
		this.uf = uf;
	}
	

}
