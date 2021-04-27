package br.com.zupacademy.yago.casadocodigo.repository;

import br.com.zupacademy.yago.casadocodigo.orm.Categoria;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoriaRepository extends CrudRepository<Categoria, Long> {
    Optional<Categoria> findByNome(String nome);
}
