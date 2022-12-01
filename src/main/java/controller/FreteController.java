package controller;

import jakarta.validation.Valid;
import model.Frete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.FreteService;

import java.net.URISyntaxException;

@RestController
public class FreteController {
    @Autowired
    private FreteService service;

    @GetMapping("/")
    public ResponseEntity<Frete> listarTodos(){
        var optional = service.all();

        if (!optional.isEmpty()){
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Frete> buscaPor(@PathVariable Integer id) {
        var optional = service.buscaId(id);

        return optional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/cliente/{id}")
    public ResponseEntity<Frete> buscaPorCliente_Id(@PathVariable Integer id) {
        var optional = service.buscaPorIdCliente(id);

        if (optional != null) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/cidade/{id}")
    public ResponseEntity<Frete> buscaPorCidade_Id(@PathVariable Integer id) {
        var optional = service.buscaPorIdCidade(id);

        if (optional != null) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/cliente/order/{id}")
    public ResponseEntity<Frete> buscarPorCliente_IdOrderByValcrAsc(@PathVariable Integer id) {
        var optional = service.buscarIdClienteOrderByValor(id);

        if (optional != null) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/inserir")
    public ResponseEntity<Frete> cadastro(@RequestBody @Valid Frete frete) throws URISyntaxException {
        var freteSalvo = service.salvaFrete(frete);
        return new ResponseEntity<>(freteSalvo, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Frete> atualiza(@PathVariable Integer id,
                                          @RequestBody @Valid Frete frete) {
        var optional = service.buscaId(id);

        if(optional.isPresent()){
            frete.setId(id);
            Frete freteAtualizado = service.salvaFrete(frete);
            return ResponseEntity.ok(freteAtualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("remover/{id}")
    public ResponseEntity<?> remover(@PathVariable Integer id) {
        var optional = service.buscaId(id);

        if (optional.isPresent()) {
            service.removeId(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
