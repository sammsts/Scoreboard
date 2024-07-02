package dao;

import model.Competicao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CompeticaoDAO {
    private Connection connection;

    public CompeticaoDAO() {
        this.connection = ContextDatabase.getConnection();
    }

    public void salvar(Competicao competicao) {
        String sql = "INSERT INTO competicoes (nome, descricao, data_inicio, data_fim) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, competicao.getNome());
            stmt.setString(2, competicao.getDescricao());
            stmt.setDate(3, new java.sql.Date(competicao.getDataInicio().getTime()));
            stmt.setDate(4, new java.sql.Date(competicao.getDataFim().getTime()));
            stmt.executeUpdate();

            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                competicao.setId(generatedKeys.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Competicao> listarCompeticoes() {
        List<Competicao> competicoes = new ArrayList<>();
        String sql = "SELECT * FROM competicoes";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Competicao competicao = new Competicao();
                competicao.setId(rs.getInt("id"));
                competicao.setNome(rs.getString("nome"));
                competicao.setDescricao(rs.getString("descricao"));
                competicao.setDataInicio(rs.getDate("data_inicio"));
                competicao.setDataFim(rs.getDate("data_fim"));
                competicoes.add(competicao);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return competicoes;
    }
}
