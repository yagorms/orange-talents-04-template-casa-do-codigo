package br.com.zupacademy.yago.casadocodigo.repository;

import br.com.zupacademy.yago.casadocodigo.orm.Autor;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AutorRepository extends CrudRepository<Autor, Long> {
    Optional<Autor> findByEmail(String email);
}
