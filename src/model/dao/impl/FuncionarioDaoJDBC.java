package model.dao.impl;

import db.DbException;
import model.dao.FuncionarioDao;
import model.entities.Departamento;
import model.entities.Funcionario;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        String sql = "DELETE FROM funcionario WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public Funcionario encontrarFuncionarioId(Integer id) {
        String sql = "select funcionario.*, departamento.nome as nomeDepartamento " +
                "from funcionario " +
                "INNER JOIN departamento " +
                "ON idDepartamento = departamento.id " +
                "WHERE funcionario.id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()){
                if (resultSet.next()) {
                    Departamento departamento = instanciarDepartamento(resultSet);
                    return instanciarFuncionario(resultSet, departamento);
                }
                return null;
            }
        }
        catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public List<Funcionario> funcionariosDepartamento(Integer idDepartamento) {
        String sql = "select funcionario.*, departamento.nome as nomeDepartamento " +
                "from funcionario " +
                "INNER JOIN departamento " +
                "ON idDepartamento = departamento.id " +
                "WHERE idDepartamento = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, idDepartamento);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                List<Funcionario> listaFuncionarios = new ArrayList<>();
                while (resultSet.next()) {
                    Departamento departamento = instanciarDepartamento(resultSet);
                    Funcionario funcionario = instanciarFuncionario(resultSet, departamento);
                    listaFuncionarios.add(funcionario);
                }
                return listaFuncionarios;
            }
        }
        catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public List<Funcionario> todosFuncionarios() {
        String sql = "select funcionario.*, departamento.nome as nomeDepartamento " +
                "from funcionario " +
                "INNER JOIN departamento " +
                "ON idDepartamento = departamento.id";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql); ResultSet resultSet
                = preparedStatement.executeQuery()) {
            List<Funcionario> listaFuncionarios = new ArrayList<>();
            //Map criado para evitar criar vários departamentos iguais
            Map<Integer, Departamento> mapDepartamentos = new HashMap<>();

            while (resultSet.next()) {
                Departamento departamento = mapDepartamentos.get(resultSet.getInt("idDepartamento"));
                if (departamento == null) {
                    departamento = instanciarDepartamento(resultSet);
                    mapDepartamentos.put(resultSet.getInt("idDepartamento"), departamento);
                }

                Funcionario funcionario = instanciarFuncionario(resultSet, departamento);
                listaFuncionarios.add(funcionario);
            }
            return listaFuncionarios;
        }
        catch (SQLException e) {
            throw new DbException(e.getMessage());
        }

    }

    //Método que instancia um funcionário com um departamento
    private Funcionario instanciarFuncionario(ResultSet resultSet, Departamento departamento) throws SQLException {
        Funcionario funcionario = new Funcionario();
        funcionario.setId(resultSet.getInt("id"));
        funcionario.setNome(resultSet.getString("nome"));
        funcionario.setEmail(resultSet.getString("email"));
        funcionario.setDepartamento(departamento);
        return funcionario;

    }

    //Método que instancia um departamento
    private Departamento instanciarDepartamento(ResultSet resultSet) throws SQLException {
        Departamento departamento = new Departamento();
        departamento.setId(resultSet.getInt("idDepartamento"));
        departamento.setNome(resultSet.getString("nomeDepartamento"));
        return departamento;

    }
}
