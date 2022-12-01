package service;

import jakarta.transaction.Transactional;
import model.Cidade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.CidadeRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CidadeService {
    @Autowired
    private CidadeRepository repository;

    public List<Cidade> all() {
        return repository.findAll();
    }

    public Optional<Cidade> buscaId(Integer id) {
        return repository.findById(id);
    }

    public List<Cidade> buscaNome(String nome) {
        return repository.findByName(nome);
    }

    public List<Cidade> buscarPorEstado(String estado) {
        return repository.findByEstado(estado);
    }

    public  Cidade buscarPorIdFrete(Integer id) {
        return repository.findByIdFrete(id);
    }

    @Transactional
    public Cidade saveCliente(Cidade cliente) {
        return repository.save(cliente);
    }

    @Transactional
    public void removePelo(Integer id) {
        repository.deleteById(id);
    }
}
