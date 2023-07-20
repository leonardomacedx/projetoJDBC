package model.dao;

import db.DB;
import model.dao.impl.DepartamentoDaoJDBC;
import model.entities.Cliente;
import model.entities.Emprestimo;
import model.entities.Funcionario;

public class DaoFactory {

    public static DepartamentoDao createDepartamentoDao() {
        return new DepartamentoDaoJDBC();
    }

    public static ClienteDao createClienteDao() {
        return null;
    }

    public static Funcionario createFuncionarioDao() {
        return null;
    }

    public static Emprestimo createEmprestimoDao() {
        return null;
    }
}
