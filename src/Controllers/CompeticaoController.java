package Controllers;

import Interfaces.IEquipeService;
import Interfaces.IParticipanteService;
import Model.Participante;
import Services.EquipeService;
import Services.ParticipanteService;
import Services.ProvaService;
import Model.Equipe;
import Model.Prova;

import java.util.ArrayList;
import java.util.List;

public class CompeticaoController {
    private List<Equipe> _equipesList;
    private List<Prova> _provasList;
    private List<Participante> _participantesList;
    private ProvaService _provaService;
    private IEquipeService _equipeService;
    private IParticipanteService _participanteService;

    public CompeticaoController() {
        this._equipesList = new ArrayList<>();
        this._provasList = new ArrayList<>();
        this._participantesList = new ArrayList<>();
        this._provaService = new ProvaService();
        this._equipeService = new EquipeService(this);
        this._participanteService = new ParticipanteService(this);
    }

    public void AdicionarEquipe(Equipe _equipe) {
        _equipeService.AddEquipe(_equipe, _equipesList);
    }

    public void AdicionarParticipante(Participante _participante) {
        _participanteService.AddParticipante(_participante);
    }

    public void RemoverParticipante(int id_participante) {
        _participanteService.RemoveParticipante(id_participante);
    }

    public void AdicionarProva(String nome, int duracao) {
        Prova prova = new Prova(nome, duracao);
        int novoId = 1;

        if (!_provasList.isEmpty()) {
            novoId = _provasList.get(_provasList.size() - 1).getId() + 1;
        }

        prova.setId(novoId);
        _provasList.add(prova);
    }

    public void IniciarCompeticao() {
        for (Equipe _equipe : _equipesList) {
            Equipe equipe = new Equipe(_equipe);
        }
        for (Prova prova : _provasList) {
        }
    }

    public List<Equipe> getEquipes() {
        return _equipesList;
    }

    public List<Prova> getProvas() {
        return _provasList;
    }

    public List<Participante> getParticipantes() {
        return _participantesList;
    }

    public void setEquipes(List<Equipe> equipes) {
        this._equipesList = equipes;
    }

    public void setParticipantes(List<Participante> participantes) {
        this._participantesList = participantes;
    }
}
