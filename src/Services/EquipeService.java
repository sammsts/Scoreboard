package Services;

import Controllers.CompeticaoController;
import Interfaces.IEquipeService;
import Model.Equipe;

import java.util.List;

/**
 * Implementação da interface IEquipeService para operações relacionadas a equipes.
 */
public class EquipeService implements IEquipeService {
    private CompeticaoController _controller;

    /**
     * Construtor da classe EquipeService.
     *
     * @param controller O controlador da competição responsável por gerenciar as equipes.
     */
    public EquipeService(CompeticaoController controller) {
        this._controller  = controller;
    }

    /**
     * Adiciona uma equipe à lista de equipes.
     *
     * @param equipe      A equipe a ser adicionada.
     * @param equipesList A lista de equipes onde a nova equipe será adicionada.
     */
    @Override
    public void AddEquipe(Equipe equipe, List<Equipe> equipesList) {
        int novoId = 1;

        if (!equipesList.isEmpty()) {
            novoId = equipesList.get(equipesList.size() - 1).getId() + 1;
        }

        equipe.setId(novoId);
        equipesList.add(equipe);
        _controller.setEquipes(equipesList);
    }
}
