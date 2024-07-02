package view;

import control.CompeticaoControl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConfiguracaoView extends JFrame {
    private JTextField nomeEquipeField;
    private JTextField nomeProvaField;
    private JTextField duracaoProvaField;
    private JButton adicionarEquipeButton;
    private JButton adicionarProvaButton;
    private JButton iniciarCompeticaoButton;
    private CompeticaoControl controle;
    private int competicaoId; // Adicione esta variável para armazenar o ID da competição

    public ConfiguracaoView(CompeticaoControl controle) {
        this(controle, 0); // Chama o construtor com competicaoId default como 0
    }

    public ConfiguracaoView(CompeticaoControl controle, int competicaoId) {
        this.controle = controle;
        this.competicaoId = competicaoId; // Inicializa o ID da competição
        setTitle("Configuração da Competição");
        setSize(700, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Painel para equipes
        JPanel equipePanel = new JPanel();
        equipePanel.setBorder(BorderFactory.createTitledBorder("EQUIPES"));
        equipePanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        equipePanel.add(new JLabel("Nome da Equipe:"), gbc);

        nomeEquipeField = new JTextField();
        nomeEquipeField.setPreferredSize(new Dimension(300, 30));
        gbc.gridx = 1;
        gbc.gridy = 0;
        equipePanel.add(nomeEquipeField, gbc);

        adicionarEquipeButton = new JButton("Adicionar");
        adicionarEquipeButton.setPreferredSize(new Dimension(150, 30));
        gbc.gridx = 2;
        gbc.gridy = 0;
        equipePanel.add(adicionarEquipeButton, gbc);

        // Painel para provas
        JPanel provaPanel = new JPanel();
        provaPanel.setBorder(BorderFactory.createTitledBorder("PROVAS"));
        provaPanel.setLayout(new GridBagLayout());

        gbc.gridx = 0;
        gbc.gridy = 0;
        provaPanel.add(new JLabel("Nome da Prova:"), gbc);

        nomeProvaField = new JTextField();
        nomeProvaField.setPreferredSize(new Dimension(300, 30));
        gbc.gridx = 1;
        gbc.gridy = 0;
        provaPanel.add(nomeProvaField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        provaPanel.add(new JLabel("Duração (minutos):"), gbc);

        duracaoProvaField = new JTextField();
        duracaoProvaField.setPreferredSize(new Dimension(300, 30));
        gbc.gridx = 1;
        gbc.gridy = 1;
        provaPanel.add(duracaoProvaField, gbc);

        adicionarProvaButton = new JButton("Adicionar");
        adicionarProvaButton.setPreferredSize(new Dimension(150, 30));
        gbc.gridx = 2;
        gbc.gridy = 1;
        provaPanel.add(adicionarProvaButton, gbc);

        // Painel para iniciar competição
        JPanel iniciarPanel = new JPanel();
        iniciarCompeticaoButton = new JButton("Iniciar Competição");
        iniciarCompeticaoButton.setPreferredSize(new Dimension(300, 50));
        iniciarPanel.add(iniciarCompeticaoButton);

        // Adicionar os painéis ao JFrame
        add(equipePanel, BorderLayout.NORTH);
        add(provaPanel, BorderLayout.CENTER);
        add(iniciarPanel, BorderLayout.SOUTH);

        // Configurar listeners
        adicionarEquipeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nomeEquipe = nomeEquipeField.getText();
                if (!nomeEquipe.isEmpty()) {
                    controle.adicionarEquipe(nomeEquipe);
                    nomeEquipeField.setText("");
                }
            }
        });

        adicionarProvaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nomeProva = nomeProvaField.getText();
                int duracaoProva = Integer.parseInt(duracaoProvaField.getText());
                if (!nomeProva.isEmpty() && duracaoProva > 0) {
                    controle.adicionarProva(nomeProva, duracaoProva);
                    nomeProvaField.setText("");
                    duracaoProvaField.setText("");
                }
            }
        });

        iniciarCompeticaoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //controle.iniciarCompeticao();
                /*new PlacarView(controle.getEquipes(), controle.getProvas()).setVisible(true);
                setVisible(false);*/

                System.out.println(controle.getEquipes());
                System.out.println(controle.getProvas());
            }
        });
    }
}
