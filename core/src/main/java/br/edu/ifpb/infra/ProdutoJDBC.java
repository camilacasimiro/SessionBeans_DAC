package br.edu.ifpb.infra;

import br.edu.ifpb.domain.Cliente;
import br.edu.ifpb.domain.Produto;
import br.edu.ifpb.domain.ProdutoInterface;

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
public class ProdutoJDBC implements ProdutoInterface {

    @Resource(lookup = "java:app/jdbc/pgadmin")
    private DataSource dataSource;

    @Override
    public List<Produto> listaProdutos() throws ClassNotFoundException, SQLException {
        try{
            List<Produto> produtos = new ArrayList<>();
            ResultSet resultQuery = this.dataSource
                    .getConnection()
                    .prepareStatement(
                            "SELECT * FROM produto")
                    .executeQuery();
//            next percore o ResultSet e reforna false quando estar na ultima posição
            while ( resultQuery.next() ){
                produtos.add(converterProduto(resultQuery));
            }
            return produtos;

        } catch(SQLException ex){
            Logger.getLogger(ProdutoJDBC.class.getName()).log(Level.SEVERE, null, ex);
            return Collections.EMPTY_LIST;
        }
    }

    public Produto converterProduto (ResultSet result) throws SQLException{
        int id = result.getInt("id");
        String descricao = result.getString("descricao");
        Double valor = result.getDouble("valor");
        return new Produto(id, descricao, valor);
    }

    @Override
    public void insertProduto(Produto produto) {
        try{
            PreparedStatement statement = this.dataSource
                    .getConnection()
                    .prepareStatement(
                            "INSERT INTO produto (descricao, valor) VALUES (?, ?)");
            statement.setString(1, produto.getDescricao());
            statement.setDouble(2, produto.getValor());
            statement.executeQuery();

        } catch (SQLException e){
            Logger.getLogger(ClienteJDBC.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    @Override
    public void updateProduto(Produto produto) {
        try{
            PreparedStatement statement = this.dataSource
                    .getConnection()
                    .prepareStatement(
                            "UPDATE produto SET descricao=?, valor=? WHERE id=?");
            statement.setString(1, produto.getDescricao());
            statement.setDouble(2, produto.getValor());
            statement.setInt(3, produto.getId());
            statement.executeQuery();

        } catch (SQLException e){
            Logger.getLogger(ProdutoJDBC.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    @Override
    public List<Produto> searchProdutoDescricao(String descricao) {
        try{
            List<Produto> produtos = new ArrayList<>();

            PreparedStatement statement = this.dataSource
                    .getConnection()
                    .prepareStatement(
                            "SELECT * FROM produto WHERE descricao = ?");

            statement.setString(1, descricao);
            statement.executeQuery();

            ResultSet produtoResult = statement.getResultSet();

            while (  produtoResult.next() ){
                produtos.add(converterProduto(produtoResult));
            }

            return produtos;

        } catch(SQLException ex){
            Logger.getLogger(ClienteJDBC.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public void deleteProduto(int id) {
        try{
            PreparedStatement statement = this.dataSource
                    .getConnection()
                    .prepareStatement(
                            "DELETE FROM produto WHERE id=?");
            statement.setInt(1, id);
            statement.executeQuery();

        } catch (SQLException e) {
            Logger.getLogger(ProdutoJDBC.class.getName()).log(Level.SEVERE, null, e);
        }
    }


}
