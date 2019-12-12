package br.ifsc.edu.firebaseddm;

public class Nota {
    int id;
    String nome;
    Long dtCriacao;
    Long dtModificacao;

    public Nota() {

    }

    public Nota(int id, String nome, Long dataCriacao, Long dataModificacao) {
        this.id = id;
        this.nome = nome;
        this.dtCriacao = dtCriacao;
        this.dtModificacao = dtModificacao;
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

    public Long getDataCriacao() {
        return dtCriacao;
    }

    public void setDataCriacao(Long dataCriacao) {
        this.dtCriacao = dtCriacao;
    }

    public Long getDataModificacao() {
        return dtModificacao;
    }

    public void setDataModificacao(Long dataModificacao) {
        this.dtModificacao = dataModificacao;
    }
}
