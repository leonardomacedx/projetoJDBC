package model.dao.impl;

import db.DbException;
import model.dao.ClienteDao;
import model.entities.Cliente;
import model.entities.Departamento;
import model.services.InstanciarEntidades;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDaoJDBC implements ClienteDao {

    private final Connection connection;

    public ClienteDaoJDBC(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void inserirCliente(Cliente cliente) {
        try {
            String sql = "INSERT INTO cliente (clienteCpf, nome, email, celular, contaBanco, endereco) " +
                    "VALUES (?, ?, ?, ?, ?, ?)";
            try (PreparedStatement preparedStatement =
                         connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

                preparedStatement.setString(1, cliente.getClienteCpf());
                preparedStatement.setString(2, cliente.getNome());
                preparedStatement.setString(3, cliente.getEmail());
                preparedStatement.setString(4, cliente.getCelular());
                preparedStatement.setString(5, cliente.getContaBanco());
                preparedStatement.setString(6, cliente.getEndereco());

                int linhasAfetadas = preparedStatement.executeUpdate();

                if (linhasAfetadas > 0) {
                    ResultSet resultSet = preparedStatement.getGeneratedKeys();
                    if (resultSet.next()) {
                        int id = resultSet.getInt(1);
                        cliente.setId(id);
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
    public void atualizarCliente(Cliente cliente) {
        try {
            String sql = "UPDATE cliente SET clienteCpf = ?, nome = ?, email = ?, " +
                    "celular = ?, contaBanco = ?, endereco = ? WHERE id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, cliente.getClienteCpf());
                preparedStatement.setString(2, cliente.getNome());
                preparedStatement.setString(3, cliente.getEmail());
                preparedStatement.setString(4, cliente.getCelular());
                preparedStatement.setString(5, cliente.getContaBanco());
                preparedStatement.setString(6, cliente.getEndereco());
                preparedStatement.setInt(7, cliente.getId());

                preparedStatement.executeUpdate();
            }
        }
        catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    //Remove cliente por id
    @Override
    public void removerCliente(Integer id) {
        try {
            String sql = "DELETE FROM cliente WHERE id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, id);

                preparedStatement.executeUpdate();
            }
        }
        catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public Cliente encontrarClienteId(Integer id) {
        try {
            String sql = "SELECT * FROM cliente WHERE id = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, id);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    Cliente cliente;
                    if (resultSet.next()) {
                        cliente = InstanciarEntidades.instanciarCliente(resultSet);
                        return cliente;

                    }
                    return null;
                }
            }
        }
        catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public Cliente encontrarCpf(String cpf) {
        try {
            String sql = "SELECT * FROM cliente WHERE clienteCpf = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, cpf);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    Cliente cliente;
                    if (resultSet.next()) {
                        cliente = InstanciarEntidades.instanciarCliente(resultSet);
                        return cliente;

                    }
                    return null;
                }
            }
        }
        catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public List<Cliente> todosClientes() {
        try {
            String sql = "SELECT * FROM cliente ORDER BY cliente.id";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
                 ResultSet resultSet = preparedStatement.executeQuery()) {
                List<Cliente> listaCliente = new ArrayList<>();
                while (resultSet.next()) {
                    Cliente cliente = InstanciarEntidades.instanciarCliente(resultSet);
                    listaCliente.add(cliente);
                }
                return listaCliente;
            }
        }
        catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }
}
