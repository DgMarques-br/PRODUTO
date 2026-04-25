package io.github.fatec.spring.repository.mongo;

import io.github.fatec.spring.repository.orm.CompraOrmMongo;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CompraRepositoryWithMongoDB  extends MongoRepository<CompraOrmMongo, String> {

    List<CompraOrmMongo> findByClienteId(String clienteId);
}