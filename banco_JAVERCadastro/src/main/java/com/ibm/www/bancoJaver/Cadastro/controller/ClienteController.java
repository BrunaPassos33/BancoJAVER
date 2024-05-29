package com.ibm.www.bancoJaver.Cadastro.controller;

import com.ibm.www.bancoJaver.Cadastro.service.ClienteService;
import com.ibm.www.bancoJaver.Cadastro.model.entity.Cliente;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@Data
@RestController
@RequestMapping("/api")
public class ClienteController {

    @Autowired //injetar instância
    ClienteService clienteService;

    @GetMapping ("/api/clientes")//retorna todos os clientes
    @ResponseBody
    public List<Cliente> getAllClientes() {
        return clienteService.getAllClientes();
    }

    @GetMapping("/clientes/{id}") //retorna um cliente com id específico
    @ResponseBody
    public ResponseEntity<Optional<Cliente>> getClienteById(@PathVariable Long id) { //Path o valor do id é extraído da URL
        Optional<Cliente> cliente = clienteService.getClienteById(id);
        return ResponseEntity.ok().body(cliente);
    }

    @PostMapping("/clientes") //cria um novo cliente
    @ResponseBody
    public Cliente createCliente(@RequestBody Cliente cliente) {

        return clienteService.createCliente(cliente);
    }

    @PutMapping("/{id}") //atualiza um cliente existente
    @ResponseBody
    public ResponseEntity<Cliente> updateCliente(@PathVariable Long id, @RequestBody Cliente clienteDetails){
        Cliente updatedCliente = clienteService.updateCliente(id, clienteDetails);
        return ResponseEntity.ok(updatedCliente);
    }

    @DeleteMapping("/{id}") //deleta um cliente
    @ResponseBody
    public ResponseEntity<Void> deleteCliente(@PathVariable Long id){
        clienteService.deleteCliente(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/score-credito") // calcula um score de crédito
    @ResponseBody
        public ResponseEntity<Float> calcularScore(@PathVariable Long id){
        float score = clienteService.calcularScoreCredito(id);
        return ResponseEntity.ok(score);
    }



}
