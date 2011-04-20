/**
 * 
 */
package br.org.doacoeseleicoes.model;

import java.util.Date;

/**
 * @author william
 * 
 */
public class Doacao {
    private long id;
    private Candidato candidato;
    private Empresa empresa;
    private float valor;
    private Date data;
    private TipoDoacao tipoDoacao;
    private String numero;

    public Doacao(Candidato candidato, Empresa empresa, float valor, Date data,
            TipoDoacao tipoDoacao, String numero) {
        super();
        this.candidato = candidato;
        this.empresa = empresa;
        this.valor = valor;
        this.data = data;
        this.tipoDoacao = tipoDoacao;
        this.numero = numero;
    }

    public Doacao() {
        super();

    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Candidato getCandidato() {
        return candidato;
    }

    public void setCandidato(Candidato candidato) {
        this.candidato = candidato;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public TipoDoacao getTipoDoacao() {
        return tipoDoacao;
    }

    public void setTipoDoacao(TipoDoacao tipoDoacao) {
        this.tipoDoacao = tipoDoacao;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

}
