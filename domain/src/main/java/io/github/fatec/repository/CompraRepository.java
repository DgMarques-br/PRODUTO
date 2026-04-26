package io.github.fatec.repository;

import io.github.fatec.entity.Compra;
import java.util.List;
import java.util.Optional;

public interface CompraRepository {

    Compra salvar(Compra compra);

    List<Compra> buscarPorClienteId(String clienteId);

    List<Compra> listarTodas();

    Optional<Compra> buscarPorId(String id);

    void deletarCompra(String id);
}