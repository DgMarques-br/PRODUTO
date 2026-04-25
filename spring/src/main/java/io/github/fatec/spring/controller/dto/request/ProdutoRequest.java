package io.github.fatec.spring.controller.dto.request;

public record ProdutoRequest(
        String nome,
        Double preco
) {
}