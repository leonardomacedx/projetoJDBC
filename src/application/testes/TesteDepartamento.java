package application.testes;

import model.dao.DaoFactory;
import model.dao.DepartamentoDao;
import model.entities.Departamento;

import java.util.List;
import java.util.Scanner;

public class TesteDepartamento {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        DepartamentoDao departamentoDao = DaoFactory.createDepartamentoDao();

        System.out.println("=== Teste 1 ===");
        Departamento departamento = new Departamento(null, "Viagem");
        departamentoDao.inserirDepartamento(departamento);
        System.out.println(departamento);
        System.out.println();

        System.out.println("=== Teste 2 ===");
        Departamento departamento1 = new Departamento(2, "Dívidas");
        departamentoDao.atualizarDepartamento(departamento1);
        System.out.println(departamento1);
        System.out.println();

        System.out.println("=== Teste 3 ===");
        System.out.print("Id para remoção: ");
        int idDelete = sc.nextInt();
        departamentoDao.removerDepartamentoPorId(idDelete);
        System.out.println("Departamento removido!");
        System.out.println();

        System.out.println("=== Teste 4 ===");
        System.out.print("Id para retorno: ");
        int idRetorno = sc.nextInt();
        Departamento departamento3 = departamentoDao.encontrarDepartamentoPorId(idRetorno);
        System.out.println(departamento3);
        System.out.println();

        System.out.println("=== Teste 5 ===");
        List<Departamento> listaDepartamentos;
        listaDepartamentos = departamentoDao.todosDepartamentos();
        for (Departamento departamentos : listaDepartamentos) {
            System.out.println(departamentos);
        }

    }
}