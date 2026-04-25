package io.github.fatec.spring.repository.orm;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "produtos")
public class ProdutoOrmMongo {

    @Id
    private String id;
    private String nome;
    private Double preco;

    public ProdutoOrmMongo() {}

    public ProdutoOrmMongo(String id, String nome, Double preco) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
    }

    public String getId() { return id; }
    public String getNome() { return nome; }
    public Double getPreco() { return preco; }
}