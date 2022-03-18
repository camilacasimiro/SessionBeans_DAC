package br.edu.ifpb.infra;

import br.edu.ifpb.domain.CarrinhoCompras;
import br.edu.ifpb.domain.Venda;
import br.edu.ifpb.domain.VendaInterface;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


@Stateless
public class VendaJDBC implements VendaInterface {

    @Resource(lookup = "java:app/jdbc/pgadmin")
    private DataSource dataSource;

    @Override
    public List<CarrinhoCompras> listaHistoricoVenda(int id) {
        try{
            List<CarrinhoCompras> venda= new ArrayList<>();
           PreparedStatement statement = this.dataSource
                   .getConnection()
                   .prepareStatement(
                            "SELECT p.id, p.descricao, c.nome, p.valor FROM venda v INNER JOIN produto p ON v.produto = p.id INNER JOIN cliente c ON v.cliente = c.id WHERE c.id = ?");

                    statement.setInt(1, id);
                    statement.executeQuery();

                    ResultSet historicoVenda = statement.getResultSet();

            while ( historicoVenda.next() ){
                venda.add(converterVenda(historicoVenda));
                System.out.println(venda);
            }
            return venda;

        } catch(SQLException ex){
            Logger.getLogger(VendaJDBC.class.getName()).log(Level.SEVERE, null, ex);
            return Collections.EMPTY_LIST;
        }
    }

    public CarrinhoCompras converterVenda (ResultSet result) throws SQLException{
        int idCod = result.getInt("id");
        String descricao = result.getString("descricao");
        String nome = result.getString("nome");
        Double valor = result.getDouble("valor");
        return new CarrinhoCompras(idCod, nome, descricao, valor);
    }


    @Override
    public void insertVenda(int idProd, int idCliente) {
        try{
            PreparedStatement statement = this.dataSource
                    .getConnection()
                    .prepareStatement(
                    "INSERT INTO venda (produto, cliente) VALUES (?, ?)");
            statement.setInt(1, idProd);
            statement.setInt(2, idCliente);
            statement.executeQuery();

        } catch (SQLException e){
            Logger.getLogger(VendaJDBC.class.getName()).log(Level.SEVERE, null, e);
        }

    }

}
