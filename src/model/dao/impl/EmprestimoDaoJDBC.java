package model.dao.impl;

import db.DbException;
import model.dao.EmprestimoDao;
import model.entities.Cliente;
import model.entities.Departamento;
import model.entities.Emprestimo;
import model.entities.Funcionario;

import java.sql.*;
import java.util.List;

public class EmprestimoDaoJDBC implements EmprestimoDao {

    Connection connection;

    public EmprestimoDaoJDBC(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void inserirEmprestimo(Emprestimo emprestimo) {
        String sql = "INSERT INTO emprestimo " +
                "(valor, parcelas, valorTotal, dataEmprestimo, idDepartamento" +
                ", idCliente, idFuncionario) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setDouble(1, emprestimo.getValor());
            preparedStatement.setInt(2, emprestimo.getParcelas());
            preparedStatement.setDouble(3, emprestimo.getValorTotal());
            preparedStatement.setDate(4, new Date(emprestimo.getDataEmprestimo().getTime()));
            preparedStatement.setInt(5, emprestimo.getDepartamento().getId());
            preparedStatement.setInt(6, emprestimo.getCliente().getId());
            preparedStatement.setInt(7, emprestimo.getFuncionario().getId());

            int linhasAfetadas = preparedStatement.executeUpdate();

            if (linhasAfetadas > 0) {
                try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                    if (resultSet.next()) {
                        int id = resultSet.getInt(1);
                        emprestimo.setId(id);
                    }
                }
            }
        }
        catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public void atualizarEmprestimo(Emprestimo emprestimo) {

    }

    @Override
    public void removerEmprestimo(Emprestimo emprestimo) {

    }

    @Override
    public Emprestimo emprestimoPorId(Integer id) {
        return null;
    }

    @Override
    public List<Emprestimo> emprestimoPorDepartamento(Departamento departamento) {
        return null;
    }

    @Override
    public List<Emprestimo> emprestimoPorCliente(Cliente cliente) {
        return null;
    }

    @Override
    public List<Emprestimo> emprestimoPorFuncionario(Funcionario funcionario) {
        return null;
    }

    @Override
    public List<Emprestimo> todosEmprestimos() {
        return null;
    }
}
