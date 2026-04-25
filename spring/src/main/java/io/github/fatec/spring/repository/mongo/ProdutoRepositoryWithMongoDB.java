package io.github.fatec.spring.repository.mongo;

import io.github.fatec.spring.repository.orm.ProdutoOrmMongo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProdutoRepositoryWithMongoDB extends MongoRepository<ProdutoOrmMongo, String> {
}