package repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class FreteRepositoryTest {
    @Autowired
    private FreteRepository repository;

    @Test
    void deveMostrarTodosFretes(){
        var frete = repository.findAll();

        assertFalse(frete.isEmpty());
    }

    @Test
    void deveBuscarFretePorId(){
        int expectedId = 1;
        var frete = repository.findById(expectedId);

        assertEquals(expectedId, frete.get().getId());
    }

    @Test
    void deveBuscarFretePorClienteId(){
        int expectedId = 1;
        var frete = repository.findByIdCliente(expectedId);

        frete.forEach(fretes -> {
            assertEquals(expectedId, fretes.getCliente().getId());
        });
    }

    @Test
    void deveBuscarFretePorClienteIdOrdenadoPorValor(){
        int expectedId = 1;
        var frete = repository.findByIdClienteOrderByValor(expectedId);

        frete.forEach(fretes -> {
            assertEquals(expectedId, fretes.getCliente().getId());
        });
    }

    @Test
    void deveBuscarFretePorCidadeId() {
        int expectedId = 1;
        var frete = repository.findByIdCidade(expectedId);

        frete.forEach(fretes -> {
            assertEquals(expectedId, fretes.getCidade().getId());
        });
    }
}