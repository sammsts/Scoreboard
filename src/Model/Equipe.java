package Model;

public class Equipe {
    private int id;
    private String nome;

    public Equipe() {
    }

    public Equipe(Equipe _equipe) {
        this.nome = _equipe.nome;
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

    @Override
    public String toString() {
        return "Equipe{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                '}';
    }
}
