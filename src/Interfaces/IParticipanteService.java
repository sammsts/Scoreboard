package Interfaces;

import Model.Equipe;
import Model.Participante;

import java.util.List;

public interface IParticipanteService {
    public List<Participante> AddParticipante(Participante participante);
    public void RemoveParticipante(int id_participante);
}
