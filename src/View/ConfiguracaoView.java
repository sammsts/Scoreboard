package View;

import Controllers.CompeticaoController;
import Erros.ConfiguracaoException;
import Helpers.Helpers;
import Model.Equipe;
import Model.Prova;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Classe que representa a interface gráfica de configuração da competição.
 * Estende JFrame para criar uma janela de aplicação.
 */
public class ConfiguracaoView extends JFrame {
    private JTextField nomeEquipeField;
    private JTextField nomeProvaField;
    private JTextField duracaoProvaField;
    private JButton adicionarEquipeButton;
    private JButton adicionarProvaButton;
    private JButton iniciarCompeticaoButton;
    private JButton adicionarParticipantesButton;
    private CompeticaoController _controller;
    private Helpers _helpers;

    /**
     * Construtor da classe ConfiguracaoView.
     *
     * @param controller O controlador da competição.
     * @param helpers    Um objeto Helpers para métodos auxiliares.
     */
    public ConfiguracaoView(CompeticaoController controller, Helpers helpers) {
        this._controller = controller;
        this._helpers = helpers;
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

        // Painel para iniciar competição / adicionar participantes
        JPanel iniciarPanel = new JPanel();
        iniciarCompeticaoButton = new JButton("Iniciar Competição");
        iniciarCompeticaoButton.setPreferredSize(new Dimension(300, 50));
        adicionarParticipantesButton = new JButton("Adicionar Participantes");
        adicionarParticipantesButton.setPreferredSize(new Dimension(300, 50));
        iniciarPanel.add(adicionarParticipantesButton);
        iniciarPanel.add(iniciarCompeticaoButton);

        // Adicionar os painéis ao JFrame
        add(equipePanel, BorderLayout.NORTH);
        add(provaPanel, BorderLayout.CENTER);
        add(iniciarPanel, BorderLayout.SOUTH);

        verificaIniciarCompeticao();

        // Configurar listeners
        adicionarEquipeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Equipe _equipe = new Equipe();
                    _equipe.setNome(nomeEquipeField.getText());
                    _equipe.setId(1);

                    if (_equipe.getNome().isEmpty()) {
                        throw new ConfiguracaoException("Defina o nome da equipe.", "Alerta", JOptionPane.WARNING_MESSAGE);
                    }

                    _controller.AdicionarEquipe(_equipe);
                    nomeEquipeField.setText("");
                } catch (ConfiguracaoException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), ex.getTitle(), ex.getMessageType());
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
        });

        adicionarProvaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String nomeProva = nomeProvaField.getText() != null && !nomeProvaField.getText().isEmpty() ? nomeProvaField.getText() : "";
                    int duracaoProva = !duracaoProvaField.getText().isEmpty() && _helpers.isNumeric(duracaoProvaField.getText()) ? Integer.parseInt(duracaoProvaField.getText()) : 0;

                    if (nomeProva.isEmpty()) {
                        throw new ConfiguracaoException("Insira o nome da prova.", "Alerta", JOptionPane.WARNING_MESSAGE);
                    }

                    if (!_helpers.isNumeric(duracaoProvaField.getText())) {
                        throw new ConfiguracaoException("A duração deve ser em dígitos (representa minutos).", "Alerta", JOptionPane.WARNING_MESSAGE);
                    }

                    if (!nomeProva.isEmpty() && duracaoProva > 0) {
                        _controller.AdicionarProva(nomeProva, duracaoProva);
                        nomeProvaField.setText("");
                        duracaoProvaField.setText("");
                    } else {
                        if (nomeProva.isEmpty() && duracaoProva == 0) {
                            throw new ConfiguracaoException("Por favor, insira um nome válido para a prova e uma duração maior que zero.", "Alerta", JOptionPane.WARNING_MESSAGE);
                        } else if (nomeProva.isEmpty()) {
                            throw new ConfiguracaoException("Por favor, insira um nome válido para a prova.", "Alerta", JOptionPane.WARNING_MESSAGE);
                        } else {
                            throw new ConfiguracaoException("Por favor, insira uma duração de prova maior que zero.", "Alerta", JOptionPane.WARNING_MESSAGE);
                        }
                    }
                } catch (ConfiguracaoException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), ex.getTitle(), ex.getMessageType());
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Ocorreu um erro inesperado.", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        iniciarCompeticaoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (_controller.getEquipes().isEmpty() || _controller.getProvas().isEmpty()) {
                        throw new ConfiguracaoException("A competição não pode ser iniciada sem equipes ou provas.", "Alerta", JOptionPane.WARNING_MESSAGE);
                    }

                    if (_controller.getParticipantes().isEmpty()) {
                        throw new ConfiguracaoException("A competição não pode ser iniciada sem participantes.", "Alerta", JOptionPane.WARNING_MESSAGE);
                    }

                    new PlacarView(_controller.getEquipes(), _controller.getProvas(), _controller).setVisible(true);
                    setVisible(false);
                } catch (ConfiguracaoException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Ocorreu um erro inesperado.", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        adicionarParticipantesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new ParticipantesView(controller, helpers, ConfiguracaoView.this).setVisible(true);
                    setEnabled(false);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Ocorreu um erro inesperado.", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    /**
     * Verifica se a competição pode ser iniciada com base na presença de equipes, provas e participantes.
     * Desabilita o botão de iniciar competição se algum dos requisitos não for atendido.
     */
    public void verificaIniciarCompeticao() {
        if (_controller.getEquipes().isEmpty() || _controller.getProvas().isEmpty() || _controller.getParticipantes().isEmpty()) {
            iniciarCompeticaoButton.setEnabled(false);
        } else {
            iniciarCompeticaoButton.setEnabled(true);
        }
    }
}
