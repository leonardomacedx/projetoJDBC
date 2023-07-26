package application;

import model.dao.ClienteDao;
import model.dao.DaoFactory;
import model.entities.Cliente;

import java.util.List;
import java.util.Scanner;

public class TesteCliente {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        ClienteDao clienteDao = DaoFactory.createClienteDao();

        System.out.println("=== Teste 1 ===");
        Cliente clienteLeonardo = new Cliente(null, "34275380100", "José Airton Martins",
                "jose@gmail.com", "61996487415",
                "75382-9", "QSC 19 CHACARA 27 CONJUNTO D LOTE 01A");
        clienteDao.inserirCliente(clienteLeonardo);
        System.out.println("Cliente inserido!");
        System.out.println();

        System.out.println("=== Teste 2 ===");
        Cliente clienteAtualizado = new Cliente(4, "72133317104", "Aline Barbosa Macedo",
                "aline@gmail.com", "61998278069",
                "60534-4", "QR 409 CJ 13 CASA 28");
        clienteDao.atualizarCliente(clienteAtualizado);
        System.out.println("Cliente atualizado!");
        System.out.println();

        System.out.println("=== Teste 3 ===");
        System.out.print("Qual a id do cliente para remoção?");
        int id = scanner.nextInt();
        clienteDao.removerCliente(id);
        System.out.println("Cliente removido!");
        System.out.println();

        System.out.println("=== Teste 4 ===");
        System.out.print("Qual a id do cliente para retorno?");
        int idEncontrar = scanner.nextInt();
        Cliente cliente = clienteDao.encontrarClienteId(idEncontrar);
        System.out.println(cliente);
        System.out.println();

        System.out.println("=== Teste 5 ===");
        System.out.print("Qual o cpf do cliente?");
        String cpf = scanner.next();
        Cliente clienteCpf = clienteDao.encontrarCpf(cpf);
        System.out.println(clienteCpf);
        System.out.println();

        System.out.println("=== Teste 6 ===");
        List<Cliente> listaCliente = clienteDao.todosClientes();
        for (Cliente cliente1 : listaCliente) {
            System.out.println(cliente1);
        }

        System.out.println("=== FIM ===");

    }
}
