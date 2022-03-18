package br.edu.ifpb.service.Cliente;

import br.edu.ifpb.domain.Cliente;
import br.edu.ifpb.domain.ClienteInterface;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.Objects;

@Stateless
public class AddCliente {
    //regra de negócio
    @EJB
    private ClienteInterface clienteInterface;

    public void adicionarCliente(Cliente cliente){

        Objects.requireNonNull(cliente,"O cliente não pode ser nulo");
        this.clienteInterface.insertClienteJDBC(cliente);
    }

}
