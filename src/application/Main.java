package application;

import model.dao.DaoFactory;
import model.dao.DepartamentoDao;
import model.entities.Departamento;

public class Main {
    public static void main(String[] args) {

        DepartamentoDao departamentoDao = DaoFactory.createDepartamentoDao();

        Departamento departamento = new Departamento(null, "Veículos");
        departamentoDao.inserirDepartamento(departamento);
        System.out.println(departamento);
    }
}