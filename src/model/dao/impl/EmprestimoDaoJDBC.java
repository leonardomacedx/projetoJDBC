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
        String sql = "UPDATE emprestimo SET valor = ?, parcelas = ?, valorTotal = ?, " +
                "dataEmprestimo = ?, idDepartamento = ?, idCliente = ?, idFuncionario = ? " +
                "WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setDouble(1, emprestimo.getValor());
            preparedStatement.setInt(2, emprestimo.getParcelas());
            preparedStatement.setDouble(3, emprestimo.getValorTotal());
            preparedStatement.setDate(4, new Date(emprestimo.getDataEmprestimo().getTime()));
            preparedStatement.setInt(5, emprestimo.getDepartamento().getId());
            preparedStatement.setInt(6, emprestimo.getCliente().getId());
            preparedStatement.setInt(7, emprestimo.getFuncionario().getId());
            preparedStatement.setInt(8, emprestimo.getId());

            preparedStatement.executeUpdate();
        }
        catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public void removerEmprestimo(Integer id) {
        String sql = "DELETE FROM emprestimo WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();
        }
        catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public Emprestimo emprestimoPorId(Integer id) {
        String sql = """
                select emprestimo.*, funcionario.*, cliente.*, departamento.*  
                from emprestimo 
                inner join cliente
                on emprestimo.idCliente = cliente.id
                inner join departamento
                on emprestimo.idDepartamento = departamento.id
                inner join funcionario
                on emprestimo.idFuncionario = funcionario.id
                where emprestimo.id = ?
                order by emprestimo.id""";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);

            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    Emprestimo emprestimo = new Emprestimo();

                }
            }
        }
        catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        return null;
    }

    @Override
    public List<Emprestimo> emprestimoPorDepartamento(Integer idDepartamento) {
        return null;
    }

    @Override
    public List<Emprestimo> emprestimoPorCliente(Integer idCliente) {
        return null;
    }

    @Override
    public List<Emprestimo> emprestimoPorFuncionario(Integer idFuncionario) {
        return null;
    }

    @Override
    public List<Emprestimo> todosEmprestimos() {
        return null;
    }
}
