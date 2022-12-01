package controller;

import jakarta.validation.Valid;
import model.Cidade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.CidadeService;

import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
public class CidadeController {
    @Autowired
    private CidadeService service;

    @GetMapping("/")
    public ResponseEntity<List<Cidade>> todos() {
        var cidades = service.all();

        if(cidades.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(cidades);
    }

    @GetMapping("id/{id}")
    public ResponseEntity<Cidade> buscaPor(@PathVariable Integer id) {
        var optional = service.buscaId(id);

        if(optional.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(optional.get());
    }

    @GetMapping("nome/{nome}")
    public ResponseEntity<List<Cidade>> buscaPor(@PathVariable String nome) {
        var cidades = service.buscaNome(nome);

        if(cidades.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(cidades);
    }

    @GetMapping("uf/{uf}")
    public ResponseEntity<List<Cidade>> buscaPorUf(@PathVariable String uf) {
        var cidades = service.buscarPorEstado(uf);

        if(cidades.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(cidades);
    }

    @GetMapping("/frete/{id}")
    public ResponseEntity<Cidade> buscarPorFrete_id(@PathVariable Integer id){
        var optional = service.buscarPorIdFrete(id);

        if(optional == null){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(optional);
    }

    @PostMapping("/inserir")
    public ResponseEntity<Cidade> salva(@RequestBody @Valid Cidade cidade) throws URISyntaxException {
        var cidadeSalva = service.saveCliente(cidade);
        return new ResponseEntity<>(cidadeSalva, HttpStatus.CREATED);
    }

    @DeleteMapping("/remover/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {

        Optional<Cidade> optional = service.buscaId(id);
        if(optional.isPresent()){
            service.removePelo(id);
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }
}
