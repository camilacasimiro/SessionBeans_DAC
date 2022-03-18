package br.edu.ifpb.controller;

import br.edu.ifpb.domain.Cliente;
import br.edu.ifpb.service.Cliente.*;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

@Named("clienteController")
@SessionScoped
public class ClienteController implements Serializable {

    @Inject
    private ListClientes listClientes;

    @Inject
    private AddCliente addCliente;

    @Inject
    private UpdateCliente updateCliente;

    @Inject
    private DeleteCliente deleteCliente;

    @Inject
    private SearchCliente searchCliente;

    private Cliente cliente = new Cliente();
    private String cpf;

    public List<Cliente> listTodosClientes() throws SQLException, ClassNotFoundException {
        return this.listClientes.allClients();
    }

    public String setAddCliente(){

        if(this.cliente.getId() > 0){
            this.updateCliente.atualizarCliente(this.cliente);
        } else{
            this.addCliente.adicionarCliente(this.cliente);
        }
        this.cliente = new Cliente();

        return "/Cliente/list?faces-redirect=true";
    }

    public String setUpdateCliente(Cliente cliente){
        this.cliente = cliente;
        return "/Cliente/edit?faces-redirect=true";
    }

    public String setDeleteCliente(Cliente cliente){

        this.deleteCliente.removeCliente(cliente);
        return "/Cliente/list?faces-redirect=true";
    }

    public List<Cliente> setSearhcCpf(){
        return this.searchCliente.buscarCPF(this.cpf);
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}
