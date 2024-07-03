import Controllers.CompeticaoController;
import Helpers.Helpers;
import View.ConfiguracaoView;
import View.PlacarView;

import javax.swing.*;

/**
 * Classe principal que inicia a aplicação da competição.
 * Cria uma instância do controlador da competição, dos ajudantes e da janela de configuração.
 * A aplicação é iniciada na thread de despacho de eventos do Swing.
 */
public class Main {
    /**
     * Método principal que inicia a aplicação da competição.
     * Cria uma instância do controlador da competição, dos ajudantes e da janela de configuração,
     * e mostra a janela de configuração para o usuário.
     *
     * @param args Argumentos da linha de comando (não utilizados neste contexto).
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Cria uma instância do controlador da competição
            CompeticaoController controller = new CompeticaoController();

            // Cria uma instância dos ajudantes
            Helpers _helpers = new Helpers();

            // Cria uma instância da janela de configuração e a torna visível
            ConfiguracaoView configuracaoView = new ConfiguracaoView(controller, _helpers);
            configuracaoView.setVisible(true);
        });
    }
}
