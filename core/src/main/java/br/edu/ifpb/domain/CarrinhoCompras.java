package br.edu.ifpb.domain;

public class CarrinhoCompras {
    private int idCod;
    private String nomeCliente;
    private String descricaoProduto;
    private Double valor;

    public CarrinhoCompras() {}


    public CarrinhoCompras(int idCod, String nomeCliente, String descricaoProduto, Double valor) {
        this.idCod = idCod;
        this.nomeCliente = nomeCliente;
        this.descricaoProduto = descricaoProduto;
        this.valor = valor;
    }

    public int getIdCod() {
        return idCod;
    }

    public void setIdCod(int idCod) {
        this.idCod = idCod;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public String getDescricaoProduto() {
        return descricaoProduto;
    }

    public void setDescricaoProduto(String descricaoProduto) {
        this.descricaoProduto = descricaoProduto;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }


}
