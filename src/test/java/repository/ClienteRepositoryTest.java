package repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
public class ClienteRepositoryTest {
    @Autowired
    private ClienteRepository repository;

    @Test
    void buscarClientePorNome(){
        String nome = "L";
        var clientes = repository.findByName(nome);

        clientes.forEach(cliente -> {
            assertTrue(cliente.getNome().contains(nome));
        });
    }

    @Test
    void buscarClientePorId(){
        int expectedId = 1;
        var cliente = repository.findByIdFretes(expectedId);

        var frete = (cliente.getFretes().stream()
                .filter(fretes -> fretes.getId() == expectedId).toList()
        );

        assertEquals(expectedId, frete.get(0).getId());
    }
}