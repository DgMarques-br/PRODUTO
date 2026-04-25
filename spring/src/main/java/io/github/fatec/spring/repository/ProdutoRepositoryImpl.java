package io.github.fatec.spring.repository;

import io.github.fatec.entity.Produto;
import io.github.fatec.repository.ProdutoRepository;
import io.github.fatec.spring.repository.mongo.ProdutoRepositoryWithMongoDB;
import io.github.fatec.spring.repository.orm.ProdutoOrmMongo;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class ProdutoRepositoryImpl implements ProdutoRepository {

    private final ProdutoRepositoryWithMongoDB repository;

    public ProdutoRepositoryImpl(ProdutoRepositoryWithMongoDB repository) {
        this.repository = repository;
    }

    @Override
    public Produto salvar(Produto produto) {
        ProdutoOrmMongo orm = new ProdutoOrmMongo(
                produto.getId(),
                produto.getNome(),
                produto.getPreco()
        );

        ProdutoOrmMongo saved = repository.save(orm);

        return new Produto(saved.getId(), saved.getNome(), saved.getPreco());
    }

    @Override
    public List<Produto> listarTodos() {
        return repository.findAll().stream()
                .map(p -> new Produto(p.getId(), p.getNome(), p.getPreco()))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Produto> buscarPorId(String id) {
        return repository.findById(id)
                .map(p -> new Produto(p.getId(), p.getNome(), p.getPreco()));
    }

    @Override
    public void deletar(String id) {
        repository.deleteById(id);
    }
}