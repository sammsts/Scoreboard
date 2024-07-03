package Model;

/**
 * Representa um participante associado a uma equipe em uma competição.
 */
public class Participante extends Equipe {
    private int id;
    private int idEquipe;
    private String nome;

    /**
     * Construtor padrão da classe Participante.
     */
    public Participante() {}

    /**
     * Construtor da classe Participante com parâmetros.
     *
     * @param id       O ID do participante.
     * @param idEquipe O ID da equipe à qual o participante está associado.
     * @param nome     O nome do participante.
     */
    public Participante(int id, int idEquipe, String nome){
        this.id = id;
        this.idEquipe = idEquipe;
        this.nome = nome;
    }

    /**
     * Retorna o ID do participante.
     *
     * @return O ID do participante.
     */
    public int getId() {
        return id;
    }

    /**
     * Define o ID do participante.
     *
     * @param id O ID do participante.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Retorna o nome do participante.
     *
     * @return O nome do participante.
     */
    public String getNome() {
        return nome;
    }

    /**
     * Define o nome do participante.
     *
     * @param nome O nome do participante.
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Retorna o ID da equipe à qual o participante está associado.
     *
     * @return O ID da equipe do participante.
     */
    public int getIdEquipe() {
        return idEquipe;
    }

    /**
     * Define o ID da equipe à qual o participante está associado.
     *
     * @param idEquipe O ID da equipe do participante.
     */
    public void setIdEquipe(int idEquipe) {
        this.idEquipe = idEquipe;
    }

    /**
     * Retorna uma representação em string do participante.
     *
     * @return Uma string representando o participante.
     */
    @Override
    public String toString() {
        return "Participante{" +
                "id=" + id +
                ", idEquipe=" + idEquipe +
                ", nome='" + nome + '\'' +
                '}';
    }
}
