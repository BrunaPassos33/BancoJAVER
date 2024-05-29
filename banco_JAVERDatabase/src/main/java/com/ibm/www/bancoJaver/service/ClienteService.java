package com.ibm.www.bancoJaver.service;

import com.ibm.www.bancoJaver.model.entity.Cliente;
import com.ibm.www.bancoJaver.model.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    @Autowired
    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public Float calcularScoreCredito(Long id) {
        // Busca o cliente pelo ID especificado. Caso não seja encontrado, lança uma exceção.
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        // Calcula o score de crédito do cliente com base no saldo da conta corrente e retorna o resultado.
        return cliente.getSaldo_cc() * 0.1f;
    }
}


