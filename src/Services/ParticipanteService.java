package Services;

import Controllers.CompeticaoController;
import Interfaces.IParticipanteService;
import Model.Participante;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementação da interface IParticipanteService para operações relacionadas a participantes.
 */
public class ParticipanteService implements IParticipanteService {
    private CompeticaoController _controller;
    private Participante _participante;
    private List<Participante> _participantesList;

    /**
     * Construtor da classe ParticipanteService.
     *
     * @param controller O controlador da competição responsável por gerenciar os participantes.
     */
    public ParticipanteService(CompeticaoController controller) {
        _controller = controller;
        this._participantesList = new ArrayList<>();
    }

    /**
     * Adiciona um participante à lista de participantes.
     *
     * @param participante O participante a ser adicionado.
     * @return A lista atualizada de participantes.
     */
    @Override
    public List<Participante> AddParticipante(Participante participante) {
        int novoId = 1;

        if (!_controller.getParticipantes().isEmpty()) {
            novoId = _controller.getParticipantes().get(_controller.getParticipantes().size() - 1).getId() + 1;
        }

        _participante = new Participante(novoId, participante.getIdEquipe(), participante.getNome());
        _participantesList.add(_participante);
        _controller.setParticipantes(_participantesList);

        return _participantesList;
    }

    /**
     * Remove um participante da lista de participantes pelo ID.
     *
     * @param id_participante O ID do participante a ser removido.
     */
    @Override
    public void RemoveParticipante(int id_participante) {
        System.out.println("Removing participante: " + id_participante);
        System.out.println(_controller.getParticipantes().toString());

        List<Participante> participantes = _controller.getParticipantes();
        Participante participanteToRemove = null;

        for (Participante participante : participantes) {
            if (participante.getId() == id_participante) {
                participanteToRemove = participante;
                break;
            }
        }

        if (participanteToRemove != null) {
            participantes.remove(participanteToRemove);
        }
    }
}
