package io.github.fatec.service;


import io.github.fatec.entity.Compra;
import io.github.fatec.entity.ItemCompra;
import io.github.fatec.entity.Produto;
import io.github.fatec.repository.CompraRepository;
import io.github.fatec.repository.ProdutoRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class ProdutoService {

    private final ProdutoRepository produtoRepository;
    private final CompraRepository compraRepository;

    public ProdutoService(ProdutoRepository produtoRepository,
                          CompraRepository compraRepository) {
        this.produtoRepository = produtoRepository;
        this.compraRepository = compraRepository;
    }


    public Produto criarProduto(String nome, Double preco) {
        Produto produto = new Produto(
                UUID.randomUUID().toString(),
                nome,
                preco
        );
        return produtoRepository.salvar(produto);
    }

    public List<Produto> listarProdutos() {
        return produtoRepository.listarTodos();
    }

    public Optional<Produto> buscarProduto(String id) {
        return produtoRepository.buscarPorId(id);
    }

    public Produto atualizarProduto(String id, String nome, Double preco) {
        Produto produto = produtoRepository.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        produto.atualizar(nome, preco);

        return produtoRepository.salvar(produto);
    }

    public void deletarProduto(String id) {
        produtoRepository.deletar(id);
    }


    public Compra realizarCompra(String clienteId, List<ItemCompra> itens) {

        if (itens == null || itens.isEmpty()) {
            throw new RuntimeException("Compra deve possuir itens");
        }

        for (ItemCompra item : itens) {
            produtoRepository.buscarPorId(item.getProdutoId())
                    .orElseThrow(() -> new RuntimeException(
                            "Produto não encontrado: " + item.getProdutoId()
                    ));
        }

        Compra compra = new Compra(
                UUID.randomUUID().toString(),
                clienteId,
                itens,
                LocalDateTime.now()
        );

        return compraRepository.salvar(compra);
    }

    public List<Compra> listarComprasPorCliente(String clienteId) {
        return compraRepository.buscarPorClienteId(clienteId);
    }
}