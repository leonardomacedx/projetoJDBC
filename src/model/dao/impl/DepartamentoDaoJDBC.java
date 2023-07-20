package model.dao.impl;

import db.DB;
import db.DbException;
import model.dao.DepartamentoDao;
import model.entities.Departamento;

import java.sql.*;
import java.util.List;

public class DepartamentoDaoJDBC implements DepartamentoDao {

    public DepartamentoDaoJDBC() {
    }

    @Override
    public void inserirDepartamento(Departamento departamento) {
        try (Connection connection = DB.getConnection()) {
            String sql = "INSERT INTO departamento (nome) VALUES (?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

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
            }
        }
        catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public void atualizarDepartamento(Departamento departamento) {

    }

    @Override
    public void removerDepartamento(Departamento departamento) {

    }

    @Override
    public Departamento encontrarDepartamentoPorId(Integer id) {
        return null;
    }

    @Override
    public List<Departamento> todosDepartamentos() {
        return null;
    }
}
