package com.ibm.www.bancoJaver.Controller;

import com.ibm.www.bancoJaver.model.entity.Cliente;
import com.ibm.www.bancoJaver.model.repository.ClienteRepository;
import com.ibm.www.bancoJaver.service.ClienteService;
import lombok.Data;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Data
@RestController
@RequestMapping("/api")
public class ControllerDatabase {

    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    ClienteService clienteService;

    @GetMapping("/api/clientes")
    @ResponseBody
    public List<Cliente> getAllClientes() {
        return clienteRepository.findAll();
    }

    @GetMapping("/clientes/{id}")
    @ResponseBody
    public ResponseEntity<Optional<Cliente>> getClienteById(@PathVariable Long id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);
        return ResponseEntity.ok().body(cliente);
    }

    @PostMapping("/clientes")
    @ResponseBody
    public Cliente createCliente(@RequestBody Cliente cliente) {

        return clienteRepository.save(cliente);
    }

    @PutMapping("/clientes/{id}")
    @ResponseBody
    public ResponseEntity<Cliente> updateCliente(@PathVariable Long id, @RequestBody @NotNull Cliente clienteDetails) {
        Cliente existingCliente = clienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado com o id: " + id));

        existingCliente.setNome(clienteDetails.getNome());

        Cliente updatedCliente = clienteRepository.save(existingCliente);
        return ResponseEntity.ok(updatedCliente);
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Void> deleteCliente(@PathVariable Long id){
        clienteRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/score-credito")
    @ResponseBody
    public ResponseEntity<Float> calcularScore(@PathVariable Long id){
        float score = clienteService.calcularScoreCredito(id);
        return ResponseEntity.ok(score);
    }





}
