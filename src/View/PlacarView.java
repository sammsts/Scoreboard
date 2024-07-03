package View;

import Model.Equipe;
import Model.Prova;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class PlacarView extends JFrame {
    public PlacarView(List<Equipe> equipes, List<Prova> provas) {
        setTitle("Placar da Competição");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Exibição das equipes
        JPanel equipePanel = new JPanel();
        equipePanel.setLayout(new GridLayout(equipes.size(), 1));
        equipePanel.setBorder(BorderFactory.createTitledBorder("Equipes"));

        for (Equipe equipe : equipes) {
            equipePanel.add(new JLabel(equipe.getNome()));
        }

        // Exibição das provas
        JPanel provaPanel = new JPanel();
        provaPanel.setLayout(new GridLayout(provas.size(), 1));
        provaPanel.setBorder(BorderFactory.createTitledBorder("Provas"));

        for (Prova prova : provas) {
            provaPanel.add(new JLabel(prova.getNome() + " - " + prova.getDuracao() + " min"));
        }

        // Adicionar painéis ao JFrame
        add(equipePanel, BorderLayout.WEST);
        add(provaPanel, BorderLayout.EAST);
    }
}
