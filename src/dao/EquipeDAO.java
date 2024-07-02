package dao;

import model.Equipe;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EquipeDAO {
    private Connection connection;

    public EquipeDAO() {
        this.connection = ContextDatabase.getConnection();
    }

    public void salvar(Equipe equipe, int competicaoId) {
        String sql = "INSERT INTO equipes (eqp_nome, eqp_competicao_id) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, equipe.getNome());
            stmt.setInt(2, competicaoId);
            stmt.executeUpdate();

            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                equipe.setId(generatedKeys.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Equipe> listarEquipes(int competicaoId) {
        List<Equipe> equipes = new ArrayList<>();
        String sql = "SELECT * FROM equipes WHERE competicao_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, competicaoId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Equipe equipe = new Equipe();
                equipe.setId(rs.getInt("id"));
                equipe.setNome(rs.getString("nome"));
                equipes.add(equipe);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return equipes;
    }
}
