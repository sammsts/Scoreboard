package model;

import java.util.Date;

public class Prova {
    private int id;
    private int competicaoId;
    private Date dataProva;
    private String nome;
    private int duracao;

    // Construtor com todos os campos
    public Prova(int id, int competicaoId, Date dataProva, String nome, int duracao) {
        this.id = id;
        this.competicaoId = competicaoId;
        this.dataProva = dataProva;
        this.nome = nome;
        this.duracao = duracao;
    }

    public Prova(String nome, int duracao) {
        this.nome = nome;
        this.duracao = duracao;
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCompeticaoId() {
        return competicaoId;
    }

    public void setCompeticaoId(int competicaoId) {
        this.competicaoId = competicaoId;
    }

    public Date getDataProva() {
        return dataProva;
    }

    public void setDataProva(Date dataProva) {
        this.dataProva = dataProva;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getDuracao() {
        return duracao;
    }

    public void setDuracao(int duracao) {
        this.duracao = duracao;
    }

    @Override
    public String toString() {
        return "Prova{" +
                "id=" + id +
                ", competicaoId=" + competicaoId +
                ", dataProva=" + dataProva +
                ", nome='" + nome + '\'' +
                ", duracao=" + duracao +
                '}';
    }
}
