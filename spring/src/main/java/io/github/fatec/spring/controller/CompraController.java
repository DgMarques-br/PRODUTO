package io.github.fatec.spring.controller;

import io.github.fatec.service.CompraService; // 👈 Importa o novo serviço
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

    private final CompraService service;
    private final ClienteFeignClient clienteFeign;

    public CompraController(CompraService service, ClienteFeignClient clienteFeign) {
        this.service = service;
        this.clienteFeign = clienteFeign;
    }

    @PostMapping
    public CompraResponse realizarCompra(@Valid @RequestBody CompraRequest request) {
        clienteFeign.buscarClientePorId(request.clienteId());
        return CompraAdapterController.toResponse(
                service.realizarCompra(request.clienteId(), CompraAdapterController.toDomainItens(request.itens()))
        );
    }

    @GetMapping("/{id}")
    public CompraResponse buscar(@PathVariable String id) {
        return CompraAdapterController.toResponse(
                service.buscarCompra(id).orElseThrow(() -> new RuntimeException("Compra não encontrada"))
        );
    }

    @GetMapping("/cliente/{clienteId}")
    public List<CompraResponse> listarPorCliente(@PathVariable String clienteId) {
        return service.listarPorCliente(clienteId).stream()
                .map(CompraAdapterController::toResponse).toList();
    }

    @GetMapping
    public List<CompraResponse> listarTodas() {
        return service.listarTodas().stream()
                .map(CompraAdapterController::toResponse)
                .toList();
    }

    @PutMapping("/{id}")
    public CompraResponse atualizar(@PathVariable String id, @Valid @RequestBody CompraRequest request) {
        clienteFeign.buscarClientePorId(request.clienteId());
        return CompraAdapterController.toResponse(
                service.atualizarCompra(id, request.clienteId(), CompraAdapterController.toDomainItens(request.itens()))
        );
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable String id) {
        service.deletarCompra(id);
    }
}