package repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Description;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class FreteRepositoryTest {
    @Autowired
    private FreteRepository repository;

    @Test
    @Description("Deve mostrar todos os fretes")
    void mostrarFretes(){
        var frete = repository.findAll();

        assertFalse(frete.isEmpty());
    }

    @Test
    void buscaFretePorId(){
        int expectedId = 1;
        var frete = repository.findById(expectedId);

        assertEquals(expectedId, frete.get().getId());
    }

    @Test
    void buscarFretePorIdCliente(){
        int expectedId = 1;
        var frete = repository.findByIdCliente(expectedId);

        frete.forEach(fretes -> {
            assertEquals(expectedId, fretes.getCliente().getId());
        });
    }

    @Test
    void buscarFretePorIdClienteOrdenado(){
        int expectedId = 1;
        var frete = repository.findByIdClienteOrderByValor(expectedId);

        frete.forEach(fretes -> {
            assertEquals(expectedId, fretes.getCliente().getId());
        });
    }

    @Test
    void buscarFretePorIdCidade() {
        int expectedId = 1;
        var frete = repository.findByIdCidade(expectedId);

        frete.forEach(fretes -> {
            assertEquals(expectedId, fretes.getCidade().getId());
        });
    }
}