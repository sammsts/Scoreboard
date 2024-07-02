package model;

import java.util.Date;

public class Resultado {
    private int id;
    private int competicaoId;
    private int equipeVencedoraId;
    private Date dataResultado;

    public Resultado() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCompeticaoId() {
        return competicaoId;
    }

    public void setCompeticaoId(int competicaoId) {
        this.competicaoId = competicaoId;
    }

    public int getEquipeVencedoraId() {
        return equipeVencedoraId;
    }

    public void setEquipeVencedoraId(int equipeVencedoraId) {
        this.equipeVencedoraId = equipeVencedoraId;
    }

    public Date getDataResultado() {
        return dataResultado;
    }

    public void setDataResultado(Date dataResultado) {
        this.dataResultado = dataResultado;
    }
}
