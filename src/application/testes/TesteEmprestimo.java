package application.testes;

import model.dao.DaoFactory;
import model.dao.EmprestimoDao;
import model.entities.Cliente;
import model.entities.Departamento;
import model.entities.Emprestimo;
import model.entities.Funcionario;

import java.util.Date;

public class TesteEmprestimo {
    public static void main(String[] args) {

        EmprestimoDao emprestimoDao = DaoFactory.createEmprestimoDao();

        System.out.println("=== Teste 1 ===");
        Emprestimo emprestimo = new Emprestimo(null, 96650.0, 8, new Date(),
                new Departamento(2),
                new Cliente(3), new Funcionario(4));
        emprestimoDao.inserirEmprestimo(emprestimo);
        System.out.println("Empréstimo adicionado!");
    }
}
