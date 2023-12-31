package model.dao.impl;

import db.DbException;
import model.dao.DepartamentoDao;
import model.entities.Departamento;
import model.services.InstanciarEntidades;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DepartamentoDaoJDBC implements DepartamentoDao {

    private final Connection connection;

    public DepartamentoDaoJDBC(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void inserirDepartamento(Departamento departamento) {
        String sql = "INSERT INTO departamento (nome) VALUES (?)";
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, departamento.getNome());

            int linhasAfetadas = preparedStatement.executeUpdate();

            if (linhasAfetadas > 0) {
                ResultSet resultSet = preparedStatement.getGeneratedKeys();
                if (resultSet.next()) {
                    int id = resultSet.getInt(1);
                    departamento.setId(id);
                }
                resultSet.close();
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    //Atualizar departamento pelo ID
    @Override
    public void atualizarDepartamento(Departamento departamento) {
        String sql = "UPDATE departamento SET nome = ? WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, departamento.getNome());
            preparedStatement.setInt(2, departamento.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    //Deletar departamento pelo ID
    @Override
    public void removerDepartamentoPorId(Integer id) {
        String sql = "DELETE FROM departamento WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public Departamento encontrarDepartamentoPorId(Integer id) {
        String sql = "SELECT * FROM departamento WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                Departamento departamento;
                if (resultSet.next()) {
                    departamento = InstanciarEntidades.departamento(resultSet);
                    return departamento;

                }
                return null;
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public List<Departamento> todosDepartamentos() {
        String sql = "SELECT * FROM departamento ORDER BY departamento.id";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            List<Departamento> listaDepartamentos = new ArrayList<>();
            while (resultSet.next()) {
                Departamento departamento = InstanciarEntidades.departamento(resultSet);
                listaDepartamentos.add(departamento);
            }
            return listaDepartamentos;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }
}
