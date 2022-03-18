package br.edu.ifpb.service.Produto;

import br.edu.ifpb.domain.Cliente;
import br.edu.ifpb.domain.ClienteInterface;
import br.edu.ifpb.domain.Produto;
import br.edu.ifpb.domain.ProdutoInterface;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.Objects;

@Stateless
public class AddProduto {
    //regra de negócio
    @EJB
    private ProdutoInterface produtoInterface;

    public void adicionarProduto(Produto produto){

        Objects.requireNonNull(produto,"O produto não pode ser nulo");
        this.produtoInterface.insertProduto(produto);
    }

}
