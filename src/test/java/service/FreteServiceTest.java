package service;

import jakarta.transaction.Transactional;
import model.Frete;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Transactional
@SpringBootTest
public class FreteServiceTest {
    @Autowired
    private FreteService service;

    @Test
    void deveSalvarFrete() {
        Frete frete = Frete.builder().peso(10).valor(10).build();
        service.salvaFrete(frete);

        var fretes = service.buscaId(frete.getId());
        assertEquals(frete.getId(), fretes.get().getId());
    }

    @Test
    void deveRemoverFrete() {
        int idFreteRemovido = 1;
        service.removeId(idFreteRemovido);

        var frete = service.buscaId(idFreteRemovido);
        assertTrue(frete.isEmpty());
    }
}