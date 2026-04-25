package io.github.fatec.entity;

import java.time.LocalDateTime;
import java.util.List;


public class Compra {

    private String id;
    private String clienteId;
    private List<ItemCompra> itens;
    private LocalDateTime dataCompra;

    public Compra(String id, String clienteId, List<ItemCompra> itens, LocalDateTime dataCompra) {
        this.id = id;
        this.clienteId = clienteId;
        this.itens = itens;
        this.dataCompra = dataCompra;
    }

    public String getId() {
        return id;
    }

    public String getClienteId() {
        return clienteId;
    }

    public List<ItemCompra> getItens() {
        return itens;
    }

    public LocalDateTime getDataCompra() {
        return dataCompra;
    }
}