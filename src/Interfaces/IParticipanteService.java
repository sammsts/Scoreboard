package Interfaces;

import Model.Equipe;
import Model.Participante;

import java.util.List;

/**
 * Interface para os serviços relacionados a participantes.
 */
public interface IParticipanteService {

    /**
     * Adiciona um participante à lista de participantes.
     *
     * @param participante O participante a ser adicionado.
     * @return A lista atualizada de participantes.
     */
    public List<Participante> AddParticipante(Participante participante);

    /**
     * Remove um participante da lista de participantes pelo ID.
     *
     * @param id_participante O ID do participante a ser removido.
     */
    public void RemoveParticipante(int id_participante);
}
