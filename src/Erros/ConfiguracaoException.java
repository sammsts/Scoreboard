package Erros;

public class ConfiguracaoException extends Throwable {
    private String title;
    private int messageType;

    public ConfiguracaoException(String message, String title, int messageType) {
        super(message);
        this.title = title;
        this.messageType = messageType;
    }

    public String getTitle() {
        return title;
    }

    public int getMessageType() {
        return messageType;
    }
}