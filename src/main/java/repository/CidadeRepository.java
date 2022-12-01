package repository;

import model.Cidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Integer> {

    @Query("select c from Cidade c where c.nome like concat('%', :nome, '%')")
    List<Cidade> findByName(@Param("nome") String nome);

    @Query("select c from Cidade c inner join c.fretes fretes where fretes.id = :id")
    Cidade findByIdFrete(@Param("id") Integer id);

    List<Cidade> findByEstado(String estado);
}
