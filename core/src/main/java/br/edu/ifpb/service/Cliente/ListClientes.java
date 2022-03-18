package br.edu.ifpb.service.Cliente;

import br.edu.ifpb.domain.Cliente;
import br.edu.ifpb.domain.ClienteInterface;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.sql.SQLException;
import java.util.List;

@Stateless
public class ListClientes {

    @EJB
    private ClienteInterface clienteInterface;

    public List<Cliente> allClients() throws SQLException, ClassNotFoundException {
        return clienteInterface.listClientes();
    }
}
