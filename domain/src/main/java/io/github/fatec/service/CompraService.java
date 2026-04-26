package io.github.fatec.service;

import io.github.fatec.entity.Compra;
import io.github.fatec.entity.ItemCompra;
import io.github.fatec.repository.CompraRepository;
import io.github.fatec.repository.ProdutoRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class CompraService {

    private final CompraRepository compraRepository;
    private final ProdutoRepository produtoRepository;

    public CompraService(CompraRepository compraRepository,
                         ProdutoRepository produtoRepository) {
        this.compraRepository = compraRepository;
        this.produtoRepository = produtoRepository;
    }

    public Compra realizarCompra(String clienteId, List<ItemCompra> itens) {
        validarItens(itens);
        Compra compra = new Compra(
                UUID.randomUUID().toString(),
                clienteId,
                itens,
                LocalDateTime.now()
        );
        return compraRepository.salvar(compra);
    }

    public Optional<Compra> buscarCompra(String id) {
        return compraRepository.buscarPorId(id);
    }

    public List<Compra> listarPorCliente(String clienteId) {
        return compraRepository.buscarPorClienteId(clienteId);
    }

    public Compra atualizarCompra(String id, String clienteId, List<ItemCompra> itens) {
        Compra existente = compraRepository.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Compra não encontrada"));

        validarItens(itens);

        Compra atualizada = new Compra(id, clienteId, itens, existente.getDataCompra());
        return compraRepository.salvar(atualizada);
    }

    public void deletarCompra(String id) {
        compraRepository.deletarCompra(id);
    }

    public List<Compra> listarTodas() {
        return compraRepository.listarTodas();
    }

    private void validarItens(List<ItemCompra> itens) {
        if (itens == null || itens.isEmpty()) throw new RuntimeException("Itens obrigatórios");
        for (ItemCompra item : itens) {
            produtoRepository.buscarPorId(item.getProdutoId())
                    .orElseThrow(() -> new RuntimeException("Produto não encontrado: " + item.getProdutoId()));
        }
    }
}