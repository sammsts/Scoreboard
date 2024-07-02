package control;

import dao.EquipeDAO;
import dao.ProvaDAO;
import model.Equipe;
import model.Prova;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;

public class CompeticaoControl {
    private List<String> nomesEquipes;
    private List<Prova> provas;
    private EquipeDAO equipeDAO;
    private ProvaDAO provaDAO;
    private int competicaoId; // ID da competição a ser criada

    public CompeticaoControl() {
        this.nomesEquipes = new ArrayList<>();
        this.provas = new ArrayList<>();
        this.equipeDAO = new EquipeDAO();
        this.provaDAO = new ProvaDAO();
    }

    public void adicionarEquipe(String nomeEquipe) {
        nomesEquipes.add(nomeEquipe);
    }

    public void adicionarProva(String nome, int duracao) {
        Prova prova = new Prova(nome, duracao);
        prova.setDataProva(new Date());

        if (provas.toArray().length != 0) {
            System.out.println(provas.toArray().length);
            prova.setId(provas.toArray().length + 1);
        } else {
            prova.setId(1);
        }

        provas.add(prova);
    }

    public void iniciarCompeticao() {
        for (String nomeEquipe : nomesEquipes) {
            Equipe equipe = new Equipe(nomeEquipe, competicaoId);
            equipeDAO.salvar(equipe, competicaoId);
        }
        for (Prova prova : provas) {
            provaDAO.salvar(prova);
        }
    }

    public List<String> getEquipes() {
        return nomesEquipes;
    }

    public List<Prova> getProvas() {
        return provas;
    }
}
