package br.edu.ifpb.domain;

import java.sql.SQLException;
import java.util.List;

public interface VendaInterface {

    public List<CarrinhoCompras> listaHistoricoVenda(int id) throws ClassNotFoundException, SQLException;

    public void insertVenda (int idProd, int idCliente);

}
