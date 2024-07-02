package dao;

import model.Resultado;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ResultadoDAO {
    private Connection connection;

    public ResultadoDAO() {
        this.connection = ContextDatabase.getConnection();
    }

    public void salvar(Resultado resultado) {
        String sql = "INSERT INTO resultados (competicao_id, equipe_vencedora_id, data_resultado) " +
                "VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, resultado.getCompeticaoId());
            stmt.setInt(2, resultado.getEquipeVencedoraId());
            stmt.setTimestamp(3, new java.sql.Timestamp(resultado.getDataResultado().getTime()));
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
