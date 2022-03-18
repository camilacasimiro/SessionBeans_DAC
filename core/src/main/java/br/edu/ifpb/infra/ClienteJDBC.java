package br.edu.ifpb.infra;

import br.edu.ifpb.domain.Cliente;
import br.edu.ifpb.domain.ClienteInterface;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


@Stateless
public class ClienteJDBC implements ClienteInterface {

    @Resource(lookup = "java:app/jdbc/pgadmin")
    private DataSource dataSource;

////    private static final Logger logger = Logger.getLogger(ClienteController().class.getName());

    @Override
    public List<Cliente> listClientes() {
        try{
            List<Cliente> clientes= new ArrayList<>();
            ResultSet resultQuery = this.dataSource
                    .getConnection()
                    .prepareStatement(
                            "SELECT * FROM cliente")
                    .executeQuery();
//            next percore o ResultSet e reforna false quando estar na ultima posição
            while ( resultQuery.next() ){
                clientes.add(converterCliente(resultQuery));
                System.out.println(clientes);
            }
            return clientes;

        } catch(SQLException ex){
            Logger.getLogger(ClienteJDBC.class.getName()).log(Level.SEVERE, null, ex);
            return Collections.EMPTY_LIST;
        }
    }

    public Cliente converterCliente (ResultSet result) throws SQLException{
        int id = result.getInt("id");
        String nome = result.getString("nome");
        String cpf = result.getString("cpf");
        return new Cliente(id, nome, cpf);
    }


    @Override
    public void insertClienteJDBC(Cliente cliente) {
        try{
            PreparedStatement statement = this.dataSource
                    .getConnection()
                    .prepareStatement(
                    "INSERT INTO cliente (nome, cpf) VALUES (?, ?)");
            statement.setString(1, cliente.getNome());
            statement.setString(2, cliente.getCpf());
            statement.executeQuery();

        } catch (SQLException e){
            Logger.getLogger(ClienteJDBC.class.getName()).log(Level.SEVERE, null, e);
        }

    }

    @Override
    public void updateClienteJDBC(Cliente cliente) {
        try{
            PreparedStatement statement = dataSource
                    .getConnection()
                    .prepareStatement(
                    "UPDATE cliente SET nome=?, cpf=? WHERE id=?");
            statement.setString(1, cliente.getNome());
            statement.setString(2, cliente.getCpf());
            statement.setInt(3, cliente.getId());
            statement.executeQuery();

        } catch (SQLException e) {
            Logger.getLogger(ClienteJDBC.class.getName()).log(Level.SEVERE, null, e);
        }

    }

    @Override
    public void deleteClienteJDBC(Cliente cliente) {
        try{
            PreparedStatement statement = this.dataSource
                    .getConnection()
                    .prepareStatement(
                            "DELETE FROM cliente WHERE id=?");
            statement.setInt(1, cliente.getId());
            statement.executeQuery();

        } catch (SQLException e) {
            Logger.getLogger(ClienteJDBC.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    @Override
    public List<Cliente> searchClientCpf(String cpf) {
        try{
            List<Cliente> clientes = new ArrayList<>();

            PreparedStatement statement = this.dataSource
                    .getConnection()
                    .prepareStatement(
                    "SELECT * FROM cliente WHERE cpf = ?");

            statement.setString(1, cpf);
            statement.executeQuery();

            ResultSet clienteResult = statement.getResultSet();

            while (  clienteResult.next() ){
                clientes.add(converterCliente(clienteResult));
            }

            return clientes;

        } catch(SQLException ex){
            Logger.getLogger(ClienteJDBC.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public Cliente searchClienteById(int id) {
        try{

//            logger.log(Level.INFO, "Cliente busca Entrando ");
            Cliente cliente = null;

            PreparedStatement statement = dataSource
                    .getConnection()
                    .prepareStatement(
                    "DISTINCT SELECT * FROM cliente WHERE id= ?");

            statement.setInt(1, id);
            statement.executeQuery();

            ResultSet clienteResult = statement.getResultSet();

            cliente = converterCliente(clienteResult);

//            logger.log(Level.INFO, "Cliente busca : " + cliente);

            return cliente;

        } catch(SQLException ex){
            Logger.getLogger(ClienteJDBC.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }
}
