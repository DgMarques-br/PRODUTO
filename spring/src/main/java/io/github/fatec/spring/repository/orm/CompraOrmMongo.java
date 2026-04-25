package io.github.fatec.spring.repository.orm;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "compras")
public class CompraOrmMongo {

    @Id
    private String id;
    private String clienteId;
    private List<Item> itens;
    private LocalDateTime dataCompra;

    public static class Item {
        public String produtoId;
        public Integer quantidade;
    }

    public CompraOrmMongo() {}

    public CompraOrmMongo(String id, String clienteId, List<Item> itens, LocalDateTime dataCompra) {
        this.id = id;
        this.clienteId = clienteId;
        this.itens = itens;
        this.dataCompra = dataCompra;
    }

    public String getId() { return id; }
    public String getClienteId() { return clienteId; }
    public List<Item> getItens() { return itens; }
    public LocalDateTime getDataCompra() { return dataCompra; }
}