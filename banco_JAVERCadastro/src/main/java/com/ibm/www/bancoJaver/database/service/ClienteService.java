package com.ibm.www.bancoJaver.database.service;


import com.ibm.www.bancoJaver.database.config.FeignCliente;
import com.ibm.www.bancoJaver.database.repository.ClienteRepository;
import com.ibm.www.bancoJaver.database.model.entity.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;


@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private FeignCliente feignCliente;

    public List<Cliente> getAllClientes(){

        return clienteRepository.findAll();
    }

    public Optional<Cliente> getClienteById(Long id){

        return clienteRepository.findById(id);
    }

    public Cliente createCliente(Cliente cliente){

        return clienteRepository.save(cliente);
    }

    public Cliente updateCliente(Long id, Cliente clienteDetails){
        Cliente cliente = clienteRepository.findById(id).orElseThrow(()-> new RuntimeException("Cliente não encontrado"));
        cliente.setNome(clienteDetails.getNome());
        cliente.setTelefone(clienteDetails.getTelefone());
        cliente.setCorrentista(clienteDetails.isCorrentista());
        cliente.setScore_credito(clienteDetails.getScore_credito());
        cliente.setSaldo_cc(clienteDetails.getSaldo_cc());
        return clienteRepository.save(cliente);
    }

    public void deleteCliente(Long id){
        clienteRepository.deleteById(id);

    }
    public Float calcularScoreCredito(Long id){
        Cliente cliente = clienteRepository.findById(id).orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        return cliente.getSaldo_cc() * 0.1f;
    }
}
