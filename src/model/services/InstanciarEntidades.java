package model.services;

import model.entities.Cliente;
import model.entities.Departamento;
import model.entities.Emprestimo;
import model.entities.Funcionario;

import java.sql.ResultSet;
import java.sql.SQLException;

public class InstanciarEntidades {

    public static Funcionario instanciarFuncionario(ResultSet resultSet, Departamento departamento) throws SQLException {
        Funcionario funcionario = new Funcionario();
        funcionario.setId(resultSet.getInt("funcionario.id"));
        funcionario.setNome(resultSet.getString("funcionario.nome"));
        funcionario.setEmail(resultSet.getString("funcionario.email"));
        funcionario.setDepartamento(departamento);
        return funcionario;
    }

    public static Departamento instanciarDepartamento(ResultSet resultSet) throws SQLException {
        Departamento departamento = new Departamento();
        departamento.setId(resultSet.getInt("departamento.id"));
        departamento.setNome(resultSet.getString("departamento.nome"));
        return departamento;
    }

    public static Cliente instanciarCliente(ResultSet resultSet) throws SQLException {
        Cliente cliente = new Cliente();
        cliente.setId(resultSet.getInt("cliente.id"));
        cliente.setClienteCpf(resultSet.getString("cliente.clienteCpf"));
        cliente.setNome(resultSet.getString("cliente.nome"));
        cliente.setEmail(resultSet.getString("cliente.email"));
        cliente.setCelular(resultSet.getString("cliente.celular"));
        cliente.setContaBanco(resultSet.getString("cliente.contaBanco"));
        cliente.setEndereco(resultSet.getString("cliente.endereco"));
        return cliente;
    }

    public static Emprestimo instanciarEmprestimo(
            ResultSet resultSet, Cliente cliente, Departamento departamento, Funcionario funcionario)
            throws SQLException{
        Emprestimo emprestimo = new Emprestimo();
        emprestimo.setId(resultSet.getInt("emprestimo.id"));
        emprestimo.setValor(resultSet.getDouble("emprestimo.valor"));
        emprestimo.setParcelas(resultSet.getInt("emprestimo.parcelas"));
        emprestimo.setValorTotal(resultSet.getDouble("emprestimo.valorTotal"));
        emprestimo.setDataEmprestimo(resultSet.getDate("emprestimo.dataEmprestimo"));
        emprestimo.setDepartamento(departamento);
        emprestimo.setCliente(cliente);
        emprestimo.setFuncionario(funcionario);
        return emprestimo;
    }
}
