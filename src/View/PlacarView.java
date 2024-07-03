package View;

import Controllers.CompeticaoController;
import Model.Equipe;
import Model.Prova;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Classe que representa a interface gráfica do placar da competição.
 * Estende JFrame para criar uma janela de aplicação.
 */
public class PlacarView extends JFrame {
    private CompeticaoController _controller;
    private JLabel _timerLabel;
    private JLabel _provaAtualLabel;
    private JButton _iniciarProvaButton;
    private JButton _pausarProvaButton;
    private JButton _retomarProvaButton;
    private JButton _reiniciarProvaButton;
    private JTable _tabela;
    private DefaultTableModel _model;
    private List<Prova> _provas;
    private int _provaAtualIndex;
    private Timer _provaTimer;
    private int _tempoRestante;
    private Prova _prova;

    /**
     * Construtor da classe PlacarView.
     *
     * @param equipes   Lista de equipes participantes da competição.
     * @param provas    Lista de provas da competição.
     * @param controller O controlador da competição.
     */
    public PlacarView(List<Equipe> equipes, List<Prova> provas, CompeticaoController controller) {
        this._controller = controller;
        this._provas = provas;
        this._provaAtualIndex = 0; // Começa com a primeira prova
        this._tempoRestante = 0; // Tempo restante inicializado com 0

        setTitle("Placar da Competição");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBackground(Color.darkGray);

        // Painel do título e temporizador
        JPanel tituloPanel = new JPanel(new BorderLayout());
        tituloPanel.setBackground(Color.lightGray);
        tituloPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel titleLabel = new JLabel("NOME DA COMPETIÇÃO");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        tituloPanel.add(titleLabel, BorderLayout.NORTH);

        JPanel timerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        _timerLabel = new JLabel("Tempo Restante: 00:00");
        _timerLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        timerPanel.add(_timerLabel);
        tituloPanel.add(timerPanel, BorderLayout.CENTER);

        contentPanel.add(tituloPanel, BorderLayout.NORTH);

        // Painel da tabela de pontuações
        JPanel tabelaPanel = new JPanel(new BorderLayout());
        tabelaPanel.setBackground(Color.white);
        tabelaPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        String[] colunas = new String[provas.size() + 2];
        colunas[0] = "Equipes";
        for (int i = 0; i < provas.size(); i++) {
            colunas[i + 1] = provas.get(i).getNome();
        }
        colunas[colunas.length - 1] = "Total";

        Object[][] dados = new Object[equipes.size()][colunas.length];
        for (int i = 0; i < equipes.size(); i++) {
            Equipe equipe = equipes.get(i);
            dados[i][0] = equipe.getNome();

            for (int j = 0; j < provas.size(); j++) {
                dados[i][j + 1] = "Pontuação";
            }

            dados[i][colunas.length - 1] = "Total";
        }

        _model = new DefaultTableModel(dados, colunas); // Removido o override para permitir edição

        _tabela = new JTable(_model);
        _tabela.setFont(new Font("Arial", Font.PLAIN, 14));
        _tabela.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        _tabela.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        _tabela.setFillsViewportHeight(true);

        JScrollPane scrollPane = new JScrollPane(_tabela);
        tabelaPanel.add(scrollPane, BorderLayout.CENTER);

        contentPanel.add(tabelaPanel, BorderLayout.CENTER);

        // Painel de controle (botões de iniciar, pausar, retomar e reiniciar prova)
        JPanel controlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        controlePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        _iniciarProvaButton = new JButton("Iniciar Prova");
        _iniciarProvaButton.setFont(new Font("Arial", Font.PLAIN, 14));
        _iniciarProvaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                iniciarProva();
            }
        });
        controlePanel.add(_iniciarProvaButton);

        _pausarProvaButton = new JButton("Pausar");
        _pausarProvaButton.setFont(new Font("Arial", Font.PLAIN, 14));
        _pausarProvaButton.setEnabled(false);
        _pausarProvaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pausarProva();
            }
        });
        controlePanel.add(_pausarProvaButton);

        _retomarProvaButton = new JButton("Retomar");
        _retomarProvaButton.setFont(new Font("Arial", Font.PLAIN, 14));
        _retomarProvaButton.setEnabled(false);
        _retomarProvaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                retomarProva();
            }
        });
        controlePanel.add(_retomarProvaButton);

        _reiniciarProvaButton = new JButton("Reiniciar");
        _reiniciarProvaButton.setFont(new Font("Arial", Font.PLAIN, 14));
        _reiniciarProvaButton.setEnabled(false);
        _reiniciarProvaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reiniciarProva();
            }
        });
        controlePanel.add(_reiniciarProvaButton);

        contentPanel.add(controlePanel, BorderLayout.SOUTH);

        setContentPane(contentPanel);
    }

    /**
     * Método para atualizar as pontuações das equipes na tabela do placar.
     *
     * @param equipes A lista atualizada de equipes com as pontuações atualizadas.
     */
    public void atualizarPontuacoes(List<Equipe> equipes) {
        for (int i = 0; i < equipes.size(); i++) {
            Equipe equipe = equipes.get(i);
            for (int j = 1; j <= _provas.size(); j++) {
                Prova prova = _provas.get(j - 1);
                int pontuacao = equipe.getPontuacaoPorProva(prova);
                _model.setValueAt(pontuacao, i, j);
            }
            int pontuacaoTotal = equipe.getPontuacaoTotal();
            _model.setValueAt(pontuacaoTotal, i, _model.getColumnCount() - 1);
        }
        _model.fireTableDataChanged();
    }

    /**
     * Método para iniciar a prova atualmente selecionada.
     * Inicia o temporizador e abre a tela de controle de pontuação da prova.
     */
    private void iniciarProva() {
        _prova = _provas.get(_provaAtualIndex);
        _timerLabel.setText("Tempo Restante: " + _prova.getDuracao() + " minutos");

        _iniciarProvaButton.setEnabled(false);
        _pausarProvaButton.setEnabled(true);
        _retomarProvaButton.setEnabled(false);
        _reiniciarProvaButton.setEnabled(false);

        SwingUtilities.invokeLater(() -> {
            ControlePontuacaoView telaPontuacao = new ControlePontuacaoView(_controller.getEquipes(), _prova, _controller, PlacarView.this);
            telaPontuacao.setVisible(true);
            telaPontuacao.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                    atualizarTabela();
                }
            });
        });

        // Inicia o temporizador para a prova atual
        _provaTimer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                _tempoRestante--;
                if (_tempoRestante >= 0) {
                    atualizarTemporizador();
                } else {
                    finalizarProvaAtual();
                }
            }
        });
        _tempoRestante = _prova.getDuracao() * 60; // Converte minutos em segundos
        _provaTimer.start();
    }

    /**
     * Método para pausar a prova atualmente em andamento.
     * Pausa o temporizador da prova.
     */
    private void pausarProva() {
        _provaTimer.stop();
        _pausarProvaButton.setEnabled(false);
        _retomarProvaButton.setEnabled(true);
    }

    /**
     * Método para retomar a prova que foi pausada.
     * Retoma o temporizador da prova.
     */
    private void retomarProva() {
        _provaTimer.start();
        _pausarProvaButton.setEnabled(true);
        _retomarProvaButton.setEnabled(false);
    }

    /**
     * Método para reiniciar a prova atualmente em andamento.
     * Reinicia o temporizador da prova para o tempo inicial.
     */
    private void reiniciarProva() {
        _provaTimer.stop();
        _tempoRestante = _prova.getDuracao() * 60;
        atualizarTemporizador();
        _iniciarProvaButton.setEnabled(true);
        _pausarProvaButton.setEnabled(false);
        _retomarProvaButton.setEnabled(false);
    }

    /**
     * Método para finalizar a prova atualmente em andamento.
     * Para o temporizador da prova e avança para a próxima prova na lista.
     * Se não houver mais provas, exibe uma mensagem de conclusão.
     */
    private void finalizarProvaAtual() {
        _provaTimer.stop();
        _provaAtualIndex++;
        setVisible(false);

        // Atualiza a tabela se houver mais provas para realizar
        if (_provaAtualIndex < _provas.size()) {
            _tempoRestante = 0;
            iniciarProva();
        } else {
            JOptionPane.showMessageDialog(this, "Todas as provas foram concluídas!");
        }
    }

    /**
     * Método para atualizar o temporizador com o tempo restante da prova atual.
     * Atualiza o rótulo do temporizador na interface gráfica.
     */
    private void atualizarTemporizador() {
        int minutos = _tempoRestante / 60;
        int segundos = _tempoRestante % 60;
        String tempoFormatado = String.format("Tempo Restante: %02d:%02d", minutos, segundos);
        _timerLabel.setText(tempoFormatado);
    }

    /**
     * Método para atualizar a tabela com as pontuações atualizadas das equipes.
     * Atualiza o modelo da tabela e notifica a interface gráfica das mudanças.
     */
    private void atualizarTabela() {
        // Atualiza o modelo da tabela com os dados atualizados das equipes
        for (int i = 0; i < _model.getRowCount(); i++) {
            for (int j = 1; j <= _provas.size(); j++) { // Começa em 1 para ignorar a coluna "Equipes"
                // Atualiza cada célula com a pontuação correta das equipes
                String nomeEquipe = _model.getValueAt(i, 0).toString();
                Equipe equipe = _controller.getEquipeByNome(nomeEquipe);
                int pontuacao = equipe.getPontuacaoPorProva(_provas.get(j - 1)); // j-1 para ajustar o índice
                _model.setValueAt(pontuacao, i, j);
            }

            // Atualiza a coluna "Total" com a pontuação total da equipe
            String nomeEquipe = _model.getValueAt(i, 0).toString();
            Equipe equipe = _controller.getEquipeByNome(nomeEquipe);
            int pontuacaoTotal = equipe.getPontuacaoTotal();
            _model.setValueAt(pontuacaoTotal, i, _model.getColumnCount() - 1);
        }

        // Notifica a tabela de que os dados foram alterados
        _model.fireTableDataChanged();
    }
}
