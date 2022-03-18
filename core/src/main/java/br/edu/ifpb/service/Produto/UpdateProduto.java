package br.edu.ifpb.service.Produto;

import br.edu.ifpb.domain.Cliente;
import br.edu.ifpb.domain.ClienteInterface;
import br.edu.ifpb.domain.Produto;
import br.edu.ifpb.domain.ProdutoInterface;

import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class UpdateProduto {

    @EJB
    private ProdutoInterface produtoInterface;

    public void atualizarProduto(Produto produto){
        this.produtoInterface.updateProduto(produto);
    }
}
