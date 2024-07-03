package View;

import Controllers.CompeticaoController;
import Model.Equipe;
import Model.Prova;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Classe que representa a interface gráfica para o controle de pontuação das equipes em uma prova específica.
 * Estende JFrame para criar uma janela de aplicação.
 */
public class ControlePontuacaoView extends JFrame {
    private List<Equipe> _equipes;
    private JLabel _labelProvaAtual;
    private JTable _tabela;
    private PlacarView _placarView;
    private DefaultTableModel _model;
    private Prova _prova;
    private CompeticaoController _controller;

    /**
     * Construtor da classe ControlePontuacaoView.
     *
     * @param equipes      A lista de equipes participantes da competição.
     * @param prova        A prova para a qual a pontuação está sendo controlada.
     * @param controller   O controlador da competição.
     * @param placarView   A referência à janela de placar para atualização da pontuação.
     */
    public ControlePontuacaoView(List<Equipe> equipes, Prova prova, CompeticaoController controller, PlacarView placarView) {
        this._equipes = equipes;
        this._prova = prova;
        this._placarView = placarView;
        this._controller = controller;

        setTitle("Controle de Pontuação");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel contentPanel = new JPanel(new BorderLayout());

        String[] colunas = {"Equipes", "Pontuação"};
        Object[][] dados = new Object[equipes.size()][2];

        // Preenche os dados da tabela com o nome da equipe e sua pontuação na prova
        for (int i = 0; i < equipes.size(); i++) {
            Equipe equipe = equipes.get(i);
            dados[i][0] = equipe.getNome();
            dados[i][1] = equipe.getPontuacaoPorProva(prova);
        }

        _model = new DefaultTableModel(dados, colunas);
        _tabela = new JTable(_model);
        _tabela.setFont(new Font("Arial", Font.PLAIN, 14));
        _tabela.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        _tabela.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        _tabela.setFillsViewportHeight(true);

        JScrollPane scrollPane = new JScrollPane(_tabela);
        contentPanel.add(scrollPane, BorderLayout.CENTER);

        JButton salvarButton = new JButton("Salvar");
        salvarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                salvarPontuacoes(_tabela);
            }
        });
        contentPanel.add(salvarButton, BorderLayout.SOUTH);

        setContentPane(contentPanel);
    }

    /**
     * Método privado para salvar as pontuações atualizadas das equipes.
     *
     * @param tabela A tabela contendo as pontuações das equipes.
     */
    private void salvarPontuacoes(JTable tabela) {
        for (int i = 0; i < _equipes.size(); i++) {
            Equipe equipe = _equipes.get(i);
            int pontuacao = Integer.parseInt(tabela.getValueAt(i, 1).toString());
            _controller.atualizarPontuacaoEquipe(equipe, _prova, pontuacao);
        }

        _placarView.atualizarPontuacoes(_controller.getEquipes());
    }
}
