package br.edu.ifpb.domain;

import java.sql.SQLException;
import java.util.List;

public interface ClienteInterface {

    public List<Cliente> listClientes() throws ClassNotFoundException, SQLException;

    public void insertClienteJDBC (Cliente cliente);

    public void updateClienteJDBC (Cliente cliente);

    public void deleteClienteJDBC (Cliente cliente);

    public List<Cliente> searchClientCpf(String local);

    public  Cliente searchClienteById(int id);

}
