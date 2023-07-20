package model.dao;

import model.entities.Departamento;
import model.entities.Funcionario;

import java.util.List;

public interface FuncionarioDao {

    void inserirFuncionario(Funcionario funcionario);
    void atualizarFuncionario(Funcionario funcionario);
    void removerFuncionario(Funcionario funcionario);
    Funcionario encontrarFuncionarioId(Integer id);
    List<Funcionario> funcionariosDepartamento(Departamento departamento);
    List<Funcionario> todosFuncionarios();
}
