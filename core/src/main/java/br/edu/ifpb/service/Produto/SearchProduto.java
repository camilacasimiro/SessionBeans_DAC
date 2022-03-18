package br.edu.ifpb.service.Produto;

import br.edu.ifpb.domain.Cliente;
import br.edu.ifpb.domain.ClienteInterface;
import br.edu.ifpb.domain.Produto;
import br.edu.ifpb.domain.ProdutoInterface;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class SearchProduto {

    @EJB
    private ProdutoInterface produtoInterface;

    public List<Produto> buscarDescricao(String descricao){
        return produtoInterface.searchProdutoDescricao(descricao);
    }
}
