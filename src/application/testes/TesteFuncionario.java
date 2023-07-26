package application.testes;

import model.dao.DaoFactory;
import model.dao.FuncionarioDao;
import model.entities.Departamento;
import model.entities.Funcionario;

public class TesteFuncionario {
    public static void main(String[] args) {

        FuncionarioDao funcionarioDao = DaoFactory.createFuncionarioDao();

        System.out.println("=== Teste 1 ===");
        Funcionario funcionario = new Funcionario(null, "João Santana", "joao@gmail.com",
                new Departamento(4, null));
        funcionarioDao.inserirFuncionario(funcionario);
        System.out.println("Funcionário adicionado!");
        System.out.println();

        System.out.println("=== Teste 2 ===");
        Funcionario atualizarFuncionario = new Funcionario(2, "Marcia Gomes", "marcia@gmail.com",
                new Departamento(3, null));
        funcionarioDao.atualizarFuncionario(atualizarFuncionario);
        System.out.println("Funcionário atulizado!");
        System.out.println();

    }
}
