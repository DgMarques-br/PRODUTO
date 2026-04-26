package io.github.fatec.spring.controller.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.util.List;

public record CompraRequest(

        @NotNull(message = "clienteId é obrigatório")
        String clienteId,

        @NotNull(message = "itens não pode ser null")
        @NotEmpty(message = "itens não pode ser vazio")
        @Valid
        List<@NotNull @Valid Item> itens

) {
    public record Item(

            @NotNull(message = "produtoId é obrigatório")
            String produtoId,

            @NotNull(message = "quantidade é obrigatória")
            Integer quantidade

    ) {}
}