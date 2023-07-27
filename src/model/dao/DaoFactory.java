package model.dao;

import db.DB;
import model.dao.impl.ClienteDaoJDBC;
import model.dao.impl.DepartamentoDaoJDBC;
import model.dao.impl.EmprestimoDaoJDBC;
import model.dao.impl.FuncionarioDaoJDBC;

public class DaoFactory {

    public static DepartamentoDao createDepartamentoDao() {
        return new DepartamentoDaoJDBC(DB.getConnection());
    }

    public static ClienteDao createClienteDao() {
        return new ClienteDaoJDBC(DB.getConnection());
    }

    public static FuncionarioDao createFuncionarioDao() {
        return new FuncionarioDaoJDBC(DB.getConnection());
    }

    public static EmprestimoDao createEmprestimoDao() {
        return new EmprestimoDaoJDBC(DB.getConnection());
    }
}
