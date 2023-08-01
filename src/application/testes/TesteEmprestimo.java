package application.testes;

import model.dao.DaoFactory;
import model.dao.EmprestimoDao;
import model.entities.Cliente;
import model.entities.Departamento;
import model.entities.Emprestimo;
import model.entities.Funcionario;

import java.time.LocalDate;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class TesteEmprestimo {
    public static void main(String[] args) {

        Locale.setDefault(Locale.US);

        Scanner scanner = new Scanner(System.in);

        EmprestimoDao emprestimoDao = DaoFactory.createEmprestimoDao();

        System.out.println("=== Teste 1 - Inserir ===");
        Emprestimo emprestimo = new Emprestimo(null, 63544.0, 17, LocalDate.now(),
                new Departamento(2),
                new Cliente(3), new Funcionario(4));
        emprestimoDao.inserirEmprestimo(emprestimo);
        System.out.println("Empréstimo adicionado!");
        System.out.println();

        System.out.println("=== Teste 2 - Atualizar ===");
        Emprestimo emprestimo1 = new Emprestimo(2, 140560.0, 30,
                LocalDate.of(2012,12, 2),
                new Departamento(3),
                new Cliente(1), new Funcionario(9));
        emprestimoDao.atualizarEmprestimo(emprestimo1);
        System.out.println("Empréstimo atualizado!");
        System.out.println();

        System.out.println("=== Teste 3 - Remover ===");
        System.out.print("Id para remoção: ");
        int id = scanner.nextInt();
        emprestimoDao.removerEmprestimo(id);
        System.out.println("Empréstimo excluido!");
        System.out.println();

        System.out.println("=== Teste 4 - Retorno por id do empréstimo");
        System.out.print("Id do empréstimo: ");
        int idEmprestimo = scanner.nextInt();
        System.out.println(emprestimoDao.emprestimoPorId(idEmprestimo));
        System.out.println();

        System.out.println("=== Teste 5 - Emprestimos por departamento");
        System.out.print("Id do departamento: ");
        int idDepartamento = scanner.nextInt();
        List<Emprestimo> listaEmprestimosDep = emprestimoDao.emprestimoPorDepartamento(idDepartamento);
        for (Emprestimo emprestimoDep : listaEmprestimosDep) {
            System.out.println(emprestimoDep);
        }
        System.out.println();

        System.out.println("=== Teste 6 - Emprestimos por cliente");
        System.out.println("Id do cliente: ");
        int idCliente = scanner.nextInt();
        List<Emprestimo> listaEmprestimosCliente = emprestimoDao.emprestimoPorCliente(idCliente);
        for (Emprestimo emprestimoCliente : listaEmprestimosCliente) {
            System.out.println(emprestimoCliente);
        }
        System.out.println();

        System.out.println("=== Teste 7 - Emprestimos por funcionário");
        System.out.println("Id do funcionário: ");
        int idFuncionario = scanner.nextInt();
        List<Emprestimo> listaEmprestimosFuncionario = emprestimoDao.emprestimoPorFuncionario(idFuncionario);
        for (Emprestimo emprestimoFuncionario : listaEmprestimosFuncionario) {
            System.out.println(emprestimoFuncionario);
        }
        System.out.println();

        System.out.println("=== Teste 8 - Todos os empréstimos");
        List<Emprestimo> todosEmprestimos = emprestimoDao.todosEmprestimos();
        for (Emprestimo emprestimos : todosEmprestimos) {
            System.out.println(emprestimos);
            System.out.println("------------");
        }
        System.out.println();
    }
}
