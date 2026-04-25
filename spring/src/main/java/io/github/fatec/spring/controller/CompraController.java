package io.github.fatec.spring.controller;

import io.github.fatec.service.ProdutoService;
import io.github.fatec.spring.controller.adapter.CompraAdapterController;
import io.github.fatec.spring.controller.dto.request.CompraRequest;
import io.github.fatec.spring.controller.dto.response.CompraResponse;
import io.github.fatec.spring.integration.ClienteFeignClient;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/compras")
public class CompraController {

    private final ProdutoService service;
    private final ClienteFeignClient clienteFeign;

    public CompraController(ProdutoService service,
                            ClienteFeignClient clienteFeign) {
        this.service = service;
        this.clienteFeign = clienteFeign;
    }

    @PostMapping
    public CompraResponse realizarCompra(@Valid @RequestBody CompraRequest request) {

        // valida cliente via Feign
        clienteFeign.buscarClientePorId(request.clienteId());

        return CompraAdapterController.toResponse(
                service.realizarCompra(
                        request.clienteId(),
                        CompraAdapterController.toDomainItens(request.itens())
                )
        );
    }

    @GetMapping("/cliente/{clienteId}")
    public List<CompraResponse> listarPorCliente(@PathVariable String clienteId) {

        return service.listarComprasPorCliente(clienteId)
                .stream()
                .map(CompraAdapterController::toResponse)
                .toList();
    }
}