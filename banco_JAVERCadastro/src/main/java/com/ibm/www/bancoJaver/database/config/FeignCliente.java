package com.ibm.www.bancoJaver.database.config;


import com.ibm.www.bancoJaver.database.model.entity.Cliente;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "cliente-service", url = "http://localhost:8080")
public interface FeignCliente {

    @GetMapping(value = "/clientes")
    List<Cliente> getAllClientes();

    @GetMapping("/clientes/{id}")
    Cliente getClienteById(@PathVariable("id") Long id);

    @PostMapping("/clientes")
    static Cliente createCliente(@RequestBody Cliente cliente) {
        return null;
    }

    @PutMapping("/clientes/{id}")
    Cliente updateCliente(@RequestBody Cliente cliente);

    @DeleteMapping("/clientes/{id}")
    void deleteClientes(@PathVariable("id") Long id);

}
