/**
 * 
 */
package br.org.doacoeseleicoes.model;

/**
 * @author william
 * 
 */
public class Empresa {
    private String nome;
    private String cnpj;
    private int id;

    public Empresa() {
        super();
    }

    public Empresa(String nome, String cnpj) {
        super();
        this.nome = nome;
        this.cnpj = cnpj;
    }

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

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

}
