package io.github.fatec.repository;

import io.github.fatec.entity.Compra;

import java.util.List;

public interface CompraRepository {

    Compra salvar(Compra compra);

    List<Compra> buscarPorClienteId(String clienteId);
}