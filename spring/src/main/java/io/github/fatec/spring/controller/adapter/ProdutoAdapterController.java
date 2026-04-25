package io.github.fatec.spring.controller.adapter;

import io.github.fatec.entity.Produto;
import io.github.fatec.spring.controller.dto.request.ProdutoRequest;
import io.github.fatec.spring.controller.dto.response.ProdutoResponse;

public class ProdutoAdapterController {

    private ProdutoAdapterController() {}

    public static Produto toDomain(String id, ProdutoRequest request) {
        return new Produto(id, request.nome(), request.preco());
    }

    public static ProdutoResponse toResponse(Produto produto) {
        return new ProdutoResponse(
                produto.getId(),
                produto.getNome(),
                produto.getPreco()
        );
    }
}