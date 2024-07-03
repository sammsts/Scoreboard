package Services;

import Controllers.CompeticaoController;
import Interfaces.IEquipeService;
import Model.Equipe;
import Model.Participante;

import java.util.List;

public class EquipeService implements IEquipeService {
    private CompeticaoController _controller;

    public EquipeService(CompeticaoController controller) {
        this._controller  = controller;
    }

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
