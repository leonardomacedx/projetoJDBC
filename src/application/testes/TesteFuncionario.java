package application.testes;

import model.dao.DaoFactory;
import model.dao.FuncionarioDao;
import model.entities.Departamento;
import model.entities.Funcionario;

import java.util.List;
import java.util.Scanner;

public class TesteFuncionario {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        FuncionarioDao funcionarioDao = DaoFactory.createFuncionarioDao();

        int id;

        System.out.println("=== Teste 1 ===");
        Funcionario funcionario = new Funcionario(null, "Marcela Vasques", "marcela@gmail.com",
                new Departamento(1, null));
        funcionarioDao.inserirFuncionario(funcionario);
        System.out.println("Funcionário adicionado!");
        System.out.println();

        System.out.println("=== Teste 2 ===");
        Funcionario atualizarFuncionario = new Funcionario(2, "Flavia Gomes", "flavia@gmail.com",
                new Departamento(3, null));
        funcionarioDao.atualizarFuncionario(atualizarFuncionario);
        System.out.println("Funcionário atulizado!");
        System.out.println();

        System.out.println("=== Teste 3 ===");
        System.out.print("Id para remoção: ");
        id = scanner.nextInt();
        funcionarioDao.removerFuncionario(id);
        System.out.println("Funcionário removido!");
        System.out.println();

        System.out.println("=== Teste 4 ===");
        System.out.print("Id do funcionário: ");
        id = scanner.nextInt();
        Funcionario funcionario1 = funcionarioDao.encontrarFuncionarioId(id);
        System.out.println(funcionario1);
        System.out.println();

        System.out.println("=== Teste 5 ===");
        System.out.print("Id do departamento: ");
        id = scanner.nextInt();
        List<Funcionario> listaFuncionarios = funcionarioDao.funcionariosDepartamento(id);
        for (Funcionario funcionarios : listaFuncionarios) {
            System.out.println(funcionarios);
        }
        System.out.println();

        System.out.println("=== Teste 6 ===");
        List<Funcionario> listaFuncionarios1 = funcionarioDao.todosFuncionarios();
        for (Funcionario funcionarios : listaFuncionarios1) {
            System.out.println(funcionarios);
        }
        System.out.println();

    }
}
