package repository;

import model.Frete;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FreteRepository extends JpaRepository<Frete, Integer> {
    @Override
    List<Frete> findAll();

    @Override
    Optional<Frete> findById(Integer integer);

    List<Frete> findByIdCliente(Integer id);

    List<Frete> findByIdClienteOrderByValor(Integer id);

    List<Frete> findByIdCidade(Integer id);
}
