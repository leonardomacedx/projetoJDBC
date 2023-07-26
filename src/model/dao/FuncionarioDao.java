package model.dao;

import model.entities.Funcionario;

import java.util.List;

public interface FuncionarioDao {

    void inserirFuncionario(Funcionario funcionario);
    void atualizarFuncionario(Funcionario funcionario);
    void removerFuncionario(Integer id);
    Funcionario encontrarFuncionarioId(Integer id);
    List<Funcionario> funcionariosDepartamento(Integer idDepartamento);
    List<Funcionario> todosFuncionarios();
}
