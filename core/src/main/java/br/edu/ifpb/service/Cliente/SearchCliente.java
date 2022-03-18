package br.edu.ifpb.service.Cliente;

import br.edu.ifpb.domain.Cliente;
import br.edu.ifpb.domain.ClienteInterface;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class SearchCliente {

    @EJB
    private ClienteInterface clienteInterface;

    public List<Cliente> buscarCPF(String cpf){
        return clienteInterface.searchClientCpf(cpf);
    }

}
