package io.github.fatec.repository;

import io.github.fatec.entity.Produto;

import java.util.List;
import java.util.Optional;


public interface ProdutoRepository {

    Produto salvar(Produto produto);

    List<Produto> listarTodos();

    Optional<Produto> buscarPorId(String id);

    void deletar(String id);
}