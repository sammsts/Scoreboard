package Controllers;

import Interfaces.IEquipeService;
import Interfaces.IParticipanteService;
import Model.Participante;
import Services.EquipeService;
import Services.ParticipanteService;
import Model.Equipe;
import Model.Prova;

import java.util.ArrayList;
import java.util.List;

/**
 * Controller para gerenciar competições.
 * Responsável por adicionar, remover e atualizar equipes, participantes e provas.
 */
public class CompeticaoController {
    private List<Equipe> _equipesList;
    private List<Prova> _provasList;
    private List<Participante> _participantesList;
    private IEquipeService _equipeService;
    private IParticipanteService _participanteService;

    /**
     * Construtor da classe CompeticaoController.
     * Inicializa as listas de equipes, provas e participantes, e os serviços de equipe e participante.
     */
    public CompeticaoController() {
        this._equipesList = new ArrayList<>();
        this._provasList = new ArrayList<>();
        this._participantesList = new ArrayList<>();
        this._equipeService = new EquipeService(this);
        this._participanteService = new ParticipanteService(this);
    }

    /**
     * Adiciona uma equipe à competição.
     *
     * @param _equipe Equipe a ser adicionada.
     */
    public void AdicionarEquipe(Equipe _equipe) {
        _equipeService.AddEquipe(_equipe, _equipesList);
    }

    /**
     * Adiciona um participante à competição.
     *
     * @param _participante Participante a ser adicionado.
     */
    public void AdicionarParticipante(Participante _participante) {
        _participanteService.AddParticipante(_participante);
    }

    /**
     * Remove um participante da competição pelo ID.
     *
     * @param id_participante ID do participante a ser removido.
     */
    public void RemoverParticipante(int id_participante) {
        _participanteService.RemoveParticipante(id_participante);
    }

    /**
     * Adiciona uma prova à competição.
     *
     * @param nome    Nome da prova.
     * @param duracao Duração da prova em minutos.
     */
    public void AdicionarProva(String nome, int duracao) {
        Prova prova = new Prova(nome, duracao);
        int novoId = 1;

        if (!_provasList.isEmpty()) {
            novoId = _provasList.get(_provasList.size() - 1).getId() + 1;
        }

        prova.setId(novoId);
        _provasList.add(prova);
    }

    /**
     * Retorna a lista de equipes da competição.
     *
     * @return Lista de equipes.
     */
    public List<Equipe> getEquipes() {
        return _equipesList;
    }

    /**
     * Retorna a lista de provas da competição.
     *
     * @return Lista de provas.
     */
    public List<Prova> getProvas() {
        return _provasList;
    }

    /**
     * Retorna a lista de participantes da competição.
     *
     * @return Lista de participantes.
     */
    public List<Participante> getParticipantes() {
        return _participantesList;
    }

    /**
     * Define a lista de equipes da competição.
     *
     * @param equipes Lista de equipes.
     */
    public void setEquipes(List<Equipe> equipes) {
        this._equipesList = equipes;
    }

    /**
     * Define a lista de participantes da competição.
     *
     * @param participantes Lista de participantes.
     */
    public void setParticipantes(List<Participante> participantes) {
        this._participantesList = participantes;
    }

    /**
     * Retorna uma equipe pelo nome.
     *
     * @param nome Nome da equipe.
     * @return Equipe correspondente ao nome, ou null se não encontrada.
     */
    public Equipe getEquipeByNome(String nome) {
        for (Equipe equipe : this._equipesList) {
            if (equipe.getNome().equals(nome)) {
                return equipe;
            }
        }
        return null;
    }

    /**
     * Atualiza a pontuação de uma equipe em uma prova.
     *
     * @param equipe     Equipe a ser atualizada.
     * @param prova      Prova correspondente.
     * @param pontuacao  Pontuação a ser adicionada.
     */
    public void atualizarPontuacaoEquipe(Equipe equipe, Prova prova, int pontuacao) {
        equipe.adicionarPontuacao(prova, pontuacao);
    }
}
