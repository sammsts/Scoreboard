package dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class ContextDatabase {
    private static  String pathDatabase = "motty.db.elephantsql.com";
    private static  String portDatabase = "5432";
    private static  String nameDatabase = "pmxwrwvx";
    private static  String userDatabase = "pmxwrwvx";
    private static  String passwordDatabase = "ISUAzjQLq5_jGXpZLfIKPH_tFl7qdVq9";

    private static  String URL = "jdbc:postgresql://"+pathDatabase+":"+portDatabase+"/"+nameDatabase;

    private static ContextDatabase instance;

    private ContextDatabase() {}

    public static ContextDatabase getInstance() {
        if (instance == null) {
            instance = new ContextDatabase();
        }
        return instance;
    }

    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(URL, userDatabase, passwordDatabase);
            System.out.println("Conexão estabelecida com sucesso!");
        } catch (Exception ex) {
            System.out.println("Erro ao conectar com o database: \n" + ex.getMessage());
        }
        return connection;
    }
}
