package com.ibm.www.bancoJaver.Cadastro.config;

import com.ibm.www.bancoJaver.Cadastro.model.entity.Cliente;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "cliente-service", url = "http://localhost:8081")//define a interface como um cliente Feign
public interface FeignCliente {

    @GetMapping("/api/clientes")
    List<Cliente> getAllClientes();

    @GetMapping("/api/clientes/{id}")
    Cliente getClienteById(@PathVariable("id") Long id);

    @PostMapping("/api/clientes")
    Cliente createCliente(@RequestBody Cliente cliente);

    @PutMapping("/api/clientes/{id}")
    Cliente updateCliente(@PathVariable("id") Long id, @RequestBody Cliente cliente);

    @DeleteMapping("/api/clientes/{id}")
    void deleteClientes(@PathVariable("id") Long id);
}