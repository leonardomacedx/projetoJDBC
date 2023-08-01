package model.dao.impl;

import db.DbException;
import model.dao.EmprestimoDao;
import model.entities.Cliente;
import model.entities.Departamento;
import model.entities.Emprestimo;
import model.entities.Funcionario;
import model.services.InstanciarEntidades;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
            preparedStatement.setDate(4, Date.valueOf(emprestimo.getDataEmprestimo()));
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
        } catch (SQLException e) {
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
            preparedStatement.setDate(4, Date.valueOf(emprestimo.getDataEmprestimo()));
            preparedStatement.setInt(5, emprestimo.getDepartamento().getId());
            preparedStatement.setInt(6, emprestimo.getCliente().getId());
            preparedStatement.setInt(7, emprestimo.getFuncionario().getId());
            preparedStatement.setInt(8, emprestimo.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public void removerEmprestimo(Integer id) {
        String sql = "DELETE FROM emprestimo WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
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

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    Departamento departamento = InstanciarEntidades.departamento(resultSet);
                    Cliente cliente = InstanciarEntidades.cliente(resultSet);
                    Funcionario funcionario = InstanciarEntidades.funcionario(resultSet, departamento);
                    return InstanciarEntidades.emprestimo(resultSet, cliente,
                            departamento, funcionario);
                }
                return null;
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public List<Emprestimo> emprestimoPorDepartamento(Integer idDepartamento) {
        String sql = """
                select emprestimo.*, funcionario.*, cliente.*, departamento.*
                from emprestimo
                inner join cliente
                on emprestimo.idCliente = cliente.id
                inner join departamento
                on emprestimo.idDepartamento = departamento.id
                inner join funcionario
                on emprestimo.idFuncionario = funcionario.id
                where departamento.id = ?
                order by emprestimo.id""";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, idDepartamento);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                List<Emprestimo> listaEmprestimos = new ArrayList<>();
                Map<Integer, Funcionario> funcionarioMap = new HashMap<>();
                Map<Integer, Cliente> clienteMap = new HashMap<>();


                while (resultSet.next()) {
                    Departamento departamento = InstanciarEntidades.departamento(resultSet);

                    Funcionario funcionario = criarMapFuncionario(funcionarioMap, resultSet, departamento);

                    Cliente cliente = criarMapCliente(clienteMap, resultSet);

                    Emprestimo emprestimo = InstanciarEntidades.emprestimo(
                            resultSet, cliente, departamento, funcionario);
                    listaEmprestimos.add(emprestimo);
                }
                return listaEmprestimos;
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public List<Emprestimo> emprestimoPorCliente(Integer idCliente) {
        String sql = """
                select emprestimo.*, funcionario.*, cliente.*, departamento.*
                from emprestimo
                inner join cliente
                on emprestimo.idCliente = cliente.id
                inner join departamento
                on emprestimo.idDepartamento = departamento.id
                inner join funcionario
                on emprestimo.idFuncionario = funcionario.id
                where cliente.id = ?
                order by emprestimo.id""";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, idCliente);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                List<Emprestimo> listaEmprestimos = new ArrayList<>();
                Map<Integer, Funcionario> funcionarioMap = new HashMap<>();
                Map<Integer, Departamento> departamentoMap = new HashMap<>();

                while (resultSet.next()) {
                    Departamento departamento = criarMapDepartamento(departamentoMap, resultSet);

                    Funcionario funcionario = criarMapFuncionario(funcionarioMap, resultSet, departamento);

                    Cliente cliente = InstanciarEntidades.cliente(resultSet);

                    Emprestimo emprestimo = InstanciarEntidades.emprestimo(
                            resultSet, cliente, departamento, funcionario);
                    listaEmprestimos.add(emprestimo);
                }
                return listaEmprestimos;
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public List<Emprestimo> emprestimoPorFuncionario(Integer idFuncionario) {
        String sql = """
                select emprestimo.*, funcionario.*, cliente.*, departamento.*
                from emprestimo
                inner join cliente
                on emprestimo.idCliente = cliente.id
                inner join departamento
                on emprestimo.idDepartamento = departamento.id
                inner join funcionario
                on emprestimo.idFuncionario = funcionario.id
                where funcionario.id = ?
                order by emprestimo.id""";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, idFuncionario);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                List<Emprestimo> listaEmprestimos = new ArrayList<>();
                Map<Integer, Cliente> clienteMap = new HashMap<>();
                Map<Integer, Departamento> departamentoMap = new HashMap<>();

                while (resultSet.next()) {
                    Departamento departamento = criarMapDepartamento(departamentoMap, resultSet);

                    Cliente cliente = criarMapCliente(clienteMap, resultSet);

                    Funcionario funcionario = InstanciarEntidades.funcionario(resultSet, departamento);

                    Emprestimo emprestimo = InstanciarEntidades.emprestimo(
                            resultSet, cliente, departamento, funcionario);
                    listaEmprestimos.add(emprestimo);
                }
                return listaEmprestimos;
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }


    @Override
    public List<Emprestimo> todosEmprestimos() {
        String sql = """
                select emprestimo.*, funcionario.*, cliente.*, departamento.*
                from emprestimo
                inner join cliente
                on emprestimo.idCliente = cliente.id
                inner join departamento
                on emprestimo.idDepartamento = departamento.id
                inner join funcionario
                on emprestimo.idFuncionario = funcionario.id
                order by emprestimo.id""";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            List<Emprestimo> listaEmprestimos = new ArrayList<>();
            Map<Integer, Cliente> clienteMap = new HashMap<>();
            Map<Integer, Departamento> departamentoMap = new HashMap<>();
            Map<Integer, Funcionario> funcionarioMap = new HashMap<>();

            while (resultSet.next()) {
                Departamento departamento = criarMapDepartamento(departamentoMap, resultSet);
                Cliente cliente = criarMapCliente(clienteMap, resultSet);
                Funcionario funcionario = criarMapFuncionario(funcionarioMap, resultSet, departamento);

                Emprestimo emprestimo = InstanciarEntidades.emprestimo(resultSet, cliente, departamento, funcionario);
                listaEmprestimos.add(emprestimo);
            }
            return listaEmprestimos;
        }
        catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    private Funcionario criarMapFuncionario(Map<Integer, Funcionario> map, ResultSet resultSet,
                                            Departamento departamento) throws SQLException {
        Funcionario funcionario = map.get(resultSet.getInt("funcionario.id"));
        if (funcionario == null) {
            funcionario = InstanciarEntidades.funcionario(resultSet, departamento);
            map.put(resultSet.getInt("funcionario.id"), funcionario);
        }
        return funcionario;
    }

    private Cliente criarMapCliente(Map<Integer, Cliente> map, ResultSet resultSet) throws SQLException {
        Cliente cliente = map.get(resultSet.getInt("cliente.id"));
        if (cliente == null) {
            cliente = InstanciarEntidades.cliente(resultSet);
            map.put(resultSet.getInt("cliente.id"), cliente);
        }
        return cliente;
    }

    private Departamento criarMapDepartamento(Map<Integer, Departamento> map, ResultSet resultSet) throws SQLException {
        Departamento departamento = map.get(resultSet.getInt("departamento.id"));
        if (departamento == null) {
            departamento = InstanciarEntidades.departamento(resultSet);
            map.put(resultSet.getInt("cliente.id"), departamento);
        }
        return departamento;
    }
}
