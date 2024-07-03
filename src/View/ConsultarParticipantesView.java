package View;

import Controllers.CompeticaoController;
import Erros.ConfiguracaoException;
import Helpers.Helpers;
import Model.Equipe;
import Model.Participante;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Classe que representa a interface gráfica para consultar e remover participantes cadastrados na competição.
 * Estende JFrame para criar uma janela de aplicação.
 */
public class ConsultarParticipantesView extends JFrame {
    private JTextField nomeParticipanteField;
    private JButton removerParticipanteButton;
    private JButton fecharParticipantesButton;
    private CompeticaoController _controller;
    private Helpers _helpers;
    private ParticipantesView _participantesView;

    /**
     * Construtor da classe ConsultarParticipantesView.
     *
     * @param controller      O controlador da competição.
     * @param helpers         Um objeto Helpers para métodos auxiliares.
     * @param participantesView A janela de participantes principal para controle de visibilidade.
     */
    public ConsultarParticipantesView(CompeticaoController controller, Helpers helpers, ParticipantesView participantesView) {
        this._controller = controller;
        this._helpers = helpers;
        this._participantesView = participantesView;

        setTitle("Consultar Participantes Cadastrados");
        setSize(700, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Painel para participantes e equipes
        JPanel participantesPanel = new JPanel();
        participantesPanel.setBorder(BorderFactory.createTitledBorder("PARTICIPANTES"));
        participantesPanel.setLayout(new GridBagLayout());

        atualizarParticipantesPanel(participantesPanel);

        JButton fecharParticipantesButton = new JButton("Fechar");
        fecharParticipantesButton.setPreferredSize(new Dimension(150, 30));
        JPanel fecharParticipantesViewPanel = new JPanel();
        fecharParticipantesViewPanel.add(fecharParticipantesButton);

        fecharParticipantesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    setVisible(false);
                    _participantesView.setEnabled(true);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Ocorreu um erro inesperado.", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        add(participantesPanel, BorderLayout.CENTER);
        add(fecharParticipantesViewPanel, BorderLayout.SOUTH);
    }

    /**
     * Método privado para atualizar o painel de participantes com base nas equipes e participantes cadastrados.
     *
     * @param participantesPanel O painel onde os participantes serão exibidos e manipulados.
     */
    private void atualizarParticipantesPanel(JPanel participantesPanel) {
        participantesPanel.removeAll();
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        participantesPanel.add(new JLabel("Participantes das equipes:"), gbc);

        List<Equipe> equipes = _controller.getEquipes();
        List<Participante> participantes = _controller.getParticipantes();
        gbc.gridy++;

        for (Equipe equipe : equipes) {
            gbc.gridx = 0;

            JLabel equipeLabel = new JLabel(equipe.getNome());
            gbc.gridy++;
            participantesPanel.add(equipeLabel, gbc);

            for (Participante participante : participantes) {
                if (participante.getIdEquipe() == equipe.getId()) {
                    gbc.gridy++;
                    gbc.gridx = 0;
                    participantesPanel.add(new JLabel("Nome:"), gbc);

                    JTextField nomeParticipanteField = new JTextField(participante.getNome());
                    nomeParticipanteField.setPreferredSize(new Dimension(300, 30));
                    nomeParticipanteField.setEditable(false);  // Campo de texto não editável
                    gbc.gridx = 1;
                    participantesPanel.add(nomeParticipanteField, gbc);

                    JButton removerParticipanteButton = new JButton("Remover");
                    removerParticipanteButton.setPreferredSize(new Dimension(150, 30));
                    gbc.gridx = 2;
                    participantesPanel.add(removerParticipanteButton, gbc);

                    removerParticipanteButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            try {
                                _controller.RemoverParticipante(participante.getId());
                                atualizarParticipantesPanel(participantesPanel);
                            } catch (Exception ex) {
                                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    });

                    gbc.gridx = 0;
                    gbc.gridy++;
                }
            }
        }

        participantesPanel.revalidate();
        participantesPanel.repaint();
    }
}
