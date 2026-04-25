package io.github.fatec.entity;

public class Produto {

    private String id;
    private String nome;
    private Double preco;

    public Produto(String id, String nome, Double preco) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
    }

    public String getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Double getPreco() {
        return preco;
    }

    public void atualizar(String nome, Double preco) {
        this.nome = nome;
        this.preco = preco;
    }
}