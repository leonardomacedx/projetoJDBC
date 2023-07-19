package model.dao;

import model.entities.Cliente;

import java.util.List;

public interface ClienteDao {

    void inserirCliente();
    void atualizarCliente();
    void removerCliente();
    Cliente encontrarCpf(String cpf);
    List<Cliente> todosClientes();

}
