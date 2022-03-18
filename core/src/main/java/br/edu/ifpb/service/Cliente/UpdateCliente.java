package br.edu.ifpb.service.Cliente;

import br.edu.ifpb.domain.Cliente;
import br.edu.ifpb.domain.ClienteInterface;

import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class UpdateCliente {

    @EJB
    private ClienteInterface clienteInterface;

    public void atualizarCliente(Cliente cliente){
        this.clienteInterface.updateClienteJDBC(cliente);
    }
}
