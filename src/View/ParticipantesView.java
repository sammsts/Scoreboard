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
 * Classe que representa a interface gráfica para a configuração de participantes nas equipes da competição.
 * Estende JFrame para criar uma janela de aplicação.
 */
public class ParticipantesView extends JFrame {
    private JTextField nomeParticipanteField;
    private JButton adicionarParticipanteButton;
    private JButton salvarParticipantesButton;
    private JButton consultarParticipantesButton;
    private CompeticaoController _controller;
    private ConfiguracaoException _configuracaoException;
    private Helpers _helpers;
    private ConfiguracaoView _configuracaoView;

    /**
     * Construtor da classe ParticipantesView.
     *
     * @param controller      O controlador da competição.
     * @param helpers         A classe utilitária Helpers.
     * @param configuracaoView A janela de configuração principal da competição.
     */
    public ParticipantesView(CompeticaoController controller, Helpers helpers, ConfiguracaoView configuracaoView) {
        this._controller = controller;
        this._helpers = helpers;
        this._configuracaoView = configuracaoView;

        setTitle("Configuração dos participantes");
        setSize(700, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel participantesPanel = new JPanel();
        participantesPanel.setBorder(BorderFactory.createTitledBorder("PARTICIPANTES"));
        participantesPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        participantesPanel.add(new JLabel("Participantes das equipes:"), gbc);

        List<Equipe> equipes = _controller.getEquipes();
        gbc.gridy++;

        // Loop para adicionar componentes de interface para cada equipe e seus participantes
        for (Equipe equipe : equipes) {
            gbc.gridx = 0;

            JLabel equipeLabel = new JLabel(equipe.getNome());
            gbc.gridy++;
            participantesPanel.add(equipeLabel, gbc);

            gbc.gridy++;
            participantesPanel.add(new JLabel("Nome:"), gbc);

            JTextField nomeParticipanteFieldLocal = new JTextField();
            nomeParticipanteFieldLocal.setPreferredSize(new Dimension(300, 30));
            gbc.gridx = 1;
            participantesPanel.add(nomeParticipanteFieldLocal, gbc);

            JButton adicionarParticipanteButtonLocal = new JButton("Adicionar");
            adicionarParticipanteButtonLocal.setPreferredSize(new Dimension(150, 30));
            gbc.gridx = 2;
            participantesPanel.add(adicionarParticipanteButtonLocal, gbc);

            // Listener para adicionar um participante à equipe
            adicionarParticipanteButtonLocal.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        Participante participante = new Participante();
                        String nomeParticipante = nomeParticipanteFieldLocal.getText() != null && !nomeParticipanteFieldLocal.getText().isEmpty() ? nomeParticipanteFieldLocal.getText() : "";

                        if (nomeParticipante.isEmpty()) {
                            throw new ConfiguracaoException("Insira o nome do participante.", "Alerta", JOptionPane.WARNING_MESSAGE);
                        }

                        participante.setId(0);
                        participante.setIdEquipe(equipe.getId());
                        participante.setNome(nomeParticipante);

                        _controller.AdicionarParticipante(participante);
                        nomeParticipanteFieldLocal.setText("");
                    } catch (ConfiguracaoException ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage(), ex.getTitle(), ex.getMessageType());
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage());
                    }
                }
            });

            gbc.gridx = 0;
            gbc.gridy++;
        }

        salvarParticipantesButton = new JButton("Salvar");
        salvarParticipantesButton.setPreferredSize(new Dimension(150, 30));
        consultarParticipantesButton = new JButton("Participantes");
        consultarParticipantesButton.setPreferredSize(new Dimension(150, 30));
        gbc.gridx = 2;

        JPanel fecharParticipantesViewPanel = new JPanel();
        fecharParticipantesViewPanel.add(salvarParticipantesButton);
        fecharParticipantesViewPanel.add(consultarParticipantesButton);

        // Listener para abrir a janela de consulta de participantes
        consultarParticipantesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new ConsultarParticipantesView(controller, helpers, ParticipantesView.this).setVisible(true);
                    setEnabled(false);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Ocorreu um erro inesperado.", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Listener para fechar a janela de configuração de participantes e retornar à configuração principal
        salvarParticipantesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    setVisible(false);
                    _configuracaoView.setEnabled(true);
                    _configuracaoView.verificaIniciarCompeticao();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Ocorreu um erro inesperado.", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        add(participantesPanel, BorderLayout.NORTH);
        add(fecharParticipantesViewPanel, BorderLayout.SOUTH);
    }
}
