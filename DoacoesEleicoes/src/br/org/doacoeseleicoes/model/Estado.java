/**
 * 
 */
package br.org.doacoeseleicoes.model;

/**
 * @author william
 *
 */
public class Estado {
    
    private int id;
    private String sigla;
    private String nome;
    
    
    
    public Estado(String sigla, String nome) {
        super();
        this.sigla = sigla;
        this.nome = nome;
    }
    
    public Estado() {
    
    }
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
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    
}
