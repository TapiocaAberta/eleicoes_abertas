/**
 * 
 */
package br.org.doacoeseleicoes.model;

/**
 * @author william
 * 
 */
public class Candidato {
    private String nome;
    private int id;
    private Estado estado;
    private Partido partido;

    public Candidato() {
        super();
    }

    public Candidato(String nome, Estado estado, Partido partido) {
        super();
        this.nome = nome;
        this.estado = estado;
        this.partido = partido;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Partido getPartido() {
        return partido;
    }

    public void setPartido(Partido partido) {
        this.partido = partido;
    }
}
