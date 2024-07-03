package Services;

import Controllers.CompeticaoController;
import Interfaces.IParticipanteService;
import Model.Equipe;
import Model.Participante;

import java.util.ArrayList;
import java.util.List;

public class ParticipanteService implements IParticipanteService {
    private CompeticaoController _controller;
    private Participante _participante;
    private List<Participante> _participantesList;

    public ParticipanteService(CompeticaoController controller) {
        _controller = controller;
        this._participantesList = new ArrayList<>();
    }

    @Override
    public List<Participante> AddParticipante(Participante participante) {
        int novoId = 1;

        if(!_controller.getParticipantes().isEmpty()) {
            novoId = _controller.getParticipantes().get(_controller.getParticipantes().size() - 1).getId() + 1;
        }

        _participante = new Participante(novoId, participante.getIdEquipe(), participante.getNome());
        _participantesList.add(_participante);
        _controller.setParticipantes(_participantesList);

        return _participantesList;
    }

    @Override
    public void RemoveParticipante(int id_participante) {
        System.out.println("Removing participante: " + id_participante);
        System.out.println(_controller.getParticipantes().toString());
        _controller.getParticipantes().remove(id_participante);
        System.out.println("\nParticipante removido!");
        System.out.println(_participantesList.toString());
    }
}
