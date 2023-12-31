package model.dao;

import model.entities.Departamento;

import java.util.List;

public interface DepartamentoDao {

    void inserirDepartamento(Departamento departamento);
    void atualizarDepartamento(Departamento departamento);
    void removerDepartamentoPorId(Integer id);
    Departamento encontrarDepartamentoPorId(Integer id);
    List<Departamento> todosDepartamentos();
}
