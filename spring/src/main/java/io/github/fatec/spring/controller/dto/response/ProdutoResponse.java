package io.github.fatec.spring.controller.dto.response;

public record ProdutoResponse(
        String id,
        String nome,
        Double preco
) {
}