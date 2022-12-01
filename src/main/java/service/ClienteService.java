package service;


import jakarta.transaction.Transactional;
import model.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.ClienteRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository repository;

    public List<Cliente> todos() {
        return repository.findAll();
    }

    public Optional<Cliente> buscaPor(Integer id) {
        return repository.findById(id);
    }

    public List<Cliente> buscaPor(String nome) {
        return repository.findByName(nome);
    }

    public Cliente buscarPorFretes_Id(Integer id) {
        return repository.findByIdFretes(id);
    }

    @Transactional
    public Cliente salva(Cliente cliente) {
        return repository.save(cliente);
    }


    @Transactional
    public void removePelo(Integer id) {
        repository.deleteById(id);
    }
}
