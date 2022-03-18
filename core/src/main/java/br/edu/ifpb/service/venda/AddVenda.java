package br.edu.ifpb.service.venda;

import br.edu.ifpb.domain.Produto;
import br.edu.ifpb.domain.ProdutoInterface;
import br.edu.ifpb.domain.Venda;
import br.edu.ifpb.domain.VendaInterface;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;
import java.util.Objects;

@Stateless
public class AddVenda {
    //regra de negócio
    @EJB
    private VendaInterface vendaInterface;

    public void adicionarVenda(int idProd, int idCliente){

        Objects.requireNonNull(idCliente,"O código não pode ser nulo");
        this.vendaInterface.insertVenda(idProd, idCliente);
    }

}
