package model.dao;

import model.entities.Cliente;
import model.entities.Departamento;
import model.entities.Emprestimo;
import model.entities.Funcionario;

import java.util.List;

public interface EmprestimoDao {

    void inserirEmprestimo(Emprestimo emprestimo);
    void atualizarEmprestimo(Emprestimo emprestimo);
    void removerEmprestimo(Integer id);
    Emprestimo emprestimoPorId(Integer id);
    List<Emprestimo> emprestimoPorDepartamento(Integer idDepartamento);
    List<Emprestimo> emprestimoPorCliente(Integer idCliente);
    List<Emprestimo> emprestimoPorFuncionario(Integer idFuncionario);
    List<Emprestimo> todosEmprestimos();
}
