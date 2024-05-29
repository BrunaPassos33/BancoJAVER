import com.ibm.www.bancoJaver.model.entity.Cliente;
import com.ibm.www.bancoJaver.model.repository.ClienteRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

public class ClienteServiceTeste {

    @Mock
    private ClienteRepository clienteRepository;

    @Before
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllCliente() {
        List<Cliente> clientes = Arrays.asList(
                new Cliente(1L, "Maria Rosa", 976439032L, true, 700.0f, 3000.0f),
                new Cliente(2L, "Robson Marino", 967542310L, false, 10f, 110f)
        );

        when(clienteRepository.findAll()).thenReturn(clientes);

        List<Cliente> result = clienteRepository.findAll();

        assertEquals(2, result.size());
        assertEquals("Maria Rosa", result.get(0).getNome());

        result.forEach(cliente -> System.out.println("Nome do cliente: " + cliente.getNome()));
    }

    @Test
    public void testGetClienteById() {
        Long id = 1L;
        Cliente cliente = new Cliente(1L, "Maria Rosa", 976439032L, true, 700.0f, 3000.0f);

        when(clienteRepository.findById(id)).thenReturn(Optional.of(cliente));

        Optional<Cliente> result = clienteRepository.findById(id);

        assertNotNull(result);
        assertEquals(id, result.get().getId());
        assertEquals("Maria Rosa", result.get().getNome());

        System.out.println("Cliente: " + result.get().getNome());

        verify(clienteRepository, times(1)).findById(id);
    }

    @Test
    public void testCreateCliente() {
        Cliente cliente = new Cliente(null, "Joaquim Passos", 978739032L, true, 900.0f, 3090.0f);
        Cliente clienteSalvo = new Cliente(3L, "Joaquim Passos", 978739032L, true, 900.0f, 3090.0f);

        when(clienteRepository.save(cliente)).thenReturn(clienteSalvo);

        Cliente result = clienteRepository.save(cliente);

        assertNotNull(result);
        assertEquals(Optional.of(3L), Optional.ofNullable(result.getId()));
        assertEquals("Joaquim Passos", result.getNome());

        System.out.println("Cliente criado: " + result.getNome());

        verify(clienteRepository, times(1)).save(cliente);
    }

    public void testUpdateCliente() {
        // Cliente existente antes da atualização
        Cliente clienteExistente = new Cliente(3L, "Joaquim Passos", 978739032L, true, 900.0f, 3090.0f);

        // Cliente atualizado
        Cliente clienteAtualizado = new Cliente(3L, "Joaquim Pereira", 978739032L, true, 900.0f, 3090.0f);

        // Mockando o comportamento do repository
        when(clienteRepository.findById(clienteExistente.getId())).thenReturn(Optional.of(clienteExistente));
        when(clienteRepository.save(clienteExistente)).thenReturn(clienteAtualizado);

        // Chamando o método que será testado
        Cliente result = clienteRepository.save(clienteExistente);

        // Verificações
        assertNotNull(result);
        assertEquals(Optional.of(3L), Optional.ofNullable(result.getId()));
        assertEquals("Joaquim Pereira", result.getNome());

        System.out.println("Cliente atualizado: " + result.getNome());

        // Verifica se o método findById foi chamado corretamente
        verify(clienteRepository, times(1)).findById(clienteExistente.getId());

        // Verifica se o método save foi chamado corretamente
        verify(clienteRepository, times(1)).save(clienteExistente);
    }
    @Test
    public void testDeleteCliente() {
        Long id = 2L;

        clienteRepository.deleteById(id);

        verify(clienteRepository, times(1)).deleteById(id);

        System.out.println("Cliente com ID " + id + " deletado");
    }

    @Test
    public void testCalculateScoreCredito() {
        Long id = 3L;
        Float saldo_cc = 3090.0f;

        Cliente cliente = new Cliente(3L, "Joaquim Passos", 978739032L, true, 309.0f, 3090.0f);
        cliente.setId(id);
        cliente.setSaldo_cc(saldo_cc);

        when(clienteRepository.findById(id)).thenReturn(Optional.of(cliente));

        float scoreCreditoEsperado = cliente.getSaldo_cc() * 0.1f;

        float result = cliente.getSaldo_cc() * 0.1f;

        assertEquals(scoreCreditoEsperado, result, 0.001);

        System.out.println("Score de crédito do cliente: " + result);
    }
}
