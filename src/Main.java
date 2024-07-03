import Controllers.CompeticaoController;
import Helpers.Helpers;
import View.ConfiguracaoView;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CompeticaoController controle = new CompeticaoController();
            Helpers _helpers = new Helpers();
            ConfiguracaoView configuracaoView = new ConfiguracaoView(controle, _helpers);
            configuracaoView.setVisible(true);
        });
    }
}
