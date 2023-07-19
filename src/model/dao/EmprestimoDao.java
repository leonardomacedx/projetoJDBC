package model.dao;

import model.entities.Cliente;
import model.entities.Departamento;
import model.entities.Emprestimo;
import model.entities.Funcionario;

import java.util.List;

public interface EmprestimoDao {

    void inserirEmprestimo(Emprestimo emprestimo);
    void atualizarEmprestimo(Emprestimo emprestimo);
    void removerEmprestimo(Emprestimo emprestimo);
    Emprestimo emprestimoPorId(Integer id);
    List<Emprestimo> emprestimoPorDepartamento(Departamento departamento);
    List<Emprestimo> emprestimoPorCliente(Cliente cliente);
    List<Emprestimo> emprestimoPorFuncionario(Funcionario funcionario);
    List<Emprestimo> todosEmprestimos();
}
