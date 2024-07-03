package Model;

import java.util.HashMap;
import java.util.Map;

/**
 * Representa uma equipe em uma competição.
 */
public class Equipe {
    private int id;
    private String nome;
    private int pontuacao;
    private Map<Prova, Integer> pontuacoes;

    /**
     * Construtor padrão da classe Equipe.
     * Inicializa o mapa de pontuações.
     */
    public Equipe() {
        this.pontuacoes = new HashMap<>();
    }

    /**
     * Construtor de cópia da classe Equipe.
     *
     * @param _equipe A equipe a ser copiada.
     */
    public Equipe(Equipe _equipe) {
        this.nome = _equipe.nome;
        this.pontuacoes = new HashMap<>();
    }

    /**
     * Retorna o ID da equipe.
     *
     * @return O ID da equipe.
     */
    public int getId() {
        return id;
    }

    /**
     * Define o ID da equipe.
     *
     * @param id O ID da equipe.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Retorna o nome da equipe.
     *
     * @return O nome da equipe.
     */
    public String getNome() {
        return nome;
    }

    /**
     * Define o nome da equipe.
     *
     * @param nome O nome da equipe.
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Retorna a pontuação total da equipe.
     *
     * @return A pontuação total da equipe.
     */
    public int getPontuacao() {
        return pontuacao;
    }

    /**
     * Define a pontuação total da equipe.
     *
     * @param pontuacao A pontuação total da equipe.
     */
    public void setPontuacao(int pontuacao) {
        this.pontuacao = pontuacao;
    }

    /**
     * Adiciona uma pontuação para uma prova específica.
     *
     * @param prova     A prova para a qual a pontuação será adicionada.
     * @param pontuacao A pontuação a ser adicionada.
     */
    public void adicionarPontuacao(Prova prova, int pontuacao) {
        this.pontuacoes.put(prova, pontuacao);
    }

    /**
     * Retorna a pontuação da equipe para uma prova específica.
     *
     * @param prova A prova para a qual a pontuação será retornada.
     * @return A pontuação da equipe para a prova específica.
     */
    public int getPontuacaoPorProva(Prova prova) {
        return this.pontuacoes.getOrDefault(prova, 0);
    }

    /**
     * Retorna a pontuação total acumulada pela equipe.
     *
     * @return A pontuação total acumulada pela equipe.
     */
    public int getPontuacaoTotal() {
        return this.pontuacoes.values().stream().mapToInt(Integer::intValue).sum();
    }

    /**
     * Retorna uma representação em string da equipe.
     *
     * @return Uma string representando a equipe.
     */
    @Override
    public String toString() {
        return "Equipe{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", pontuacao=" + pontuacao +
                ", pontuacoes=" + pontuacoes +
                '}';
    }
}
