package model.dao.impl;

import db.DbException;
import model.dao.FuncionarioDao;
import model.entities.Departamento;
import model.entities.Funcionario;

import java.sql.*;
import java.util.List;

public class FuncionarioDaoJDBC implements FuncionarioDao {

    private final Connection connection;

    public FuncionarioDaoJDBC(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void inserirFuncionario(Funcionario funcionario) {
        String sql = "INSERT INTO funcionario " +
                "(nome, email, idDepartamento) " +
                "VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, funcionario.getNome());
            preparedStatement.setString(2, funcionario.getEmail());
            preparedStatement.setInt(3, funcionario.getDepartamento().getId());

            int linhasAfetadas = preparedStatement.executeUpdate();

            if (linhasAfetadas > 0) {
                try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                    if (resultSet.next()) {
                        int id = resultSet.getInt(1);
                        funcionario.setId(id);
                    }
                }
            }
        }
        catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }


    @Override
    public void atualizarFuncionario(Funcionario funcionario) {
        String sql = "UPDATE funcionario SET nome = ?, email = ?, idDepartamento = ? WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, funcionario.getNome());
            preparedStatement.setString(2, funcionario.getEmail());
            preparedStatement.setInt(3, funcionario.getDepartamento().getId());
            preparedStatement.setInt(4, funcionario.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public void removerFuncionario(Integer id) {

    }

    @Override
    public Funcionario encontrarFuncionarioId(Integer id) {
        return null;
    }

    @Override
    public List<Funcionario> funcionariosDepartamento(Departamento departamento) {
        return null;
    }

    @Override
    public List<Funcionario> todosFuncionarios() {
        return null;
    }
}
