package Helpers;

/**
 * Classe de ajudantes contendo métodos utilitários.
 */
public class Helpers {

    /**
     * Verifica se uma string é numérica.
     *
     * @param str A string a ser verificada.
     * @return {@code true} se a string for numérica, caso contrário, {@code false}.
     */
    public boolean isNumeric(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }
        for (char c : str.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }
}
