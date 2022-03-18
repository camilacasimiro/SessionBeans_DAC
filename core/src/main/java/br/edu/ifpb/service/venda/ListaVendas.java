package br.edu.ifpb.service.venda;

import br.edu.ifpb.domain.*;

import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import java.sql.SQLException;
import java.util.List;

@Stateful
public class ListaVendas {

    @EJB
    private VendaInterface vendaInterface;

    public List<CarrinhoCompras> listaVendas(int id) throws SQLException, ClassNotFoundException {

        return vendaInterface.listaHistoricoVenda(id);
    }
}
