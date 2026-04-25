package io.github.fatec.spring.repository;

import io.github.fatec.entity.Compra;
import io.github.fatec.entity.ItemCompra;
import io.github.fatec.repository.CompraRepository;
import io.github.fatec.spring.repository.mongo.CompraRepositoryWithMongoDB;
import io.github.fatec.spring.repository.orm.CompraOrmMongo;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CompraRepositoryImpl implements CompraRepository {

    private final CompraRepositoryWithMongoDB repository;

    public CompraRepositoryImpl(CompraRepositoryWithMongoDB repository) {
        this.repository = repository;
    }

    @Override
    public Compra salvar(Compra compra) {

        List<CompraOrmMongo.Item> itens = compra.getItens().stream()
                .map(i -> {
                    CompraOrmMongo.Item item = new CompraOrmMongo.Item();
                    item.produtoId = i.getProdutoId();
                    item.quantidade = i.getQuantidade();
                    return item;
                })
                .collect(Collectors.toList());

        CompraOrmMongo orm = new CompraOrmMongo(
                compra.getId(),
                compra.getClienteId(),
                itens,
                compra.getDataCompra()
        );

        repository.save(orm);
        return compra;
    }

    @Override
    public List<Compra> buscarPorClienteId(String clienteId) {

        return repository.findByClienteId(clienteId)
                .stream()
                .map(c -> new Compra(
                        c.getId(),
                        c.getClienteId(),
                        c.getItens().stream()
                                .map(i -> new ItemCompra(i.produtoId, i.quantidade))
                                .collect(Collectors.toList()),
                        c.getDataCompra()
                ))
                .collect(Collectors.toList());
    }
}