package com.ibm.www.bancoJaver.test;

import com.ibm.www.bancoJaver.database.config.FeignCliente;
import com.ibm.www.bancoJaver.database.model.entity.Cliente;
import com.ibm.www.bancoJaver.database.repository.ClienteRepository;
import com.ibm.www.bancoJaver.database.service.ClienteService;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles("test")
public class ClienteServiceTeste {


    @InjectMocks
    private ClienteService clienteService;

    @Mock
    private FeignCliente feignCliente;

    @Mock
    private ClienteRepository repository;

    @Before
    public void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testObterClientePorFeign(){
        Long id = 1L;
        Cliente cliente = new Cliente();
        cliente.setId(id);
        cliente.setNome("Maria Rosa");
        cliente.setTelefone(123456789L);
        cliente.setCorrentista(true);
        cliente.setScore_credito(100f);
        cliente.setSaldo_cc(1000f);

        Cliente clienteCriado = clienteService.createCliente(cliente);

        when(feignCliente.getClienteById(id)).thenReturn(cliente);

        Cliente clienteObtido = feignCliente.getClienteById(cliente.getId());
        assertNotNull(clienteObtido, "Cliente não encontrado");
        assertEquals(cliente.getNome(), clienteObtido.getNome());
        assertEquals(cliente.getTelefone(), clienteObtido.getTelefone());
        Assertions.assertTrue(cliente.isCorrentista());
        assertEquals(cliente.getScore_credito(), clienteObtido.getScore_credito(), 0.001);
        assertEquals(cliente.getSaldo_cc(), clienteObtido.getSaldo_cc(), 0.001);
    }
}