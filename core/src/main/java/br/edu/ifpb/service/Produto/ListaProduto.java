package br.edu.ifpb.service.Produto;

import br.edu.ifpb.domain.Produto;
import br.edu.ifpb.domain.ProdutoInterface;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.sql.SQLException;
import java.util.List;

@Stateless
public class ListaProduto {

    @EJB
    private ProdutoInterface produtoInterface;

    public List<Produto> listarTodosProd() throws SQLException, ClassNotFoundException {

        return produtoInterface.listaProdutos();
    }
}
