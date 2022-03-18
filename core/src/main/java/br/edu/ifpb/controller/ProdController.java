package br.edu.ifpb.controller;

import br.edu.ifpb.domain.CarrinhoCompras;
import br.edu.ifpb.domain.Produto;
import br.edu.ifpb.domain.Venda;
import br.edu.ifpb.domain.VendaInterface;
import br.edu.ifpb.service.Produto.DeleteProduto;
import br.edu.ifpb.service.Produto.ListaProduto;
import br.edu.ifpb.service.venda.AddVenda;
import br.edu.ifpb.service.venda.ListaVendas;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Named("produtoController")
@SessionScoped
public class ProdController implements Serializable {

    @Inject
    private ListaProduto listaProduto;
    @Inject
    private ListaVendas listaVendas;
    @Inject
    private AddVenda addVenda;
    @Inject
    private DeleteProduto deleteProduto;

    private List<Produto> produtos = new ArrayList<>();
    private List<Produto> prodSelected = new ArrayList<>();
    private Boolean finalizado = false;
    private Long quantProduto;
    private int codCliente;
    private List<CarrinhoCompras> adicioneVenda = new ArrayList<>();

    private static Logger logger = Logger.getLogger(ProdController.class.getName());

    public ProdController() {
        codCliente = 0;
    }

    public List<Produto> listProd() throws SQLException, ClassNotFoundException {
        List<Produto> prods = this.listaProduto.listarTodosProd();
        logger.log(Level.INFO, "Lista Produtos" + prods);
        return prods;
    }

    public List<CarrinhoCompras> carrinhoCompras() throws SQLException, ClassNotFoundException {
        return listaVendas.listaVendas(this.codCliente);
    }

    public String AdicionarCompra(){

        logger.log(Level.INFO, "Carrinho: produto" + this.prodSelected +"Carrinho: cliente"+this.codCliente);
        this.prodSelected.forEach(produto ->
        addVenda.adicionarVenda(produto.getId(), this.codCliente));

        return "/Compra/carrinhoCompras?faces-redirect=true";
    }
    public void finalizar() throws SQLException, ClassNotFoundException {
        this.adicioneVenda.addAll(listaVendas.listaVendas(this.codCliente));
        this.adicioneVenda.forEach(obj -> deleteProduto.removeProduto(obj.getIdCod()));
        FacesContext.getCurrentInstance().addMessage("mensagens", new FacesMessage(FacesMessage.SEVERITY_INFO, "Compra realizada com sucesso", "Compra realizada com sucesso"));
        finalizado = true;
    }

    public List<Produto> getProdSelected() {
        return prodSelected;
    }

    public void setProdSelected(List<Produto> prodSelected) {
        this.prodSelected = prodSelected;
    }

    public Boolean getFinalizado() {
        if(this.codCliente > 0){
            return finalizado = true;
        }
        return finalizado = false;
    }

    public void setFinalizado(Boolean finalizado) {
        this.finalizado = finalizado;
    }

    public Long getQuantProduto() {
        return quantProduto;
    }

    public void setQuantProduto(Long quantProduto) {
        this.quantProduto = quantProduto;
    }

    public int getCodCliente() {
        return codCliente;
    }

    public void setCodCliente(int codCliente) {
        this.codCliente = codCliente;
    }

//    public List<Long> getVenda() {
//        return Venda;
//    }
//
//    public void setVenda(List<Long> venda) {
//        Venda = venda;
//    }
}