package controller;

import model.Cidade;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CidadeControllerTest {
    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    void listarTodasCidades() {
        ParameterizedTypeReference<List<Cidade>> tipoRetorno = new ParameterizedTypeReference<List<Cidade>>() {};

        ResponseEntity<List<Cidade>> response = testRestTemplate.exchange(
                "/cidades/", HttpMethod.GET,null, tipoRetorno
        );
        System.out.println("######## " + response.getStatusCode());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void buscarCidadePorId() {
        int expectedId = 27;
        ResponseEntity<Cidade> response = testRestTemplate.exchange(
                "/cidades/id/{id}",HttpMethod.GET,null, Cidade.class, expectedId
        );
        System.out.println("######## " + response.getBody());
        System.out.println("######## " + response.getStatusCode());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void buscarCidadePorNome() {
        ParameterizedTypeReference<List<Cidade>> tipoRetorno = new ParameterizedTypeReference<>() {};

        String expectedName = "Belém";
        ResponseEntity<List<Cidade>> response = testRestTemplate.exchange(
                "/cidades/nome/{nome}",HttpMethod.GET, null, tipoRetorno,expectedName
        );

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void buscarCidadePorEstado() {
        ParameterizedTypeReference<List<Cidade>> tipoRetorno = new ParameterizedTypeReference<List<Cidade>>() {};

        String expectedUf = "GO";
        ResponseEntity<List<Cidade>> response = testRestTemplate.exchange(
                "/cidades/estado/{estado}",HttpMethod.GET, null, tipoRetorno,expectedUf
        );

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void buscarCidadePorIdFrete() {
        int expectedId = 1;
        ResponseEntity<Cidade> response = testRestTemplate.exchange(
                "/cidades/frete/{id}",HttpMethod.GET,null, Cidade.class, expectedId
        );
        System.out.println("######## " + response.getStatusCode());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void salvarCidade(){
        Cidade cidade = Cidade.builder().nome("Santo Amaro").taxa(10).build();
        HttpEntity<Cidade> httpEntity = new HttpEntity<>(cidade);

        ResponseEntity<Cidade> response = testRestTemplate.exchange(
                "/cidades/inserir/", HttpMethod.POST, httpEntity, Cidade.class
        );
        System.out.println("######## " + response.getStatusCode());
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    void removerCidadePorId(){
        int expectedId = 1;
        ResponseEntity<?> response = testRestTemplate.exchange(
                "/cidades/remover/{id}", HttpMethod.DELETE, null, Cidade.class, expectedId
        );
        assertEquals(HttpStatus.NO_CONTENT,response.getStatusCode());
    }
}