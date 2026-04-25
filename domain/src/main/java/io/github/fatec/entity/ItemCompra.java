package io.github.fatec.entity;

public class ItemCompra {

    private String produtoId;
    private Integer quantidade;

    public ItemCompra(String produtoId, Integer quantidade) {
        this.produtoId = produtoId;
        this.quantidade = quantidade;
    }

    public String getProdutoId() {
        return produtoId;
    }

    public Integer getQuantidade() {
        return quantidade;
    }
}