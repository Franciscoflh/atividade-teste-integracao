package repository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@ExtendWith(SpringExtension.class)
public class CidadeRepositoryTest {
    @Autowired
    private CidadeRepository cidadeRepository;

    @Test
    void buscarCidadesPorNome(){
        String nome = "São";
        var cidades = cidadeRepository.findByName(nome);

        cidades.forEach(cidade -> {
            assertTrue(cidade.getNome().contains(nome));
        });
    }

    @Test
    void buscarCidadePorIdFretes(){
        int expectedId = 1;
        var cidade = cidadeRepository.findByIdFrete(expectedId);

        var frete = (cidade.getFretes().stream()
                .filter(fretes -> fretes.getId() == expectedId).toList()
        );

        assertEquals(expectedId, frete.get(0).getId());
    }

    @Test
    void buscarCidadesPorEstado(){
        String estado = "GO";
        var cidades = cidadeRepository.findByEstado(estado);

        cidades.forEach(cidade -> {
            assertEquals(cidade.getEstado(), estado);
        });
    }

}
