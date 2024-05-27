package com.ibm.www.bancoJaver.database.controller;

import com.ibm.www.bancoJaver.database.service.ClienteService;
import com.ibm.www.bancoJaver.database.model.entity.Cliente;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Data
@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    ClienteService clienteService;

    @GetMapping
    public List<Cliente> getAllClientes() {
        return clienteService.getAllClientes();
    }

    @GetMapping("/clientes/{id}")
    public ResponseEntity<Optional<Cliente>> getClienteById(@PathVariable Long id) {
        Optional<Cliente> cliente = clienteService.getClienteById(id);
        return ResponseEntity.ok().body(cliente);

    }

    @PostMapping("/clientes/{id}")
    public Cliente createCliente(@RequestBody Cliente cliente) {

        return clienteService.createCliente(cliente);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> updateCliente(@PathVariable Long id, @RequestBody Cliente clienteDetails){
        Cliente updatedCliente = clienteService.updateCliente(id, clienteDetails);
        return ResponseEntity.ok(updatedCliente);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCliente(@PathVariable Long id){
        clienteService.deleteCliente(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/score-credito")
        public ResponseEntity<Float> calcularScore(@PathVariable Long id){
        float score = clienteService.calcularScoreCredito(id);
        return ResponseEntity.ok(score);
    }



}
