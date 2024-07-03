package Model;

public class Participante extends Equipe{
    private int id;
    private int idEquipe;
    private String nome;

    public Participante() {}

    public Participante(int id, int idEquipe, String nome){
        this.id = id;
        this.idEquipe = idEquipe;
        this.nome = nome;
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

    public int getIdEquipe() {
        return idEquipe;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setIdEquipe(int idEquipe) {
        this.idEquipe = idEquipe;
    }

    @Override
    public String toString() {
        return "Participante{" +
                "id=" + id +
                ", idEquipe=" + idEquipe +
                ", nome='" + nome + '\'' +
                '}';
    }
}
