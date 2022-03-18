package br.edu.ifpb.service.Cliente;

import br.edu.ifpb.domain.Cliente;
import br.edu.ifpb.domain.ClienteInterface;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.io.Serializable;

@Stateless
public class DeleteCliente {

    @EJB
    private ClienteInterface clienteInterface;

    public void removeCliente(Cliente cliente){
        this.clienteInterface.deleteClienteJDBC(cliente);
    }

}
