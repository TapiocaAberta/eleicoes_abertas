/**
 * 
 */
package br.org.doacoeseleicoes.model;

/**
 * @author william
 * 
 */
public class TipoDoacao {
    private int id;

    private String tipo;

    public TipoDoacao() {
        super();
    }

    public TipoDoacao(String tipo) {
        super();
        this.tipo = tipo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

}
