package service;

import jakarta.transaction.Transactional;
import model.Cidade;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Transactional
@SpringBootTest
public class CidadeServiceTest {
    @Autowired
    private CidadeService service;

    @Test
    void salvarCidade(){
        String nomeCidade = "Santo Amaro";
        Cidade cidade = Cidade.builder().nome(nomeCidade).taxa(13).build();
        service.saveCliente(cidade);

        var cidades = service.buscaNome(nomeCidade);
        cidades.forEach(city -> {
            assertEquals(nomeCidade, city.getNome());
        });
    }
}
