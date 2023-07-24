package application;

import model.dao.ClienteDao;
import model.dao.DaoFactory;
import model.entities.Cliente;

public class TesteCliente {

    public static void main(String[] args) {

        ClienteDao clienteDao = DaoFactory.createClienteDao();

        System.out.println("=== Teste 1 ===");
        Cliente clienteLucas = new Cliente(null, "03945732199", "Lucas Emmanuel", "lucas@gmail.com", "61986540964",
                "06495-5", "QNA 12 Casa 3");
        clienteDao.inserirCliente(clienteLucas);
        System.out.println("Cliente inserido!");
        System.out.println();
    }
}
