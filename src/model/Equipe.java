package model;

public class Equipe {
    private int id;
    private String nome;
    private int competicaoId;

    public Equipe() {
    }

    public Equipe(String nome, int competicaoId) {
        this.nome = nome;
        this.competicaoId = competicaoId;
    }

    // Getters e Setters
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

    public int getCompeticaoId() {
        return competicaoId;
    }

    public void setCompeticaoId(int competicaoId) {
        this.competicaoId = competicaoId;
    }

    @Override
    public String toString() {
        return "Equipe{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", competicaoId=" + competicaoId +
                '}';
    }
}
