package io.github.fatec.spring.controller;

import io.github.fatec.service.ProdutoService;
import io.github.fatec.spring.controller.adapter.ProdutoAdapterController;
import io.github.fatec.spring.controller.dto.request.ProdutoRequest;
import io.github.fatec.spring.controller.dto.response.ProdutoResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private final ProdutoService service;

    public ProdutoController(ProdutoService service) {
        this.service = service;
    }

    @PostMapping
    public ProdutoResponse criar(@RequestBody ProdutoRequest request) {
        return ProdutoAdapterController.toResponse(
                service.criarProduto(request.nome(), request.preco())
        );
    }

    @GetMapping
    public List<ProdutoResponse> listar() {
        return service.listarProdutos()
                .stream()
                .map(ProdutoAdapterController::toResponse)
                .toList();
    }

    @GetMapping("/{id}")
    public ProdutoResponse buscar(@PathVariable String id) {
        return ProdutoAdapterController.toResponse(
                service.buscarProduto(id)
                        .orElseThrow(() -> new RuntimeException("Produto não encontrado"))
        );
    }

    @PutMapping("/{id}")
    public ProdutoResponse atualizar(@PathVariable String id,
                                     @RequestBody ProdutoRequest request) {
        return ProdutoAdapterController.toResponse(
                service.atualizarProduto(id, request.nome(), request.preco())
        );
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable String id) {
        service.deletarProduto(id);
    }
}