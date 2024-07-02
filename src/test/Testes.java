package test;

import dao.ContextDatabase;
import java.sql.Connection;

public class Testes {
    public static void main(String[] args) {
        Connection connection = ContextDatabase.getInstance().getConnection();
        if (connection != null) {
            System.out.println("Connection established");
        }
    }
}
