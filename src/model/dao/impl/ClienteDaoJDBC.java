package model.dao.impl;

import db.DbException;
import model.dao.ClienteDao;
import model.entities.Cliente;

import java.sql.*;
import java.util.List;

public class ClienteDaoJDBC implements ClienteDao {

    private Connection connection;

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

    }

    @Override
    public void removerCliente(Cliente cliente) {

    }

    @Override
    public Cliente encontrarClienteId(String id) {
        return null;
    }

    @Override
    public Cliente encontrarCpf(String cpf) {
        return null;
    }

    @Override
    public List<Cliente> todosClientes() {
        return null;
    }
}
