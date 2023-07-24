package model.dao;

import model.entities.Cliente;

import java.util.List;

public interface ClienteDao {

    void inserirCliente(Cliente cliente);
    void atualizarCliente(Cliente cliente);
    void removerCliente(Cliente cliente);
    Cliente encontrarClienteId(String id);
    Cliente encontrarCpf(String cpf);
    List<Cliente> todosClientes();

}
