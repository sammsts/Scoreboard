import control.CompeticaoControl;
import view.ConfiguracaoView;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CompeticaoControl controle = new CompeticaoControl();
            ConfiguracaoView configuracaoView = new ConfiguracaoView(controle);
            configuracaoView.setVisible(true);
        });
    }
}
