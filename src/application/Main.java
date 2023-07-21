package application;

import model.dao.DaoFactory;
import model.dao.DepartamentoDao;
import model.entities.Departamento;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
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
        Departamento departamento2 = new Departamento(1, null);
        departamentoDao.removerDepartamento(departamento2);
        System.out.println("Departamento removido!");
        System.out.println();

        System.out.println("=== Teste 4 ===");
        System.out.print("Id para retorno: ");
        int id = sc.nextInt();
        Departamento departamento3 = departamentoDao.encontrarDepartamentoPorId(id);
        System.out.println(departamento3);
        System.out.println();

        System.out.println("=== Teste 5 ===");
        List<Departamento> listaDepartamentos = new ArrayList<>();
        listaDepartamentos = departamentoDao.todosDepartamentos();
        System.out.println(listaDepartamentos);

    }
}