package Erros;

/**
 * Exceção personalizada para erros de configuração.
 * Esta exceção contém um título e um tipo de mensagem além da mensagem padrão.
 */
public class ConfiguracaoException extends Throwable {
    private String title;
    private int messageType;

    /**
     * Construtor para a exceção ConfiguracaoException.
     *
     * @param message     A mensagem de erro.
     * @param title       O título da mensagem de erro.
     * @param messageType O tipo da mensagem de erro.
     */
    public ConfiguracaoException(String message, String title, int messageType) {
        super(message);
        this.title = title;
        this.messageType = messageType;
    }

    /**
     * Retorna o título da mensagem de erro.
     *
     * @return O título da mensagem de erro.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Retorna o tipo da mensagem de erro.
     *
     * @return O tipo da mensagem de erro.
     */
    public int getMessageType() {
        return messageType;
    }
}
