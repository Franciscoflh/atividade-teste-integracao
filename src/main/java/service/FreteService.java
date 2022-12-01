package service;

import jakarta.transaction.Transactional;
import model.Frete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.FreteRepository;

import java.util.List;
import java.util.Optional;

@Service
public class FreteService {
    @Autowired
    private FreteRepository repository;

    public List<Frete> all() {
        return repository.findAll();
    }

    public Optional<Frete> buscaId(Integer id) {
        return repository.findById(id);
    }

    public List<Frete> buscaPorIdCliente(Integer id) {
        return repository.findByIdCliente(id);
    }

    public List<Frete> buscaPorIdCidade(Integer id) {
        return repository.findByIdCidade(id);
    }

    public List<Frete> buscarIdClienteOrderByValor(Integer id) {
        return repository.findByIdClienteOrderByValor(id);
    }

    @Transactional
    public Frete salvaFrete(Frete frete) {
        return repository.save(frete);
    }

    @Transactional
    public void removeId(Integer id) {
        repository.deleteById(id);
    }
}
