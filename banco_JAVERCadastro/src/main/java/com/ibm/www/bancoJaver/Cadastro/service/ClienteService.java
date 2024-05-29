package com.ibm.www.bancoJaver.Cadastro.service;


import com.ibm.www.bancoJaver.Cadastro.config.FeignCliente;
import com.ibm.www.bancoJaver.Cadastro.repository.ClienteRepository;
import com.ibm.www.bancoJaver.Cadastro.model.entity.Cliente;
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
        // Verifica se o cliente com o ID especificado existe. Caso contrário, lança uma exceção.
        Cliente cliente = clienteRepository.findById(id).orElseThrow(()-> new RuntimeException("Cliente não encontrado"));
        cliente.setNome(clienteDetails.getNome());
        cliente.setTelefone(clienteDetails.getTelefone());
        cliente.setCorrentista(clienteDetails.isCorrentista());
        cliente.setScore_credito(clienteDetails.getScore_credito());
        cliente.setSaldo_cc(clienteDetails.getSaldo_cc());
        // Atualiza os detalhes do cliente com os dados fornecidos.
        return clienteRepository.save(cliente);
        // Salva as alterações no banco de dados e retorna o cliente atualizado.
    }

    public void deleteCliente(Long id){
        clienteRepository.deleteById(id);

    }
    public Float calcularScoreCredito(Long id){
        // Busca o cliente pelo ID especificado. Caso não seja encontrado, lança uma exceção.
        Cliente cliente = clienteRepository.findById(id).orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        return cliente.getSaldo_cc() * 0.1f;
        // Calcula o score de crédito do cliente com base no saldo da conta corrente e retorna o resultado.
    }
}
