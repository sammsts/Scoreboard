package Model;

/**
 * Representa uma prova em uma competição.
 */
public class Prova {
    private int id;
    private String nome;
    private int duracao;

    /**
     * Construtor da classe Prova com todos os campos.
     *
     * @param id      O ID da prova.
     * @param nome    O nome da prova.
     * @param duracao A duração da prova em minutos.
     */
    public Prova(int id, String nome, int duracao) {
        this.id = id;
        this.nome = nome;
        this.duracao = duracao;
    }

    /**
     * Construtor da classe Prova sem o campo ID (para criação de novas provas).
     *
     * @param nome    O nome da prova.
     * @param duracao A duração da prova em minutos.
     */
    public Prova(String nome, int duracao) {
        this.nome = nome;
        this.duracao = duracao;
    }

    /**
     * Construtor padrão da classe Prova.
     */
    public Prova() {}

    /**
     * Retorna o ID da prova.
     *
     * @return O ID da prova.
     */
    public int getId() {
        return id;
    }

    /**
     * Define o ID da prova.
     *
     * @param id O ID da prova.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Retorna o nome da prova.
     *
     * @return O nome da prova.
     */
    public String getNome() {
        return nome;
    }

    /**
     * Define o nome da prova.
     *
     * @param nome O nome da prova.
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Retorna a duração da prova em minutos.
     *
     * @return A duração da prova em minutos.
     */
    public int getDuracao() {
        return duracao;
    }

    /**
     * Define a duração da prova em minutos.
     *
     * @param duracao A duração da prova em minutos.
     */
    public void setDuracao(int duracao) {
        this.duracao = duracao;
    }

    /**
     * Retorna uma representação em string da prova.
     *
     * @return Uma string representando a prova.
     */
    @Override
    public String toString() {
        return "Prova{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", duracao=" + duracao +
                '}';
    }
}
