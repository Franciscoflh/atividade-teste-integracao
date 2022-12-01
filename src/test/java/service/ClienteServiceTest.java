package service;

import jakarta.transaction.Transactional;
import model.Cliente;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Transactional
@SpringBootTest
public class ClienteServiceTest {
    @Autowired
    private ClienteService service;

    @Test
    void salvarCliente() {
        String nomeCliente = "Francisco Remo";
        Cliente cliente = Cliente.builder().nome(nomeCliente).build();
        service.salva(cliente);

        var clientes = service.buscaPor(nomeCliente);
        clientes.forEach(user -> {
            assertEquals(nomeCliente, user.getNome());
        });
    }

    @Test
    void removerCliente() {
        int idClienteRemovido = 1;
        service.removePelo(idClienteRemovido);

        var cliente = service.buscaPor(idClienteRemovido);
        assertTrue(cliente.isEmpty());
    }
}