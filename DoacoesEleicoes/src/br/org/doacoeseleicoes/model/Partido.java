/**
 * 
 */
package br.org.doacoeseleicoes.model;

/**
 * @author william
 * 
 */
public class Partido {
    private String sigla;
    private int id;

    public Partido(String sigla) {
        super();
        this.sigla = sigla;
    }

    public Partido() {
        super();
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
