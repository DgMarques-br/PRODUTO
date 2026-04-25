package io.github.fatec.spring.controller.adapter;

import io.github.fatec.entity.Compra;
import io.github.fatec.entity.ItemCompra;
import io.github.fatec.spring.controller.dto.request.CompraRequest;
import io.github.fatec.spring.controller.dto.response.CompraResponse;

import java.util.List;
import java.util.stream.Collectors;

public class CompraAdapterController {

    private CompraAdapterController() {}

    public static List<ItemCompra> toDomainItens(List<CompraRequest.Item> itens) {
        return itens.stream()
                .map(i -> new ItemCompra(i.produtoId(), i.quantidade()))
                .collect(Collectors.toList());
    }

    public static CompraResponse toResponse(Compra compra) {
        return new CompraResponse(
                compra.getId(),
                compra.getClienteId(),
                compra.getItens().stream()
                        .map(i -> new CompraResponse.Item(
                                i.getProdutoId(),
                                i.getQuantidade()
                        ))
                        .collect(Collectors.toList()),
                compra.getDataCompra()
        );
    }
}