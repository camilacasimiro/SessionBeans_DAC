package br.edu.ifpb.domain;

import java.util.Objects;

public class Venda {
    private int idProduto;
    private int idCliente;

    public Venda() {
    }

    public Venda(int idProduto, int idCliente) {
        this.idProduto = idProduto;
        this.idCliente = idCliente;
    }

    public int getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Venda venda = (Venda) o;
        return idProduto == venda.idProduto && idCliente == venda.idCliente;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idProduto, idCliente);
    }

    @Override
    public String toString() {
        return "Venda{" +
                "idProduto=" + idProduto +
                ", idCliente=" + idCliente +
                '}';
    }
}
