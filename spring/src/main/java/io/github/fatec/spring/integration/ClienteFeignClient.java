package io.github.fatec.spring.integration;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "cliente", url = "http://localhost:8082")
public interface ClienteFeignClient {

    @GetMapping("/clientes/{id}")
    Object buscarClientePorId(@PathVariable String id);
}