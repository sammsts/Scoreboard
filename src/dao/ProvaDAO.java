package dao;

import model.Prova;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProvaDAO {
    private Connection connection;

    public ProvaDAO() {
        this.connection = ContextDatabase.getConnection();
    }

    public void salvar(Prova prova) {
        String sql = "INSERT INTO provas (competicao_id, data_prova, nome, duracao) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, prova.getCompeticaoId());
            stmt.setTimestamp(2, new java.sql.Timestamp(prova.getDataProva().getTime()));
            stmt.setString(3, prova.getNome());
            stmt.setInt(4, prova.getDuracao());
            stmt.executeUpdate();

            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                prova.setId(generatedKeys.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Prova> listarProvas(int competicaoId) {
        List<Prova> provas = new ArrayList<>();
        String sql = "SELECT * FROM provas WHERE competicao_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, competicaoId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Prova prova = new Prova(
                        rs.getInt("id"),
                        rs.getInt("competicao_id"),
                        rs.getTimestamp("data_prova"),
                        rs.getString("nome"),
                        rs.getInt("duracao")
                );
                provas.add(prova);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return provas;
    }
}
