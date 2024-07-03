package Interfaces;

import Model.Equipe;

import java.util.List;

/**
 * Interface para os serviços relacionados a equipes.
 */
public interface IEquipeService {

    /**
     * Adiciona uma equipe à lista de equipes.
     *
     * @param equipe  A equipe a ser adicionada.
     * @param equipes A lista de equipes onde a nova equipe será adicionada.
     */
    public void AddEquipe(Equipe equipe, List<Equipe> equipes);
}
